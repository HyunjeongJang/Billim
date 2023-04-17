package com.web.billim.client.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class IamPortService {

	public String generateMerchantUid() {

		return UUID.randomUUID().toString();
	}

}
