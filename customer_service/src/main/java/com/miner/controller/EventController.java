package com.miner.controller;

import com.miner.client.UserClient;
import com.miner.common.utils.R;
import com.miner.dto.EventInputDTO;
import com.miner.dto.UserModel;
import com.miner.entity.EventEntity;
import com.miner.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 直播活动控制器
 * Created by hushangjie on 2017/9/6.
 */
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserClient userClient;
    /**
     * 创建直播活动
     * @param eventInputDTO
     * @return
     */
    @PostMapping("")
    public R createEvent(@RequestBody @Validated EventInputDTO eventInputDTO){
        EventEntity eventEntity = eventInputDTO.convertToEntity(eventInputDTO);
        eventService.save(eventEntity);
        return R.ok();
    }
    @DeleteMapping("")
    public R deleteEvent(@RequestBody Long[] eventIds){
        return R.ok();
    }
    @GetMapping("/test")
    public UserModel hello(){

        return userClient.getUser("15579870840");
    }
}
