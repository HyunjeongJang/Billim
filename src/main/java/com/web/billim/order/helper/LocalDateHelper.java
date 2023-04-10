package com.web.billim.order.helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocalDateHelper {

    public static List<LocalDate> generateLocalDateInterval(LocalDate start, LocalDate end) {
        List<LocalDate> result = new ArrayList<>();
        do {
            result.add(start);
            start = start.plusDays(1);
        } while(start.isEqual(end) || start.isBefore(end)); // !start.isAfter(end)
        return result;
    }

}

