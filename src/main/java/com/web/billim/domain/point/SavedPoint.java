package com.web.billim.domain.point;

import com.web.billim.domain.JpaEntity;
import com.web.billim.domain.Member;
import com.web.billim.dto.AppendPointCommand;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "saved_point")
@Builder
@Getter
public class SavedPoint extends JpaEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "point_id")
	private Integer id;

	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	@ManyToOne
	private Member member;

	private int amount;
	private int availableAmount;
	private LocalDateTime expiredAt;

	public static SavedPoint of(Member member, AppendPointCommand command) {
		return SavedPoint.builder()
			.member(member)
			.amount(command.getAmount())
			.availableAmount(command.getAmount())
			.expiredAt(command.getExpiredAt())
			.build();
	}

	public int use(int amount) {
		int usedAmount;
		if (this.availableAmount >= amount) {
			usedAmount = amount;
			this.availableAmount -= amount;
		} else {
			usedAmount = this.availableAmount;
			this.availableAmount = 0;
		}
		return usedAmount;
	}

}
