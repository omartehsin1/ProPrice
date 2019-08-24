package com.hackthe6ix.proprice.service;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.hackthe6ix.proprice.domain.request.GCPRequest;
import com.hackthe6ix.proprice.utils.GCPConstants;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.vision.v1.ImportProductSetsGcsSource.Builder;

@Service
public class GCPService {


    public AnnotateImageRequest createRequest(String encoded_img){
        AnnotateImageRequest result = null;
        if(!isBase64(encoded_img)) return result;

            try (ImageAnnotatorClient queryImageClient = ImageAnnotatorClient.create()) {

                // Get the full path of the product set.
                String productSetPath =
                        ProductSearchClient.formatProductSetName(GCPConstants.GCP_PROJECT_ID, "CA", GCPConstants.GCP_PRODUCT_SET_ID);

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
            return result;
    }


    private boolean isBase64(String encoded_img){
        try{
            Base64.getDecoder().decode(encoded_img);
        } catch(Exception e){
            return false;
        }

        return true;
    }
}
