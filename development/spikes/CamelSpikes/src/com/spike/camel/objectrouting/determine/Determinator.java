package com.spike.camel.objectrouting.determine;

import com.spike.camel.objectrouting.bean.MyBean;

/**
 * Created by mandark on 03-04-2016.
 */
public class Determinator {
    public MyBean determineType(MyBean myBean) {
        if(myBean.getBeanId().equals("bean1")||myBean.getBeanId().equals("bean3") || myBean.getBeanId().equals("bean5")) {
            myBean.setProcessorType(ProcessorType.PROCESSOR1);
        }else{
            myBean.setProcessorType(ProcessorType.PROCESSOR2);
        }
        return myBean;
    }
}
