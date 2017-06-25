package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.build.ProductActualsViewBuilder;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by mandar on 02-07-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ProductActualsViewRepositoryTest {
    private static final int QTY = 20;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    @Autowired
    private ProductActualsViewBuilder productActualsViewBuilder;
    @Before
    public void init() throws FileNotFoundException,IOException {
        productActualsViewBuilder.buildProductActualsView();
    }
    @After
    public void shutdown(){
        productActualsViewBuilder.deleteProductActualsView();
    }
    @Test
    public void findALLTest(){
        Iterable<ProductActualsView> forecasts= productActualsViewRepository.findAll();
        for(ProductActualsView view: forecasts){
            System.out.println("No of existing subscriptions:" + view.getTotalNumberOfExistingSubscriptions());
        }
    }
}
