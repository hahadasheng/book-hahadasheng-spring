package com.springframework.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserService {

    private String name;
    private String gender;
    private Integer age;

    private String initType;

    public UserService(String name, String gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.initType = "type1";
    }

    public UserService(Integer age, String name, String gender) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.initType = "type2";
    }

    public void queryUserInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", initType='" + initType + '\'' +
                '}';
    }
}
