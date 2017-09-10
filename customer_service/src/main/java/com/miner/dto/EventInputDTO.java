package com.miner.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miner.entity.EventEntity;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by hushangjie on 2017/9/7.
 */
@Data
public class EventInputDTO {
    //机构id
    @NotNull(message = "活动组织机构不能为空")
    private Long orgId;
    //活动主播id
    @NotNull(message = "主播不能为空")
    private Long hostId;
    @NotBlank(message = "活动标题不能为空")
    //活动标题
    private String eventTitle;
    //直播类别id
    @NotNull(message = "活动类别不能为空")

    private Integer eventTypeId;
    //活动预计开始时间：主要用来配合生成推流过期时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "活动预计开始时间不能为空")
    private Date eventStartTime;
    //直播海报封面
    private String roomPoster;

    public EventEntity convertToEntity(EventInputDTO eventInputDTO){
        EventEntity eventEntity = new EventEntity();
        BeanUtils.copyProperties(eventInputDTO,eventEntity);
        return eventEntity;
    }

    public EventInputDTO convertForDTO(EventEntity eventEntity){
        EventInputDTO eventInputDTO = new EventInputDTO();
        BeanUtils.copyProperties(eventEntity,eventInputDTO);
        return eventInputDTO;
    }

}
