package com.affaince.subscription.product.registration.query.view;


import com.affaince.subscription.common.type.ProductStatus;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by anayonkar on 28/2/16.
 */
@Ignore
public class ProductStatusViewTest {
    /*private ProductStatusView productStatusView;

    @Before
    public void init() {
        productStatusView = new ProductStatusView("111");
    }*//*

    //@Test
    public void testNegative() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//* boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_ACTIVATED);
            //System.out.println(result);
            Assert.fail();
        } *//*catch (InvalidProductStatusException e) {
            //System.out.println(e);
        }*//* catch (Exception e) {
            //System.out.println(e);
            Assert.fail();
        }
    }

    //@Test
    public void testPositive() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//* boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
            //System.out.println(result);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
            //System.out.println(result);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
            //System.out.println(result);
            //Assert.assertEquals("message", result, ProductStatus.PRODUCT_COMPLETED);
        } *//*catch (InvalidProductStatusException e) {
            //System.out.println(e);
            Assert.fail();
        }*//*
        finally {

        }

    }

    @Test
    public void positiveConfigTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//*boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
            //System.out.println(result);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
            //System.out.println(result);
            Assert.assertEquals("message", result, ProductStatus.PRODUCT_CONFIGURED);
        } *//*catch (InvalidProductStatusException e) {
            //System.out.println(e);
            Assert.fail();
        }*//*
        finally {

        }
    }

    @Test
    public void negativeConfigTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//* boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
            Assert.fail();
        } *//*catch (InvalidProductStatusException e) {

        }*//*
        finally {

        }
    }

    @Test
    public void positiveForecastTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//*boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
            //System.out.println(result);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
            //System.out.println(result);
            Assert.assertEquals("message", result, ProductStatus.PRODUCT_FORECASTED);
        } *//*catch (InvalidProductStatusException e) {
            //System.out.println(e);
            Assert.fail();
        }*//*
        finally {

        }
    }

    @Test
    public void negativeForecastTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//*boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
            Assert.fail();
        } *//*catch (InvalidProductStatusException e) {

        }*//*
        finally {

        }
    }

    @Test
    public void positiveCompleteTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//*boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
            //Assert.assertEquals("message", result, ProductStatus.PRODUCT_COMPLETED);
        } *//*catch (InvalidProductStatusException e) {
            Assert.fail();
        }*//*
        finally {

        }
    }

    @Test
    public void negativeCompleteTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//*boolean result;
            //result = productStatusView.addProductStatus(ProductStatus.PRODUCT_COMPLETED);
            Assert.fail();
        } *//*catch (InvalidProductStatusException e) {

        }*//*
        finally {

        }
    }

    @Test
    public void positiveExpenseDistributeTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//*boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED);
            Assert.assertEquals("message", result, ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED);
        } *//*catch (InvalidProductStatusException e) {
            Assert.fail();
        }*//*
        finally {

        }
    }

    @Test
    public void negativeExpenseDistributeTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//*boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED);
            Assert.fail();
        } *//*catch (InvalidProductStatusException e) {

        }*//*
        finally {

        }
    }

    @Test
    public void positiveActiveTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//*boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_EXPENSES_DISTRIBUTED);
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_ACTIVATED);
            Assert.assertEquals("message", result, ProductStatus.PRODUCT_ACTIVATED);
        } *//*catch (InvalidProductStatusException e) {
            Assert.fail();
        }*//*
        finally {

        }
    }

    @Test
    public void negativeActiveTest() {
        try {
            ProductStatusView productStatusView = new ProductStatusView("111");
            *//*ProductStatus*//*boolean result;
            result = productStatusView.addProductStatus(ProductStatus.PRODUCT_ACTIVATED);
            Assert.fail();
        } *//*catch (InvalidProductStatusException e) {

        }*//*
        finally {

        }
    }*/
}
