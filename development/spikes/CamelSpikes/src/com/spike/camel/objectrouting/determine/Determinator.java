package com.spike.camel.objectrouting.determine;

import com.spike.camel.objectrouting.bean.MyBean;

/**
 * Created by mandark on 03-04-2016.
 */
public class Determinator {
    public MyBean determineType(MyBean myBean) {
        myBean.setProcessorType(ProcessorType.PROCESSOR1);
        return myBean;
    }
}
