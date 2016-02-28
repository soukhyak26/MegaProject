package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.common.type.ProductStatus;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by anayonkar on 28/2/16.
 */
public class ProductConfigurationValidatorTest {
    @Test
    public void positiveConfigTest() {
        ProductStatusView productStatusView = new ProductStatusView("111");
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED));
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED));
        Assert.assertEquals("message", productStatusView.getLatestProductStatus(), ProductStatus.PRODUCT_CONFIGURED);
    }

    @Test
    public void negativeConfigTest() {
        ProductStatusView productStatusView = new ProductStatusView("111");
        Assert.assertFalse(productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED));
        Assert.assertFalse(productStatusView.getProductStatuses().contains(ProductStatus.PRODUCT_CONFIGURED));
    }

    @Test
    public void positiveForecastTest() {
        ProductStatusView productStatusView = new ProductStatusView("111");
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED));
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED));
        Assert.assertEquals("message", productStatusView.getLatestProductStatus(), ProductStatus.PRODUCT_FORECASTED);
    }

    @Test
    public void negativeForecastTest() {
        ProductStatusView productStatusView = new ProductStatusView("111");
        Assert.assertFalse(productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED));
        Assert.assertFalse(productStatusView.getProductStatuses().contains(ProductStatus.PRODUCT_FORECASTED));
    }

    @Test
    public void positiveExpenseDistributeTest() {
        ProductStatusView productStatusView = new ProductStatusView("111");
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED));
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED));
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED));
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED));
        Assert.assertEquals("message", productStatusView.getLatestProductStatus(), ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED);
    }

    @Test
    public void negativeExpenseDistributeTest() {
        ProductStatusView productStatusView = new ProductStatusView("111");
        Assert.assertFalse(productStatusView.addProductStatus(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED));
        Assert.assertFalse(productStatusView.getProductStatuses().contains(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED));
    }

    @Test
    public void positiveActiveTest() {
        ProductStatusView productStatusView = new ProductStatusView("111");
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED));
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED));
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED));
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED));
        Assert.assertTrue(productStatusView.addProductStatus(ProductStatus.PRODUCT_ACTIVATED));
        Assert.assertEquals("message", productStatusView.getLatestProductStatus(), ProductStatus.PRODUCT_ACTIVATED);
    }

    @Test
    public void negativeActiveTest() {
        ProductStatusView productStatusView = new ProductStatusView("111");
        Assert.assertFalse(productStatusView.addProductStatus(ProductStatus.PRODUCT_ACTIVATED));
        Assert.assertFalse(productStatusView.getProductStatuses().contains(ProductStatus.PRODUCT_ACTIVATED));
    }
}
