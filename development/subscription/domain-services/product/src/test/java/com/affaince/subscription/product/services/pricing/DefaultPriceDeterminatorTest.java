package com.affaince.subscription.product.services.pricing;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.services.forecast.*;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 29-06-2016.
 */
public class DefaultPriceDeterminatorTest {
    @Mock
    private static ProductViewRepository productViewRepository;
    @Mock
    private static ProductActualMetricsViewRepository productActualMetricsViewRepository;
    @Mock
    private static ProductForecastMetricsViewRepository productForecastMetricsViewRepository;

    @InjectMocks
    private DemandForecasterChain chain;

    @Before
    public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        MockitoAnnotations.initMocks(this);
        chain = new DemandForecasterChain().buildForecasterChain(productForecastMetricsViewRepository,productActualMetricsViewRepository);
    }


    public void createForecast(){
        List<ProductActualMetricsView> productActualMetricsViewList;
        productActualMetricsViewList = new ArrayList<>();

        ProductActualMetricsView view1 = new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view1.setTotalNumberOfExistingSubscriptions(500);
        productActualMetricsViewList.add(view1);

        ProductActualMetricsView view2 = new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view2.setTotalNumberOfExistingSubscriptions(750);
        productActualMetricsViewList.add(view2);

        ProductActualMetricsView view3 = new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view3.setTotalNumberOfExistingSubscriptions(1000);
        productActualMetricsViewList.add(view3);

        ProductActualMetricsView view4 = new ProductActualMetricsView("1",new LocalDate(2016,1,1),new LocalDate(9999,12,31));
        view4.setTotalNumberOfExistingSubscriptions(1250);
        productActualMetricsViewList.add(view4);
        // Mockito.when(productViewRepository.findOne(productId)).thenReturn(product);
        String productId = "1";
        ProductView product = new ProductView(productId, "Myproduct", "MyCat", "MySubCat", 100, QuantityUnit.GM, null, null, null);
        List<ProductView> allProducts=new ArrayList<ProductView>();
        allProducts.add(product);
        Mockito.when(productViewRepository.findAll()).thenReturn(allProducts);

        Mockito.when(productActualMetricsViewRepository.findByProductVersionId_ProductId(product.getProductId())).thenReturn(productActualMetricsViewList);

        chain.forecast(product.getProductId());

    }
}
