package com.miner.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 客户主体表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public class CustomerPrincipalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long cId;
	//账号对应机构id
	private Long orgId;
	//是否为主账号：0否，1是
	private Integer isParent;
	//客户账号
	private String cAccount;
	//客户密码
	private String cPassword;
	//账号状态：0 正常，1锁定
	private Integer state;
	//
	private Date lastPasswordResetDate;
	//创建时间
	private Date createTime;
	
	private Collection<? extends GrantedAuthority> authorities;
	/**
	 * 设置：
	 */
	public void setCId(Long cId) {
		this.cId = cId;
	}
	/**
	 * 获取：
	 */
	public Long getCId() {
		return cId;
	}
	/**
	 * 设置：账号对应机构id
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：账号对应机构id
	 */
	public Long getOrgId() {
		return orgId;
	}
	/**
	 * 设置：是否为主账号：0否，1是
	 */
	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}
	/**
	 * 获取：是否为主账号：0否，1是
	 */
	public Integer getIsParent() {
		return isParent;
	}
	/**
	 * 设置：客户账号
	 */
	public void setCAccount(String cAccount) {
		this.cAccount = cAccount;
	}
	/**
	 * 获取：客户账号
	 */
	public String getCAccount() {
		return cAccount;
	}
	/**
	 * 设置：客户密码
	 */
	public void setCPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	/**
	 * 获取：客户密码
	 */
	public String getCPassword() {
		return cPassword;
	}
	/**
	 * 设置：账号状态：0 正常，1锁定
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：账号状态：0 正常，1锁定
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：
	 */
	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	/**
	 * 获取：
	 */
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
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
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.cPassword;
    }

    @Override
    public String getUsername() {
        return this.cAccount;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.state == 0 ? true :false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
