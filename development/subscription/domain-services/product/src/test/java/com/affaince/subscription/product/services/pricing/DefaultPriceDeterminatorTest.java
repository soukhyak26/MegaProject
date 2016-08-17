package com.affaince.subscription.product.services.pricing;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.services.forecast.ProductDemandForecastBuilder;
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
    private static ProductActualsViewRepository productActualsViewRepository;
    @Mock
    private static ProductForecastViewRepository productForecastViewRepository;

    @InjectMocks
    private ProductDemandForecastBuilder builder;

    @Before
    public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException,NoSuchMethodException{
        MockitoAnnotations.initMocks(this);
        //chain = new DemandForecasterChain().buildForecasterChain(productForecastMetricsViewRepository,ProductActualsViewRepository);
    }


    public void createForecast(){
        List<ProductActualsView> productActualsViewList;
        productActualsViewList = new ArrayList<>();

        ProductActualsView view1 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view1.setNewSubscriptions(500);
        productActualsViewList.add(view1);

        ProductActualsView view2 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view2.setNewSubscriptions(750);
        productActualsViewList.add(view2);

        ProductActualsView view3 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view3.setNewSubscriptions(1000);
        productActualsViewList.add(view3);

        ProductActualsView view4 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31));
        view4.setNewSubscriptions(1250);
        productActualsViewList.add(view4);
        // Mockito.when(productViewRepository.findOne(productId)).thenReturn(product);
        String productId = "1";
        ProductView product = new ProductView(productId, "Myproduct", "MyCat", "MySubCat", 100, QuantityUnit.GM, null, null, null);
        List<ProductView> allProducts=new ArrayList<ProductView>();
        allProducts.add(product);
        Mockito.when(productViewRepository.findAll()).thenReturn(allProducts);

        Mockito.when(productActualsViewRepository.findByProductVersionId_ProductId(product.getProductId())).thenReturn(productActualsViewList);

        builder.buildForecast(product.getProductId(), 1);

    }
}
