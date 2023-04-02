package com.web.billim.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "image_product")
@Getter
@Builder
public class ImageProduct extends JpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_product_id")
    private Integer id;

    private String url;
    
    public static ImageProduct of(String url) {
        return ImageProduct.builder()
                .url(url)
                .build();
    }
}
