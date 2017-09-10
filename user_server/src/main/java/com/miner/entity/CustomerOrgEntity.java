package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;



/**
 * 客户机构关联表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-09 19:49:24
 */
@Data
public class CustomerOrgEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long customerOrgId;
	//客户id(包含小主播)
	private Long customerId;
	//机构id
	private Long orgId;

}
