from google.cloud import vision

def delete_product_set(project_id, location, product_set_id):
    """Delete a product set.
    Args:
        project_id: Id of the project.
        location: A compute region name.
        product_set_id: Id of the product set.
    """
    client = vision.ProductSearchClient()

    # Get the full path of the product set.
    product_set_path = client.product_set_path(
        project=project_id, location=location,
        product_set=product_set_id)

    # Delete the product set.
    client.delete_product_set(name=product_set_path)
    print('Product set deleted.')

if __name__ == "__main__":
    delete_product_set('smart-tracer-250813','us-east1','hack6')