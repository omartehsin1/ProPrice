package com.hackthe6ix.proprice.service.retail;

import com.google.api.client.http.HttpStatusCodes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hackthe6ix.proprice.domain.response.ProductMapsResponse;
import com.hackthe6ix.proprice.domain.response.RetailResponse;
import com.hackthe6ix.proprice.utils.RetailConstants;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class RetailService {

    private static OkHttpClient httpClient;
    @PostConstruct
    private void init(){
        httpClient = new OkHttpClient();
    }

    public List<RetailResponse> queryRetailStores(String searchTerm) throws IOException, URISyntaxException, ExecutionException, InterruptedException {

        CompletableFuture<RetailResponse> amazonQuery = queryAmazon(searchTerm);
        CompletableFuture<RetailResponse> bestBuyQuery = queryBestBuy(searchTerm);
        CompletableFuture<RetailResponse> walmartQuery = queryWalmart(searchTerm);

        CompletableFuture.allOf(amazonQuery, bestBuyQuery, walmartQuery).join();

        return Arrays.asList(amazonQuery.get(), bestBuyQuery.get(), walmartQuery.get());
    }

    //Amazon API
    @Async
    public CompletableFuture<RetailResponse> queryAmazon(String searchTerm){
        RetailResponse response = null;

        try{
            HttpUrl httpUrl = new HttpUrl.Builder()
                                    .scheme("https")
                                    .host(RetailConstants.AmazonConstants.AMAZON_RETAIL_ENDPOINT)
                                    .addPathSegment("api1")
                                    .addPathSegment(searchTerm)
                                    .build();

            Request httpRequest = new Request.Builder()
                                            .url(httpUrl)
                                            .build();

            Call call = httpClient.newCall(httpRequest);
            System.out.println("CALLED MICROSERVICE FOR AMAZON PRICE: " + httpRequest);

            Response httpResponse = call.execute();

            if(httpResponse.code() == HttpStatusCodes.STATUS_CODE_OK) {

                String json = httpResponse.body().string();
                JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();

                response = new RetailResponse();
                response.setRetail_type(RetailResponse.RETAIL_TYPE.AMAZON);
                response.setPrice(jsonArray.get(0).getAsJsonObject().get("price").getAsString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
            return CompletableFuture.completedFuture(response);
        }

        //BestBuy API
    @Async
    public CompletableFuture<RetailResponse> queryBestBuy(String searchTerm) throws URISyntaxException, IOException {
        RetailResponse response = null;

        String[] getWords = searchTerm.split("-");
        StringBuilder sBuilder = new StringBuilder();

        //generate bestbuy endpoint
        sBuilder.append("(").append(genSearchParams(getWords)).append(")");

        String firstPart = RetailConstants.BestBuyConstants.BESTBUY_RETAIL_ENDPOINT + sBuilder.toString();

        sBuilder = new StringBuilder();
        sBuilder.append("?format=json&show=sku%2Cname%2CsalePrice&apiKey=3blstprFHKBAvXqWGKNLC1cw");

        String finalUrl = firstPart + sBuilder.toString();
        URL httpURL = new URL(finalUrl);

        Request request = new Request.Builder()
                .url(httpURL)
                .build();

        System.out.println("BEST BUY REQUEST::: " + request.toString());

        Call call = httpClient.newCall(request);
        Response httpResponse = call.execute();

        if(httpResponse.isSuccessful()){
            String jsonString = httpResponse.body().string();
            JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();

            response = new RetailResponse();
            response.setRetail_type(RetailResponse.RETAIL_TYPE.BEST_BUY);

            if(jsonObject!=null && jsonObject.get("products")!=null && jsonObject.get("products").getAsJsonArray().size()>0){
                response.setPrice(jsonObject.get("products").getAsJsonArray().get(0).getAsJsonObject().get("salePrice").getAsString());
            }
        }

        return CompletableFuture.completedFuture(response);
    }

    //Walmart API
    @Async
    public CompletableFuture<RetailResponse> queryWalmart(String searchTerm){
        RetailResponse response = null;

        try{
            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme("https")
                    .host(RetailConstants.WalmartConstants.WALMART_RETAIL_ENDPOINT)
                    .addPathSegment("api2")
                    .addPathSegment(searchTerm)
                    .build();

            Request httpRequest = new Request.Builder()
                    .url(httpUrl)
                    .build();

            Call call = httpClient.newCall(httpRequest);
            System.out.println("CALLED MICROSERVICE FOR WALMART PRICE: " + httpRequest);

            Response httpResponse = call.execute();

            if(httpResponse.code() == HttpStatusCodes.STATUS_CODE_OK) {

                String json = httpResponse.body().string();
                JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();

                response = new RetailResponse();
                response.setRetail_type(RetailResponse.RETAIL_TYPE.WALMART);
                response.setPrice(jsonArray.get(0).getAsJsonObject().get("price").getAsString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(response);
    }

    public String genSearchParams(String[] words){
        StringBuilder queryParam = new StringBuilder();
        for(String s : words){
            queryParam.append("search").append("=").append(s).append("&");
        }
        String queryString= queryParam.toString();
        queryString = queryString.substring(0, queryString.length() - 1);
        return queryString;
    }
}
