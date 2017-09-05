package com.miner.model;

import com.miner.entity.SysMenuEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */
public class MenuNode {
    private SysMenuEntity menu;
    private List<MenuNode> children;

    public void setMenu(SysMenuEntity menu) {
        this.menu = menu;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }

    public SysMenuEntity getMenu() {

        return menu;
    }

    public List<MenuNode> getChild() {
        return children;
    }

    public static List<MenuNode> getMenuTree(List<SysMenuEntity> menuList , Long parent){
        List<MenuNode> menus = new ArrayList<>();
        for (SysMenuEntity menu : menuList){
            MenuNode node = new MenuNode();
            if (menu.getParentId() == parent){
                List<MenuNode> menuNodes = getMenuTree(menuList ,menu.getMenuId());
                node.setMenu(menu);
                node.setChildren(menuNodes);
                menus.add(node);
            }
        }
        return menus;
    }
}
