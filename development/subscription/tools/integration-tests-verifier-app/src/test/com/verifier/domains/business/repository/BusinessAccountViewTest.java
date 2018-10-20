package com.verifier.domains.business.repository;

import com.affaince.subscription.date.SysDate;
import com.verifier.Application;
import com.verifier.domains.business.view.BusinessAccountView;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class BusinessAccountViewTest {
    @Autowired
    BusinessBusinessAccountViewRepository businessAccountViewRepository;
    @Before
    public void init () {
        BusinessAccountView businessAccountView = new BusinessAccountView("1","2", new LocalDate( SysDate.now().getYear(),1,1),new LocalDate( SysDate.now().getYear(),12,31), SysDate.now());
        businessAccountViewRepository.save(businessAccountView);
    }
    @Test
    public void testGreaterThanEqualAPI(){
        List<BusinessAccountView> accounts = businessAccountViewRepository.findByEndDateGreaterThanEqual(SysDate.now());
        assertThat(accounts.size(), is (1));
        assertThat(accounts.get(0).getBusinessAccountId(), is ("1"));
    }

    @Test
    public void testEndDateAfterCurrentDate(){
        List<BusinessAccountView> accounts = businessAccountViewRepository.findByEndDateAfter(SysDate.now());
        assertThat(accounts.size(), is (1));
        assertThat(accounts.get(0).getBusinessAccountId(), is ("1"));
    }

} 
