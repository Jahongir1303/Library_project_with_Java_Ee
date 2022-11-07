package com.company.library.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    USER(50),
    ADMIN(75),
    SUPER_ADMIN(100);
    private final Integer priority;
}
