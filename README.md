# ProPrice
Your one-stop Google Cloud Platform-Powered shopping tool to make sure you always get value for your money!

## Getting Started
Following instructions will allow for a copy of the project to run on your local machine for development and testing purposes. 

### Prerequisites

Google Cloud Platform (GCP) Vision Product Search offers pre-trained machine learning models through REST and RPC APIs. It assign labels to images and quickly classify them into millions of predefined categories. For ProPrice, the Product Search API is specifically trained to detect objects from within our catalogue and identifies their similarities. 

Vision API Demo with Converse 
![GIF of API Web Entities and JSON](https://github.com/preyansh98/ProPrice/blob/README-files-update/GCP%20Vision%20API%20Demo.gif?raw=true)

Google Maps Platform (GMP) Places API offers a service that returns information about places using HTTP requests. Places are defined within this API as establishments, geographic locations, or prominent points of interest. ProPrice identifies the nearest stores from the user that has their desired product in stock at the lowest price. Working along GMP,  Amazon Web Services (AWS) MongoDB, an open source, NoSQL database that provides support for JSON-styled, document-oriented storage systems, stores this catalogue information, images, and location information. 

Amazon Web Servics (AWS) Product Advertising API offers developers access to the data used by Amazon including their sale items, customer reviews, seller reviews, as well as the functionalities available on Amazon.com, such as product search, customer reviews, and product promotions. Walmart Labs Search API offers developers full access to item prices and availabilities in real-time. Search API allows text search on the Walmart.com catalogue and returns matching items available for sale online. Best Buy Keyword Search API offers developers access to retail data, including prices, offers, and sales by searching across several common attributes, such as item name and brand name. Overall, with these three APIs provided by large commerce companies, a price match can be made and links to the online sites are provided to the user. 

## Built With

* [GCP Vision Product Search](https://cloud.google.com/vision/product-search/docs/) - Main search API that allowed for photo recognition through reverse photo search 
* [GMP Places](https://developers.google.com/places/web-service/search) - Location/Map Services
* [AWS MongoDB](https://docs.aws.amazon.com/quickstart/latest/mongodb/overview.html) - Open Source Database
* [AWS Product Advertising](https://docs.aws.amazon.com/AWSECommerceService/latest/DG/EX_RetrievingPriceInformation.html) - Amazon Product Advertising
* [Walmart Labs](https://developer.walmartlabs.com/docs/read/Search_API_IR_V2) - Product Lookup
* [Best Buy](https://bestbuyapis.github.io/api-documentation/#keyword-search-function) - Keyword Search
