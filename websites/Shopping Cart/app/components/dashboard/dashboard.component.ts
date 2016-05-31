import {Component, ViewChild} from '@angular/core';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS, Router } from '@angular/router-deprecated';
import { LoginComponent } from '../login/login.component';
import { SignUpComponent } from '../signup/signup.component';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';

declare var jQuery: any;

@Component({
    selector: 'dashboard',
    templateUrl: 'app/components/dashboard/dashboard.html',
    directives: [ROUTER_DIRECTIVES]
})

export class DashboardComponent {
    
    allProducts:Array<Product>;
    
    constructor(private productService: ProductService, private _router: Router) {
    }
    
    ngOnInit(){
        this.allProducts = this.productService.getMockedProducts();
    }
    
    addToCart(product:Product) {
        this.productService.addNewProduct(product);

        console.log('product: ', product.name);
        console.log('price: ', product.price);
        console.log('quantity: ', product.quantity);
    }

    checkOut() {
        console.log('checking out cart...');
        this._router.navigate(['CheckOut']);
    }
}
