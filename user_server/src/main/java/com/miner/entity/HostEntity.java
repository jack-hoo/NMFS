package com.miner.entity;

import java.io.Serializable;
import java.util.Date;

import com.miner.common.validator.group.AddGroup;
import com.miner.common.validator.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


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
    @NotNull(message = "更新时id不能为空" ,groups = UpdateGroup.class)
    private Long hostId;
    //所属机构id
    @NotNull(message = "个人申请主播时必须指定机构",groups = AddGroup.class)
    private Long orgId;
    //主播账号id
    private Long cId;
    //主播名称
    @NotBlank(message = "名字不能为空")
    private String hostName;
    //主播qq


    private String hostQq;
    //主播性别：0男，1女
    @NotNull(message = "性别不能为空")
    private Integer hostSex;
    //主播邮箱
    private String hostEmail;
    //主播电话
    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",message = "请输入正确的手机号！")
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
