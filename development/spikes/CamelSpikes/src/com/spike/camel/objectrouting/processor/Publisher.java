package com.spike.camel.objectrouting.processor;

import com.spike.camel.objectrouting.bean.MyBean;

/**
 * Created by mandark on 03-04-2016.
 */
public class Publisher {
    public MyBean receiveMyBean(MyBean myBean){
        System.out.println("$$$$$$$$$$$$received myBean in Publisher:"+ myBean);
        return myBean;
    }
}
