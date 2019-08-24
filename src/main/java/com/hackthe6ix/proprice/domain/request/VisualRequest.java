package com.hackthe6ix.proprice.domain.request;

import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// HttpClient libraries

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.*;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


public class VisualRequest {

    static String endpoint = "https://api.cognitive.microsoft.com/bing/v7.0/images/visualsearch";
    static String subscriptionKey = "7fb5f51b56474420921c8d98679ecf9d";
    static String imagePath = "src/switch.jpg";

    static String header_api_key = "Ocp-Apim-Subscription-Key";



    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

    public static String genPost() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .addBinaryBody("image", new File(imagePath))
                .build();
        HttpPost httpPost = new HttpPost();
        httpPost.setHeader(header_api_key, subscriptionKey);
        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost);
        InputStream stream = response.getEntity().getContent();


        String json = new Scanner(stream).useDelimiter("\\A").next();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nJSON Response:\n").append(prettify(json));

        return stringBuilder.toString();
    }


}