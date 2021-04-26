package com.verifier.domains.inventory.view;

import com.verifier.domains.inventory.vo.ProductServiceIdentifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SupplierView")
public class SupplierView {
    @Id
    private String supplierId;
    private String supplierName;
    private String supplierGSTN;
    private ProductServiceIdentifier supplierType;
    private String productId;

    public SupplierView(String supplierId, String supplierName, String supplierGSTN, ProductServiceIdentifier supplierType, String productId) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierGSTN = supplierGSTN;
        this.supplierType = supplierType;
        this.productId = productId;
    }

    public SupplierView() {

    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierGSTN() {
        return supplierGSTN;
    }

    public ProductServiceIdentifier getSupplierType() {
        return supplierType;
    }

    public String getProductId() {
        return productId;
    }
}
