import { Product } from '../models/product.model';

export class ProductService {
    selectedProducts: Array<Product>;
    
    constructor(){
        this.selectedProducts = new Array<Product>();
    }
    
    addNewProduct(newProduct:Product){
        this.selectedProducts.push(newProduct);
    }
}