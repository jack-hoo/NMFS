package com.miner.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miner.entity.OrgInfoEntity;
import com.miner.service.OrgInfoService;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;




/**
 * 机构客户信息表(平台后台添加)
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@RestController
@RequestMapping("orginfo")
public class OrgInfoController {
	@Autowired
	private OrgInfoService orgInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<OrgInfoEntity> orgInfoList = orgInfoService.queryList(query);
		int total = orgInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(orgInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{orgId}")
	public R info(@PathVariable("orgId") Long orgId){
		OrgInfoEntity orgInfo = orgInfoService.queryObject(orgId);
		
		return R.ok().put("orgInfo", orgInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody OrgInfoEntity orgInfo){
		orgInfoService.save(orgInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody OrgInfoEntity orgInfo){
		orgInfoService.update(orgInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] orgIds){
		orgInfoService.deleteBatch(orgIds);
		
		return R.ok();
	}
	
}
