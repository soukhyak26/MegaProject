package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.vo.ForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/21/2017.
 */
@Document(collection="ProductForecastTrendView")
public class ProductForecastTrendView {
    @Id
    private ForecastVersionId forecastVersionId;
    private LocalDate trendSettingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private long changeInTotalSusbcriptionCount;

    public ProductForecastTrendView(String productId, LocalDate trendSettingDate, LocalDate startDate, LocalDate endDate, long changeInTotalSusbcriptionCount) {
        this.forecastVersionId = new ForecastVersionId(productId,startDate,trendSettingDate);
        this.trendSettingDate = trendSettingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.changeInTotalSusbcriptionCount = changeInTotalSusbcriptionCount;
    }


    public ForecastVersionId getForecastVersionId() {
        return forecastVersionId;
    }

    public String getProductId() {
        return this.forecastVersionId.getProductId();
    }

    public LocalDate getTrendSettingDate() {
        return trendSettingDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getChangeInTotalSusbcriptionCount() {
        return changeInTotalSusbcriptionCount;
    }
}
