from google.cloud import vision

def import_product_sets(project_id, location, gcs_uri):
    """Import images of different products in the product set.
    Args:
        project_id: Id of the project.
        location: A compute region name.
        gcs_uri: Google Cloud Storage URI.
            Target files must be in Product Search CSV format.
    """
    client = vision.ProductSearchClient()

    # A resource that represents Google Cloud Platform location.
    location_path = client.location_path(
        project=project_id, location=location)

    # Set the input configuration along with Google Cloud Storage URI
    gcs_source = vision.types.ImportProductSetsGcsSource(
        csv_file_uri=gcs_uri)
    input_config = vision.types.ImportProductSetsInputConfig(
        gcs_source=gcs_source)

    # Import the product sets from the input URI.
    response = client.import_product_sets(
        parent=location_path, input_config=input_config)
        
    print('Processing operation name: {}'.format(response.operation.name))
    # synchronous check of operation status
    result = response.result()
    print('Processing done.')

    # for i, status in enumerate(result.statuses):
    #     print('Status of processing line {} of the csv: {}'.format(
    #         i, status))
    #     # Check the status of reference image
    #     # `0` is the code for OK in google.rpc.Code.
    #     if status.code == 0:
    #         reference_image = result.reference_images[i]
    #         print('hi')
    #         print(reference_image)
    #     else:
    #         print('Status code not OK: {}'.format(status.message))
    #     # if i == 743:
    #     #     break

if __name__ == "__main__":
    import_product_sets('smart-tracer-250813','us-east1','gs://labeled-images1234/reference.csv')