package com.springframework.test3.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserService {

    private String uId;
    private UserDao userDao;

    public String queryUserInfo() {
        return "query user info for test3：" + userDao.queryUserName(uId);
    }
}
