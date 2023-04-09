package com.web.billim.repository;

import com.web.billim.domain.point.SavedPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedPointRepository extends JpaRepository<SavedPoint, Integer> {

	@Query("SELECT sp FROM SavedPoint sp WHERE sp.member.memberId = :memberId "
		+ "AND sp.expiredAt >= current_timestamp ORDER BY sp.expiredAt DESC")
	List<SavedPoint> findAllNotExpired(@Param("memberId") int memberId);

}
