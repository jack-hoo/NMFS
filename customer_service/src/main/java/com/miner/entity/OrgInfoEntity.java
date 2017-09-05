package com.miner.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", message="手机号格式不正确")
	private String cPhone;
	//机构负责人名字
    @NotBlank(message = "负责人名字不能为空")
	private String cName;
	//机构名称
    @NotBlank(message = "机构名字不能为空")
	private String orgName;
	//机构地址
    @NotBlank(message = "机构地址不能为空")
	private String orgAddr;
	//创建时间
	private Date createTime;

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOrgId() {
        return orgId;
    }

    public String getcPhone() {
        return cPhone;
    }

    public String getcName() {
        return cName;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgAddr() {
        return orgAddr;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
