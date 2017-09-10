package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;



/**
 * 主播信息表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@Data
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
	private boolean hostSex;
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

}
