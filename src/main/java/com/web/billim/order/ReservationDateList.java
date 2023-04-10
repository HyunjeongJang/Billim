package com.web.billim.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDateList {

    public static List<LocalDate> changeDate(LocalDate start, LocalDate end){
        List<LocalDate> result = new ArrayList<>();
        do {
            result.add(start);
            start = start.plusDays(1);
        } while(start.isEqual(end) || start.isBefore(end)); // !start.isAfter(end)
        return result;
    }
}
