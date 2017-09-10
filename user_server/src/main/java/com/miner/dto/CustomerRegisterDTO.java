package com.miner.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by hushangjie on 2017/9/9.
 */
@Data
public class CustomerRegisterDTO {
    @NotBlank(message = "手机号不能为空")
    //@Pattern(regexp = "^((13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$",message = "手机号格式不正确")
    private String username;
    @NotBlank(message = "图片验证码不能为空")
    private String imgCode;
    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$",message = "密码由6-16个数字或字母构成")
    private String password;
    @NotBlank(message = "机构负责人名字不能为空")
    private String orgPrincipalName;
    @NotBlank(message = "机构名字不能为空")
    private String orgName;
    @NotBlank(message = "机构地址不能为空")
    private String orgAddr;
}
