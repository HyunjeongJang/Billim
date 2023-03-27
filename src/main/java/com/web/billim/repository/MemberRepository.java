package com.web.billim.repository;

import com.web.billim.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByNickname(String nickname);




}
