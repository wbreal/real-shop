package com.shop.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public enum PointActionType {
    EARN,
    USE;

    public static PointActionType getInstance(String s) {
        try {
            return PointActionType.valueOf(s);
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error(illegalArgumentException.getMessage());
            return null;
        }
    }

}
