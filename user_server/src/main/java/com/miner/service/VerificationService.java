package com.miner.service;

/**
 * 验证码服务
 * Created by hushangjie on 2017/9/8.
 */
public interface VerificationService {
    String generateCodeNum(String sessionId);
    void validateCodeNum(String sessionId, String codeNum);
    String generateSMSNum(String phone);
    void validateSMSNum(String phone, String SMSNum);
}
