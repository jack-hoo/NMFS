package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;



/**
 * 系统角色表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@Data
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//系统角色自增长id
	private Integer roleId;
	//角色名称
	private String roleName;
	//备注
	private String remark;
	//
	private Integer createUserId;
	//创建时间
	private Date createTime;

}
