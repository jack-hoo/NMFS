package com.miner.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 菜单管理
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-08-29 12:50:33
 */
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long menuId;
	//父菜单ID，一级菜单为0
	private Long parentId;
	//菜单名称
	private String name;
	//菜单URL
	private String path;
	//路由是否隐藏：1是，0否
	private Integer hidden;
	//没有展开选项:0否，1是
	private Integer noDropdown;
	//组件名称
	private String component;
	//类型   0：目录   1：菜单   2：按钮
	private Integer type;
	//菜单图标
	private String icon;
	//排序
	private Integer orderNum;

	/**
	 * 设置：
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	/**
	 * 获取：
	 */
	public Long getMenuId() {
		return menuId;
	}
	/**
	 * 设置：父菜单ID，一级菜单为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父菜单ID，一级菜单为0
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：菜单名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：菜单URL
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：菜单URL
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 设置：路由是否隐藏：1是，0否
	 */
	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}
	/**
	 * 获取：路由是否隐藏：1是，0否
	 */
	public Integer getHidden() {
		return hidden;
	}
	/**
	 * 设置：没有展开选项:0否，1是
	 */
	public void setNoDropdown(Integer noDropdown) {
		this.noDropdown = noDropdown;
	}
	/**
	 * 获取：没有展开选项:0否，1是
	 */
	public Integer getNoDropdown() {
		return noDropdown;
	}
	/**
	 * 设置：组件名称
	 */
	public void setComponent(String component) {
		this.component = component;
	}
	/**
	 * 获取：组件名称
	 */
	public String getComponent() {
		return component;
	}
	/**
	 * 设置：类型   0：目录   1：菜单   2：按钮
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型   0：目录   1：菜单   2：按钮
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：菜单图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：菜单图标
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
}
