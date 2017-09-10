package com.miner.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javafx.beans.DefaultProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;



/**
 * 直播活动表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
@Data
@Accessors(chain = true)
public class EventEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//活动id
	private Long eventId;
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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "活动预计开始时间不能为空")
	private Date eventStartTime;
	//直播结束时间，由阿里回调设置

	private Date eventEndTime;
	//融云聊天室
	private String eventDesc;
	//直播间观看人数：通过查询直播间拉流人数+点播观看人数+自定义
	private Integer eventVisitedNum;
	//融云聊天室id
	private String chatRoomId;
	//直播海报封面

	private String roomPoster;
	//直播间状态：0-未开始1-直播中2-结束
	private Integer liveStatus;
	//直播间推流地址
	private String pushUrl;
	//直播间拉流rtmp地址
	private String liveRtmp;
	//直播间拉流hls地址
	private String liveHls;
	//直播间拉流hlv地址
	private String liveFlv;
	//录像回看地址
	private String replayUrl;
	//记录创建时间
	private Date createTime;


}
