package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;



/**
 * 机构客户信息表(平台后台添加)
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@Data
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

}
