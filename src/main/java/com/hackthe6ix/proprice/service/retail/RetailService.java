package com.hackthe6ix.proprice.service.retail;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hackthe6ix.proprice.domain.response.RetailResponse;
import com.hackthe6ix.proprice.utils.Keys;
import com.hackthe6ix.proprice.utils.RetailConstants;
import com.squareup.okhttp.*;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class RetailService {

    private static OkHttpClient httpClient;
    @PostConstruct
    private void init(){
        httpClient = new OkHttpClient();
    }

    //Amazon API
    public void queryAmazon(){

    }


    //BestBuy API
    public RetailResponse queryBestBuy(String searchTerm) throws URISyntaxException, IOException {
        RetailResponse response = null;

        String[] getWords = searchTerm.split(" ");
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
            response.setPrice(jsonObject.get("products").getAsJsonArray().get(0).getAsJsonObject().get("salePrice").getAsString());
        }

        return response;
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

    //Walmart API
    public void queryWalmart(){

    }
}
