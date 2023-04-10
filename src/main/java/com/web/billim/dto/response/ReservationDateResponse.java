//package com.web.billim.dto.response;
//
//
//import com.web.billim.domain.Order;
//import lombok.Data;
//import org.springframework.beans.factory.CannotLoadBeanClassException;
//
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//
//@Data
//public class ReservationDateResponse {
//
//
//
//
//    public static List<Date> of(Order order){
//        Date[] dates = new Date[];
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
//        Calendar calendar1  = Calendar.getInstance();
//        Calendar calendar2 = Calendar.getInstance();
//
//        calendar1.setTime(order.getStartAt());
//        calendar2.setTime(order.getEndAt());
//
//        while (order.getStartAt().compareTo(order.getEndAt()) != 1){
//            dates.(calendar1.getTime());
//            calendar1.add(Calendar.DATE, 1);
//        }
//        return list;
//    }
//
//}
