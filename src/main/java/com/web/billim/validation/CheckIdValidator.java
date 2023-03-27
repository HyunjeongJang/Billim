package com.web.billim.validation;

import com.web.billim.dto.request.MemberSignupRequest;
import com.web.billim.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckIdValidator extends AbstractValidator<MemberSignupRequest> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberSignupRequest dto, Errors errors) {
        if(memberRepository.existsById(dto.toEntity().getMemberId())){
            errors.rejectValue("id","아이디 중복 오류","이미 사용중인 아이디 입니다");
        }
    }
}
