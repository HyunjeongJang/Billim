package com.web.billim.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;

    private int category_id;

    private int member_id;

    private String product_name;

    private String detail;

    private int price;

    private String area;

    private Timestamp created_at;

    private Timestamp updated_at;

    private String trade_method;

}
