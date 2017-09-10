package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;



/**
 * 系统用户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@Data
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//系统用户id
	private Long userId;
	//用户名：手机号或者指定字符
	private String username;
	//密码:由6-16位的数字或字母组成
	private String password;
	//账号是否可用：0 禁用，1 可用
	private boolean enable;
	//创建时间
	private Date createTime;
	//最后一次密码重置时间
	private Date lastPasswordResetDate;

}
