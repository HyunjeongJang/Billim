package com.web.billim.product.dto.response;

import com.web.billim.product.domain.ImageProduct;
import com.web.billim.product.domain.Product;
import com.web.billim.product.domain.ProductCategory;
import com.web.billim.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ProductListResponse {

    private int productId;
    private String productName;
    private ProductCategory productCategory;
    private String detail;
    private int price;
    private List<String> imageUrls;

    public static ProductListResponse of(Product product) {
        return ProductListResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .detail(product.getDetail())
                .price(product.getPrice())
                .imageUrls(
                        product.getImages().stream()
                                .map(ImageProduct::getUrl)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
