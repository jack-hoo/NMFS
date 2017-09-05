package com.miner.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 机构客户信息表(平台后台添加)
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public class OrgInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//机构自增长id
	private Long orgId;
	//机构负责人电话
	private String cPhone;
	//机构负责人名字
	private String cName;
	//机构名称
	private String orgName;
	//机构地址
	private String orgAddr;
	//创建时间
	private Date createTime;

	/**
	 * 设置：机构自增长id
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：机构自增长id
	 */
	public Long getOrgId() {
		return orgId;
	}
	/**
	 * 设置：机构负责人电话
	 */
	public void setCPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	/**
	 * 获取：机构负责人电话
	 */
	public String getCPhone() {
		return cPhone;
	}
	/**
	 * 设置：机构负责人名字
	 */
	public void setCName(String cName) {
		this.cName = cName;
	}
	/**
	 * 获取：机构负责人名字
	 */
	public String getCName() {
		return cName;
	}
	/**
	 * 设置：机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：机构名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：机构地址
	 */
	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}
	/**
	 * 获取：机构地址
	 */
	public String getOrgAddr() {
		return orgAddr;
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
