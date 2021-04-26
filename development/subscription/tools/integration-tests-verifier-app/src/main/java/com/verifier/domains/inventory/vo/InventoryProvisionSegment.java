package com.verifier.domains.inventory.vo;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.repository.IdGenerator;
import com.verifier.domains.product.view.TaggedPriceVersionsView;
import org.joda.time.LocalDate;

import java.util.*;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class InventoryProvisionSegment implements Comparable<InventoryProvisionSegment>{
    private LocalDate startDate;
    private LocalDate endDate;
    private long demand;
    private long provision;
    private Set<InventoryInOutRecord> inventoryInOutRecords;

    public InventoryProvisionSegment() {
    }

    public InventoryProvisionSegment(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.inventoryInOutRecords = new TreeSet<>();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getDemand() {
        return demand;
    }

    public long getProvision() {
        return provision;
    }

    public void setDemand(long demand) {
        this.demand = demand;
    }

    public void setProvision(long provision) {
        this.provision = provision;
    }

    public void addDemand(long incrementalDemand) {
        this.demand += incrementalDemand;
    }

    public Set<InventoryInOutRecord> getInventoryInOutRecords() {
        return inventoryInOutRecords;
    }

    public void setInventoryInOutRecords(Set<InventoryInOutRecord> inventoryInOutRecords) {
        this.inventoryInOutRecords = inventoryInOutRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryProvisionSegment that = (InventoryProvisionSegment) o;
        return Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public int compareTo(InventoryProvisionSegment o) {
        return this.startDate.compareTo(o.startDate) & this.endDate.compareTo(o.endDate);
    }

}
