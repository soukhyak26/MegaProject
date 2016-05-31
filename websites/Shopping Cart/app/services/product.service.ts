import { Product } from '../models/product.model';

export class ProductService {
    selectedProducts: Array<Product>;

    constructor() {
        this.selectedProducts = new Array<Product>();
    }

    addNewProduct(newProduct: Product) {
        this.selectedProducts.push(newProduct);
    }

    getSelectedProducts(): Array<Product> {
        return this.selectedProducts;
    }

    getMockedProducts(): Array<Product> {
        let mockedProducts = new Array<Product>();
        
        let product = new Product();
        product.imageUrl = '../../../images/ata.jpg';
        product.name = 'Aashirvaad Atta - Whole Weat';
        product.mrp = 400;
        product.price = 322;
        product.quantity = 1;
        mockedProducts.push(product);
        
        product = new Product();
        product.imageUrl = '../../../images/almonds.jpg';
        product.name = 'BB Royal - Almond/Badam California';
        product.mrp = 700;
        product.price = 605;
        product.quantity = 1;
        mockedProducts.push(product);
        
        product = new Product();
        product.imageUrl = '../../../images/oil.jpg';
        product.name = 'Fortune - Sunflower Refined Oil';
        product.mrp = 600;
        product.price = 469;
        product.quantity = 1;
        mockedProducts.push(product);
        
        product = new Product();
        product.imageUrl = '../../../images/rice.jpg';
        product.name = 'Sona Masoori Raw Rice';
        product.mrp = 555;
        product.price = 466;
        product.quantity = 1;
        mockedProducts.push(product);
        
        return mockedProducts;
    }

}