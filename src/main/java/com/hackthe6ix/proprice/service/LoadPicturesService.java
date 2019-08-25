package com.hackthe6ix.proprice.service;

import com.hackthe6ix.proprice.utils.MongoConstants;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoadPicturesService {

    private static MongoClient client;
    private static MongoDatabase db;
    private static MongoCollection imageCollection;

    @PostConstruct
    private void init(){

//        System.setProperty("javax.net.ssl.trustStore", "src/main/resources/rds-ca-certs");
//        System.setProperty("javax.net.ssl.trustStorePassword", "mongopassword");
//
//        String template = "mongodb://%s:%s@%s/test?ssl=true&replicaSet=rs0&readpreference=%s";
//        String username = "mongouser";
//        String password = "mongopassword";
//        String readPreference = "secondaryPreferred";
//        String connectionString = String.format(template, username, password,
//                    MongoConstants.MONGO_HOST+":"+MongoConstants.MONGO_PORT, readPreference);
//
        String connectionString = MongoConstants.MONGO_URI + "?retryWrites=false";

        MongoClientURI clientURI = new MongoClientURI(connectionString);
        client = new MongoClient(clientURI);
        db = client.getDatabase(MongoConstants.MONGO_DB_NAME);
        imageCollection = db.getCollection(MongoConstants.MONGO_COLLECTION_NAME);

    }

    public List<String> loadPictures(){
        //return all pictures
        List<String> allImages = new ArrayList<>();

        FindIterable<Document> iterable = imageCollection.find();

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                allImages.add(document.get("_image").toString());
            }
        });

        return allImages;
    }

    public void savePicture(String encoded_image){

        Document doc = new Document("_id", UUID.randomUUID())
                            .append("_image", encoded_image);

        imageCollection.insertOne(doc);
    }

}
