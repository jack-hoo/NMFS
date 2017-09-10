package com.miner.service.impl;

import com.miner.common.utils.TxVideoUtils;
import com.miner.entity.EventEntity;
import com.miner.service.LiveRoomService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hushangjie on 2017/9/6.
 */
@Service
public class LiveRoomServiceImpl implements LiveRoomService{

    @Value("${txvideo.pushSecureKey}")
    private String key;

    @Override
    public String createPushURL(String streamId, long txTime) {

       return TxVideoUtils.createPushURL(key ,streamId,txTime);
    }


}
