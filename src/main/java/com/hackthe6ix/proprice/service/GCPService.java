package com.hackthe6ix.proprice.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.hackthe6ix.proprice.domain.response.SSResponse;
import com.hackthe6ix.proprice.utils.GCPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class GCPService {

    private static final Logger logger = LoggerFactory.getLogger(GCPService.class);

    @Autowired
    private PriceDBService priceDBService;

    private static ImageAnnotatorClient imageClient = null;

    @PostConstruct
    private void init(){
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "src/gcp_creds.json");
    }

    public AnnotateImageRequest createRequest(String encoded_img){
        logger.debug("GCP: createRequest is starting");

        AnnotateImageRequest result = null;

            try (ImageAnnotatorClient queryImageClient = ImageAnnotatorClient.create()) {
                imageClient = queryImageClient;
                // Get the full path of the product set.
                String productSetPath =
                        ProductSearchClient.formatProductSetName(GCPConstants.GCP_PROJECT_ID, GCPConstants.GCP_REGION_NAME, GCPConstants.GCP_PRODUCT_SET_ID);

                byte[] content = encoded_img.getBytes();

                // Create annotate image request along with product search feature.
                Feature featuresElement = Feature.newBuilder().setType(Feature.Type.PRODUCT_SEARCH).build();
                Image image = Image.newBuilder().setContent(ByteString.copyFrom(content)).build();
                ImageContext imageContext =
                        ImageContext.newBuilder()
                                .setProductSearchParams(
                                        ProductSearchParams.newBuilder()
                                                .setProductSet(productSetPath))
//                                                .addProductCategories(GCPConstants.)
//                                                .setFilter(filter))
                                .build();

                result = AnnotateImageRequest.newBuilder()
                                .addFeatures(featuresElement)
                                .setImage(image)
                                .setImageContext(imageContext)
                                .build();

            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.debug("JSON REQUEST CREATED::::: " + result);
            return result;
    }

    public SSResponse getResponse(AnnotateImageRequest request) {
        BatchAnnotateImagesResponse batchResponse =
                imageClient.batchAnnotateImages(new ArrayList<>(Arrays.asList(request)));

        List<ProductSearchResults.Result> similarProducts =
                batchResponse.getResponses(0).getProductSearchResults().getResultsList();

        SSResponse ssResponse = new SSResponse();

        if(similarProducts!=null){
            similarProducts.stream().forEach(result -> {
                ssResponse.setConfidence_score(result.getScore());
                ssResponse.setProductName(result.getProduct().getDisplayName());

                //query db for price.
                int productIDKey = findProductKey(result.getProduct().getName());
                ssResponse.setProductPrice(priceDBService.getPriceFromDB(productIDKey));
            });
        }

        return ssResponse;

    }

    private int findProductKey(String name){
        //projects/project-id/locations/location-id/products/product_id16

        StringBuilder sBuilder = new StringBuilder();
        int i = name.length() -1;
        while(name.charAt(i) != '/' && i > 0){
            sBuilder.append(name.charAt(i));
            i--;
        }
        return Integer.valueOf(sBuilder.toString());
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
