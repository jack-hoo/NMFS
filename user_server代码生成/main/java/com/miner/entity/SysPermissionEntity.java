package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;



/**
 * 系统权限表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 22:23:29
 */
@Data
public class SysPermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer permId;
	//请求url的前缀
	private String urlPrefix;
	//权限名称
	private String permName;
	//权限描述
	private String permDesc;

}
