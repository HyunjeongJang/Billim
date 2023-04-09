package com.web.billim.dto.response;

import com.web.billim.domain.ImageProduct;
import com.web.billim.domain.Member;
import com.web.billim.domain.Product;
import com.web.billim.type.TradeMethod;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ProductDetailResponse {

    private int productId;
    private String productName;
    private String detail;
    private int price;
    private Member member;
    private List<String> imageUrls;
    private List<TradeMethod> tradeMethods;

    public static ProductDetailResponse of(Product product){
        return ProductDetailResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .member(product.getMember())
                .detail(product.getDetail())
                .price(product.getPrice())
                .imageUrls(
                        product.getImages().stream()
                                .map(ImageProduct::getUrl)
                                .collect(Collectors.toList())
                )
                .tradeMethods(product.getTradeMethods())
                .build();
    }
}
