package com.miner.controller;

import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;
import com.miner.entity.OrgInfoEntity;
import com.miner.service.OrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by hushangjie on 2017/9/5.
 */
@RestController
@RequestMapping("/org")
public class OrgController {
    @Autowired
    private OrgInfoService orgInfoService;

    /**
     * 保存机构信息
     * @param orgInfo
     * @return R
     */
    @PostMapping("")
    public R saveOrg(@RequestBody @Validated OrgInfoEntity orgInfo){
        orgInfoService.save(orgInfo);
        return R.ok();
    }

    /**
     * 保存机构信息
     * @param orgInfo
     * @return R
     */
    @PutMapping("")
    public R updateOrg(@RequestBody OrgInfoEntity orgInfo){
        if (orgInfo.getOrgId() == null){
            return R.error(40001,"更新必须指定机构id");
        }else {
            orgInfo.setCreateTime(null);
            orgInfoService.update(orgInfo);
            return R.ok();
        }
    }

    /**
     * 删除(可批量) 参数格式 [27,29]
     * @param orgIds
     * @return
     */
    @DeleteMapping("")
    public R delete(@RequestBody Long[] orgIds){
        orgInfoService.deleteBatch(orgIds);

        return R.ok();
    }

    /**
     * 获取多条信息
     * @param params
     * @return
     */
    @GetMapping("")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        if (params.get("page") != null && params.get("limit")!= null){
            List<OrgInfoEntity> orgInfoList = orgInfoService.queryList(query);
            int total = orgInfoService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(orgInfoList, total, query.getLimit(), query.getPage());

            return R.ok().put("page", pageUtil);
        }else {
            List<OrgInfoEntity> orgInfoList = orgInfoService.queryList(query);
            return R.ok().put("list", orgInfoList);
        }


    }

    /**
     * 获取指定id
     * @param orgId
     * @return
     */
    @GetMapping("/{orgId}")
    public R info(@PathVariable("orgId") Long orgId){
        OrgInfoEntity orgInfo = orgInfoService.queryObject(orgId);

        return R.ok().put("orgInfo", orgInfo);
    }


}
