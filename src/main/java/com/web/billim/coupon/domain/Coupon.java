package com.web.billim.coupon.domain;

import com.web.billim.common.domain.JpaEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "coupon")
@Builder
@Getter
public class Coupon extends JpaEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_id")
	private Integer id;

	private String name;
	private int rate;
	private int limitDate;

}
