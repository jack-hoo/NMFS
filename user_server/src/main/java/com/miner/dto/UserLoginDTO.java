package com.miner.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by hushangjie on 2017/9/7.
 */
@Data
public class UserLoginDTO {
    @NotBlank(message = "账号不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$",message = "密码由6-16个数字或字母构成")
    private String password;
}
