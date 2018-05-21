package com.verifier.domains.business.view;

import com.verifier.domains.business.vo.ProvisionSegment;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "CommonExpenseAccountView")
public class CommonExpenseAccountView extends ProvisionAccountView{

    public CommonExpenseAccountView(){super();}
    public CommonExpenseAccountView(String businessAccountId, double provisionAmount, LocalDate startDate, LocalDate endDate) {
        super(businessAccountId, provisionAmount, startDate, endDate);
    }

    public CommonExpenseAccountView(String businessAccountId, double provisionAmount, Set<ProvisionSegment> distributionCalendar, LocalDate startDate, LocalDate endDate) {
        super(businessAccountId, provisionAmount, distributionCalendar, startDate, endDate);
    }

}
