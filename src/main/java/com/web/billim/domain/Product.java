package com.web.billim.domain;

import com.web.billim.dto.request.ProductRegisterRequest;
import com.web.billim.type.TradeMethod;

import lombok.*;

import javax.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    // TODO : 카테고리 Entity 랑 연관관계 맺기
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne
    private ProductCategory productCategory;

    // private int categoryId;

    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    @ManyToOne
    private Member member;

    private String productName;

    private String detail;

    private int price;

    // Getter 없는게 나은데..
    private String tradeMethod;

    @JoinColumn(name = "product_id")
    @OneToMany(fetch = FetchType.LAZY) // EAGER(즉시 로딩)
    private List<ImageProduct> images;

    public List<TradeMethod> getTradeMethods() {
        return Arrays.stream(tradeMethod.split(",")).map(TradeMethod::valueOf).collect(Collectors.toList());
    }

    public static Product generateNewProduct(ProductRegisterRequest request, ProductCategory category, Member member, List<ImageProduct> images) {
        return Product.builder()
                .productCategory(category)
                .member(member)
                .productName(request.getName())
                .detail(request.getDetail())
                .price(request.getPrice())
                .tradeMethod(request.getTradeMethods().stream().map(Objects::toString).collect(Collectors.joining(",")))
                .images(images)
                .build();
    }

}

