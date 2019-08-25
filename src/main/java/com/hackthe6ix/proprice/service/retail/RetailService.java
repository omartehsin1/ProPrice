package com.hackthe6ix.proprice.service.retail;

import com.hackthe6ix.proprice.utils.Keys;
import com.hackthe6ix.proprice.utils.RetailConstants;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class RetailService {

    private static HttpClient client;

    @PostConstruct
    private void init(){
        client = HttpClients.createDefault();
    }

    //Amazon API
    public void queryAmazon(){

    }


    //BestBuy API

    /*
    Sample:: "https://api.bestbuy.com/v1/products(search=oven&search=stainless&search=steel)?format=json&show=sku,name,salePrice&apiKey=YourAPIKey"
     */
    public void queryBestBuy(String searchTerm) throws URISyntaxException, IOException {

        String[] getWords = searchTerm.split(" ");
        StringBuilder sBuilder = new StringBuilder();

        //generate bestbuy endpoint
        sBuilder.append(RetailConstants.BestBuyConstants.BESTBUY_RETAIL_ENDPOINT).append("(").append(genSearchParams(getWords)).append(")");

        URIBuilder builder = new URIBuilder().setHost(sBuilder.toString())
                .setParameter("format", "json")
                .setParameter("show", "sku,name,salePrice")
                .setParameter("apiKey" , RetailConstants.BestBuyConstants.BESTBUY_RETAIL_ENDPOINT);

        HttpGet getRequest = new HttpGet(builder.build());

        System.out.println("BEST BUY REQ: " + getRequest);

        HttpResponse httpResponse = client.execute(getRequest);

        System.out.println(httpResponse.getStatusLine().getStatusCode());
    }

    private String genSearchParams(String[] words){
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
