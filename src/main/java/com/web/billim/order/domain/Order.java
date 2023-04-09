package com.web.billim.order.domain;

import com.web.billim.common.domain.JpaEntity;
import com.web.billim.member.domain.Member;
import com.web.billim.product.domain.Product;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "order")
@Builder
@Getter
public class Order extends JpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne
    private Member member;

    private String status;

    private Timestamp startAt;

    private Timestamp endAt;

    private String address;

    private String phone;



}
