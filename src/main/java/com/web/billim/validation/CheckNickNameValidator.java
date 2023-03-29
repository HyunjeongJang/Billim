package com.web.billim.validation;

import com.web.billim.dto.request.MemberSignupRequest;
import com.web.billim.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckNickNameValidator extends AbstractValidator<MemberSignupRequest>{

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberSignupRequest dto, Errors errors) {
        if(memberRepository.existsByNickname(dto.toEntity().getNickname())){
            errors.rejectValue("nickName","닉네임 중복 오류","이미 사용중인 닉네임 입니다.");
        }

    }
}
