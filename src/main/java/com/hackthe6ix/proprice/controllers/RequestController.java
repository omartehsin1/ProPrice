package com.hackthe6ix.proprice.controllers;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.hackthe6ix.proprice.domain.request.ProductRequest;
import com.hackthe6ix.proprice.domain.response.SSResponse;
import com.hackthe6ix.proprice.service.GCPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @Autowired
    private GCPService gcpService;

    @PostMapping(value = "/call_api")
    public ResponseEntity<SSResponse> mainRequest(@RequestBody ProductRequest productRequest){

        if(productRequest != null && productRequest.getEncoded_img() != null){

            //construct the GCP REQUEST.
            AnnotateImageRequest request = gcpService.createRequest(productRequest.getEncoded_img());

            //get response for request.
            SSResponse response = gcpService.getResponse(request);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<SSResponse>(new SSResponse(), HttpStatus.BAD_REQUEST);
    }
}
