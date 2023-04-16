package com.web.billim.common.vo;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Period {

	LocalDate startAt;
	LocalDate endAt;

}
