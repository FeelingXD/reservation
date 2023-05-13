package com.zerobase.reservation.model.entity.constraint;

import lombok.Getter;

@Getter
public enum UserType {
  CUSTOMER("고객"),
  MANAGER("매니저");

  private final String type;

  UserType(String type) {
    this.type = type;
  }
}