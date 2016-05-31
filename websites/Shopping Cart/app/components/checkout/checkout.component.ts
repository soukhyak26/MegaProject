import {Component, ViewChild} from '@angular/core';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router-deprecated';
import { LoginComponent } from '../login/login.component';
import { SignUpComponent } from '../signup/signup.component';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';

declare var jQuery: any;

@Component({
    selector: 'checkout',
    templateUrl: 'app/components/checkout/checkout.html',
    directives: [ROUTER_DIRECTIVES],
    providers: [
        ROUTER_PROVIDERS
    ]
})

export class CheckOutComponent {

    selectedProducts: Array<Product>;
    
    constructor(private productService: ProductService) {
    }
    
    ngOnInit(){
        jQuery('select').material_select();
        
        this.selectedProducts = this.productService.getSelectedProducts();
    }
}
