package com.verifier.domains.business.vo;

import com.verifier.domains.business.exception.NotEnoughProvisionException;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by mandar on 11/22/2017.
 */
public class ProvisionCalendar {
    private Set<ProvisionSegment> installmentCalendar;
    private LocalDate startDate;
    private LocalDate endDate;
    public ProvisionCalendar(){
        this.installmentCalendar=new TreeSet<>();
    }
    public ProvisionCalendar(LocalDate startDate, LocalDate endDate) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.installmentCalendar=new TreeSet<>();
    }

    public ProvisionCalendar(LocalDate startDate, LocalDate endDate, Set<ProvisionSegment> installmentCalendar) {
        this.startDate=startDate;
        this.endDate=endDate;
        this.installmentCalendar=installmentCalendar;
    }

    private Set<ProvisionSegment> buildInstallationCalendar(LocalDate startDate, LocalDate endDate){
        installmentCalendar = new TreeSet<>();
        int startMonth = startDate.getMonthOfYear();
        YearMonth month = new YearMonth(startDate.getYear(), startDate.getMonthOfYear());
        int endMonth = endDate.getMonthOfYear();
        int period = endMonth - startMonth + 1;
        ProvisionSegment totalPeriodInstallment = new ProvisionSegment(startDate, endDate);
        installmentCalendar.add(totalPeriodInstallment);
        for (int i = 0; i < period; i++) {
            ProvisionSegment installment = new ProvisionSegment(startDate.dayOfMonth().withMinimumValue(), startDate.dayOfMonth().withMaximumValue());
            installmentCalendar.add(installment);
            startDate = startDate.plusMonths(1);
        }
        return installmentCalendar;
    }


    public void addProvisionForAPeriod(double provisionAmount, LocalDate transactionDate){
        installmentCalendar.stream().filter(ic -> (ic.getStartDate().isBefore(transactionDate) ||ic.getStartDate().equals(transactionDate)) && ic.getEndDate().isAfter(transactionDate)).collect(Collectors.toList()).get(0).credit(provisionAmount);
    }
    public void addProvisionForAPeriod(double provisionAmount, LocalDate startDate, LocalDate endDate) {
        installmentCalendar.stream().filter(ic -> ic.getStartDate().equals(startDate) && ic.getEndDate().equals(endDate)).collect(Collectors.toList()).get(0).credit(provisionAmount);
    }

    public void deductProvisionForAPeriod(double provisionAmount, LocalDate transactionDate) throws NotEnoughProvisionException {
        List<LocalDate> periodBoundaryDates = obtainPeriodBoundaryDates(transactionDate);
        deductProvisionForAPeriod(provisionAmount,periodBoundaryDates.get(0),periodBoundaryDates.get(1));
    }

    public void deductProvisionForAPeriod(double provisionAmount, LocalDate startDate, LocalDate endDate) throws NotEnoughProvisionException{
       ProvisionSegment segment= installmentCalendar.stream().filter(ic -> ic.getStartDate().equals(startDate) && ic.getEndDate().equals(endDate)).collect(Collectors.toList()).get(0);
        if(segment.getProvisionedAmount()>=provisionAmount) {
            segment.debit(provisionAmount);
        }else{
            double pendingProvisionAmount=shiftFutureProvisionsForCurrentPeriod(provisionAmount-segment.getProvisionedAmount(),startDate,endDate);

            if( pendingProvisionAmount==0){
                segment.debit(provisionAmount);
            }else{
                throw NotEnoughProvisionException.build();
            }
        }
    }

    public List<LocalDate> obtainPeriodBoundaryDates(LocalDate transactionDate){
        ProvisionSegment provisionForTargetPeriod = installmentCalendar.stream().filter(ic -> (ic.getStartDate().isBefore(transactionDate) ||ic.getStartDate().equals(transactionDate)) && ic.getEndDate().isAfter(transactionDate)).collect(Collectors.toList()).get(0);
        List<LocalDate> periodBoundaryDates=new ArrayList<>();
        periodBoundaryDates.add(provisionForTargetPeriod.getStartDate());
        periodBoundaryDates.add(provisionForTargetPeriod.getEndDate());
        return periodBoundaryDates;
    }


    public double shiftFutureProvisionsForCurrentPeriod(double requiredProvisionAmount, LocalDate startDate, LocalDate endDate) {
        ProvisionSegment provisionForTargetPeriod = installmentCalendar.stream().filter(ic -> ic.getStartDate().equals(startDate) && ic.getEndDate().equals(endDate)).collect(Collectors.toList()).get(0);
        for (ProvisionSegment provision : installmentCalendar) {
            if (provision.getStartDate().equals(provisionForTargetPeriod.getEndDate()) || provision.getStartDate().isAfter(provisionForTargetPeriod.getEndDate())) {
                if (requiredProvisionAmount > 0) {
                    if (provision.getProvisionedAmount() >= requiredProvisionAmount) {
                        provision.debit(requiredProvisionAmount);
                        provisionForTargetPeriod.credit(requiredProvisionAmount);
                        requiredProvisionAmount = 0;
                    } else {
                        provision.debit(provision.getProvisionedAmount());
                        provisionForTargetPeriod.credit(provision.getProvisionedAmount());
                        requiredProvisionAmount -= provision.getProvisionedAmount();
                    }
                } else if (requiredProvisionAmount == 0) {
                    break;
                }
            }
        }
        return requiredProvisionAmount;
    }

    public Set<ProvisionSegment> getInstallmentCalendar() {
        return installmentCalendar;
    }

    public void setInstallmentCalendar(Set<ProvisionSegment> installmentCalendar) {
        this.installmentCalendar = installmentCalendar;
    }

    public void reviseExpectedProvisionForAPeriod(double expectedProvisionAmount, LocalDate startDate, LocalDate endDate){
        installmentCalendar.stream().filter(ic -> ic.getStartDate().equals(startDate) && ic.getEndDate().equals(endDate)).collect(Collectors.toList()).get(0).setExpectedAmount(expectedProvisionAmount);
    }
    //TODO:distribute available provision equally... this API should not be used.
    //TODO:ONce the forecasting is available the budget distribution will not be required.
    public Set<ProvisionSegment> distributeProvisionAcrossYear(double provisionAmount, LocalDate startDate, LocalDate endDate){
        Set<ProvisionSegment> distributionCalender=buildInstallationCalendar(startDate,endDate);
        int calendarSize=distributionCalender.size();
        double amountPerPeriod=provisionAmount/calendarSize;

        distributionCalender.stream().forEach(ic->{
            ic.setExpectedAmount(amountPerPeriod);
            ic.credit(amountPerPeriod);
        });
        return distributionCalender;
    }

}
