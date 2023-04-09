package com.web.billim.payment.domain;

import com.web.billim.common.domain.JpaEntity;
import com.web.billim.coupon.domain.CouponIssue;
import com.web.billim.order.domain.Order;
import com.web.billim.common.type.TradeMethod;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "payment")
@Builder
@Getter
public class Payment extends JpaEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer id;

	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	@OneToOne
	private Order order;

	@JoinColumn(name = "coupon_issue_id", referencedColumnName = "coupon_issue_id")
	@OneToOne
	private CouponIssue couponIssue;

	@Column(name = "point")
	private int usedPoint;

	@Enumerated(EnumType.STRING)
	private TradeMethod tradeMethod;

	private String merchantUid;
	private int totalAmount;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

}

enum PaymentStatus {
//	SHARING, PENDING, CANCELED
}
