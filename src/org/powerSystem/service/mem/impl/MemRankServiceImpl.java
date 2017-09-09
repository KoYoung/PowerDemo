package org.powerSystem.service.mem.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;

import org.powerSystem.dao.mem.MemRankDao;
import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemRank;
import org.powerSystem.service.mem.MemRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemRankServiceImpl implements MemRankService {
	@Autowired
	private MemRankDao memRankDao;
	/**
	 * 查看全部
	 */
	public List<MemRank> queryMemRankAll() {
		return memRankDao.query();
	}

	public MemRank getRank(Integer id) {
		// TODO Auto-generated method stub
		return memRankDao.query(id);
	}

	/**
	 * 添加
	 */
	public void savaMemRank(MemRank mr) {
		memRankDao.save(mr);
	}

	public void updateRank(MemRank mr) {
		// TODO Auto-generated method stub
		memRankDao.update(mr);
	}

	public List<MemRank> getMemRank(Map<String, Object> map, int startIndex,
			int pageSize) {
		return memRankDao.query(map,startIndex,pageSize);
	}

	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return memRankDao.getCount(map);
	}

	/**
	 * 删除
	 * @param rankId
	 */
	public void delMemRank(String ids) {
		// TODO Auto-generated method stub
		memRankDao.delOfin(ids);
	}
	//转换成json对象
	public String getDeptJson() {
		JSONArray ja=JSONArray.fromObject(queryMemRankAll());//转换成json对象
		String str=ja.toString();
		return str;
	}
	

	public MemRankDao getMemRankDao() {
		return memRankDao;
	}

	public void setMemRankDao(MemRankDao memRankDao) {
		this.memRankDao = memRankDao;
	}

	public MemRankServiceImpl(MemRankDao memRankDao) {
		super();
		this.memRankDao = memRankDao;
	}

	public MemRankServiceImpl() {
		super();
	}

	public void updateMemRank(MemRank mr) {
		// TODO Auto-generated method stub
		memRankDao.update(mr);
	}

	public MemCard getCard(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public float query(Integer rankId) {
	    float dis=memRankDao.query(rankId).getDiscount();
		return dis;
	}

	

}
