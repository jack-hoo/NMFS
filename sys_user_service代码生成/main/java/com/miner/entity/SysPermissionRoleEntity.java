package com.miner.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 系统角色权限对应表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
public class SysPermissionRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//角色id
	private Integer roleId;
	//权限id
	private Integer permId;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：角色id
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：角色id
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * 设置：权限id
	 */
	public void setPermId(Integer permId) {
		this.permId = permId;
	}
	/**
	 * 获取：权限id
	 */
	public Integer getPermId() {
		return permId;
	}
}
