package com.miner.entity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;



/**
 * 系统角色表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 16:31:54
 */
public class SysRoleEntity implements Serializable ,GrantedAuthority {
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
	//删除标志位；0 未删除，1已经删除
	private Integer deleteMark;

	/**
	 * 设置：系统角色自增长id
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：系统角色自增长id
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * 设置：角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取：角色名称
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：删除标志位；0 未删除，1已经删除
	 */
	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}
	/**
	 * 获取：删除标志位；0 未删除，1已经删除
	 */
	public Integer getDeleteMark() {
		return deleteMark;
	}

    @Override
    public String getAuthority() {
        return "ROLE_"+this.roleName;
    }
}
