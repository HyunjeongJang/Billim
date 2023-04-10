package com.web.billim.order.service;

import com.web.billim.order.ReservationDateList;
import com.web.billim.order.domain.ProductOrder;
import com.web.billim.order.repository.OrderRepository;
import com.web.billim.product.domain.Product;
import com.web.billim.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<LocalDate> reservationDate(Product product) {
        List<ProductOrder> orderList = orderRepository.findAllByProductAndEndAtAfter(product,LocalDate.now());

        return orderList.stream()
                .flatMap(order -> ReservationDateList.changeDate(order.getStartAt(),order.getEndAt()).stream())
                .filter(date -> !date.isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
