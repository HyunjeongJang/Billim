package com.web.billim.order.dto.response;


import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
@Builder
public class ReservationDateResponse {

    private  List<Timestamp> resDate;

//    private Timestamp startAt;
//    private Timestamp endAt;


//    public static ReservationDateResponse of(ProductOrder productOrder){
//        return null;
//    }

}
