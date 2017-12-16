package com.affaince.subscription.business.event;

import com.affaince.subscription.business.vo.OperatingExpenseVO;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 13-01-2017.
 */
public class ProvisionForCommonExpensesRegisteredEvent {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<OperatingExpenseVO> expenses;
    private double provisionForCommonExpenses;
    public ProvisionForCommonExpensesRegisteredEvent(Integer id,LocalDate startDate, LocalDate endDate,List<OperatingExpenseVO> expenses, double provisionForCommonExpenses) {
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.expenses=expenses;
        this.provisionForCommonExpenses=provisionForCommonExpenses;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getProvisionForCommonExpenses() {
        return provisionForCommonExpenses;
    }

    public List<OperatingExpenseVO> getExpenses() {
        return expenses;
    }
}
