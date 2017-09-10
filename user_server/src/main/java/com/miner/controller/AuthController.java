package com.miner.controller;

import com.google.code.kaptcha.Producer;
import com.miner.common.annotation.CurrentUser;
import com.miner.common.utils.R;
import com.miner.common.utils.YunPianUtils;
import com.miner.dto.ChangePwdDTO;
import com.miner.dto.ResetPwdDTO;
import com.miner.dto.UserLoginDTO;
import com.miner.dto.UserOutPutDTO;
import com.miner.entity.SysUserEntity;
import com.miner.service.SysUserService;
import com.miner.service.VerificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by hushangjie on 2017/9/7.
 */
@Api(value = "认证接口")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private YunPianUtils yunPianUtils;
    @Autowired
    private Producer producer;
    @Autowired
    private  VerificationService verificationService;
    /**
     * 保存所有用户的最底层方法
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/register")
    public UserOutPutDTO registerUser(@RequestBody @Validated UserLoginDTO userLoginDTO){
        SysUserEntity userEntity = new SysUserEntity();
        BeanUtils.copyProperties(userLoginDTO, userEntity);
        SysUserEntity savedUser = sysUserService.save(userEntity);
        UserOutPutDTO userOutPutDTO = new UserOutPutDTO();
        BeanUtils.copyProperties(savedUser, userOutPutDTO);
        return userOutPutDTO;
    }

    /**
     * 用户登录，获取token
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody @Validated UserLoginDTO userLoginDTO){
        String token = sysUserService.login(userLoginDTO.getUsername(),userLoginDTO.getPassword());
        return R.ok().put("token",token);
    }

    /**
     * 通过原来的密码换取新密码
     * @param currentUser
     * @param changePwdDTO
     * @return
     */
    @PostMapping("/change_pwd")
    public R changePwd(@CurrentUser SysUserEntity currentUser,@RequestBody ChangePwdDTO changePwdDTO){
        sysUserService.changePwd(changePwdDTO.getOldPassword(),changePwdDTO.getNewPassword(),currentUser);
        return R.ok().put("user",currentUser);
    }
    //通过短息验证码修改密码
    @PostMapping("/reset_pwd")
    public R resetPwd(@RequestBody ResetPwdDTO resetPwdDTO){
        //验证手机号和验证码
        verificationService.validateSMSNum(resetPwdDTO.getPhone(),resetPwdDTO.getSmsCode());
        //更新用户
        SysUserEntity userEntity = sysUserService.queryByUsername(resetPwdDTO.getPhone());
        sysUserService.resetPwd(userEntity,resetPwdDTO.getNewPwd());
        return R.ok();
    }

    /**
     * 获取图片验证码，必须携带session
     * @param response
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    @ApiOperation(value = "获取图片验证码",notes = "该接口会获取客户端session")
    //@ApiOperation(authorizations = )
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response , HttpServletRequest request)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = verificationService.generateCodeNum(request.getSession().getId());
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //获取客户端的sessionid存放至redis
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 验证图片验证码，必须携带session
     * @param imgCode
     * @param request
     * @return
     */
    @GetMapping("/validate_img_code")
    public R validateImgCode(@RequestParam(value = "img_code",required = true) String imgCode,HttpServletRequest request){
        verificationService.validateCodeNum(request.getSession().getId(),imgCode);
        return R.ok();
    }

    /**
     * 通过电话号码验证短信验证码
     * @param smsCode
     * @param phone
     * @return
     */
    @GetMapping("/validate_sms_code")
    public R validateSmsCode(@RequestParam(value = "sms_code",required = true) String smsCode,
                             @RequestParam(value = "phone",required = true) String phone){
        verificationService.validateSMSNum(phone,smsCode);
        return R.ok();
    }

    /**
     * 发送验证短信
     * @param phone
     * @return
     */
    @GetMapping("/send_sms_code")
    public R sendSmsCode(@RequestParam(value = "phone",required = true) String phone){
        verificationService.generateSMSNum(phone);
        return R.ok();
    }

    /**
     * 禁用用户
     * @param userId
     * @return
     */
    @GetMapping("/forbid_user/{userId}")
    public R forbidUser(@PathVariable(value = "userId",required = true) Long userId){
        SysUserEntity userEntity = sysUserService.queryObject(userId);
        userEntity.setEnable(false);
        sysUserService.update(userEntity);
        return R.ok();
    }


}
