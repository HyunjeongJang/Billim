package com.web.billim.domain.point;

import com.web.billim.domain.JpaEntity;
import com.web.billim.domain.Payment;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "point_history")
@Builder
@Getter
public class PointHistory extends JpaEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "point_history_id")
	private Integer id;

	@JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
	@ManyToOne
	private Payment payment;

	@JoinColumn(name = "saved_point_id", referencedColumnName = "point_id")
	@ManyToOne
	private SavedPoint savedPoint;

	private int amount;

}
