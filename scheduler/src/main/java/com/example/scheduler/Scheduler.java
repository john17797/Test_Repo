package com.example.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Component
public class Scheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class.getName());

    @Value("${automaticDtc.tat.code}")
    private List<String> TAT_CODE;

    @Value("${automaticDtc.release.enable}")
    private boolean AUTOMATIC_DTC_RELEASE_ENABLE;

    private Double aDouble = 17.18;

    @Scheduled(cron = "${automaticDtc.cron.expression}", zone = "${automaticDtc.cron.timezone}")
    public void schedulerDemoMethod() {
        if (AUTOMATIC_DTC_RELEASE_ENABLE) {
            Date date = new Date();
            LOGGER.info("Scheduler starts at {}", date);

            ValueGroup valueGroup = new ValueGroup();
            valueGroup.setDecimalValue(
                            BigDecimal.valueOf(aDouble)
                                    .setScale(6, RoundingMode.HALF_UP)
                                    .doubleValue());
        }
    }

    class ValueGroup{
        private Double decimalValue;

        public Double getDecimalValue() {
            return decimalValue;
        }

        public void setDecimalValue(Double decimalValue) {
            this.decimalValue = decimalValue;
        }
    }
}
