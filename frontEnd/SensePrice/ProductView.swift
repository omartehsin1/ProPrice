//
//  ProductView.swift
//  SensePrice
//
//  Created by Omar Tehsin on 2019-08-25.
//  Copyright © 2019 Omar Tehsin. All rights reserved.
//
import UIKit

class ProductView: UIView {
    
    @IBOutlet var contentView: UIView!
    
    @IBOutlet weak var productCollectionView: UICollectionView!
    let cellID = "cellId"
    let imagesToPost = ["camera", "emptyHanger", "nintendoSwitch", "proPriceLogo", "ps4"]
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
        productCollectionView.delegate = self
        productCollectionView.dataSource = self
        productCollectionView.register(CustomProductCell.self, forCellWithReuseIdentifier: cellID)
        self.contentView.backgroundColor = .blue
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = UICollectionView.ScrollDirection.horizontal
        productCollectionView.isScrollEnabled = true
    }
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        commonInit()
    }
    private func commonInit() {
        Bundle.main.loadNibNamed("ProductView", owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
        
    }
    
}

extension ProductView: UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return imagesToPost.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = productCollectionView.dequeueReusableCell(withReuseIdentifier: cellID, for: indexPath) as! CustomProductCell
        
        cell.imageView.image = UIImage(named: imagesToPost[indexPath.row])
        
        return cell
    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 100, height: 100)
    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 2, left: 2, bottom: 2, right: 2)
    }
}

class CustomProductCell: UICollectionViewCell {
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUpView()
    }
    
    let imageView: UIImageView = {
        let image = UIImageView()
        image.translatesAutoresizingMaskIntoConstraints = false
        image.contentMode = UIView.ContentMode.scaleAspectFill
        image.clipsToBounds = true
        image.layer.cornerRadius = 25
        image.backgroundColor = UIColor.gray
        return image
        
    }()
    

    
    func setUpView() {
        addSubview(imageView)
        
        imageView.centerXAnchor.constraint(equalTo: self.centerXAnchor).isActive = true
        imageView.centerYAnchor.constraint(equalTo: self.centerYAnchor).isActive = true
        imageView.heightAnchor.constraint(equalToConstant: 70).isActive = true
        imageView.widthAnchor.constraint(equalToConstant: 70).isActive = true
    }
    
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
}

