package com.hackthe6ix.proprice.service;

import com.google.cloud.vision.v1.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.protobuf.ByteString;
import com.hackthe6ix.proprice.domain.request.ProductRequest;
import com.hackthe6ix.proprice.domain.response.SSResponse;
import com.hackthe6ix.proprice.utils.GCPConstants;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class GCPService {

    private static final Logger logger = LoggerFactory.getLogger(GCPService.class);

    public SSResponse classifyProduct(String encoded_image)  {
        SSResponse response = new SSResponse();

        try {
            ProductRequest request = new ProductRequest();
            request.setEncoded_img(encoded_image);

            OkHttpClient httpClient = new OkHttpClient();

            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme("http")
                    .host(GCPConstants.GCP_IDENTIFIER_MS_ENDPOINT)
                    .addPathSegment("labelImage")
                    .build();

            Gson gson = new Gson();
            String jsonString = gson.toJson(request);
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            RequestBody requestBody = RequestBody.create(JSON, jsonString);
            Request postRequest = new Request.Builder()
                    .url(httpUrl)
                    .post(requestBody)
                    .build();

            System.out.println("CLASSIFY REQUEST: " + postRequest);

            Response postResponse = httpClient.newCall(postRequest).execute();
            String responseJSONString = postResponse.body().string();
            JsonObject jsonObject = new JsonParser().parse(responseJSONString).getAsJsonObject();

            response.setProductName(jsonObject.get("label").getAsString());
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }


//    @Deprecated
//    public AnnotateImageRequest createRequest(String encoded_img){
//        logger.debug("GCP: createRequest is starting");
//
//        AnnotateImageRequest result = null;
//
//            try (ImageAnnotatorClient queryImageClient = ImageAnnotatorClient.create()) {
//                imageClient = queryImageClient;
//                // Get the full path of the product set.
//                String productSetPath =
//                        ProductSearchClient.formatProductSetName(GCPConstants.GCP_PROJECT_ID, GCPConstants.GCP_REGION_NAME, GCPConstants.GCP_PRODUCT_SET_ID);
//
//                File img = new File("src/switch.jpg");
//                byte[] content = Files.readAllBytes(img.toPath());
//
//                // Create annotate image request along with product search feature.
//                Feature featuresElement = Feature.newBuilder().setType(Feature.Type.PRODUCT_SEARCH).build();
//                Image image = Image.newBuilder().setContent(ByteString.copyFrom(content)).build();
//                ImageContext imageContext =
//                        ImageContext.newBuilder()
//                                .setProductSearchParams(
//                                        ProductSearchParams.newBuilder()
//                                                .setProductSet(productSetPath)
//                                                .addProductCategories("apparel"))
//                                               // .setFilter(""))
//                                .build();
//
//                result = AnnotateImageRequest.newBuilder()
//                                .addFeatures(featuresElement)
//                                .setImage(image)
//                                .setImageContext(imageContext)
//                                .build();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            logger.debug("JSON REQUEST CREATED::::: " + result);
//            return result;
//    }
//
//    @Deprecated
//    public SSResponse getResponse(AnnotateImageRequest request) {
//        BatchAnnotateImagesResponse batchResponse =
//                imageClient.batchAnnotateImages(new ArrayList<>(Arrays.asList(request)));
//
//        List<ProductSearchResults.Result> similarProducts =
//                batchResponse.getResponses(0).getProductSearchResults().getResultsList();
//
//        SSResponse ssResponse = new SSResponse();
//
//        if(similarProducts!=null){
//            similarProducts.stream().forEach(result -> {
//                ssResponse.setConfidence_score(result.getScore());
//                ssResponse.setProductName(result.getProduct().getDisplayName());
//
//                //query db for price.
//                int productIDKey = findProductKey(result.getProduct().getName());
//            });
//        }
//
//        return ssResponse;
//    }
//
//    @Deprecated
//    public SSResponse getResponseV2(AnnotateImageRequest request){
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//
//        try{
//            HttpPost postRequest = new HttpPost("https://vision.googleapis.com/v1/images:annotate");
//            postRequest.addHeader("Content-type", "application/json");
//            postRequest.addHeader("Authorization", "Bearer ya29.c.El9uBwiZLtChca3O2SSmwHQns6qg1JpEjMXRKV-w0LgoPQqNzwvZhXJVB_SKDDh93IQuuffN4As85bUmnQiDmHujrJzQE9ZLhQl6fyi0OtQZ_wqpT5fQMAdNuwnkO9C2eA");
//            Gson gson = new Gson();
//            StringEntity postString = new StringEntity(gson.toJson(request));
//            postRequest.setEntity(postString);
//            logger.debug("post request:::::::::::::" + postRequest);
//            HttpResponse response = httpClient.execute(postRequest);
//            System.out.println("RESPONSE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1" + response);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return new SSResponse();
//    }


    private String findProductKey(String name){
        //projects/project-id/locations/location-id/products/product_id16

        StringBuilder sBuilder = new StringBuilder();
        int i = name.length() -1;
        while(name.charAt(i) != '/' && i > 0){
            sBuilder.append(name.charAt(i));
            i--;
        }
        return (sBuilder.toString());
    }

    public boolean isBase64(String encoded_img){
        try{
            Base64.getDecoder().decode(encoded_img);
        } catch(Exception e){
            return false;
        }

        return true;
    }
}
