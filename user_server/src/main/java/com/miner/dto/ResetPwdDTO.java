package com.miner.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 通过短息验证修改密码
 * Created by hushangjie on 2017/9/8.
 */
@Data
public class ResetPwdDTO {
    @NotBlank(message = "电话不能为空")
    private String phone;
    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;
    @Pattern(regexp = "^[a-zA-Z0-9]{6,16}$",message = "密码由6-16个数字或字母构成")
    @NotBlank(message = "密码不能为空")
    private String newPwd;
}
