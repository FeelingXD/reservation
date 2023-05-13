package com.zerobase.reservation.model.form;

import io.swagger.annotations.ApiModelProperty;
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
public class SignInForm {

  @NotNull
  @ApiModelProperty(notes = "이메일(사용자id)", example = "testAcount@naver.com")
  String email;
  @NotNull
  @ApiModelProperty(notes = "비밀번호", example = "examplepassWord")
  String password;
}
