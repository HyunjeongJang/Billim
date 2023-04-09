package com.web.billim.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "coupon_issue")
@Builder
@Getter
public class CouponIssue extends JpaEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_issue_id")
	private Integer id;

	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@JoinColumn(name = "coupon_id", referencedColumnName = "coupon_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Coupon coupon;

	@Enumerated(EnumType.STRING)
	private CouponStatus status;

}

enum CouponStatus {
	PENDING, DELETED, USED
}