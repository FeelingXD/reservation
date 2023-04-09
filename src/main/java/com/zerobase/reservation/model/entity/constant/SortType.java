package com.zerobase.reservation.model.entity.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum SortType {
    RATE("별점"),
    NAME("이름");

    private final String text;
    private static final Map<String, SortType> BY_TEXT =
            Stream.of(values()).collect(Collectors.toMap(SortType::name, e -> e));

    public static SortType valueOfText(String text) {
        return BY_TEXT.get(text);
    }

}
