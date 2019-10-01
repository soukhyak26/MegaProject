package com.verifier.domains.business.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.util.Objects;

/**
 * Created by mandar on 10/4/2017.
 */
public class ProvisionSegment implements Comparable<ProvisionSegment> {

   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private double expectedAmount;
    private double provisionedAmount;
    public ProvisionSegment(){}
    public ProvisionSegment(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getExpectedAmount() {
        return expectedAmount;
    }

    public double getProvisionedAmount() {
        return provisionedAmount;
    }

    public void credit(double additionalAmount){
        provisionedAmount +=additionalAmount;
    }
    public void debit(double amountTobeDeducted){
        provisionedAmount -=amountTobeDeducted;
    }

    public void setExpectedAmount(double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public void setProvisionedAmount(double provisionedAmount) {
        this.provisionedAmount = provisionedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvisionSegment that = (ProvisionSegment) o;
        return Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public int compareTo(ProvisionSegment o) {
       return this.startDate.compareTo(o.startDate) & this.endDate.compareTo(o.endDate);
    }
}
