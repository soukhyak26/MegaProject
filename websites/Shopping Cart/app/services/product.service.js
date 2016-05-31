"use strict";
var ProductService = (function () {
    function ProductService() {
        this.selectedProducts = new Array();
    }
    ProductService.prototype.addNewProduct = function (newProduct) {
        this.selectedProducts.push(newProduct);
    };
    return ProductService;
}());
exports.ProductService = ProductService;
//# sourceMappingURL=product.service.js.map