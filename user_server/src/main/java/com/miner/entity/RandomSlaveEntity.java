package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;



/**
 * 随机生成子帐号辅助表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-10 10:23:03
 */
@Data
public class RandomSlaveEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增长id用来作为随机生成子账户的登录账号
	private Long slaveAccount;
	//申请者的主账号
	private Long masterId;

}
