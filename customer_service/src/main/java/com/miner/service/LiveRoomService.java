package com.miner.service;

import com.miner.entity.EventEntity;

/**
 * Created by hushangjie on 2017/9/6.
 */

public interface LiveRoomService {
    String createPushURL(String streamId, long txTime);
}
