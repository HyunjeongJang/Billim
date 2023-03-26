package com.web.billim.domain;

import com.web.billim.type.TradeMethod;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "product")
@Builder
@Getter
public class Product extends JpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    private int categoryId;

    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne
    private Member member;

    private String productName;

    private String detail;

    private int price;

    private String area;

    @Enumerated(EnumType.STRING)
    private TradeMethod tradeMethod;

}
