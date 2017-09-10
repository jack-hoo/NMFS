package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;



/**
 * 用户与角色对应关系
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-08 18:47:38
 */
@Data
public class SysUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户ID
	private Long userId;
	//角色ID
	private Integer roleId;

}
