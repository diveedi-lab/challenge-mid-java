package com.diveedi.storage.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DateUtils {
    public static LocalDate date8toDate(BigDecimal date) {
        long dateLong = date.longValueExact();

        int year = (int) (dateLong / 10000);
        int month = (int) ((dateLong % 10000) / 100);
        int day = (int) (dateLong % 100);

        return LocalDate.of(year, month, day);
    }
}
