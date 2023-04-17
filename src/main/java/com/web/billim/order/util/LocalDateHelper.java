package com.web.billim.order.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.web.billim.order.vo.Period;

public class LocalDateHelper {

    public static List<LocalDate> changeDate(LocalDate start, LocalDate end){
        List<LocalDate> result = new ArrayList<>();
        do {
            result.add(start);
            start = start.plusDays(1);
        } while(start.isEqual(end) || start.isBefore(end)); // !start.isAfter(end)
        return result;
    }

    public static Boolean checkDuplicatedPeriod(Period standard, Period parameter) {
        return !(standard.getEndAt().isBefore(parameter.getStartAt()) || standard.getStartAt().isAfter(parameter.getEndAt()));
    }

}
