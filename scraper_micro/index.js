// const puppeteer = require("puppeteer");
const express = require("express");
const app = express();
const port = 3000;

app.get("/:item", (req, res) => {
  item = req.params.item;
  res.json(hashList[item])
});

app.listen(port, () => console.log("Gator app listening on port 3000!"));

// (async () => {
//   const browser = await puppeteer.launch();
//   const page = await browser.newPage();
//   let item = "fruit_candy";
//   await page.goto(`https://www.amazon.ca/s?k=${item}&ref=nb_sb_noss_2`);

//   let hotelData = await page.evaluate(() => {
//     // get the hotel elements
//     let hotels = [];
//     let namesELms = document.getElementsByClassName("a-size-medium");
//     let hotelsElms = document.querySelectorAll("span.a-price-whole");
//     for (let i = 0; i < namesELms.length; i++) {
//       let itemEach = {};
//       itemEach.name = namesELms[i].innerText;
//       itemEach.price = parseInt(hotelsElms[i].innerText, 10);
//       hotels.push(itemEach);
//     }

//     return hotels;
//   });

//   console.dir(hotelData);

//   await browser.close();
// })();

let hashList = {
  laptop: [
    {
      name:
        "HP Chromebook 14-inch Laptop with 180-degree Hinge, AMD Dual-Core A4-9120 Processor, 4 GB SDRAM, 32 GB eMMC Storage, Chrome OS (14-db0030nr, Snow White)",
      price: 382
    },
    {
      name:
        "Dell Latitude E7450 14in HD High Performance Ultra Book Business Laptop Notebook (Intel Quad Core i5 5300U, 8GB Ram, 256GB Solid State SSD, Camera, HDMI, WiFi) Win 10 Pro (Renewed)",
      price: 409
    },
    {
      name:
        "Thin and Light Laptop 14.1 inch HD 1920 * 1080 Intel Atom X5-E8000 Quad core, 1.04Ghz CPU, up to 2.0Ghz, 4GB RAM, 32GB eMMC",
      price: 288
    },
    {
      name:
        "Lenovo IdeaPad 15.6 HD High Performance Laptop PC |7th Gen AMD A9-9425 Dual-Core 3.10 GHz| 4GB RAM | 128GB SSD | 802.11ac",
      price: 399
    },
    {
      name:
        'Asus C202SA-YS02 11.6" Ruggedized and Water Resistant Design Chromebook with 180 Degree Hinge, 4GB DDR3 RAM, Dark Blue/Silver',
      price: 238
    },
    {
      name:
        'HP 14" Stream Laptop AMD A4-9125 Dual-Core, 4GB RAM, Windows 10 Snowflake 14-dk0030ca',
      price: 299
    },
    {
      name:
        'Asus FX505DD-AB71-CA TUF Gaming Laptop, 15.6" FHD, AMD Ryzen 7-3750H, GeForce GTX 1050, 8GB DDR4, 512GB SSD, Gigabit Wi-Fi 5',
      price: 829
    },
    {
      name:
        'Asus UM462DA-AB51-CA ZenBook Flip 14 Ultra Slim Convertible Laptop, 14" FHD, AMD Quad Core R5-3500U, 8GB RAM, 512GB SSD',
      price: 849
    },
    {
      name:
        'Asus 15.6" VivoBook S Ultra Thin Laptop, i5-8250U Processor, 8GB DDR4, 1TB SSHD, (S510UA-RS51)',
      price: 699
    },
    {
      name:
        'ASUS VivoBook 14 Thin and Light 14" HD, AMD Dual Core R3-3200U, 4GB DDR4, 128GB SSD, AMD Radeon Vega 3',
      price: 429
    },
    {
      name:
        'Asus C202SA-YS02 11.6" Ruggedized and Water Resistant Design Chromebook with 180 Degree Hinge, 4GB DDR3 RAM, Dark Blue/Silver',
      price: 238
    },
    {
      name:
        'Acer Aspire CB5-132T-C7R5 11.6" HD Touch Notebook (Celeron N3160, 4GB RAM, 32GB Storage) Chrome (French Bilingual Keyboard) - NX.G54AA.016',
      price: 347
    },
    {
      name:
        "Lenovo Flex 14 2-in-1 Convertible Laptop, 14 Inch FHD, Touchscreen, AMD Ryzen 5 3500U Processor, Radeon Vega 8 Graphics, 8GB DDR4 RAM, 256GB NVMe SSD, Win 10, Black, Pen Included",
      price: 385
    },
    {
      name:
        "2017 Dell Latitude E7440 14.1in HD Flagship Ultrabook PC, Intel Core i5-4300U 1.9GHz, 8GB DDR3 RAM, 256GB SSD, Bluetooth, Webcam, Windows 10 Professional (Renewed)",
      price: 799
    },
    {
      name:
        'Acer Canada NX.HG5AA.002 Aspire 5 Notebook, 15.6" FHD Display/ Ci5-8265U/8GB RAM/ 256 SSD/ Windows 10/Silver',
      price: 478
    },
    {
      name:
        'Asus X540BA-RB94 Laptop PC, AMD A9-9425 with R5 Graphics, 8GB RAM, 1TB HDD, 15.6" HD Display',
      price: 259
    },
    {
      name:
        "Lenovo 11.6inch Laptop Intel Celeron Processor N4000, 4GB Memory 64GB eMMC Flash Memory, Webcam, HDMI, Bluetooth, Mineral Gray",
      price: 179
    },
    {
      name:
        "Microsoft Surface Laptop Intel Core i5 7th Gen 8GB RAM 256GB SSD Win 10 Platinum (Renewed)",
      price: 437
    },
    {
      name:
        "HP Chromebook 11 G4 11.6 Inch Laptop (Intel N2840 Dual-Core, 2GB RAM, 16GB Flash SSD, Chrome OS), Black (Renewed)",
      price: 429
    },
    {
      name:
        'Acer Aspire 3, 15.6" HD LCD, AMD A9-9420, 8GB RAM, 1TB HDD, AMD Radeon 520 Graphics Card with 2GB DDR5, Black',
      price: 829
    }
  ],
  Dads_Oatmeal_Cookie: [
    {
      name: "Dads Portion Pack Oatmeal Chocolate Chip Cookies, 300g",
      price: 3
    },
    { name: "Dads Oatmeal Original Cookies, 320g", price: 3 },
    { name: "Dad's Oatmeal Cookies, 1.8 Kg", price: 24 },
    { name: "Dads Oatmeal Chocolate Chip Cookies, 305g", price: 3 },
    { name: "Dads Oatmeal Choc Chip Cookies, 500g", price: 5 },
    { name: "Dads Oatmeal Original Cookie, 520g", price: 5 },
    {
      name:
        "belVita Soft Baked Breakfast Biscuits, Banana Oatmeal & Chocolate Flavour, 5 Pouches (1 Biscuit Per Pouch)",
      price: 3
    },
    { name: "Dads Oatmeal Chocolate/Chip Cookie", price: 27 },
    {
      name:
        "Stork and Dove, Booby Boons Caramel Crunch Booby Boons Lactation Cookies 168 Gram Caramel Crunch, Orange",
      price: 9
    },
    {
      name:
        "CLIF KID ZBAR - Organic Energy Bars - Iced Oatmeal Cookie - (36 Gram Snack Bars, 5 Count)",
      price: 3
    },
    {
      name:
        "NATURE VALLEY Oatmeal Squares Banana Bread & Dark Chocolate, 5-Count, 175 Gram",
      price: 2
    },
    { name: "Dad's Chocolate Chip Cookies 48 Pack, 18 Kilogram", price: 28 },
    {
      name:
        "Milkmakers Lactation Cookie Bites, Oatmeal Chocolate Chip, 10 Count, 570g",
      price: 29
    },
    {
      name:
        "Christie Pirate Oatmeal Peanut Butter Sandwich Cookies, 1 Box (300g)",
      price: 1
    },
    {
      name: "Booby Boons Oatmeal raisin lactation cookies, 168 Gram",
      price: 9
    },
    {
      name:
        "Detour Smart Gluten-Free Non-GMO 14 (x 38g) Oatmeal Protein Bars (Organic Rolled Oats) Flavor: Chocolate Chip Cookie Dough",
      price: 25
    }
  ],
  Water_Bottle: [
    { name: "Nalgene Tritan Wide Mouth Water Bottle, 32-Ounce", price: 17 },
    {
      name: "THERMOS 24 Ounce Tritan Hydration Bottle with Meter, Teal",
      price: 9
    },
    {
      name:
        "OMORC 316 Stainless Steel Sports Water Bottle-20oz,34oz, Double Wall Vacuum Insulated Water Bottle, Straw and 2 Lids, Wide Mouth,Thermo Travel Modern Mug,Stay Cold for 48 Hrs,Hot for 24 Hrs,BPA Free",
      price: 29
    },
    {
      name:
        "OMORC Sports Water Bottle Plastic Drinking Bottle, BPA Free, Non-toxin, 32oz Large Capacity with Scale Mark, Flip Lid, One-handed Open, Easy to Clean, for Running, Hiking, Backpacking, Camping, Cycling, Traveling, Home, Office",
      price: 16
    },
    {
      name: "CamelBak Eddy Kids Water Bottle (2019 Back-to-School Series)",
      price: 12
    },
    {
      name:
        "Contigo Matterhorn Water Bottle, 32 oz, Stainless Steel, Matte Black",
      price: 26
    },
    {
      name:
        "BOGI 20oz Double Wall Vacuum Insulated Stainless Steel Water Bottle-Scratch Resistance&Eco-Friendly Outdoor Sports Yoga Camping,Straw Flip Cap+Cleaning Brush-1Year Warranty",
      price: 19
    },
    {
      name: "Rubbermaid Leak-Proof Chug Water Bottle, 24 oz, Dusty Lilac",
      price: 9
    },
    {
      name: "Brita Water Filter Bottle Replacement Filters, 2 Count",
      price: 7
    },
    {
      name: "Contigo AUTOSPOUT® Chug Leak-Proof Water Bottles 24oz, 3 Pack",
      price: 36
    },
    {
      name:
        "2L Sports Water Bottles,Lonni Portable Wide Mouth Bottle Leakproof Plastic Space Cup Travel Mugs with Straw and Adjustable Strap for Kids Adult Summer Outdoor Sports",
      price: 16
    },
    {
      name:
        "Takeya Actives Insulated Stainless Water Bottle Beer Growler with Insulated Spout Lid, 64 oz, Onyx",
      price: 55
    },
    {
      name:
        "Contigo AUTOSEAL Chill Stainless Steel Water Bottles, 24 oz, SS/Very Berry & Very Berry, 2-Pack",
      price: 44
    },
    {
      name: "Contigo AUTOSPOUT Straw Ashland Water Bottle, 24 oz, Grapevine",
      price: 16
    },
    {
      name:
        "Sports Water Bottle [Large Capacity 85 OZ, Hard BPA-Free PETG Material] Drinking Huge Tank Jug Container for Outdoor Training Bodybuilding Gym Camping and more",
      price: 24
    },
    {
      name: "S'well Stainless Steel Water Bottle (London Chimney, 17oz/500ml)",
      price: 45
    }
  ],
  "Coca-Cola": [
    {
      name: "7UP Cans, Natural Refreshing Lemon-Lime Taste 355mL, 12 Pack",
      price: 4
    },
    { name: "Coca-Cola 355mL Cans, 12 Pack", price: 4 },
    { name: "Coca-Cola Zero Sugar 355mL Cans, 12 Pack", price: 4 },
    {
      name: "Schweppes Ginger Ale Cans, Caffeine free, 355mL, 12 Pack",
      price: 4
    },
    { name: "Pepsi Cans, 355mL, 12 Pack", price: 4 },
    { name: "Canada Dry Ginger Ale 355 mL Cans, 12 Pack", price: 4 },
    { name: "A&W Root Beer 355mL Cans, 12 Pack", price: 4 },
    { name: "Sprite Zero Sugar 355mL Cans, 12 Pack", price: 4 },
    { name: "NESTEA Original Lemon Iced Tea, Canister, 2.2 Kg", price: 9 },
    { name: "Nestea Lemon, 341mL cans, Pack of 12", price: 4 },
    {
      name: "OREO Original Sandwich Cookies,1 Resealable Pack (303g)",
      price: 2
    },
    { name: "Kraft Dinner Original Macaroni & Cheese", price: 2 },
    { name: "Lot of 12 Crush Soft Drink Cans (355ml) (Pineapple)", price: 13 },
    { name: "Country Time Lemonade Liquid Drink Mix, 48mL", price: 3 },
    {
      name: "Crystal Light Liquid Drink Mix, Strawberry Lemonbabe, 48mL",
      price: 2
    },
    {
      name:
        "100 Pcs Disposable Umbrella-Shaped Straws Flexible Bendable Table Decor Straws for Tropical Drinks Soft Drinks Hawaiian Cocktail Bars Restaurants Luau Party Supply Beverage Decorations",
      price: 14
    }
  ],
  lays_chips: [
    { name: "Coca-Cola 355mL Cans, 12 Pack", price: 4 },
    { name: "Kettle Chips Backyard BBQ Chips, 220 Gram", price: 4 },
    { name: "Kettle Chips Sea Salt and Vinegar Chips, 220 Gram", price: 2 },
    { name: "Kettle Chips Krinkle Salt and Pepper Chips, 220 Gram", price: 2 },
    {
      name: "Lays Potato Chips, Ketchup, Large Family Size - 2 Pack",
      price: 29
    },
    { name: "Kettle Chips Sea Salted Chips, 220 Gram", price: 2 },
    {
      name:
        "Orville Redenbacher Popcorn - Microwave Buttery (24 pack with 24 bags total)",
      price: 10
    },
    { name: "Frito Lay Variety Pack, 50 Bags 1.4 KG", price: 48 },
    { name: "Tomahawk Ketchup Potato Chips, Ketchup, 43g", price: 0 },
    { name: "Lays Stax Original (Pack of 17)", price: 33 },
    { name: "Kettle Chips Honey Dijon Chips, 220 Gram", price: 2 },
    { name: "Pringles Dill Pickle Chips, 156 Grams", price: 2 },
    { name: "Kettle Chips Maple Bacon Chips, 220 Gram", price: 2 },
    { name: "Kettle Chips Yogurt and Green Onion Chips, 220 Gram", price: 2 },
    { name: "Lays Stax Cheddar Flavour Potato Chips (Pack of 17)", price: 33 },
    {
      name: "Pepperidge Farm Goldfish Cheddar Crackers, 28g, 22 Count",
      price: 6
    }
  ],
  Iphone_6: [
    {
      name: "Apple iPhone 7, GSM Unlocked , 32GB - Rose Gold (Renewed)",
      price: 299
    },
    {
      name:
        "Apple iPhone 7 Factory Unlocked Phone - 4.7Inch Screen - 32GB - Black (Renewed)",
      price: 299
    },
    {
      name: "Apple iPhone 6S, GSM Unlocked, 64GB - Space Grey (Renewed)",
      price: 278
    },
    { name: "Apple iPhone 7 128 GB Unlocked, Black (Renewed)", price: 349 },
    {
      name:
        "Apple iPhone SE Unlocked Phone - 64 GB Retail Packaging - Rose Gold (Renewed)",
      price: 199
    },
    {
      name: "Apple iPhone 5S Space Gray 16GB Unlocked GSM Smartphone (Renewed)",
      price: 135
    },
    {
      name: "Apple iPhone 7 Plus, GSM Unlocked, 32GB - Black (Renewed)",
      price: 459
    },
    {
      name: "Apple iPhone 7 - 32GB - GSM Unlocked - Gold (Renewed)",
      price: 382
    }
  ],
  Welchs_Fruit_Snacks: [
    {
      name: "Mott's Fruitsations Veggie Gluten Free Berry, 32-Count, 723 Gram",
      price: 7
    },
    {
      name:
        "Laura Secord Deluxe Hard Fruit Candies in Decorative Glass Jar 34 OZ / 966 g",
      price: 18
    },
    { name: "Assorted Fruit Filled Hard Candies - 1.3 kg Bag", price: 22 },
    { name: "Yupik Nitwitz - Mini Fruit Shapes, 1Kg", price: 10 },
    {
      name: "Starburst Original Fruit Chews Candy, Stand Up Pouch, 320 Grams",
      price: 3
    },
    {
      name:
        "AirHeads Bars, Chewy Fruit Candy, Variety Pack, Party, Halloween, 60 Count (Packaging May Vary)",
      price: 13
    },
    { name: "Juicy Fruit Sugar, The Original, 20-Count", price: 7 },
    {
      name:
        "Betty Crocker Gluten Free Fruit by The Foot Variety Pack, 6 Count, 128 Gram",
      price: 1
    },
    { name: "Mentos (mixed fruit), 4 Count", price: 2 },
    {
      name:
        "Betty Crocker Gluten Free Fruit Snacks Variety Pack, 14-Count, 272 Gram",
      price: 5
    },
    {
      name: "JOLLY RANCHER Candy Lollipops Assortment, 50 Count (850 Gram)",
      price: 6
    },
    { name: "Yupik Wrapped Fruit Slices, 1Kg", price: 15 },
    {
      name:
        "Betty Crocker Gluten Free Gushers Gushin Grape/Tropical, 6-Count, 138 Gram",
      price: 1
    },
    {
      name:
        "Cavendish And Harvey Candy (3 Pack) Fruit Hard Candy Tin 5.3 Ounces Imported German Candy (Sour Lemon Drops)",
      price: 19
    },
    { name: "Real Fruit Gummies Super Fruits Fresh Pack, 350g", price: 3 },
    {
      name:
        "Betty Crocker Gluten Free Gushin Grape/Strawberry Gushers, 6-Count, 138 Gram",
      price: 1
    }
  ],
  Adidas_Shirt: [
    {
      name: "Men's Clima Tech Tee",
      price: 20
    },
    {
      name: "Boy's BOS T-Shirt",
      price: 11
    },
    {
      name: "adidas Men's Tango Logo Tee",
      price: 23
    },
    {
      name: "adidas Originals Men's 3 Stripes T-Shirt",
      price: 40
    },
    {
      name: "adidas Originals Kid's Trefoil Tee",
      price: 13
    },
    {
      name: "adidas Men's Entrada 18 Jersey",
      price: 13
    },
    {
      name: "Women's Essentials Linear Slim Tee",
      price: 15
    },
    {
      name: "Champion Mens Classic Jersey Graphic T-Shirt",
      price: 7
    }
  ],
  Macbook: [
    {
      name: "Apple MacBook Air MJVM2LL/A 11.6-Inch laptop",
      price: 634
    },
    {
      name:
        "Apple MacBook Air 13.3in LED Laptop Intel i5-5250U Dual Core 1.6GHz 4GB 128GB SSD ",
      price: 749
    },
    {
      name:
        "Apple 13 MacBook Air, 1.8GHz Intel Core i5 Dual Core Processor, 8GB RAM",
      price: 1077
    },
    {
      name:
        "Apple MacBook Pro MD101LL/A 13.3-inch Laptop (2.5Ghz, 4GB RAM, 500GB HD) (Renewed)",
      price: 519
    },
    {
      name:
        "Apple MacBook Pro ME865LL/A 13.3-Inch Laptop with Retina Display (Renewed)",
      price: 875
    },
    {
      name:
        "Apple MacBook Air MJVE2LL/A 13-inch Laptop 1.6GHz Core i5, 8GB RAM, 128GB SSD (Renewed)",
      price: 789
    },
    {
      name:
        "Apple MacBook 12in Retina 2017 (Newest Version) 256GB SSD / 8GB RAM - Gold (Renewed)",
      price: 1233
    }
  ],
  yeezys: [
    {
      name: 'SPLSPORT Yeezys Boost 350 V2 Sport Running Lightweight Casual Sneaker',
      price: 67
    },
    {
      name: "Womens Mens Boost Low-Top Sport Running Shoes Fashion V2 Walking ",
      price: 89
    },
    {
      name: "Sherwood Yeezy Boost 350 V2 Sport Running Lightweight Sneaker",
      price: 91
    },
    {
      name: "Sherwood Yeezy Boost 350 V2 Black Red- Sport Running Lightweight Sneaker",
      price: 59
    },
    {
      name: 'Adicas Men\'s Women\'s Lightweight Sneakers Fashion Running Shoes ',
      price: 58
    },
    {
      name: 'Adidas Mens Yeezy Boost 350 Turtle/Blue-Gray Fabric',
      price: 425
    }
  ]
};
