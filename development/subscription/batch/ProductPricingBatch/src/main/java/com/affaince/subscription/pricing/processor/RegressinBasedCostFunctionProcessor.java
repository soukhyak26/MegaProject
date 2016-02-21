package com.affaince.subscription.pricing.processor;

import com.affaince.subscription.common.factory.TrainingDataBuilder;
import com.affaince.subscription.common.vo.TrainingData;
import com.affaince.subscription.pricing.vo.Product;

/**
 * Created by mandark on 21-02-2016.
 */
public class RegressinBasedCostFunctionProcessor implements CostProcessor {
    public void determineCostOfAProduct(Product product){
        //Cost of a product depends on
        //purchase price( fixed cost)
        //fixed operating expense per Product( rent,electiricty bill etc) as a fixed cost
        //variable operating expenses such as shipping price per product
        //TrainingData trainingData = TrainingDataBuilder.buildTrainingData()

    }
}
