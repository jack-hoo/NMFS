package com.miner.entity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;



/**
 * 系统权限表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-24 17:56:49
 */
public class SysPermissionEntity implements Serializable ,GrantedAuthority{
	private static final long serialVersionUID = 1L;
	
	//
	private Integer permId;
	//请求url的前缀
	private String urlPrefix;
	//权限名称
	private String permName;
	//权限描述
	private String permDesc;

	/**
	 * 设置：
	 */
	public void setPermId(Integer permId) {
		this.permId = permId;
	}
	/**
	 * 获取：
	 */
	public Integer getPermId() {
		return permId;
	}
	/**
	 * 设置：请求url的前缀
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	/**
	 * 获取：请求url的前缀
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}
	/**
	 * 设置：权限名称
	 */
	public void setPermName(String permName) {
		this.permName = permName;
	}
	/**
	 * 获取：权限名称
	 */
	public String getPermName() {
		return permName;
	}
	/**
	 * 设置：权限描述
	 */
	public void setPermDesc(String permDesc) {
		this.permDesc = permDesc;
	}
	/**
	 * 获取：权限描述
	 */
	public String getPermDesc() {
		return permDesc;
	}

    @Override
    public String getAuthority() {
        return this.permName;
    }
}
