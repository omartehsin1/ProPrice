# ProPrice
Your one-stop Google Cloud Platform-Powered shopping tool to make sure you always get value for your money!

## Getting Started
Following instructions will allow for a copy of the project to run on your local machine for development and testing purposes. 

### Prerequisites

Google Cloud Platform (GCP) Vision Product Search offers pre-trained machine learning models through REST and RPC APIs. It assign labels to images and quickly classify them into millions of predefined categories. For ProPrice, the Product Search API is specifically trained to detect objects from within our catalogue and identifies their similarities. 

Vision API Demo with Converse 
![Image of API Web Entities](https://github.com/preyansh98/ProPrice/blob/README-files-update/Images%20for%20README/Image%20of%20API%20Web%20Entities.png?raw=true)

JSON Request & Response
![Image of API JSON](https://github.com/preyansh98/ProPrice/blob/README-files-update/Images%20for%20README/Image%20of%20API%20JSOn.png?raw=true)

Google Maps Platform (GMP) Places API offers a service that returns information about places using HTTP requests. Places are defined within this API as establishments, geographic locations, or prominent points of interest. ProPrice identifies the nearest stores from the user that has their desired product in stock at the lowest price. Working along GMP,  Amazon Web Services (AWS) MongoDB, an open source, NoSQL database that provides support for JSON-styled, document-oriented storage systems, stores this catalogue information, images, and location information. 

## Built With

* [GCP Vision Product Search](https://cloud.google.com/vision/product-search/docs/) - Main search API that allowed for photo recognition through reverse photo search 
* [GMP Places API](https://developers.google.com/places/web-service/search) - Location/Map Services
* [AWS MongoDB](https://docs.aws.amazon.com/quickstart/latest/mongodb/overview.html) - Open Source Database
