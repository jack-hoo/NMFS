package com.miner.controller;

import com.miner.common.Constant.RoleConstant;
import com.miner.common.annotation.CurrentUser;
import com.miner.common.exception.MinerException;
import com.miner.common.utils.PageUtils;
import com.miner.common.utils.Query;
import com.miner.common.utils.R;
import com.miner.common.validator.group.AddGroup;
import com.miner.common.validator.group.UpdateGroup;
import com.miner.dto.CustomerRegisterDTO;
import com.miner.dto.UserDetailsModel;
import com.miner.dto.UserLoginDTO;
import com.miner.entity.*;
import com.miner.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by hushangjie on 2017/9/9.
 */
@Api(value = "客户操作接口")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private VerificationService verificationService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private OrgInfoService orgInfoService;
    @Autowired
    private CustomerOrgService customerOrgService;
    @Autowired
    private HostService hostService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CustomerService customerService;
    /**
     * 客户注册，(如何简化注册步骤)
     * @param customerRegisterDTO
     * @param request
     * @return
     */
    /*@ApiOperation(value = "客户申请注册",notes = "该接口仅提供给客户注册")
    @ApiImplicitParam(value = "customerRegisterDTO",name = "客户注册信息",dataType = "CustomerRegisterDTO",required = true)*/
    @PostMapping("/register")
    public R registerCustomer(@RequestBody @Validated CustomerRegisterDTO customerRegisterDTO, HttpServletRequest request){
        //验证图片验证码
        //verificationService.validateCodeNum(request.getSession().getId(),customerRegisterDTO.getImgCode());
        //验证短信验证码
        //verificationService.validateSMSNum(customerRegisterDTO.getUsername(),customerRegisterDTO.getSmsCode());
        //保存该用户
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(customerRegisterDTO.getPassword());
        userEntity.setUsername(customerRegisterDTO.getUsername());
        sysUserService.save(userEntity);

        SysRoleEntity roleEntity = roleService.getRoleByRoleName(RoleConstant.CUSTOMER);
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setRoleId(roleEntity.getRoleId());
        sysUserRoleEntity.setUserId(userEntity.getUserId());
        //保存用户角色对应关系
        sysUserRoleService.save(sysUserRoleEntity);
        //保存机构信息
        OrgInfoEntity orgInfoEntity = new OrgInfoEntity();
        orgInfoEntity.setCreateTime(new Date());
        orgInfoEntity.setCName(customerRegisterDTO.getOrgPrincipalName());
        orgInfoEntity.setCPhone(customerRegisterDTO.getUsername());
        orgInfoEntity.setOrgAddr(customerRegisterDTO.getOrgAddr());
        orgInfoEntity.setOrgName(customerRegisterDTO.getOrgName());
        orgInfoService.save(orgInfoEntity);
        /*//保存用户机构对应关系
        CustomerOrgEntity customerOrgEntity = new CustomerOrgEntity();
        customerOrgEntity.setCustomerId(userEntity.getUserId());
        customerOrgEntity.setOrgId(orgInfoEntity.getOrgId());
        customerOrgService.save(customerOrgEntity);*/
        //保存用户主播信息
        HostEntity hostEntity = new HostEntity();
        hostEntity.setCreateTime(new Date());
        hostEntity.setOrgId(orgInfoEntity.getOrgId());
        hostEntity.setCId(userEntity.getUserId());
        hostEntity.setRemark("客户");
        hostEntity.setHostPhone(customerRegisterDTO.getUsername());
        hostEntity.setHostName(customerRegisterDTO.getOrgPrincipalName());
        hostService.save(hostEntity);
        //生成用户帐户信息

        return R.ok();
    }

    /**
     * 客户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @PostAuthorize("hasAnyRole('CUSTOMER','HOST')")
    public R customerLogin(@RequestBody @Validated UserLoginDTO userLoginDTO){
        String token = sysUserService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        //返回客户信息，方便前端存储(角色，主播信息)
        List<SysRoleEntity> userRoles = roleService.getUserRoles(userLoginDTO.getUsername());
        UserDetailsModel currentUser = (UserDetailsModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HostEntity host = hostService.queryByCustomerId(currentUser.getUserId());
        return R.ok().put("roles",userRoles).put("hostInfo",host).put("token",token);
    }
    /**
     * 增加子帐号
     * @param currentUser
     * @param hostEntity
     * @return
     */
    @PostMapping("/slave")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public R addSlave(@CurrentUser SysUserEntity currentUser,@RequestBody @Validated HostEntity hostEntity){
        customerService.addHost(hostEntity,currentUser);
        return R.ok();
    }
    /**
     * 获取该机构所有主播信息
     * @param currentUser
     * @param params
     * @return
     */
    @GetMapping("/host")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public R getHosts(@CurrentUser SysUserEntity currentUser, @RequestParam Map<String, Object> params){
        //当前用户所在机构
        HostEntity master = hostService.queryByCustomerId(currentUser.getUserId());
        //查询列表数据
        params.put("org_id",master.getOrgId());
        Query query = new Query(params);

        if (params.get("page") != null && params.get("limit")!= null){
            List<HostEntity> orgInfoList = hostService.queryList(query);
            int total = hostService.queryTotal(query);

            PageUtils pageUtil = new PageUtils(orgInfoList, total, query.getLimit(), query.getPage());

            return R.ok().put("page", pageUtil);
        }else {
            List<HostEntity> orgInfoList = hostService.queryList(query);
            return R.ok().put("list", orgInfoList);
        }
    }
    /**
     * 禁用某个主播账号
     * @param userId
     * @return
     */
    @GetMapping("/forbid/{user_id}")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public R forbidOneSlave(@CurrentUser SysUserEntity currentUser,@PathVariable(value = "user_id",required = true) Long userId){
        //判断操作者的角色
        List<SysRoleEntity> roleEntities = roleService.getUserRoles(currentUser.getUsername());
        Set<String> roles = new HashSet<>();
        for (SysRoleEntity roleEntity : roleEntities){
            roles.add(roleEntity.getRoleName());
        }
        if (roles.contains(RoleConstant.CUSTOMER) && roles.size()==1){
            //该用户角色是客户，需要判断其禁用的账号是否是自己机构的
            HostEntity master = hostService.queryByCustomerId(currentUser.getUserId());
            HostEntity forbidHost = hostService.queryByCustomerId(userId);
            if (master.getOrgId() != forbidHost.getOrgId()){
                throw new MinerException("无权操作",403);
            }
        }
        sysUserService.forbidUser(userId);
        return R.ok();
    }
    /**
     * 更新主播信息
     * @param hostEntity
     * @return
     */
    @PutMapping("")
    public R updateHost(@RequestBody @Validated({UpdateGroup.class}) HostEntity hostEntity){
        hostService.update(hostEntity);
        return R.ok();
    }

    /**
     * 删除某个主播账号
     * @param hostId
     * @return
     */
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/host/{host_id}")
    public R deleteOneHost(@CurrentUser SysUserEntity currentUser, @PathVariable(value = "host_id",required = true) Long hostId){
        HostEntity master = hostService.queryByCustomerId(currentUser.getUserId());
        HostEntity hostToApp = hostService.queryObject(hostId);
        if (master.getOrgId() != hostToApp.getOrgId()){
            throw new MinerException("无权操作",403);
        }
        //删除主播信息
        HostEntity hostEntity = hostService.queryObject(hostId);
        if (hostEntity == null){
            throw new MinerException("该条数据不存在！",500);
        }
        hostService.delete(hostId);
        //删除用户信息
        sysUserService.delete(hostEntity.getCId());
        sysUserRoleService.deleteByUserId(hostEntity.getCId());
        //删除用户帐户信息
        return R.ok();
    }

    /**
     * 获取单个主播信息
     * @param hostId
     * @return
     */
    @GetMapping("/host/{host_id}")
    public R getOneHost(@PathVariable(value = "host_id",required = true) Long hostId){
        HostEntity hostEntity = hostService.queryObject(hostId);
        return R.ok().put("host",hostEntity);
    }
    /**
     * 申请成为主播，必须指定机构id，申请后发送通知给管理员
     * @param hostEntity
     * @return
     */
    @PostMapping("/host/apply")
    public R applyHost(@RequestBody @Validated({AddGroup.class}) HostEntity hostEntity){
        hostService.save(hostEntity);
        return R.ok();
    }
    //审批主播,审批成功后发送邮件给用户
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/host/approval/{host_id}")
    public R approvalHost(@CurrentUser SysUserEntity currentUser,@PathVariable(value = "host_id",required = true) Long hostId){
        HostEntity master = hostService.queryByCustomerId(currentUser.getUserId());
        HostEntity hostToApp = hostService.queryObject(hostId);
        if (master.getOrgId() != hostToApp.getOrgId()){
            throw new MinerException("无权操作",403);
        }
        //开始审批
        customerService.approvalHost(hostId,currentUser.getUserId());
        return R.ok();
    }

}
