package org.powerSystem.service.mem.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.powerSystem.dao.mem.MemScoreRuleDao;
import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.mem.MemScoreRule;
import org.powerSystem.service.mem.MemScoreRuleService;
import org.powerSystem.util.JsonConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
public class MemScoreRuleServiceImpl implements MemScoreRuleService {

	@Autowired
	private  MemScoreRuleDao  memScoreRuleDao;
	
	public void savaMemScoreRule(MemScoreRule m) {
		memScoreRuleDao.save(m);
	}

	public List<MemScoreRule> getMemScoreRule(Map<String,Object> map, int startIndex,
			int pageSize) {
		return memScoreRuleDao.query(map, startIndex, pageSize);
	}

	public String getPage() {
		PageUtil page = new PageUtil();
		page.setRows(getAll());
		page.setTotal(1);
		return JSONObject.fromObject(page,JsonConfigUtil.getJsonConfig()).toString();
	}

	private int getcount(Map<String,Object> map) {
		return memScoreRuleDao.getCount(map);
	}

	public void updateScoreRule(MemScoreRule memScoreRule) {
		memScoreRuleDao.update(memScoreRule);
	}

	public MemScoreRule get(Integer id) {
		return memScoreRuleDao.query(id);
	}

	public List<MemScoreRule> getAll() {
//		List<MemScoreRule> scList=new ArrayList<MemScoreRule>();
//		System.out.println(scList+"--------------------");
//		List<MemScoreRule> list = memScoreRuleDaoImpl.query(null,0,10);
//		System.out.println(list+"-------ssssss------------");
//		for (MemScoreRule memScoreRule : list) {
//			MemScoreRule ms = new MemScoreRule();
//			ms.setScore(1);
//			scList.add(ms);
//		}

		return memScoreRuleDao.query();
	
	}

	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void delMemScoreRank(String ids) {
		// TODO Auto-generated method stub
		memScoreRuleDao.delOfin(ids);
	}

	//转换成json对象
		public String getDeptJson() {
			JSONArray ja=JSONArray.fromObject(getAll());//转换成json对象
			String str=ja.toString();
			return str;
		}

		public void updateMemSr(MemScoreRule msr) {
			// TODO Auto-generated method stub
			memScoreRuleDao.update(msr);
		}

	

}
