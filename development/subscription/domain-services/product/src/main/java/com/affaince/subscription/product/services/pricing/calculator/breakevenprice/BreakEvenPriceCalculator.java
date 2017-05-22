package com.affaince.subscription.product.services.pricing.calculator.breakevenprice;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.services.operatingexpense.OperatingExpenseService;
import com.affaince.subscription.product.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by mandar on 26-06-2016.
 */
//This class is responsible for calculating the break even price of a product per unit given differnt cost headers which contribute to the BE price.
@Component
public class BreakEvenPriceCalculator {

/*
    public double calculateBreakEvenPrice(List<CostHeader> costHeaders) {
        double breakEvenPrice = 0.0;
        for (CostHeader costHeader : costHeaders) {
            if (costHeader.getCostHeaderApplicability() == CostHeaderApplicability.ABSOLUTE) {
                breakEvenPrice += costHeader.getNewValue();
            }
        }
        List<CostHeader> costHeadersWithPercentApplicability = costHeaders.stream().filter(costHeader -> costHeader.getCostHeaderApplicability() == CostHeaderApplicability.PERCENT_OF_TOTAL_COST).collect(Collectors.toList());
        double percentValue = 0.0;
        for (CostHeader costHeaderWithPercentApplicability : costHeadersWithPercentApplicability) {
            percentValue += costHeaderWithPercentApplicability.getNewValue();
        }
        breakEvenPrice = breakEvenPrice + (breakEvenPrice * percentValue);
        return breakEvenPrice;
    }
*/

    public PriceTaggedWithProduct calculateBreakEvenPrice(EnumSet<CostHeaderType> applicableCostHeaderTypes, List<CostHeader> changedCostHeaders, PriceTaggedWithProduct activeTaggedPriceVersion) {
        List<CostHeader> costHeaders = createCostHeaders(applicableCostHeaderTypes, changedCostHeaders, activeTaggedPriceVersion);
        double breakEvenPrice = 0.0;
        for (CostHeader costHeader : costHeaders) {
            if (costHeader.getCostHeaderApplicability() == CostHeaderApplicability.ABSOLUTE) {
                breakEvenPrice += costHeader.getNewValue();
            }
            if (costHeader.getCostHeaderApplicability() == CostHeaderApplicability.PERCENT_OF_TOTAL_COST) {
                breakEvenPrice = breakEvenPrice + (breakEvenPrice * costHeader.getNewValue());
            }
        }
        activeTaggedPriceVersion.setBreakEvenPrice(breakEvenPrice);
        return activeTaggedPriceVersion;
    }

    private List<CostHeader> createCostHeaders(EnumSet<CostHeaderType> applicableCostHeaderTypes, List<CostHeader> changedCostHeaders, PriceTaggedWithProduct activeTaggedPriceVersion) {
        Map<PriceTaggedWithProduct, List<CostHeader>> costHeadersAssociatedWithTaggedPrice = new HashMap<>();
        //PriceTaggedWithProduct latestTaggedPriceVersion = getLatestTaggedPriceVersion();
        // we will need to calculate revised break-even price for active tagged price versions as differnt tagged price versions are embedded in different price buckets
        List<CostHeader> costHeaders = new ArrayList<>(applicableCostHeaderTypes.size());
        for (CostHeaderType costHeaderType : applicableCostHeaderTypes) {
            if (costHeaderType == CostHeaderType.PURCHASE_PRICE_PER_UNIT) {
                CostHeader purchasePriceHeader = findChangedCostHeaderByType(changedCostHeaders, CostHeaderType.PURCHASE_PRICE_PER_UNIT);
                if (null == purchasePriceHeader) {
                    purchasePriceHeader = new CostHeader(costHeaderType, "purchase price per unit", 0, activeTaggedPriceVersion.getPurchasePricePerUnit(), CostHeaderApplicability.ABSOLUTE);
                }
                costHeaders.add(purchasePriceHeader);
            }
            if (costHeaderType == CostHeaderType.FIXED_EXPENSE_PER_UNIT) {
                CostHeader fixedExpensePriceHeader = findChangedCostHeaderByType(changedCostHeaders, CostHeaderType.FIXED_EXPENSE_PER_UNIT);
                if (null != fixedExpensePriceHeader) {
                    fixedExpensePriceHeader.setNewValue(fixedExpensePriceHeader.getNewValue() - fixedExpensePriceHeader.getOldValue());
                    costHeaders.add(fixedExpensePriceHeader);
                }
            }
            if (costHeaderType == CostHeaderType.VARIABLE_EXPENSE_PER_UNIT) {
                CostHeader variableExpensePriceHeader = findChangedCostHeaderByType(changedCostHeaders, CostHeaderType.VARIABLE_EXPENSE_PER_UNIT);
                if (null != variableExpensePriceHeader) {
                    variableExpensePriceHeader.setNewValue(variableExpensePriceHeader.getNewValue() - variableExpensePriceHeader.getOldValue());
                    costHeaders.add(variableExpensePriceHeader);
                }
            }
            if (costHeaderType == CostHeaderType.TAX_PER_UNIT) {
                CostHeader taxCostHeader = findChangedCostHeaderByType(changedCostHeaders, CostHeaderType.TAX_PER_UNIT);
                if (null != taxCostHeader) {
                    taxCostHeader.setNewValue(taxCostHeader.getNewValue() - taxCostHeader.getOldValue());
                    costHeaders.add(taxCostHeader);
                }
            }
            if (costHeaderType == CostHeaderType.MERCHANT_MARGIN_PER_UNIT) {
                CostHeader merchantMarginCostHeader = findChangedCostHeaderByType(changedCostHeaders, CostHeaderType.MERCHANT_MARGIN_PER_UNIT);
                if (null != merchantMarginCostHeader) {
                    merchantMarginCostHeader.setNewValue(merchantMarginCostHeader.getNewValue() - merchantMarginCostHeader.getOldValue());
                    costHeaders.add(merchantMarginCostHeader);
                }
            }

            if (costHeaderType == CostHeaderType.OTHERS_PER_UNIT) {
                CostHeader othersCostHeader = findChangedCostHeaderByType(changedCostHeaders, CostHeaderType.OTHERS_PER_UNIT);
                if (null != othersCostHeader) {
                    othersCostHeader.setNewValue(othersCostHeader.getNewValue() - othersCostHeader.getOldValue());
                    costHeaders.add(othersCostHeader);
                }
            }
        }
        return costHeaders;
    }

    private CostHeader findChangedCostHeaderByType(List<CostHeader> changedCostHeaders, CostHeaderType costHeaderType) {
        for (CostHeader changedCostHeader : changedCostHeaders) {
            if (changedCostHeader.getCostHeaderType() == costHeaderType) {
                return changedCostHeader;
            }
        }
        return null;
    }
}
