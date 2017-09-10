package com.miner.service.impl;

import com.miner.common.utils.BeanValidatorUtils;
import com.miner.common.validator.ValidatorUtils;
import com.miner.service.LiveRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miner.dao.EventDao;
import com.miner.entity.EventEntity;
import com.miner.service.EventService;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;


@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private Validator validator;
	@Autowired
	private EventDao eventDao;
	@Autowired
    private LiveRoomService liveRoomService;
	@Value("${txvideo.bizid}")
    private String bizid;
	@Override
	public EventEntity queryObject(Long eventId){
		return eventDao.queryObject(eventId);
	}
	
	@Override
	public List<EventEntity> queryList(Map<String, Object> map){
		return eventDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return eventDao.queryTotal(map);
	}
	//保存活动信息
	@Override
    @Transactional
	public void save(EventEntity event){
	    //判断创建活动的主播所属的机构账户是否有流量剩余
        //设置活动默认值
        event.setCreateTime(new Date())
                .setRoomPoster("http://img0.imgtn.bdimg.com/it/u=1596533918,3569525918&fm=11&gp=0.jpg");
	    //先保存，获取eventId
		eventDao.save(event);
        //设置推流和播放地址
        Map<String, String> playUrls = new HashMap<>();
        playUrls = generatePlayUrl(event.getEventId().toString());
        event.setPushUrl(generatePushUrl(event.getEventStartTime(),event.getEventId().toString()))
                .setLiveFlv(playUrls.get("liveFlv"))
                .setLiveHls(playUrls.get("liveHls"))
                .setLiveRtmp(playUrls.get("liveRtmp"));
        //更新活动信息
        eventDao.update(event);
	}

    /**
     * 生成推流地址
     * @param startTime
     * @param streamId
     * @return
     */
	public String generatePushUrl(Date startTime, String streamId){
	    //过期时间设置为活动开始时间+24小时
        Long txTime = (startTime.getTime() + 24*60*60*1000)/1000;
        StringBuffer stringBuffer = new StringBuffer();
        String pushUrl = stringBuffer.append("rtmp://")
                .append(bizid)
                .append(".livepush.myqcloud.com/live/").append(bizid)
                .append("_")
                .append(streamId)
                .append("?")
                .append("bizid=")
                .append(bizid).append("&")
                .append(liveRoomService.createPushURL(bizid+"_"+streamId,txTime))
                .toString();
        return pushUrl;
    }

    /**
     * 生成播放地址
     * @param streamId
     * @return
     */
    public Map<String, String > generatePlayUrl(String streamId){
	    Map<String, String> playUrls = new HashMap<>();
	    playUrls.put("liveRtmp","rtmp://"+bizid+".liveplay.myqcloud.com/live/"+bizid+"_"+streamId+"");
	    playUrls.put("liveFlv","http://"+bizid+".liveplay.myqcloud.com/live/"+bizid+"_"+streamId+".flv");
        playUrls.put("liveHls","http://"+bizid+".liveplay.myqcloud.com/live/"+bizid+"_"+streamId+".m3u8");
        return playUrls;
    }

	@Override
	public void update(EventEntity event){
		eventDao.update(event);
	}
	
	@Override
	public void delete(Long eventId){
		eventDao.delete(eventId);
	}
	
	@Override
	public void deleteBatch(Long[] eventIds){
		eventDao.deleteBatch(eventIds);
	}
	
}
