package com.web.billim.domain;

import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.type.TradeMethod;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "product")
@Builder
@Getter
public class Product extends JpaEntity {

    @Id   // 이 필드가 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    private int categoryId;

    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne
    private Member member;

    private String productName;

    private String detail;

    private int price;

    @Enumerated(EnumType.STRING)
    private TradeMethod tradeMethod;

    @JoinColumn(name = "product_id")
    @OneToMany(fetch = FetchType.LAZY) // EAGER(즉시 로딩)
    private List<ImageProduct> images;

    public static Product generateNewProduct(ProductRegisterRequest request, List<ImageProduct> images) {
        return Product.builder()
                .categoryId(request.getCategoryId())
                .member(request.getMember())
                .productName(request.getName())
                .detail(request.getDetail())
                .price(request.getPrice())
                .tradeMethod(TradeMethod.DIRECT)
                .images(images)
                .build();
    }

}
