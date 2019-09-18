package com.verifier.domains.business.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

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
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    public ProvisionCalendar() {
        this.installmentCalendar = new TreeSet<>();
    }

    public ProvisionCalendar(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.installmentCalendar = new TreeSet<>();//buildInstallationCalendar(startDate,endDate);
    }

    public ProvisionCalendar(LocalDate startDate, LocalDate endDate, Set<ProvisionSegment> installmentCalendar) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.installmentCalendar = installmentCalendar;
    }

    private Set<ProvisionSegment> buildInstallationCalendar(LocalDate startDate, LocalDate endDate) {
        Set<ProvisionSegment> newInstallmentCalendar = new TreeSet<>();
        int startMonth = startDate.getMonthOfYear();
        int endMonth = endDate.getMonthOfYear();
        int period = endMonth - startMonth + 1;
/*
        ProvisionSegment totalPeriodInstallment = new ProvisionSegment(startDate, endDate);
        installmentCalendar.add(totalPeriodInstallment);
*/
        for (int i = 0; i < period; i++) {
            ProvisionSegment installment = new ProvisionSegment(startDate.dayOfMonth().withMinimumValue(), startDate.dayOfMonth().withMaximumValue());
            newInstallmentCalendar.add(installment);
            startDate = startDate.plusMonths(1);
        }
        return newInstallmentCalendar;
    }


    /*
        public void addProvisionForAPeriod(double provisionAmount, LocalDate transactionDate){
            installmentCalendar.stream().filter(ic -> (ic.getStartDate().isBefore(transactionDate) ||ic.getStartDate().equals(transactionDate)) && ic.getEndDate().isAfter(transactionDate)).collect(Collectors.toList()).get(0).credit(provisionAmount);
        }
    */
    public ProvisionSegment obtainMatchingSegment(LocalDate startDate, LocalDate endDate) {
        Set<ProvisionSegment> localInstallationCalendar = new TreeSet<>();
        localInstallationCalendar.addAll(this.installmentCalendar);
        if (!localInstallationCalendar.isEmpty()) {
            Set<ProvisionSegment> matchingSegements = localInstallationCalendar.stream().filter(ic -> ic.getStartDate().equals(startDate) && ic.getEndDate().equals(endDate)).collect(Collectors.toSet());
            if (null != matchingSegements && !matchingSegements.isEmpty()) {
                return matchingSegements.iterator().next();
            } else {
                ProvisionSegment newSegment = new ProvisionSegment(startDate, endDate);
                return newSegment;
            }
        } else {
            ProvisionSegment newSegment = new ProvisionSegment(startDate, endDate);
            return newSegment;
        }
    }
/*
    public void addProvisionForAPeriod(double provisionAmount, LocalDate startDate, LocalDate endDate) {
        try {
            if (!localInstallationCalendar.isEmpty()) {
                List<ProvisionSegment> matchingSegements = localInstallationCalendar.stream().filter(ic -> ic.getStartDate().equals(startDate) && ic.getEndDate().equals(endDate)).collect(Collectors.toList());
                if (null != matchingSegements && !matchingSegements.isEmpty()) {
                    matchingSegements.get(0).credit(provisionAmount);
                } else {
                    ProvisionSegment newSegment = new ProvisionSegment(startDate, endDate);
                    newSegment.setExpectedAmount(provisionAmount);
                    newSegment.credit(provisionAmount);
                    this.installmentCalendar.add(newSegment);
                }
            } else {
                System.out.println("$$$This block should be unreachable$$$");
                this.installmentCalendar = new TreeSet<>();
                ProvisionSegment newSegment = new ProvisionSegment(startDate, endDate);
                newSegment.setExpectedAmount(provisionAmount);
                newSegment.credit(provisionAmount);
                this.installmentCalendar.add(newSegment);
            }
        }catch(Exception ex){
            System.out.println("^^^^ProvisionCalendar::addProvisionForAPeriod");
            ex.printStackTrace();
        }
    }
*/
/*
public List<ProvisionSegment> findEligibleProvisionSegmentsForDebit(double provisionAmount, LocalDate startDate, LocalDate endDate){
    ProvisionSegment segment= installmentCalendar.stream().filter(ic -> ic.getStartDate().equals(startDate) && ic.getEndDate().equals(endDate)).collect(Collectors.toList()).get(0);
    List<ProvisionSegment> eligibleSegments = new ArrayList<>();
    if(segment.getProvisionedAmount()>=provisionAmount) {
        eligibleSegments.add(segment);
    }else{
        double pendingProvisionAmount = provisionAmount;
        if(segment.getProvisionedAmount()>0){
            eligibleSegments.add(segment);
            pendingProvisionAmount = provisionAmount - segment.getProvisionedAmount();
        }
        ProvisionSegment nextProvisionSegment = null;
        for (ProvisionSegment provision : installmentCalendar) {
            nextProvisionSegment= provision;
            if (nextProvisionSegment.getStartDate().equals(segment.getEndDate().plusDays(1))) {
                if (pendingProvisionAmount > 0) {
                    eligibleSegments.add(nextProvisionSegment);
                    pendingProvisionAmount = provisionAmount - segment.getProvisionedAmount();
                    segment=nextProvisionSegment;
                }
            }
        }

    }
    return eligibleSegments;
}
*/

/*
    public void deductProvisionForAPeriod(double provisionAmount, LocalDate transactionDate) throws NotEnoughProvisionException {
        List<LocalDate> periodBoundaryDates = obtainPeriodBoundaryDates(transactionDate);
        deductProvisionForAPeriod(provisionAmount,periodBoundaryDates.get(0),periodBoundaryDates.get(1));
    }
*/

/*
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
*/

    public List<LocalDate> obtainPeriodBoundaryDates(LocalDate transactionDate) {
        List<LocalDate> periodBoundaryDates = new ArrayList<>();
        List<ProvisionSegment> matchedSegments = installmentCalendar.stream().filter(ic -> (ic.getStartDate().isBefore(transactionDate) || ic.getStartDate().equals(transactionDate)) && ic.getEndDate().isAfter(transactionDate)).collect(Collectors.toList());
        if (null != matchedSegments && !matchedSegments.isEmpty()) {
            ProvisionSegment provisionForTargetPeriod = matchedSegments.get(0);
            periodBoundaryDates.add(provisionForTargetPeriod.getStartDate());
            periodBoundaryDates.add(provisionForTargetPeriod.getEndDate());
        }else{
            periodBoundaryDates.add(transactionDate.monthOfYear().withMinimumValue());
            periodBoundaryDates.add(transactionDate.monthOfYear().withMaximumValue());
        }
        return periodBoundaryDates;
    }


/*
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
*/

    public Set<ProvisionSegment> getInstallmentCalendar() {
        return installmentCalendar;
    }

    public void setInstallmentCalendar(Set<ProvisionSegment> installmentCalendar) {
        this.installmentCalendar = installmentCalendar;
    }

    public void reviseExpectedProvisionForAPeriod(double expectedProvisionAmount, LocalDate startDate, LocalDate endDate) {
        installmentCalendar.stream().filter(ic -> ic.getStartDate().equals(startDate) && ic.getEndDate().equals(endDate)).collect(Collectors.toList()).get(0).setExpectedAmount(expectedProvisionAmount);
    }

    //TODO:distribute available provision equally... this API should not be used.
    //TODO:ONce the forecasting is available the budget distribution will not be required.
    public void distributeProvisionAcrossYear(double provisionAmount, LocalDate startDate, LocalDate endDate) {
        //Set<ProvisionSegment> distributionCalender=buildInstallationCalendar(startDate,endDate);
        int calendarSize = this.installmentCalendar.size();
        double amountPerPeriod = provisionAmount / calendarSize;

        this.installmentCalendar.stream().forEach(ic -> {
            ic.setExpectedAmount(amountPerPeriod);
            ic.credit(amountPerPeriod);
        });
    }

    public void addProvisionSegment(ProvisionSegment matchingProvisionSegment, LocalDate startDate, LocalDate endDate) {
        //TODO: This is expected to replace existing provision segment if any.. need to check
        try {
            if (!this.installmentCalendar.isEmpty()) {
                Set<ProvisionSegment> matchingSegements = this.installmentCalendar.stream().filter(ic -> ic.getStartDate().equals(matchingProvisionSegment.getStartDate()) && ic.getEndDate().equals(matchingProvisionSegment.getEndDate())).collect(Collectors.toSet());
                if (null != matchingSegements && !matchingSegements.isEmpty()) {
                    ProvisionSegment locatedProvisionSegment = matchingSegements.iterator().next();
                    locatedProvisionSegment.setExpectedAmount(matchingProvisionSegment.getExpectedAmount());
                    locatedProvisionSegment.setProvisionedAmount(matchingProvisionSegment.getProvisionedAmount());
                } else {
                    ProvisionSegment newSegment = new ProvisionSegment(startDate, endDate);
                    newSegment.setExpectedAmount(matchingProvisionSegment.getExpectedAmount());
                    newSegment.setProvisionedAmount(matchingProvisionSegment.getProvisionedAmount());
                    this.installmentCalendar.add(newSegment);
                }
            } else {
                ProvisionSegment newSegment = new ProvisionSegment(startDate, endDate);
                newSegment.setExpectedAmount(matchingProvisionSegment.getExpectedAmount());
                newSegment.setProvisionedAmount(matchingProvisionSegment.getProvisionedAmount());
                this.installmentCalendar.add(newSegment);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}