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
        ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView("111", new ArrayList<>());
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED));
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED));
        Assert.assertEquals("message", productActivationStatusView.getLatestProductStatus(), ProductStatus.PRODUCT_CONFIGURED);
    }

    @Test
    public void negativeConfigTest() {
        ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView("111", new ArrayList<>());
        Assert.assertFalse(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED));
        Assert.assertFalse(productActivationStatusView.getProductStatuses().contains(ProductStatus.PRODUCT_CONFIGURED));
    }

    @Test
    public void positiveForecastTest() {
        ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView("111", new ArrayList<>());
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED));
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED));
        Assert.assertEquals("message", productActivationStatusView.getLatestProductStatus(), ProductStatus.PRODUCT_FORECASTED);
    }

    @Test
    public void negativeForecastTest() {
        ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView("111", new ArrayList<>());
        Assert.assertFalse(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED));
        Assert.assertFalse(productActivationStatusView.getProductStatuses().contains(ProductStatus.PRODUCT_FORECASTED));
    }

    @Test
    public void positiveExpenseDistributeTest() {
        ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView("111", new ArrayList<>());
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED));
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED));
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED));
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED));
        Assert.assertEquals("message", productActivationStatusView.getLatestProductStatus(), ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED);
    }

    @Test
    public void negativeExpenseDistributeTest() {
        ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView("111", new ArrayList<>());
        Assert.assertFalse(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED));
        Assert.assertFalse(productActivationStatusView.getProductStatuses().contains(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED));
    }

    @Test
    public void positiveActiveTest() {
        ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView("111", new ArrayList<>());
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED));
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED));
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED));
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED));
        Assert.assertTrue(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_ACTIVATED));
        Assert.assertEquals("message", productActivationStatusView.getLatestProductStatus(), ProductStatus.PRODUCT_ACTIVATED);
    }

    @Test
    public void negativeActiveTest() {
        ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView("111", new ArrayList<>());
        Assert.assertFalse(productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_ACTIVATED));
        Assert.assertFalse(productActivationStatusView.getProductStatuses().contains(ProductStatus.PRODUCT_ACTIVATED));
    }
}
