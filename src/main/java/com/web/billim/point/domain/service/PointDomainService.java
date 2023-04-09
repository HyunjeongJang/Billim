package com.web.billim.point.domain.service;

import com.web.billim.member.domain.Member;
import com.web.billim.point.repository.SavedPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointDomainService {

	private final SavedPointRepository savedPointRepository;

	public void use(Member member, int amount) {
		List<SavedPoint> savedPoints = savedPointRepository.findAllNotExpired(member.getMemberId());
		if (savedPoints.stream().mapToInt(SavedPoint::getAvailableAmount).sum() < amount) {
			throw new RuntimeException("사용가능 적립금이 부족합니다.");
		}

		int totalUsedAmount = 0;
		for (SavedPoint savedPoint: savedPoints) {
			totalUsedAmount += savedPoint.use(amount - totalUsedAmount);
			if (totalUsedAmount >= amount) break;
		}
	}

}
