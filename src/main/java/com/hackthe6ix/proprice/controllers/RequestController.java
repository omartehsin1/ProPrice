package com.hackthe6ix.proprice.controllers;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.hackthe6ix.proprice.domain.request.PriceRequest;
import com.hackthe6ix.proprice.domain.request.ProductMapsRequest;
import com.hackthe6ix.proprice.domain.request.ProductRequest;
import com.hackthe6ix.proprice.domain.response.ProductMapsResponse;
import com.hackthe6ix.proprice.domain.response.RetailResponse;
import com.hackthe6ix.proprice.domain.response.SSResponse;
import com.hackthe6ix.proprice.domain.response.UserProductsResponse;
import com.hackthe6ix.proprice.service.GCPService;
import com.hackthe6ix.proprice.service.LoadPicturesService;
import com.hackthe6ix.proprice.service.MapsService;
import com.hackthe6ix.proprice.service.retail.RetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class RequestController {

    @Autowired
    private GCPService gcpService;

    @Autowired
    private MapsService mapsService;

    @Autowired
    private RetailService retailService;

    @Autowired
    private LoadPicturesService loadPicturesService;

    @GetMapping(value = "/heartbeat")
    public ResponseEntity<String> heartbeat() {
        return new ResponseEntity<>("ProPrice is alive!", HttpStatus.OK);
    }

    @PostMapping(value = "/call_api")
    public ResponseEntity<SSResponse> mainRequest(@RequestBody ProductRequest productRequest) {

        if (productRequest != null && productRequest.getEncoded_img() != null
                && gcpService.isBase64(productRequest.getEncoded_img())) {

            SSResponse response = gcpService.classifyProduct(productRequest.getEncoded_img());
            System.out.println(response.getProductName());
            loadPicturesService.savePicture(productRequest.getEncoded_img());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<SSResponse>(new SSResponse(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/user_img")
    public ResponseEntity<UserProductsResponse> loadPictures() {
        UserProductsResponse response = new UserProductsResponse();

        loadPicturesService.loadPictures().stream()
                .filter(picture -> picture != null && !picture.isEmpty())
                .forEach(pic -> {
                    if(response.getEncoded_images() == null) response.setEncoded_images(new ArrayList<>());
                    response.getEncoded_images().add(pic);
                });

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/query_maps")
    public ResponseEntity<ProductMapsResponse> queryMaps(@RequestBody ProductMapsRequest productMapsRequest) {
        ProductMapsResponse productMapsResponse = null;

        try {
            productMapsResponse = mapsService.queryPlaces(productMapsRequest.getProduct_name(),
                    productMapsRequest.getUser_lat(), productMapsRequest.getUser_long());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(productMapsResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/get_prices")
    public ResponseEntity<List<RetailResponse>> getPrices(@RequestBody PriceRequest request) {
        List<RetailResponse> list = new ArrayList<>();

        try {
            list = retailService.queryRetailStores(request.getProductName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
