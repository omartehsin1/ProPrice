package com.hackthe6ix.proprice.controllers;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.hackthe6ix.proprice.domain.request.ProductRequest;
import com.hackthe6ix.proprice.domain.response.SSResponse;
import com.hackthe6ix.proprice.domain.response.UserProductsResponse;
import com.hackthe6ix.proprice.service.GCPService;
import com.hackthe6ix.proprice.service.LoadPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class RequestController {

    @Autowired
    private GCPService gcpService;

    @Autowired
    private LoadPicturesService loadPicturesService;

    @GetMapping(value = "/heartbeat")
    public ResponseEntity<String> heartbeat(){
        return new ResponseEntity<>("ProPrice is alive!", HttpStatus.OK);
    }

    @PostMapping(value = "/call_api")
    public ResponseEntity<SSResponse> mainRequest(@RequestBody ProductRequest productRequest){

        if(productRequest != null && productRequest.getEncoded_img() != null
            && gcpService.isBase64(productRequest.getEncoded_img())){

            //construct the GCP REQUEST.
            AnnotateImageRequest request = gcpService.createRequest(productRequest.getEncoded_img());

            //get response for request.
            SSResponse response = gcpService.getResponse(request);

            //save picture async.
        //    loadPicturesService.savePicture(productRequest.getEncoded_img());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<SSResponse>(new SSResponse(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/user_img")
    public ResponseEntity<UserProductsResponse> loadPictures(){
        UserProductsResponse response = new UserProductsResponse();

        loadPicturesService.loadPictures().stream()
                                .filter(picture -> picture!=null && !picture.isEmpty())
                                .forEach(pic -> {
                                    response.getEncoded_images().add(pic);
                                });

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
