import {Component, ViewChild} from '@angular/core';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router-deprecated';
import { LoginComponent } from '../login/login.component';
import { SignUpComponent } from '../signup/signup.component';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';

declare var jQuery: any;

@Component({
    selector: 'dashboard',
    templateUrl: 'app/components/dashboard/dashboard.html',
    directives: [ROUTER_DIRECTIVES],
    providers: [
        ROUTER_PROVIDERS
    ]
})

export class DashboardComponent {
    
    constructor(private productService:ProductService){
    }
    
    addToCart(productName: string, price: number, quantity:number) {
        let newProduct = new Product();
        newProduct.name = productName;
        newProduct.price = price;
        newProduct.quantity = quantity;
        
        this.productService.addNewProduct(newProduct);
        
        console.log('product: ', productName);
        console.log('price: ', price);
        console.log('quantity: ', quantity);
    }
    
    checkOut(){
        
    }
}
