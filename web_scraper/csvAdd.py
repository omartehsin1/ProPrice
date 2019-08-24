import csv

productSetID = 'hack6'
productIDArray = ['Adidas_Shirt','Coca-Cola','Dads_Oatmeal_Cookie','Iphone_6','laptop','lays_chips','Macbook','Microsoft_T-shirt','Water_Bottle','Welchs_Fruit_Snacks','Yeezys']

csvData = [['image-uri', 'product-set-id','product-id','product-category']]

ss = 'gs://labeled-images123/dataset/yeezys/0.jpg'


for productID in productIDArray:
  for i in range(75):
    bucketLink = 'gs://labeled-images123/dataset/' + productID + '/' + str(i) + '.jpg'
    csvData.append([bucketLink,productSetID,productID,'apparel'])


with open('reference.csv', 'w',newline='') as csvFile:
    writer = csv.writer(csvFile)
    writer.writerows(csvData)
csvFile.close()