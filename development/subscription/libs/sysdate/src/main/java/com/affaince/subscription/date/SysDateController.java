package com.affaince.subscription.date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;

/**
 * Created by rahul on 25/5/17.
 */
@RestController
@RequestMapping(value = "sysdate")
public class SysDateController {


    @RequestMapping(method = RequestMethod.PUT)
    @Consumes("application/json")
    public ResponseEntity<Object> setCurrentDateAndTime (@RequestBody SysDateTimeRequest request){
        SysDate.setCurrentDate(request.getSysDate());
        SysDateTime.setCurrentDateTime(request.getSysDateTime());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
