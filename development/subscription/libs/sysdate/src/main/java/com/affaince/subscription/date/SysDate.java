package com.affaince.subscription.date;

import com.affaince.subscription.repository.SysDateViewRepository;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
@Component
public class SysDate {

    private LocalDate localDate;
    private SysDate sysDate;
    @Value("${subscription.productionMode}")
    private boolean productionMode;
    @Autowired
    private SysDateViewRepository sysDateViewRepository;

    public SysDate() {
    }

    public SysDate(LocalDate currentDate) {
        sysDateViewRepository.deleteAll();
        SysDateView sysDateView = new SysDateView(currentDate);
        sysDateViewRepository.save(sysDateView);
    }

    public SysDate(String date, DateTimeFormatter dateTimeFormatter) {
        this.sysDate = new SysDate(LocalDate.parse(date, dateTimeFormatter));
    }

    public void setCurrentDate(LocalDate currentDate) {
        sysDateViewRepository.deleteAll();
        SysDateView sysDateView = new SysDateView(currentDate);
        sysDateViewRepository.save(sysDateView);
    }

    public LocalDate now() {
        if (productionMode) {
            return LocalDate.now();
        }
        return sysDateViewRepository.findAll().iterator().next().getCurrentDate();
    }

    public LocalDate minusDays(int days) {
        return localDate.minusDays(days);
    }

    public LocalDate plusDays(int days) {
        return localDate.plusDays(days);
    }
}
