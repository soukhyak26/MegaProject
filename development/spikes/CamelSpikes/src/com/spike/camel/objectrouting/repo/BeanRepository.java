package com.spike.camel.objectrouting.repo;

import com.spike.camel.objectrouting.bean.MyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 03-04-2016.
 */
public class BeanRepository {

    List<MyBean> findAll(){
        System.out.println("$$$$in BeanRepository$$$$$$");
        List<MyBean> myBeans= new ArrayList<MyBean>();
        MyBean bean1=new MyBean("bean1",1.0);
        MyBean bean2=new MyBean("bean2",2.0);
        MyBean bean3=new MyBean("bean3",3.0);
        MyBean bean4=new MyBean("bean4",4.0);
        MyBean bean5=new MyBean("bean5",5.0);
        MyBean bean6=new MyBean("bean6",6.0);
        myBeans.add(bean1);
        myBeans.add(bean2);
        myBeans.add(bean3);
        myBeans.add(bean4);
        myBeans.add(bean5);
        myBeans.add(bean6);
        return myBeans;
    }
}
