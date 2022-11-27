package com.springframework.test2.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<String, String>() {
        {
            put("10001", "刘亦菲");
            put("10002", "舒畅");
            put("10003", "关晓彤");
        }
    };

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
