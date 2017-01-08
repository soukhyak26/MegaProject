package com.affaince.subscription.product.web.controller;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.Application;
import com.affaince.subscription.product.query.repository.*;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.services.forecast.DemandForecasterChain;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mandar on 05-07-2016.
 */
//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class ForecastControllerTest {
    @Mock
    private static ProductViewRepository productViewRepository;
    @Mock
    private static ProductActualsViewRepository productActualsViewRepository;
    @Mock
    private static ProductForecastViewRepository productForecastViewRepository;
    final RestTemplate template = new RestTemplate();
    @InjectMocks
    @Autowired
    private ForecastController forecastController;
    @InjectMocks
    @Autowired
    private DemandForecasterChain chain;
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        MockitoAnnotations.initMocks(this);
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //chain = chain.buildForecasterChain(productForecastMetricsViewRepository,ProductActualsViewRepository);
        this.mockMvc=MockMvcBuilders.standaloneSetup(forecastController).build();
    }

    @Test
    public void testFindAllProducts() throws Exception{

        List<ProductActualsView> ProductActualsViewList;
        ProductActualsViewList = new ArrayList<>();
        long totalSubscriptions=500;

        ProductActualsView view1 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),500,0,500);
        ProductActualsViewList.add(view1);

        totalSubscriptions=totalSubscriptions+750;
        ProductActualsView view2 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),750,0,totalSubscriptions);
        ProductActualsViewList.add(view2);

        totalSubscriptions=totalSubscriptions+1000;
        ProductActualsView view3 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),1000,0,totalSubscriptions);
        ProductActualsViewList.add(view3);

        totalSubscriptions=totalSubscriptions+1250;
        ProductActualsView view4 = new ProductActualsView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),1250,0,totalSubscriptions);
        ProductActualsViewList.add(view4);

        ProductForecastView forecastView = new ProductForecastView(new ProductVersionId("1", new LocalDate(2016, 1, 1)), new LocalDate(9999, 12, 31),1250,0,1250);
        List<ProductForecastView> forecasts=new ArrayList<>();
        forecasts.add(forecastView);

        String productId = "1";
        ProductView product = new ProductView(productId, "Myproduct", "MyCat", "MySubCat", 100, QuantityUnit.GM, null, null, null);
        List<ProductView> allProducts=new ArrayList<ProductView>();
        allProducts.add(product);

        Mockito.when(productViewRepository.findAll()).thenReturn(allProducts);
        Mockito.when(productForecastViewRepository.findByProductVersionId_ProductId(product.getProductId(),new Sort(Sort.Direction.DESC, "productVersionId.fromDate"))).thenReturn(forecasts);
        Mockito.when(productActualsViewRepository.findByProductVersionId_ProductId(product.getProductId())).thenReturn(ProductActualsViewList);

        MvcResult result= this.mockMvc.perform(get("/forecast/findall"))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn();
        String json=result.getResponse().getContentAsString();
        ObjectMapper mapper=new ObjectMapper();
        List<String> productIds=mapper.readValue(json, new TypeReference<ArrayList<String>>() {});
        for(String tempProductId: productIds){
            System.out.println("$$$$forecastController:: productId: "+ tempProductId);
        }

    }

    public WebApplicationContext getWac() {
        return wac;
    }
}
