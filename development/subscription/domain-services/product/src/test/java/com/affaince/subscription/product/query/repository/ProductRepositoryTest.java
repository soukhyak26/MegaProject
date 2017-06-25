package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.build.ProductViewBuilder;
import com.affaince.subscription.product.query.view.ProductView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 25-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ProductRepositoryTest {

    @Autowired
    private ProductViewRepository productViewRepository;
    @Autowired
    ProductViewBuilder productViewBuilder;
    @Before
    public void init () throws FileNotFoundException {
        productViewBuilder.buildProductView();
    }

    @After
    public void shutdown(){
        productViewBuilder.deleteProductView();
    }
    @Test
    public void testTotalProducts () {
        final List <ProductView> productViews = new ArrayList<>();
        productViewRepository.findAll().forEach(productView -> productViews.add(productView));
        assertThat(productViews.size(), is (1000));
    }
}
