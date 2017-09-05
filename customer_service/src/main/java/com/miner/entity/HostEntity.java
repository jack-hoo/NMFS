package com.miner.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 主播信息表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public class HostEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主播自增长id
	private Long hostId;
	//所属机构id
	private Long orgId;
	//主播账号id
	private Long cId;
	//主播名称
	private String hostName;
	//主播qq
	private String hostQq;
	//主播性别：0男，1女
	private Integer hostSex;
	//主播邮箱
	private String hostEmail;
	//主播电话
	private String hostPhone;
	//主播头像
	private String hostAvatar;
	//部门组织
	private Long department;
	//备注信息
	private String remark;
	//创建时间
	private Date createTime;

	/**
	 * 设置：主播自增长id
	 */
	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}
	/**
	 * 获取：主播自增长id
	 */
	public Long getHostId() {
		return hostId;
	}
	/**
	 * 设置：所属机构id
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：所属机构id
	 */
	public Long getOrgId() {
		return orgId;
	}
	/**
	 * 设置：主播账号id
	 */
	public void setCId(Long cId) {
		this.cId = cId;
	}
	/**
	 * 获取：主播账号id
	 */
	public Long getCId() {
		return cId;
	}
	/**
	 * 设置：主播名称
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	/**
	 * 获取：主播名称
	 */
	public String getHostName() {
		return hostName;
	}
	/**
	 * 设置：主播qq
	 */
	public void setHostQq(String hostQq) {
		this.hostQq = hostQq;
	}
	/**
	 * 获取：主播qq
	 */
	public String getHostQq() {
		return hostQq;
	}
	/**
	 * 设置：主播性别：0男，1女
	 */
	public void setHostSex(Integer hostSex) {
		this.hostSex = hostSex;
	}
	/**
	 * 获取：主播性别：0男，1女
	 */
	public Integer getHostSex() {
		return hostSex;
	}
	/**
	 * 设置：主播邮箱
	 */
	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}
	/**
	 * 获取：主播邮箱
	 */
	public String getHostEmail() {
		return hostEmail;
	}
	/**
	 * 设置：主播电话
	 */
	public void setHostPhone(String hostPhone) {
		this.hostPhone = hostPhone;
	}
	/**
	 * 获取：主播电话
	 */
	public String getHostPhone() {
		return hostPhone;
	}
	/**
	 * 设置：主播头像
	 */
	public void setHostAvatar(String hostAvatar) {
		this.hostAvatar = hostAvatar;
	}
	/**
	 * 获取：主播头像
	 */
	public String getHostAvatar() {
		return hostAvatar;
	}
	/**
	 * 设置：部门组织
	 */
	public void setDepartment(Long department) {
		this.department = department;
	}
	/**
	 * 获取：部门组织
	 */
	public Long getDepartment() {
		return department;
	}
	/**
	 * 设置：备注信息
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注信息
	 */
	public String getRemark() {
		return remark;
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
}
