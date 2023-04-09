package com.web.billim.service;

import com.web.billim.domain.Member;
import com.web.billim.dto.request.FindIdRequest;
import com.web.billim.dto.request.MemberSignupRequest;
import com.web.billim.dto.response.FindIdResponse;
import com.web.billim.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Map<String, String> validateHandling(BindingResult bindingResult) {
        Map<String, String> validatorResult = new HashMap<>();

        for(FieldError error : bindingResult.getFieldErrors()){
            String validKeyName = String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }

    public void singUp(MemberSignupRequest memberSignupRequest) {
        memberSignupRequest.setPassword(bCryptPasswordEncoder.encode(memberSignupRequest.getPassword()));
        Member member = memberSignupRequest.toEntity();
        memberRepository.save(member);
    }



    public void findId(FindIdRequest findIdRequest) {
        Member member = memberRepository.findByUserIdAndEmail(findIdRequest.getName(), findIdRequest.getEmail());

        if(member != null && member.getUserId().equals(findIdRequest.getName()) && member.getEmail().equals(findIdRequest.getEmail())) {
            FindIdResponse.from(member);
        } else {
            new FindIdResponse();
        }

    }
}
