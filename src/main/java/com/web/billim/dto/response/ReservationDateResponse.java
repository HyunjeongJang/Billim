package com.web.billim.dto.response;


import com.web.billim.domain.Order;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class ReservationDateResponse {

    private  List<Timestamp> resDate;


    public static ReservationDateResponse of(Order order){


    }

}
