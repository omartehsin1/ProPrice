//
//  StretchyHeaderFlowLayout.swift
//  SensePrice
//
//  Created by Omar Tehsin on 2019-08-24.
//  Copyright © 2019 Omar Tehsin. All rights reserved.
//

import UIKit

class StretchyHeaderFlowLayout: UICollectionViewFlowLayout {
    override func layoutAttributesForElements(in rect: CGRect) -> [UICollectionViewLayoutAttributes]? {
        
        let layoutAttribute = super.layoutAttributesForElements(in: rect)
        
        layoutAttribute?.forEach({ (attributes) in
            if attributes.representedElementKind == UICollectionView.elementKindSectionHeader && attributes.indexPath.section == 0{
                
                
                guard let collectionView = collectionView else {return}
                let contentOffsetY = collectionView.contentOffset.y
                let width = collectionView.frame.width
                
                if contentOffsetY > 0 {
                    return
                }
                
                let height = attributes.frame.height - contentOffsetY
                
                attributes.frame = CGRect(x: 0, y: contentOffsetY, width: width, height: height)
            }
        })
        
        return layoutAttribute
    }
    
    override func shouldInvalidateLayout(forBoundsChange newBounds: CGRect) -> Bool {
        return true
    }
}
