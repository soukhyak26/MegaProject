package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductView;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 24-06-2016.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
//@SpringApplicationConfiguration(classes = DemandForecasterChain.class)
//@ContextConfiguration(locations = {"/applicationContext_mock.xml"})
public class DemandForecasterChainTest {
    @Mock
    private static ProductViewRepository productViewRepository;
    @Mock
    private static ProductActualMetricsViewRepository productActualMetricsViewRepository;
    @Mock
    private static ProductForecastMetricsViewRepository productForecastMetricsViewRepository;

   @InjectMocks
    private DemandForecasterChain chain;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ProductDemandForecaster forecaster1 = new SimpleMovingAverageDemandForecaster();
        ProductDemandForecaster forecaster2 = new SimpleExponentialSmoothingDemandForecaster();
        ProductDemandForecaster forecaster3 = new TripleExponentialSmoothingDemandForecaster();
        ProductDemandForecaster forecaster4 = new ARIMABasedDemandForecaster();
        forecaster1.addNextForecaster(forecaster2);
        forecaster2.addNextForecaster(forecaster3);
        forecaster3.addNextForecaster(forecaster4);
        chain = new DemandForecasterChain(productForecastMetricsViewRepository,productActualMetricsViewRepository,productViewRepository);
        chain.addForecaster(forecaster1);

    }

    @Test
    public void testForecastFor4HistoricalLinearRecords() {
        List<ProductActualMetricsView> productActualMetricsViewList;
        productActualMetricsViewList = new ArrayList<>();

        ProductActualMetricsView view1 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view1.setTotalNumberOfExistingSubscriptions(500);
        productActualMetricsViewList.add(view1);

        ProductActualMetricsView view2 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view2.setTotalNumberOfExistingSubscriptions(750);
        productActualMetricsViewList.add(view2);

        ProductActualMetricsView view3 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view3.setTotalNumberOfExistingSubscriptions(1000);
        productActualMetricsViewList.add(view3);

        ProductActualMetricsView view4 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view4.setTotalNumberOfExistingSubscriptions(1250);
        productActualMetricsViewList.add(view4);
       // Mockito.when(productViewRepository.findOne(productId)).thenReturn(product);
        String productId = "1";
        ProductView product = new ProductView(productId, "Myproduct", "MyCat", "MySubCat", 100, QuantityUnit.GM, null, null, null);
        List<ProductView> allProducts=new ArrayList<ProductView>();
        allProducts.add(product);
        Mockito.when(productViewRepository.findAll()).thenReturn(allProducts);

        Mockito.when(productActualMetricsViewRepository.findByProductId(product.getProductId())).thenReturn(productActualMetricsViewList);

        chain.forecast();
    }

    @Test
    public void testForecastFor40HistoricalLinearRecords() {
        List<ProductActualMetricsView> productActualMetricsViewList;
        productActualMetricsViewList = new ArrayList<>();

        ProductActualMetricsView view1 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view1.setTotalNumberOfExistingSubscriptions(500);
        productActualMetricsViewList.add(view1);

        ProductActualMetricsView view2 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view2.setTotalNumberOfExistingSubscriptions(750);
        productActualMetricsViewList.add(view2);

        ProductActualMetricsView view3 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view3.setTotalNumberOfExistingSubscriptions(1000);
        productActualMetricsViewList.add(view3);

        ProductActualMetricsView view4 = new ProductActualMetricsView("1",new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view4.setTotalNumberOfExistingSubscriptions(1250);
        productActualMetricsViewList.add(view4);

        ProductActualMetricsView view5 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view5.setTotalNumberOfExistingSubscriptions(1500);
        productActualMetricsViewList.add(view5);

        ProductActualMetricsView view6 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view6.setTotalNumberOfExistingSubscriptions(1750);
        productActualMetricsViewList.add(view6);

        ProductActualMetricsView view7 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view7.setTotalNumberOfExistingSubscriptions(2000);
        productActualMetricsViewList.add(view7);

        ProductActualMetricsView view8 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view8.setTotalNumberOfExistingSubscriptions(2250);
        productActualMetricsViewList.add(view8);

        ProductActualMetricsView view9 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view9.setTotalNumberOfExistingSubscriptions(2500);
        productActualMetricsViewList.add(view9);

        ProductActualMetricsView view10 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view10.setTotalNumberOfExistingSubscriptions(2750);
        productActualMetricsViewList.add(view10);

        ProductActualMetricsView view11 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view11.setTotalNumberOfExistingSubscriptions(3000);
        productActualMetricsViewList.add(view11);

        ProductActualMetricsView view12 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view12.setTotalNumberOfExistingSubscriptions(3250);
        productActualMetricsViewList.add(view12);

        ProductActualMetricsView view13 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view13.setTotalNumberOfExistingSubscriptions(3500);
        productActualMetricsViewList.add(view13);

        ProductActualMetricsView view14 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view14.setTotalNumberOfExistingSubscriptions(3750);
        productActualMetricsViewList.add(view14);

        ProductActualMetricsView view15 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view15.setTotalNumberOfExistingSubscriptions(4000);
        productActualMetricsViewList.add(view15);

        ProductActualMetricsView view16 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view16.setTotalNumberOfExistingSubscriptions(4250);
        productActualMetricsViewList.add(view16);

        ProductActualMetricsView view17 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view17.setTotalNumberOfExistingSubscriptions(4500);
        productActualMetricsViewList.add(view17);

        ProductActualMetricsView view18 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view18.setTotalNumberOfExistingSubscriptions(4750);
        productActualMetricsViewList.add(view18);

        ProductActualMetricsView view19 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view19.setTotalNumberOfExistingSubscriptions(5000);
        productActualMetricsViewList.add(view19);

        ProductActualMetricsView view20 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view20.setTotalNumberOfExistingSubscriptions(5250);
        productActualMetricsViewList.add(view20);

        ProductActualMetricsView view21 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view21.setTotalNumberOfExistingSubscriptions(5500);
        productActualMetricsViewList.add(view21);

        ProductActualMetricsView view22 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view22.setTotalNumberOfExistingSubscriptions(5750);
        productActualMetricsViewList.add(view22);

        ProductActualMetricsView view23 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view23.setTotalNumberOfExistingSubscriptions(6000);
        productActualMetricsViewList.add(view23);

        ProductActualMetricsView view24 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view24.setTotalNumberOfExistingSubscriptions(6250);
        productActualMetricsViewList.add(view24);

        ProductActualMetricsView view25 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view25.setTotalNumberOfExistingSubscriptions(6500);
        productActualMetricsViewList.add(view25);

        ProductActualMetricsView view26 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view26.setTotalNumberOfExistingSubscriptions(6750);
        productActualMetricsViewList.add(view26);

        ProductActualMetricsView view27 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view27.setTotalNumberOfExistingSubscriptions(7000);
        productActualMetricsViewList.add(view27);

        ProductActualMetricsView view28 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view28.setTotalNumberOfExistingSubscriptions(7250);
        productActualMetricsViewList.add(view28);

        ProductActualMetricsView view29 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view29.setTotalNumberOfExistingSubscriptions(7500);
        productActualMetricsViewList.add(view29);

        ProductActualMetricsView view30 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view30.setTotalNumberOfExistingSubscriptions(7750);
        productActualMetricsViewList.add(view30);

        ProductActualMetricsView view31 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view31.setTotalNumberOfExistingSubscriptions(8000);
        productActualMetricsViewList.add(view31);

        ProductActualMetricsView view32 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view32.setTotalNumberOfExistingSubscriptions(8250);
        productActualMetricsViewList.add(view32);

        ProductActualMetricsView view33 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view33.setTotalNumberOfExistingSubscriptions(8500);
        productActualMetricsViewList.add(view33);

        ProductActualMetricsView view34 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view34.setTotalNumberOfExistingSubscriptions(8750);
        productActualMetricsViewList.add(view34);

        ProductActualMetricsView view35 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view35.setTotalNumberOfExistingSubscriptions(9000);
        productActualMetricsViewList.add(view35);

        ProductActualMetricsView view36 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view36.setTotalNumberOfExistingSubscriptions(9250);
        productActualMetricsViewList.add(view36);

        ProductActualMetricsView view37 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view37.setTotalNumberOfExistingSubscriptions(9500);
        productActualMetricsViewList.add(view37);

        ProductActualMetricsView view38 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view38.setTotalNumberOfExistingSubscriptions(9750);
        productActualMetricsViewList.add(view38);

        ProductActualMetricsView view39 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view39.setTotalNumberOfExistingSubscriptions(10000);
        productActualMetricsViewList.add(view39);

        ProductActualMetricsView view40 = new ProductActualMetricsView("1", new Interval( new LocalDate(2016,1,1).toDateTimeAtStartOfDay(),new LocalDate(9999,12,31).toDateTimeAtStartOfDay()));
        view40.setTotalNumberOfExistingSubscriptions(10250);
        productActualMetricsViewList.add(view40);
        String productId = "1";
        ProductView product = new ProductView(productId, "Myproduct", "MyCat", "MySubCat", 100, QuantityUnit.GM, null, null, null);
        List<ProductView> allProducts=new ArrayList<ProductView>();
        allProducts.add(product);
        Mockito.when(productViewRepository.findAll()).thenReturn(allProducts);
       // Mockito.when(productViewRepository.findOne(productId)).thenReturn(product);
        Mockito.when(productActualMetricsViewRepository.findByProductId(product.getProductId())).thenReturn(productActualMetricsViewList);
        chain.forecast();

    }


}
