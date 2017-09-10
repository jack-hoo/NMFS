package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;



/**
 * 系统角色权限对应表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 22:23:29
 */
@Data
public class SysPermissionRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//角色id
	private Integer roleId;
	//权限id
	private Integer permId;

}
