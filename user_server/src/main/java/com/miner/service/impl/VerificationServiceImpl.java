package com.miner.service.impl;

import com.google.code.kaptcha.Producer;
import com.miner.common.exception.MinerException;
import com.miner.common.utils.YunPianUtils;
import com.miner.service.VerificationService;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by hushangjie on 2017/9/8.
 */
@Service
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private YunPianUtils yunPianUtils;
    @Autowired
    private Producer producer;

    @Value("nmfs:sys:vcode:")
    private String vcodePrifix;
    @Value("nmfs:sys:smscode:")
    private String smscodePrifix;

    @Override
    public String generateCodeNum(String sessionId) {
        String text = producer.createText();
        //redis存储sessionID与验证码关联
        redisTemplate.opsForValue().set(vcodePrifix+sessionId,text,1,TimeUnit.DAYS);
        return text;
    }

    @Override
    public void validateCodeNum(String sessionId, String codeNum) {
        String vcode = redisTemplate.opsForValue().get(vcodePrifix+sessionId).toString();
        if (!codeNum.equals(vcode)){
            throw new MinerException("图片验证码错误",406);
        }
        //是否需要删除掉验证码信息,一天过期
    }

    /**
     * 生成短信验证码
     * @param phone
     * @return
     */
    @Override
    public String generateSMSNum(String phone) {
        //如果redis中存在该手机的验证码则返回异常
        if (redisTemplate.hasKey(smscodePrifix+phone)){
            throw new MinerException("请60s后再试",407);
        }
        int random = (int)((Math.random()*9+1)*100000);
        Result<SmsSingleSend> result = yunPianUtils.sendMes(phone,String.valueOf(random));
        if (result.getCode() == 0){
            //发送成功，保存至redis中
            redisTemplate.opsForValue().set(smscodePrifix+phone,random,60, TimeUnit.SECONDS);
        }
        return String.valueOf(random);
    }

    @Override
    public void validateSMSNum(String phone, String smsNum) {
        Object r_smsnum = redisTemplate.opsForValue().get(smscodePrifix+phone);
        if (r_smsnum != null){
            if (!r_smsnum.toString().equals(smsNum)){
                throw new MinerException("短信验证失败!",408);
            }
        }
    }
}
