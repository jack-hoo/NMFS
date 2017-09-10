package com.miner.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by hushangjie on 2017/9/7.
 */
@Data
public class UserOutPutDTO {
    private Long userId;
    private String username;
    private boolean enable;
    private Date createTime;
    private Date lastPasswordResetDate;
}
