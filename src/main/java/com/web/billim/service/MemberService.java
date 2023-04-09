package com.web.billim.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.web.billim.domain.Member;
import com.web.billim.dto.request.FindIdRequest;
import com.web.billim.dto.request.MemberSignupRequest;
import com.web.billim.dto.response.FindIdResponse;
import com.web.billim.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Map<String, String> validateHandling(BindingResult bindingResult) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    public void singUp(MemberSignupRequest memberSignupRequest) {
        memberSignupRequest.setPassword(bCryptPasswordEncoder.encode(memberSignupRequest.getPassword()));
        Member member = memberSignupRequest.toEntity();
        memberRepository.save(member);
    }

    public FindIdResponse findId(FindIdRequest findIdRequest) {
        return memberRepository.findByNameAndEmail(findIdRequest.getName(), findIdRequest.getEmail())
                .map(FindIdResponse::from)
                .orElse(new FindIdResponse());
    }

    public Member retrieve(int memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 사용자(" + memberId + ") 를 찾을 수 없습니다."));
    }
}
