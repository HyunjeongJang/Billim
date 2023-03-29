package com.web.billim.validation;

import com.web.billim.dto.request.MemberSignupRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CheckPasswordValidator extends AbstractValidator<MemberSignupRequest> {
    @Override
    protected void doValidate(MemberSignupRequest dto, Errors errors) {
        if(dto.getPassword().equals(dto.getPassword2()) == false){
            errors.rejectValue("password2","비밀번호 일치 오류","비밀번호가 일치하지 않습니다.");
        }
    }
}
