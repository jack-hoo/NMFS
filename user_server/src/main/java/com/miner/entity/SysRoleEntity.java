package com.miner.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


/**
 * 系统角色表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-08 18:48:14
 */
@Data
public class SysRoleEntity implements Serializable,GrantedAuthority {
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

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
