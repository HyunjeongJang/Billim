package com.web.billim.repository;

import com.web.billim.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByNickname(String nickname);
    boolean existsByUserId(String userId);
}
