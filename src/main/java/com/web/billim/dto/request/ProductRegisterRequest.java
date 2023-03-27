package com.web.billim.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.web.billim.domain.Member;
import com.web.billim.type.TradeMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductRegisterRequest {

	private int categoryId;
	private Member member;
	@NotEmpty
	private String name;
	@NotEmpty
	private String detail;
	@Min(0)
	private int price;

	private String area;
	private TradeMethod tradeMethod;

	public void setRegisterMember(Member member) {
		this.member = member;
	}
}
