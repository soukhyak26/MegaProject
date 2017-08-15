package com.affaince.subscription.common.deserializer;

import com.affaince.subscription.common.vo.DataFrameVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 8/14/2017.
 */
@ContextConfiguration
public class DataFrameVODeserializerTest {

    @Test
    public void testSerialize() throws IOException {
        DataFrameVO vo1= new DataFrameVO(new LocalDate(2017,1,1),"token1", 20.50);
        DataFrameVO vo2= new DataFrameVO(new LocalDate(2017,1,2),"token2", 30.50);
        DataFrameVO vo3= new DataFrameVO(new LocalDate(2017,1,3),"token3", 40.50);
        DataFrameVO vo4= new DataFrameVO(new LocalDate(2017,1,4),"token4", 50.50);
        DataFrameVO vo5= new DataFrameVO(new LocalDate(2017,1,5),"token5", 60.50);
        List<DataFrameVO> dfList= new ArrayList<>();
        dfList.add(vo1);
        dfList.add(vo2);
        dfList.add(vo3);
        dfList.add(vo4);
        dfList.add(vo5);
        ObjectMapper mapper=new ObjectMapper();
        String serialized =mapper.writeValueAsString(dfList);
        System.out.println(serialized);
        mapper.readValue(serialized,DataFrameVO.class);

    }

}
