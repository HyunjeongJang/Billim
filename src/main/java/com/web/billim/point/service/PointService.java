package com.web.billim.point.service;

import com.web.billim.member.domain.Member;
import com.web.billim.point.domain.service.SavedPoint;
import com.web.billim.point.domain.service.PointDomainService;
import com.web.billim.point.dto.AppendPointCommand;
import com.web.billim.member.service.MemberService;
import com.web.billim.point.repository.PointHistoryRepository;
import com.web.billim.point.repository.SavedPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

	private final MemberService memberService;
	private final PointDomainService pointDomainService;
	private final SavedPointRepository savedPointRepository;
	private final PointHistoryRepository pointHistoryRepository;

	// UseCase
	// 1. 특정 사용자에게 적립금 부여 (C)
	public void addPoint(AppendPointCommand command) {
		Member member = memberService.retrieve(command.getMemberId());
		SavedPoint point = SavedPoint.of(member, command);
		savedPointRepository.save(point);
	}

	// 2. 특정 사용자의 사용가능 적립금 조회 (R)
	public int retrieveAvailablePoint(int memberId) {
		return savedPointRepository.findAllNotExpired(memberId).stream()
			// A -> B
			// A : SavedPoint
			// B : Integer
			.mapToInt(SavedPoint::getAvailableAmount)
			.sum();
	}

	// 3. 특정 사용자의 적립금 사용 (U)
	// @Transactional
	// public void usePoint(int memberId, int amount) {
	// 	// 1. 사용 가능한 포인트 있는지 체크
	// 	boolean isAvailable = pointDomainService.checkAvailable(memberId, amount);
	// 	if (!isAvailable) {
	// 		throw new RuntimeException("사용 가능한 포인트가 부족합니다.");
	// 	}
	//
	// 	// 2. 포인트 사용
	// 	List<SavedPoint> usedPoint = pointDomainService.use(memberId, amount);
	//
	// 	// 3. 포인트 사용내역 추가
	//
	//
	// 	// Service 에서는 이런 복잡한 도메인 로직을 처리하지 않고 위임해야함.
	// 	if (this.retrieveAvailablePoint(memberId) < amount) {
	// 		throw new RuntimeException("사용 가능한 포인트가 부족합니다.");
	// 	}
	// 	List<SavedPoint> availablePoint = savedPointRepository.findAllNotExpired(memberId);
	//
	// }

}
