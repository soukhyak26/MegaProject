"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_deprecated_1 = require('@angular/router-deprecated');
var product_model_1 = require('../../models/product.model');
var product_service_1 = require('../../services/product.service');
var DashboardComponent = (function () {
    function DashboardComponent(productService) {
        this.productService = productService;
    }
    DashboardComponent.prototype.addToCart = function (productName, price, quantity) {
        var newProduct = new product_model_1.Product();
        newProduct.name = productName;
        newProduct.price = price;
        newProduct.quantity = quantity;
        this.productService.addNewProduct(newProduct);
        console.log('product: ', productName);
        console.log('price: ', price);
        console.log('quantity: ', quantity);
    };
    DashboardComponent.prototype.checkOut = function () {
    };
    DashboardComponent = __decorate([
        core_1.Component({
            selector: 'dashboard',
            templateUrl: 'app/components/dashboard/dashboard.html',
            directives: [router_deprecated_1.ROUTER_DIRECTIVES],
            providers: [
                router_deprecated_1.ROUTER_PROVIDERS
            ]
        }), 
        __metadata('design:paramtypes', [product_service_1.ProductService])
    ], DashboardComponent);
    return DashboardComponent;
}());
exports.DashboardComponent = DashboardComponent;
//# sourceMappingURL=dashboard.component.js.map