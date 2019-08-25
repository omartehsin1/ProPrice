//
//  HeaderView.swift
//  SensePrice
//
//  Created by Omar Tehsin on 2019-08-24.
//  Copyright © 2019 Omar Tehsin. All rights reserved.
//

import UIKit

class HeaderView: UICollectionReusableView {
    let imageView: UIImageView = {
       let iv = UIImageView()
        iv.contentMode = ContentMode.scaleAspectFill
        return iv
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.backgroundColor = .red
        addSubview(imageView)
        imageView.fillSuperview()
        setUpVisualEffectBlur()
        
    }
    
    var animator: UIViewPropertyAnimator!
    fileprivate func setUpVisualEffectBlur() {
        animator = UIViewPropertyAnimator(duration: 3.0, curve: .linear, animations: { [weak self] in
            let blurEffect = UIBlurEffect(style: .regular)
            let visualEffectView = UIVisualEffectView(effect: blurEffect)
            
            self?.addSubview(visualEffectView)
            visualEffectView.fillSuperview()
        })
        
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
