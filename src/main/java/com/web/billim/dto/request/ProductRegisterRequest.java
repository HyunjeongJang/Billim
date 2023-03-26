package com.web.billim.dto.request;

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
	private String name;
	private String detail;
	private int price;
	private String area;
	private TradeMethod tradeMethod;

	public void setRegisterMember(Member member) {
		this.member = member;
	}
}
