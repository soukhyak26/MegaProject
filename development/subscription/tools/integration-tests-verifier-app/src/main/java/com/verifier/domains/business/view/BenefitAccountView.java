package com.verifier.domains.business.view;

import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 31-12-2016.
 */
@Document(collection = "BenefitAccountView")
public class BenefitAccountView extends ProvisionAccountView{
    public BenefitAccountView(){}
    public BenefitAccountView(String businessAccountId, double provisionAmount, LocalDate startDate, LocalDate endDate) {
        super(businessAccountId, provisionAmount, startDate, endDate);
    }

}
