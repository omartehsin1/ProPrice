from google.cloud import vision
from flask import Flask, request
import json
import base64
import os
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = r'C:\Users\orenl\Downloads\hi.json'

app = Flask(__name__)

def get_similar_products_file(base64IMG):
  project_id='smart-tracer-250813'
  location='us-east1'
  product_set_id='hack6'
  product_category='toys'
  
  # product_search_client is needed only for its helper methods.
  product_search_client = vision.ProductSearchClient()
  image_annotator_client = vision.ImageAnnotatorClient()


  # Read the image as a stream of bytes.
  image_binary=base64.b64decode(base64IMG)

  with open('temp.png','wb') as f:
    f.write(image_binary)

  with open('temp.png', 'rb') as image_file:
    content = image_file.read()

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

  return response

@app.route("/")
def hello():
  return "Hello World!"

@app.route('/labelImage', methods=['POST']) #GET requests will be blocked
def json_example():
  req_data = request.get_json()
  img = req_data['encoded_img']
  response = get_similar_products_file(img)
  label = response.product_search_results.results[0].product.name.split('/')[-1]
  print(label)
  return json.dumps({'label': label}), 200, {'ContentType': 'application/json'}

if __name__ == "__main__":
  app.run()