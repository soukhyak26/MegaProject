package com.affaince.subscription.pricing.vo;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mandark on 04-03-2016.
 */
public class PriceDeterminationCriteria<T2 extends List<? extends Serializable>> {
    //private final T1 dataRepositories;
    private final T2 listOfCriteriaElements;

    public PriceDeterminationCriteria(T2 listOfCriteriaElements) {
       // this.dataRepositories = dataRepositories;
        this.listOfCriteriaElements = listOfCriteriaElements;
    }


    public T2 getListOfCriteriaElements() {
        return this.listOfCriteriaElements;
    }
}
