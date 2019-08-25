//
//  ViewController.swift
//  SensePrice
//
//  Created by Omar Tehsin on 2019-08-23.
//  Copyright © 2019 Omar Tehsin. All rights reserved.
//

import UIKit
import EmptyDataSet_Swift
import NVActivityIndicatorView
import PMSuperButton
import Alamofire
import SwiftyJSON

class HomeViewController: UIViewController, NVActivityIndicatorViewable {
    @IBOutlet weak var cameraButton: UIButton!
    @IBOutlet weak var productCollectionView: UICollectionView!
    var productCollectionViewCell = ProductCollectionViewCell()
    var halfModalTransitioningDelegate: HalfModalTransitioningDelegate?
    
    var imageArray : [Data] = []
    var strImageArray: [String] = []
    var theSegue: UIStoryboardSegue!
    let cache = NSCache<NSString, NSString>()
    let myProduct = String()
    var productNameArray : [String] = []
    var priceArray: [String] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        productCollectionView.delegate = self
        productCollectionView.dataSource = self
        productCollectionView.emptyDataSetSource = self
        productCollectionView.emptyDataSetDelegate = self        
        productCollectionView.layer.borderWidth = 3.0
        buttonModifier()
        productCollectionView.reloadData()
        navigationItem.title = "Recent"
        //getImageData()
        // Do any additional setup after loading the view.
        
        
    }
    @IBAction func modalPressed(_ sender: Any) {
        

    }
    
    func getImageData() {
        let urlString = "https://proprice.herokuapp.com/user_img"
        Alamofire.request(urlString, method: .get, parameters: [:], headers: nil).responseJSON { (response) in
            if let jsonValue = response.result.value {
                let json = JSON(jsonValue)
                
                for(_, subJSON) in json["encoded_images"] {
                    if let imgData = subJSON.string {
                        self.strImageArray.append(imgData)
                    }
                }
                DispatchQueue.main.async {
                    self.productCollectionView.reloadData()
                }
            }
        }
    }
//    func getPrices() {
//        let urlString = "https://proprice.herokuapp.com/get_prices"
//        let json = "{\"body\":\"\(str)\"}"
//
//        let url = URL(string: urlString)
//        let jsonData = json.data(using: .utf8, allowLossyConversion: false)!
//
//        var request = URLRequest(url: url!)
//        request.httpMethod = HTTPMethod.post.rawValue
//        request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")
//        request.httpBody = jsonData
//
//        Alamofire.request(request).responseJSON {
//            (response) in
//            if let jsonValue = response.result.value {
//                let json = JSON(jsonValue)
//
//                for(_, subJSON) in json["productName"] {
//                    if let productName = subJSON.string {
//                        self.productNameArray.append(productName)
//                    }
//                }
//                DispatchQueue.main.async {
//                    self.productCollectionView.reloadData()
//                }
//            }
//
//
//            print(response)
//        }
//    }
    
    
    
    @IBAction func cameraButtonPressed(_ sender: Any) {
        let imagePickerController = UIImagePickerController()
        imagePickerController.delegate = self
        let actionSheet = UIAlertController(title: "Photo Source", message: "Choose Source", preferredStyle: .actionSheet)
        
        actionSheet.addAction(UIAlertAction(title: "Camera", style: .default, handler: { (action) in
            if UIImagePickerController.isSourceTypeAvailable(.camera) {
                imagePickerController.sourceType = .camera
                self.present(imagePickerController, animated: true, completion: nil)
            } else {
                print("Camera not available")
            }

        }))
        
        actionSheet.addAction(UIAlertAction(title: "Photo", style: .default, handler: { (action) in
            imagePickerController.sourceType = .photoLibrary
            self.present(imagePickerController, animated: true, completion: nil)
            
        }))
        
        actionSheet.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: nil))
        
        self.present(actionSheet, animated: true, completion: nil)
    }
    
    func buttonModifier() {
        //cameraButton.backgroundColor = .orange
        cameraButton.layer.cornerRadius = cameraButton.frame.height / 2
        
        self.view.layoutIfNeeded()
        
    }
    func getTopMostViewController() -> UIViewController? {
        var topMostViewController = UIApplication.shared.keyWindow?.rootViewController
        
        while let presentedViewController = topMostViewController?.presentedViewController {
            topMostViewController = presentedViewController
        }
        
        return topMostViewController
    }
    func base64Convert(base64String: String?) -> UIImage{
        if (base64String?.isEmpty)! {
            return #imageLiteral(resourceName: "camera")
        }else {
            // !!! Separation part is optional, depends on your Base64String !!!
            let temp = base64String?.components(separatedBy: ",")
            let dataDecoded : Data = Data(base64Encoded: temp![1], options: .ignoreUnknownCharacters)!
            let decodedimage = UIImage(data: dataDecoded)
            return decodedimage!
        }
    }
    
}

extension HomeViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        
        return imageArray.count
        //return strImageArray.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        productCollectionViewCell = productCollectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! ProductCollectionViewCell
        
        let myProducts = imageArray[indexPath.row]
        DispatchQueue.main.async {
            let myProductName = self.productNameArray[indexPath.row]
            print(myProductName)
        }

        //let myPrices = priceArray[indexPath.row]
        //guard let myProductsData = myProducts.data(using: String.Encoding.utf8) else {return}
//        DispatchQueue.main.async {
//            if let myProductsData = myProducts.data(using: String.Encoding.utf8) {
//
//
//                }
//            }
        self.productCollectionViewCell.productImageView.image = UIImage(data: myProducts)
    
        
        //productCollectionViewCell.productName.text = productNameArray[indexPath.row]
        //productCollectionViewCell.productPrice.text = myPrices
        return productCollectionViewCell
    }
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        performSegue(withIdentifier: "showProduct", sender: self)
        self.productCollectionView.deselectItem(at: indexPath as IndexPath, animated: true)
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        theSegue = segue
        if segue.identifier == "showProduct" {
            if let indexPath = productCollectionView.indexPathsForSelectedItems?.first {
                let productScreenVC = segue.destination as! ProductViewController
                let product = imageArray[indexPath.row]
                productScreenVC.data = product
                
            }
        }
        else if segue.identifier == "showModalView"{
            let headerView = HeaderView()
            
            self.halfModalTransitioningDelegate = HalfModalTransitioningDelegate(viewController: self, presentingViewController: theSegue.destination)
            
            theSegue.destination.modalPresentationStyle = .custom
            theSegue.destination.transitioningDelegate = self.halfModalTransitioningDelegate
            }
        }
    
}

extension HomeViewController: EmptyDataSetSource, EmptyDataSetDelegate {
    func image(forEmptyDataSet scrollView: UIScrollView) -> UIImage? {
        let noProduct = UIImage(named: "emptyHanger")
        return noProduct
    }
    func title(forEmptyDataSet scrollView: UIScrollView) -> NSAttributedString? {
        let title = "You have no searches"
        let font = UIFont.boldSystemFont(ofSize: 18)
        let shadow = NSShadow()
        shadow.shadowColor = UIColor.black
        shadow.shadowBlurRadius = 1
        
        let attributes: [NSAttributedString.Key : Any] = [.font : font, .foregroundColor: UIColor.darkGray, .shadow: shadow]
        let attributedQuote = NSAttributedString(string: title, attributes: attributes)
        return attributedQuote
    }
}

extension HomeViewController: UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        let image = info[.originalImage] as! UIImage
        //prodImV.image = image
        
        guard let imageData = image.jpegData(compressionQuality: 0.25) else {return}
        imageArray.append(imageData)
        let str = imageData.base64EncodedString()

        DispatchQueue.main.async {
            self.productCollectionView.reloadData()
        }

        picker.dismiss(animated: true) {
            
//            let size = CGSize(width: 100, height: 100)
//            self.startAnimating(size, message: "Loading. This may take a moment.", messageFont: UIFont(name: "Times New Roman", size: 18.0), type: .circleStrokeSpin, color: UIColor(red: 255, green: 182, blue: 72, alpha: 1.0), padding: 0, displayTimeThreshold: 1, minimumDisplayTime: 4, backgroundColor: nil, textColor: UIColor(red: 255, green: 182, blue: 72, alpha: 0.7), fadeInAnimation: nil)
            
//
            
            let urlString = "https://proprice.herokuapp.com/call_api"
            let json = "{\"encoded_img\":\"\(str)\"}"

            let url = URL(string: urlString)
            let jsonData = json.data(using: .utf8, allowLossyConversion: false)!

            var request = URLRequest(url: url!)
            request.httpMethod = HTTPMethod.post.rawValue
            request.setValue("application/json; charset=UTF-8", forHTTPHeaderField: "Content-Type")
            request.httpBody = jsonData

            Alamofire.request(request).responseJSON {
                (response) in
                    if let jsonValue = response.result.value {
                        let json = JSON(jsonValue)
                        print(json)
                        if let productName = json["productName"].string {
                            print(productName)
                            self.productNameArray.append(productName)
                        }

                        DispatchQueue.main.async {
                            self.productCollectionView.reloadData()
                        }
                    }
                
                
                //print(response)
            }

            self.performSegue(withIdentifier: "showModalView", sender: self)
            
        }

    }

    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        picker.dismiss(animated: true, completion: nil)
    }
}

extension String {
    
    func fromBase64() -> String? {
        guard let data = Data(base64Encoded: self) else {
            return nil
        }
        
        return String(data: data, encoding: .utf8)
    }
    
    func toBase64() -> String {
        return Data(self.utf8).base64EncodedString()
    }
}
