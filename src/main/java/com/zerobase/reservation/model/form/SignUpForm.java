package com.zerobase.reservation.model.form;

import com.zerobase.reservation.config.validation.Telephone;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignUpForm {

  @ApiModelProperty(notes = "이메일(사용자id)", example = "testAcount@naver.com")
  @NotNull
  @Email
  String email;
  @ApiModelProperty(notes = "비밀번호", example = "examplepassWord")
  @NotNull
  String password;
  @ApiModelProperty(notes = "사용자 핸드폰", example = "010-0000-000(String)")
  @NotNull
  @Telephone
  String phone;
  @ApiModelProperty(notes = "사용자 이름", example = "홍길동")
  @NotNull
  String name;

}
