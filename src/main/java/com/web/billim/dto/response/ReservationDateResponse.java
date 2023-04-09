package com.web.billim.dto.response;


import com.web.billim.domain.ImageProduct;
import com.web.billim.domain.Order;
import com.web.billim.domain.Product;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
public class ReservationDateResponse {

    private  List<Timestamp> resDate;

//    private Timestamp startAt;
//    private Timestamp endAt;


//    public static ReservationDateResponse of(Order order){
//        return null;
//    }

}
