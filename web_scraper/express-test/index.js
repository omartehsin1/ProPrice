function getSimilarProductsFile() {
  // Imports the Google Cloud client library
  const vision = require("@google-cloud/vision");
  const fs = require("fs");
  // Creates a client
  const productSearchClient = new vision.ProductSearchClient();
  const imageAnnotatorClient = new vision.ImageAnnotatorClient();

  /**
   * TODO(developer): Uncomment the following line before running the sample.
   */
  const projectId = "smart-tracer-250813";
  const location = "us-east1";
  const productSetId = "hack6";
  const productCategory = "toys";
  const filePath = "0.jpg";
  const filter = "";
  const productSetPath = productSearchClient.productSetPath(
    projectId,
    location,
    productSetId
  );
  const content = fs.readFileSync(filePath, "base64");
  const request = {
    // The input image can be a GCS link or HTTPS link or Raw image bytes.
    // Example:
    // To use GCS link replace with below code
    // image: {source: {gcsImageUri: filePath}}
    // To use HTTP link replace with below code
    // image: {source: {imageUri: filePath}}
    image: { content: content },
    features: [{ type: "PRODUCT_SEARCH" }],
    imageContext: {
      productSearchParams: {
        productSet: productSetPath,
        productCategories: [productCategory],
        filter: filter
      }
    }
  };
  const [response] = await imageAnnotatorClient.batchAnnotateImages({
    requests: [request]
  });

  console.log("Search Image:", filePath);
  const results = response["responses"][0]["productSearchResults"]["results"];
  console.log("\nSimilar product information:");
  results.forEach(result => {
    console.log("Product id:", result["product"].name.split("/").pop(-1));
    console.log("Product display name:", result["product"].displayName);
    console.log("Product description:", result["product"].description);
    console.log("Product category:", result["product"].productCategory);
  });
}
try {
  getSimilarProductsFile();
} catch (error) {
  console.log(error);
}
