package com.verifier.domains.product.vo;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class InventoryProvisionCalendar {
    private long demand;
    private long provision;
    private Set<InventoryProvisionSegment> demandVsSupplyCalendar;


    public InventoryProvisionCalendar() {
        this.demandVsSupplyCalendar = new TreeSet<>();
        //this.inventoryInOutRecords = new TreeSet<>();
    }


    public long getDemand() {
        return demand;
    }

    public long getProvision() {
        return provision;
    }

    public Set<InventoryProvisionSegment> getDemandVsSupplyCalendar() {
        return demandVsSupplyCalendar;
    }

    public InventoryProvisionSegment obtainMatchingSegment(LocalDate startDate, LocalDate endDate) {
        Set<InventoryProvisionSegment> localInstallationCalendar = new TreeSet<>();
        localInstallationCalendar.addAll(this.demandVsSupplyCalendar);
        if (!localInstallationCalendar.isEmpty()) {
            Set<InventoryProvisionSegment> matchingSegements = localInstallationCalendar.stream().filter(ic -> ic.getStartDate().equals(startDate) && ic.getEndDate().equals(endDate)).collect(Collectors.toSet());
            if (null != matchingSegements && !matchingSegements.isEmpty()) {
                return matchingSegements.iterator().next();
            } else {
                InventoryProvisionSegment newSegment = new InventoryProvisionSegment(startDate, endDate);
                return newSegment;
            }
        } else {
            InventoryProvisionSegment newSegment = new InventoryProvisionSegment(startDate, endDate);
            return newSegment;
        }
    }

    private InventoryProvisionSegment findNextProvisionSegment(InventoryProvisionSegment inventoryProvisionSegment){
        for (InventoryProvisionSegment provision : demandVsSupplyCalendar) {
            InventoryProvisionSegment nextProvisionSegment= provision;
            if (nextProvisionSegment.getStartDate().equals(inventoryProvisionSegment.getEndDate().plusDays(1))) {
                return nextProvisionSegment;
            }
        }
        return null;
    }
    private InventoryProvisionSegment findEarlierProvisionSegment(InventoryProvisionSegment inventoryProvisionSegment){
        for (InventoryProvisionSegment provision : demandVsSupplyCalendar) {
            InventoryProvisionSegment earlierProvisionSegment= provision;
            if (earlierProvisionSegment.getEndDate().plusDays(1).equals(inventoryProvisionSegment.getStartDate())) {
                return earlierProvisionSegment;
            }
        }
        return null;
    }

    public void addProvisionSegment(InventoryProvisionSegment matchingProvisionSegment, LocalDate startDate, LocalDate endDate) {
        //TODO: This is expected to replace existing provision segment if any.. need to check
        try {
            if (!this.demandVsSupplyCalendar.isEmpty()) {
                Set<InventoryProvisionSegment> matchingSegements = this.demandVsSupplyCalendar.stream().filter(ic -> ic.getStartDate().equals(matchingProvisionSegment.getStartDate()) && ic.getEndDate().equals(matchingProvisionSegment.getEndDate())).collect(Collectors.toSet());
                if (null != matchingSegements && !matchingSegements.isEmpty()) {
                    InventoryProvisionSegment locatedProvisionSegment = (InventoryProvisionSegment)matchingSegements.toArray()[0];
                    locatedProvisionSegment.setDemand(matchingProvisionSegment.getDemand());
                    locatedProvisionSegment.setProvision(matchingProvisionSegment.getProvision());
                } else {
                    InventoryProvisionSegment newSegment = new InventoryProvisionSegment(startDate, endDate);
                    newSegment.setDemand(matchingProvisionSegment.getDemand());
                    newSegment.setProvision(matchingProvisionSegment.getProvision());
                    this.demandVsSupplyCalendar.add(newSegment);
                }
            } else {
                InventoryProvisionSegment newSegment = new InventoryProvisionSegment(startDate, endDate);
                newSegment.setDemand(matchingProvisionSegment.getDemand());
                newSegment.setProvision(matchingProvisionSegment.getProvision());
                this.demandVsSupplyCalendar.add(newSegment);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
