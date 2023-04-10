package com.zerobase.reservation.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignInForm {
    @ApiModelProperty(notes = "이메일(사용자id)" , example = "testAcount@naver.com")
    String email;
    @ApiModelProperty(notes = "비밀번호" , example = "examplepassWord")
    String password;
}
