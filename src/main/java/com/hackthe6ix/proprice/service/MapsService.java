package com.hackthe6ix.proprice.service;

import com.google.api.client.http.HttpStatusCodes;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hackthe6ix.proprice.domain.response.ProductMapsResponse;
import com.hackthe6ix.proprice.utils.GMapsConstants;
import com.hackthe6ix.proprice.utils.Keys;
import com.squareup.okhttp.*;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Service
public class MapsService {

    private static final Logger logger = LoggerFactory.getLogger(MapsService.class);

    public ProductMapsResponse queryPlaces(String productName, Double user_lat, Double user_long) throws URISyntaxException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        ProductMapsResponse resp = null;

        OkHttpClient httpClient = new OkHttpClient();

        URIBuilder builder = new URIBuilder().setHost(GMapsConstants.GOOGLE_MAPS_ENDPOINT)
                                        .setParameter("key", Keys.GOOGLE_MAPS_API_KEY)
                                        .setParameter("input", productName)
                                        .setParameter("inputtype", GMapsConstants.GOOGLE_MAPS_INPUT_TYPE)
                                        .setParameter("fields", genFields(user_lat, user_long));

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("maps.googleapis.com")
                .addPathSegment("maps")
                .addPathSegment("api")
                .addPathSegment("place")
                .addPathSegment("findplacefromtext")
                .addPathSegment("json")
                .addQueryParameter("key", Keys.GOOGLE_MAPS_API_KEY)
                .addQueryParameter("input", productName)
                .addQueryParameter("inputtype", GMapsConstants.GOOGLE_MAPS_INPUT_TYPE)
                .addQueryParameter("fields", genFields(user_lat, user_long))
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Call call = httpClient.newCall(request);
        System.out.println("REQUEST::: " + request.toString());

        Response httpResponse = call.execute();

        if(httpResponse.code() == HttpStatusCodes.STATUS_CODE_OK){

            String json = httpResponse.body().string();
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

            ProductMapsResponse responseToFE = new ProductMapsResponse();

            JsonObject topCandidate = jsonObject.getAsJsonArray("candidates").get(0).getAsJsonObject();

            responseToFE.setAddress(topCandidate.get("formatted_address").toString());
            responseToFE.setName(topCandidate.get("name").toString());
            responseToFE.setDest_lat((topCandidate.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat")).getAsDouble());
            responseToFE.setDest_long((topCandidate.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng")).getAsDouble());
            responseToFE.setOpen(Boolean.valueOf(topCandidate.get("opening_hours").getAsJsonObject().get("open_now").getAsBoolean()));

            resp = responseToFE;
        } else {
            throw new IllegalStateException("Google Maps response failed");
        }
        return resp;
    }

    private String genFields(Double user_lat, Double user_long){
        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append("formatted_address,name,rating,opening_hours,geometry");

//        sBuilder.append("geometry,formatted_address,name,opening_hours,rating&locationbias=circle:2000)@")
//                .append((user_lat)).append(",")
//                .append((user_long));

        return sBuilder.toString();
    }

}
