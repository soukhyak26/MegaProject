package com.affaince.subscription.common.service.interpolate;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mandar on 3/1/2017.
 */
@Ignore
public class SimpleInterpolatorTest {


    @Test
    public void testSimpleInterpolation(){

        double [] x = new double[] {0,30};
        double [] y= new double[] {0,2000};
        SimpleLinearInterpolator interpolator = new SimpleLinearInterpolator();
        double[] result = interpolator.interpolate(x,y);
         for(int i=0;i< result.length;i++){
             System.out.println("i:" + i + "=" + result[i]);
         }

    }
}
