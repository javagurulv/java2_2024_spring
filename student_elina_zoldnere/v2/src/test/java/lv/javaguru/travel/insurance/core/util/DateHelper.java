package lv.javaguru.travel.insurance.core.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class DateHelper {

    public Date newDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy.MM.dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}