package com.springframework.test2.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserService {

    private String uId;
    private UserDao userDao;


    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

}
