//
//  ModalViewController.swift
//  SensePrice
//
//  Created by Omar Tehsin on 2019-08-24.
//  Copyright © 2019 Omar Tehsin. All rights reserved.
//

import UIKit
import MapKit
import CoreLocation

class ModalViewController: UIViewController, HalfModalPresentable {
    var data = Data()
    @IBOutlet weak var fullProductCollectionView: UICollectionView!
    var mapView = MapViewCell()
    var productViewCell = CustomProductCell()

    let locationManager = CLLocationManager()
    fileprivate let cellId = "cellId"
    fileprivate let headerId = "headerId"
    fileprivate let padding: CGFloat = 16
    var headerView: HeaderView?
    let productView = ProductView()

    override func viewDidLoad() {
        super.viewDidLoad()
        setUpCollectionView()
        setUpLayout()
        //productView.productCollectionView.reloadData()

        
    }
    
    func checkLocationAuthorization() {
        switch CLLocationManager.authorizationStatus() {
        case .authorizedWhenInUse:
            mapView.mapView.showsUserLocation = true
        case .denied:
            break
        case .notDetermined:
            locationManager.requestWhenInUseAuthorization()
        case .restricted:
            break
        case .authorizedAlways:
            break
        }
    }
    func setUpLocationManager() {
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
    }
    func centerViewOnUserLocation() {
        if let location = locationManager.location?.coordinate {
            let region = MKCoordinateRegion.init(center: location, latitudinalMeters: 100, longitudinalMeters: 100)
            mapView.mapView.setRegion(region, animated: true)
        }
    }
    func checkLocationServices() {
        if CLLocationManager.locationServicesEnabled() {
            setUpLocationManager()
            checkLocationAuthorization()
            locationManager.startUpdatingLocation()
        } else {
            //show alert
        }
    }
    
    
    fileprivate func setUpCollectionView() {
        //fullProductCollectionView.backgroundColor = .white
        fullProductCollectionView.delegate = self
        fullProductCollectionView.dataSource = self
//        fullProductCollectionView.register(UICollectionViewCell.self, forCellWithReuseIdentifier: cellId)
        fullProductCollectionView.register(HeaderView.self, forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: headerId)
        fullProductCollectionView.collectionViewLayout = StretchyHeaderFlowLayout()
        fullProductCollectionView.contentInsetAdjustmentBehavior = .never
    }
    
    fileprivate func setUpLayout() {
        if let layout = fullProductCollectionView.collectionViewLayout as? UICollectionViewFlowLayout {
            layout.sectionInset = .init(top: padding, left: padding, bottom: padding, right: padding)
        }
    }
    
    
    @IBAction func maximizeButtonTapped(sender: AnyObject) {
        maximizeToFullScreen()
    }
    
    @IBAction func cancelButtonTapped(sender: AnyObject) {
        if let delegate = navigationController?.transitioningDelegate as? HalfModalTransitioningDelegate {
            delegate.interactiveDismiss = false
        }
        
        dismiss(animated: true, completion: nil)
    }
}

extension ModalViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 3
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if indexPath.row == 0 {
            mapView = collectionView.dequeueReusableCell(withReuseIdentifier: "mapCell", for: indexPath) as! MapViewCell
            self.checkLocationServices()
            return mapView
        }
        else if indexPath.row > 0{
            productViewCell = collectionView.dequeueReusableCell(withReuseIdentifier: "productViewCell", for: indexPath) as! CustomProductCell
            //cell.backgroundColor = .black
            return productViewCell
        }

        return UICollectionViewCell()
    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        if indexPath.row == 0 {
            return .init(width: view.frame.width - 2 * padding, height: 400)
        } else {
            return .init(width: view.frame.width - 2 * padding, height: 150)
        }
        //return .init(width: view.frame.width - 2 * padding, height: 50)
    }
    
    
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        headerView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: headerId, for: indexPath) as? HeaderView
        return headerView!
    }
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let contentOffsetY = scrollView.contentOffset.y
        
        if contentOffsetY > 0 {
            headerView?.animator.fractionComplete = 0
            return
        }
        
        headerView?.animator.fractionComplete = abs(contentOffsetY) / 100
    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForHeaderInSection section: Int) -> CGSize {
        return .init(width: view.frame.width, height: 310)
    }
    
    
}

extension ModalViewController: CLLocationManagerDelegate {
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        //we'll be back
        let location = locations.last
        print(location)
    }
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        //we'll be back
    }
}

//class LinkImage: UICollectionViewCell, UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
//    let imagesToPost = ["camera", "emptyHanger", "nintendoSwitch", "proPriceLogo", "ps4"]
//
//    let cellId = "CellId"; // same as above unique id
//
//
//    let productImageView: UIImageView = {
//        let imageView = UIImageView()
//        imageView.contentMode = UIView.ContentMode.scaleAspectFill
//        imageView.layer.cornerRadius = 15
//        imageView.layer.masksToBounds = true
//        return imageView
//    }()
//
//    override init(frame: CGRect) {
//        super.init(frame: frame);
//        //productImageView = UIImageView()
//
//        setupViews();
//        collectionView.register(UICollectionViewCell.self, forCellWithReuseIdentifier: cellId); //register custom UICollectionViewCell class.
////        productImageView.translatesAutoresizingMaskIntoConstraints = false
////        productImageView.topAnchor.constraint(equalTo: topAnchor).isActive = true
////        productImageView.leftAnchor.constraint(equalTo: leftAnchor).isActive = true
////        productImageView.rightAnchor.constraint(equalTo: rightAnchor).isActive = true
////        productImageView.heightAnchor.constraint(equalToConstant: 100).isActive = true
//        addSubview(productImageView)
//
//        // Here I am not using any custom class
//
//    }
//
//
//    func setupViews(){
//        addSubview(collectionView);
//
//        collectionView.delegate = self;
//        collectionView.dataSource = self;
//        collectionView.reloadData()
//        collectionView.leftAnchor.constraint(equalTo: leftAnchor).isActive = true;
//        collectionView.rightAnchor.constraint(equalTo: rightAnchor).isActive = true;
//        collectionView.topAnchor.constraint(equalTo: topAnchor).isActive = true;
//        collectionView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true;
//    }
//
//    let collectionView: UICollectionView = {
//        let layout = UICollectionViewFlowLayout();
//        layout.scrollDirection = .horizontal; //set scroll direction to horizontal
//        let cv = UICollectionView(frame: .zero, collectionViewLayout: layout);
//        //cv.backgroundColor = .clear
//        cv.translatesAutoresizingMaskIntoConstraints = false;
//        return cv;
//    }();
//
//
//    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
//        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: cellId, for: indexPath)
//        let image = UIImage(named: imagesToPost[indexPath.row])
//        print(imagesToPost)
//        productImageView.image = image
//        //cell.backgroundColor = .clear
//
//        cell.addSubview(productImageView)
//        return cell;
//    }
//
//    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
//        return imagesToPost.count
//    }
//
//    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
//        return CGSize(width: self.frame.width, height: self.frame.height - 10);
//    }
//    required init?(coder aDecoder: NSCoder) {
//        fatalError("init(coder:) has not been implemented")
//    }
//}
//class LinkImageCell: UICollectionViewCell {
//
//}
