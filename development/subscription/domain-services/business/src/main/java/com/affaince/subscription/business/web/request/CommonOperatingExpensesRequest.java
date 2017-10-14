package com.affaince.subscription.business.web.request;


import com.affaince.subscription.business.vo.OperatingExpenseVO;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class CommonOperatingExpensesRequest {

    private List<OperatingExpenseVO> expenses;
    private LocalDate startDate;
    private LocalDate endDate;

    public List<OperatingExpenseVO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<OperatingExpenseVO> expenses) {
        this.expenses = expenses;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
