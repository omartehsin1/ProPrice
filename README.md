# ProPrice
Your one-stop Google Cloud Platform-Powered shopping tool to make sure you always get value for your money!

## Getting Started
Following instructions will allow for a copy of the project to run on your local machine for development and testing purposes. 

### Prerequisites

**Google Cloud Platform (GCP) Vision Product Search** offers pre-trained machine learning models through REST and RPC APIs. It assign labels to images and quickly classify them into millions of predefined categories. For ProPrice, the Product Search API is specifically trained to detect objects from within our catalogue and identifies their similarities. 

Vision API Demo with Converse 
![GIF of API Web Entities and JSON](https://github.com/preyansh98/ProPrice/blob/README-files-update/GCP%20Vision%20API%20Demo.gif?raw=true)

**Google Maps Platform (GMP) Places API** offers a service that returns information about places using HTTP requests. Places are defined within this API as establishments, geographic locations, or prominent points of interest. ProPrice identifies the nearest stores from the user that has their desired product in stock at the lowest price. Working along GMP, **Amazon Web Services (AWS) MongoDB**, an open source, NoSQL database that provides support for JSON-styled, document-oriented storage systems, stores this catalogue information, images, and location information. 

**Macy's Search Phrase API** offers developers full access to item prices and availabilities in real-time. The Macys.com APIs provide access to Macys' rich content such as product catalog, store events, promotions, coupons, shopping bag, registry and more, allowing partners to build applications and add value to their businesses. **Best Buy Keyword Search API** offers developers access to retail data, including prices, offers, and sales by searching across several common attributes, such as item name and brand name. Overall, with these two APIs provided by large commerce companies, along with scraping Amazon's product data, a competitive price match can be made and links are shown to the users to make their experience with ProPrice even more convenient. 

## Built With

* [GCP Vision Product Search](https://cloud.google.com/vision/product-search/docs/) - Main search API that allowed for photo recognition through reverse photo search 
* [GMP Places](https://developers.google.com/places/web-service/search) - Location/Map Services
* [AWS MongoDB](https://docs.aws.amazon.com/quickstart/latest/mongodb/overview.html) - Open Source Database
* [Macy's Search Phrase](https://developer.walmartlabs.com/docs/read/Search_API_IR_V2) - Product Keyword Search
* [Best Buy Search Function](https://bestbuyapis.github.io/api-documentation/#keyword-search-function) - Product Keyword Search
