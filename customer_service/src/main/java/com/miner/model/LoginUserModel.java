package com.miner.model;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/28.
 */
public class LoginUserModel implements Serializable{
    private static final long serialVersionUID = 1L;
    @NotBlank(message ="用户名不能为空" )
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
