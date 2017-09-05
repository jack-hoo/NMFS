package com.miner.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 直播活动表
 * 
 * @author hushangjie
 * @email 979783618@qq.com
 * @date 2017-09-05 10:57:43
 */
public class EventEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//活动id
	private Long eventId;
	//机构id
	private Long orgId;
	//活动主播id
	private Long hostId;
	//活动标题
	private String eventTitle;
	//直播类别id
	private Integer eventTypeId;
	//活动预计开始时间：主要用来配合生成推流过期时间
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

	/**
	 * 设置：活动id
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	/**
	 * 获取：活动id
	 */
	public Long getEventId() {
		return eventId;
	}
	/**
	 * 设置：机构id
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：机构id
	 */
	public Long getOrgId() {
		return orgId;
	}
	/**
	 * 设置：活动主播id
	 */
	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}
	/**
	 * 获取：活动主播id
	 */
	public Long getHostId() {
		return hostId;
	}
	/**
	 * 设置：活动标题
	 */
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	/**
	 * 获取：活动标题
	 */
	public String getEventTitle() {
		return eventTitle;
	}
	/**
	 * 设置：直播类别id
	 */
	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	/**
	 * 获取：直播类别id
	 */
	public Integer getEventTypeId() {
		return eventTypeId;
	}
	/**
	 * 设置：活动预计开始时间：主要用来配合生成推流过期时间
	 */
	public void setEventStartTime(Date eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	/**
	 * 获取：活动预计开始时间：主要用来配合生成推流过期时间
	 */
	public Date getEventStartTime() {
		return eventStartTime;
	}
	/**
	 * 设置：直播结束时间，由阿里回调设置
	 */
	public void setEventEndTime(Date eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	/**
	 * 获取：直播结束时间，由阿里回调设置
	 */
	public Date getEventEndTime() {
		return eventEndTime;
	}
	/**
	 * 设置：融云聊天室
	 */
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	/**
	 * 获取：融云聊天室
	 */
	public String getEventDesc() {
		return eventDesc;
	}
	/**
	 * 设置：直播间观看人数：通过查询直播间拉流人数+点播观看人数+自定义
	 */
	public void setEventVisitedNum(Integer eventVisitedNum) {
		this.eventVisitedNum = eventVisitedNum;
	}
	/**
	 * 获取：直播间观看人数：通过查询直播间拉流人数+点播观看人数+自定义
	 */
	public Integer getEventVisitedNum() {
		return eventVisitedNum;
	}
	/**
	 * 设置：融云聊天室id
	 */
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	/**
	 * 获取：融云聊天室id
	 */
	public String getChatRoomId() {
		return chatRoomId;
	}
	/**
	 * 设置：直播海报封面
	 */
	public void setRoomPoster(String roomPoster) {
		this.roomPoster = roomPoster;
	}
	/**
	 * 获取：直播海报封面
	 */
	public String getRoomPoster() {
		return roomPoster;
	}
	/**
	 * 设置：直播间状态：0-未开始1-直播中2-结束
	 */
	public void setLiveStatus(Integer liveStatus) {
		this.liveStatus = liveStatus;
	}
	/**
	 * 获取：直播间状态：0-未开始1-直播中2-结束
	 */
	public Integer getLiveStatus() {
		return liveStatus;
	}
	/**
	 * 设置：直播间推流地址
	 */
	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}
	/**
	 * 获取：直播间推流地址
	 */
	public String getPushUrl() {
		return pushUrl;
	}
	/**
	 * 设置：直播间拉流rtmp地址
	 */
	public void setLiveRtmp(String liveRtmp) {
		this.liveRtmp = liveRtmp;
	}
	/**
	 * 获取：直播间拉流rtmp地址
	 */
	public String getLiveRtmp() {
		return liveRtmp;
	}
	/**
	 * 设置：直播间拉流hls地址
	 */
	public void setLiveHls(String liveHls) {
		this.liveHls = liveHls;
	}
	/**
	 * 获取：直播间拉流hls地址
	 */
	public String getLiveHls() {
		return liveHls;
	}
	/**
	 * 设置：直播间拉流hlv地址
	 */
	public void setLiveFlv(String liveFlv) {
		this.liveFlv = liveFlv;
	}
	/**
	 * 获取：直播间拉流hlv地址
	 */
	public String getLiveFlv() {
		return liveFlv;
	}
	/**
	 * 设置：录像回看地址
	 */
	public void setReplayUrl(String replayUrl) {
		this.replayUrl = replayUrl;
	}
	/**
	 * 获取：录像回看地址
	 */
	public String getReplayUrl() {
		return replayUrl;
	}
	/**
	 * 设置：记录创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：记录创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
