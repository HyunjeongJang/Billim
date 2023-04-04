package com.web.billim.dto.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.web.billim.domain.Member;
import com.web.billim.type.TradeMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRegisterRequest {

    private int categoryId;

    private Member member;

    @NotEmpty
//	@NotEmpty(message = "대여 상품명은 필수 항목입니다.")
    private String name;

    @NotEmpty
//	@NotEmpty(message = "상품 설명은 필수 항목입니다.")
    private String detail;

    //	@NotEmpty(message = "금액은 필수항목입니다. 100원 이상 입력해 주세요.")
    @Min(value = 100, message = "100원 이상 입력해 주세요.")
    private int price;

    @NotEmpty
    private List<MultipartFile> images;

    @NotEmpty
    private List<TradeMethod> tradeMethods;

    public void setRegisterMember(Member member) {
        this.member = member;
    }
}

// AWS AccessKey, SecretKey