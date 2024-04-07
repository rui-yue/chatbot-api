package com.jr.ai.api.domain.ai.common;

/**
 * author: JiangJiang
 * description 通用类
 * date: 2024/4/7 15:56
 */
public class Constants {

    public static final String NULL = "NULL";

    public enum Role {
        SYSTEM("system"),
        USER("user"),
        ASSISTANT("assistant"),
        ;

        private String code;

        Role(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

}
