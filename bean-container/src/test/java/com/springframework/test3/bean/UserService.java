package com.springframework.test3.bean;

import com.springframework.test2.bean.UserDao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserService {

    private String uId;
    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("query user info for test3：" + userDao.queryUserName(uId));
    }
}
