package com.spike.camel.objectrouting.processor;

import com.spike.camel.objectrouting.bean.MyBean;

/**
 * Created by mandark on 03-04-2016.
 */
public class Processor1 {

    public MyBean receiveBean(MyBean myBean){
        System.out.println("$$$$$$$$$$$$$$$$$$$$My Bean received in processor1:"+ myBean);
        return myBean;
    }
}
