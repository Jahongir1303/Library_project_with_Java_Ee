package com.company.library.utils;

import com.company.library.configs.PasswordConfigurer;

public class BaseUtils {
    private static BaseUtils baseUtils;

    public String encode(String rawPassword) {
        return PasswordConfigurer.encode(rawPassword);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return PasswordConfigurer.matchPassword(rawPassword, encodedPassword);

    }

    public static BaseUtils getInstance() {
        if (baseUtils == null) {
            baseUtils = new BaseUtils();
        }
        return baseUtils;
    }
}
