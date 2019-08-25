from google.cloud import vision

def get_similar_products_uri(
  project_id, location, product_set_id, product_category,image_uri):
  """Search similar products to image.
    Args:
        project_id: Id of the project.
        location: A compute region name.
        product_set_id: Id of the product set.
        product_category: Category of the product.
        file_path: Local file path of the image to be searched.
        filter: Condition to be applied on the labels.
        Example for filter: (color = red OR color = blue) AND style = kids
        It will search on all products with the following labels:
        color:red AND style:kids
        color:blue AND style:kids
  """
  # product_search_client is needed only for its helper methods.
  product_search_client = vision.ProductSearchClient()
  image_annotator_client = vision.ImageAnnotatorClient()

  # Create annotate image request along with product search feature.
  image_source = vision.types.ImageSource(image_uri=image_uri)
  image = vision.types.Image(source=image_source)

  # product search specific parameters
  product_set_path = product_search_client.product_set_path(
      project=project_id, location=location,
      product_set=product_set_id)
  product_search_params = vision.types.ProductSearchParams(
      product_set=product_set_path,
      product_categories=[product_category])
  image_context = vision.types.ImageContext(
      product_search_params=product_search_params)

  # Search products similar to the image.
  response = image_annotator_client.product_search(
      image, image_context=image_context)
  
  index_time = response.product_search_results.index_time
  print('Product set index time:')
  print('  seconds: {}'.format(index_time.seconds))
  print('  nanos: {}\n'.format(index_time.nanos))

  results = response.product_search_results.results

  print(response)

  print('Search results:')
  for result in results:
      product = result.product

      print('Score(Confidence): {}'.format(result.score))
      print('Image name: {}'.format(result.image))

      print('Product name: {}'.format(product.name))
      print('Product display name: {}'.format(
          product.display_name))
      print('Product description: {}\n'.format(product.description))
      print('Product labels: {}\n'.format(product.product_labels))

def list_reference_images(project_id, location, product_id):
    """List all images in a product.
    Args:
        project_id: Id of the project.
        location: A compute region name.
        product_id: Id of the product.
    """
    client = vision.ProductSearchClient()

    # Get the full path of the product.
    product_path = client.product_path(
        project=project_id, location=location, product=product_id)

    # List all the reference images available in the product.
    reference_images = client.list_reference_images(parent=product_path)

    # Display the reference image information.
    for image in reference_images:
        print('Reference image name: {}'.format(image.name))
        print('Reference image id: {}'.format(image.name.split('/')[-1]))
        print('Reference image uri: {}'.format(image.uri))
        print('Reference image bounding polygons: {}'.format(
            image.bounding_polys))

def get_similar_products_file(base64IMG):
    project_id='smart-tracer-250813'
    location='us-east1'
    product_set_id='hack6'
    product_category='toys'
    
    # product_search_client is needed only for its helper methods.
    product_search_client = vision.ProductSearchClient()
    image_annotator_client = vision.ImageAnnotatorClient()

    # Read the image as a stream of bytes.
    content = base64IMG

    # Create annotate image request along with product search feature.
    image = vision.types.Image(content=content)

    # product search specific parameters
    product_set_path = product_search_client.product_set_path(
        project=project_id, location=location,
        product_set=product_set_id)
    product_search_params = vision.types.ProductSearchParams(
        product_set=product_set_path,
        product_categories=[product_category])
    image_context = vision.types.ImageContext(
        product_search_params=product_search_params)

    # Search products similar to the image.
    response = image_annotator_client.product_search(
        image, image_context=image_context)

    print(response)

if __name__ == "__main__":
    # list_reference_images('smart-tracer-250813', 'us-east1','Coca-Cola')
    # client = vision.ImageAnnotatorClient()
    # response = client.annotate_image({
    # 'image': {'source': {'image_uri': r'gs://labeled-images1234/obama.jpg'}},
    # 'features': [{'type': vision.enums.Feature.Type.FACE_DETECTION}],
    # })
    # print(response)
    # get_similar_products_uri('smart-tracer-250813', 'us-east1', 'hack6', 'toys',r'gs://labeled-images1234/dataset/Welchs_Fruit_Snacks/0.jpg')
    get_similar_products_file()