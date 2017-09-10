package com.miner.common.utils;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 云片短信发送工具
 * Created by hushangjie on 2017/9/8.
 */
@Component
public class YunPianUtils {

    private static final YunpianClient client = new YunpianClient("270b48342e6db88b2ff69750e5b3ecb6").init();


    public Result<SmsSingleSend> sendMes(String phoneNum , String code){
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, phoneNum);
        param.put(YunpianClient.TEXT, "【松果直播】您的验证码是"+code+"。如非本人操作，请忽略本短信");
        Result<SmsSingleSend> r = client.sms().single_send(param);
        client.close();
        return r;
    }
}
