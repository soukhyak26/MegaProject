package com.affaince.subscription.pricing.vo;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mandark on 04-03-2016.
 */
public class PriceDeterminationCriteria<T1 extends List<CrudRepository>, T2 extends List<? extends Serializable>> {
    private final T1 dataRepositories;
    private final T2 listOfCriteriaElements;

    public PriceDeterminationCriteria(T1 dataRepositories, T2 listOfCriteriaElements) {
        this.dataRepositories = dataRepositories;
        this.listOfCriteriaElements = listOfCriteriaElements;
    }

    public T1 getDataRepositories() {
        return this.dataRepositories;
    }

    public T2 getListOfCriteriaElements() {
        return this.listOfCriteriaElements;
    }
}
