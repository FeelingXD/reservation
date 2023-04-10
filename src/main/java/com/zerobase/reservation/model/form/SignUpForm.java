package com.zerobase.reservation.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignUpForm {
    @ApiModelProperty(notes = "이메일(사용자id)" , example = "testAcount@naver.com")

    String email;
    @ApiModelProperty(notes = "비밀번호" , example = "examplepassWord")

    String password;
    @ApiModelProperty(notes = "사용자 핸드폰" , example = "010-0000-000(String)")

    String phone;
    @ApiModelProperty(notes = "사용자 이름" , example = "홍길동")

    String name;

}
