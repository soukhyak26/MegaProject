package com.affaince.subscription.product.command;

import com.affaince.subscription.common.vo.EntityMetadata;
import org.joda.time.LocalDate;


/**
 * Created by mandar on 10/1/2017.
 */
public class DetectChangeInProductTrendCommand {
    private Integer productAnalyserId;
    private String productId;
    private EntityMetadata entityMetadata;
    private LocalDate forecastDate;

    public DetectChangeInProductTrendCommand(Integer productAnalyserId,String productId, EntityMetadata entityMetadata, LocalDate forecastDate) {
        this.productAnalyserId=productAnalyserId;
        this.productId = productId;
        this.entityMetadata = entityMetadata;
        this.forecastDate = forecastDate;
    }

    public Integer getProductAnalyserId() {
        return productAnalyserId;
    }

    public String getProductId() {
        return productId;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }
}
