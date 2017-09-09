package org.powerSystem.service.mem;

import java.util.List;
import java.util.Map;

import org.powerSystem.entity.mem.MemActivity;
import org.powerSystem.entity.mem.MemActivitydetail;


public interface MemActivityService {

	/**
	 * 活动定制
	 */
	public boolean saveActivity(MemActivity actcity,List<MemActivitydetail> detailList) throws RuntimeException;
	/**
	 * 活动更新
	 */
	public void updateActivity(MemActivity actcity) throws RuntimeException;
	/**
	 * 活动查询
	 */
	public List<MemActivity> get(Map map ,int startIndex,int pageSize);
	/**
	 * 删除活动
	 */
	public boolean delActivity(String ids);
	
	/**
	 * 拿一堆转json   String
	 * @param map
	 * @return
	 */
	public String getPage(Map map,int startIndex,int pageSize);
	
	/**
	 * 取一个对象
	 * @param id
	 * @return
	 */
	public MemActivity get(int id);
	
	public int getCount(Map map);
}
