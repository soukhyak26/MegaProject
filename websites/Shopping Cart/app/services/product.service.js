"use strict";
var product_model_1 = require('../models/product.model');
var ProductService = (function () {
    function ProductService() {
        this.selectedProducts = new Array();
    }
    ProductService.prototype.addNewProduct = function (newProduct) {
        this.selectedProducts.push(newProduct);
    };
    ProductService.prototype.getSelectedProducts = function () {
        return this.selectedProducts;
    };
    ProductService.prototype.getMockedProducts = function () {
        var mockedProducts = new Array();
        var product = new product_model_1.Product();
        product.imageUrl = '../../../images/ata.jpg';
        product.name = 'Aashirvaad Atta - Whole Weat';
        product.mrp = 400;
        product.price = 322;
        product.quantity = 1;
        mockedProducts.push(product);
        product = new product_model_1.Product();
        product.imageUrl = '../../../images/almonds.jpg';
        product.name = 'BB Royal - Almond/Badam California';
        product.mrp = 700;
        product.price = 605;
        product.quantity = 1;
        mockedProducts.push(product);
        product = new product_model_1.Product();
        product.imageUrl = '../../../images/oil.jpg';
        product.name = 'Fortune - Sunflower Refined Oil';
        product.mrp = 600;
        product.price = 469;
        product.quantity = 1;
        mockedProducts.push(product);
        product = new product_model_1.Product();
        product.imageUrl = '../../../images/rice.jpg';
        product.name = 'Sona Masoori Raw Rice';
        product.mrp = 555;
        product.price = 466;
        product.quantity = 1;
        mockedProducts.push(product);
        return mockedProducts;
    };
    return ProductService;
}());
exports.ProductService = ProductService;
//# sourceMappingURL=product.service.js.map