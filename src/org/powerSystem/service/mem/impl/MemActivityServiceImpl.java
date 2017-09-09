package org.powerSystem.service.mem.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.powerSystem.dao.mem.MemActivityDao;
import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.mem.MemActivity;
import org.powerSystem.entity.mem.MemActivitydetail;
import org.powerSystem.service.mem.MemActivityService;
import org.powerSystem.service.mem.MemActivitydetailService;
import org.powerSystem.util.JsonConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemActivityServiceImpl implements MemActivityService{
	@Autowired
	private MemActivitydetailService memActivitydetailServiceImpl;
	@Autowired
	private MemActivityDao memActivityDaoImpl;
	public boolean saveActivity(MemActivity actcity,List<MemActivitydetail> detailList) throws RuntimeException {
		memActivityDaoImpl.save(actcity);
		//save 商品
		if(detailList!=null){
			for (MemActivitydetail memActivitydetail : detailList) {
				memActivitydetail.setActId(actcity.getActId());
				memActivitydetailServiceImpl.saveActivitydetail(memActivitydetail);
			}
		}
		return true;
	}

	public void updateActivity(MemActivity actcity) throws RuntimeException {
		memActivityDaoImpl.update(actcity);
	}

	public List<MemActivity> get(Map map ,int startIndex,int pageSize) {
		//------
		return memActivityDaoImpl.query();
	}

	public String getPage(Map map,int startIndex,int pageSize) {
		PageUtil page = new PageUtil();
		page.setRows(get(map,startIndex,pageSize));
		page.setTotal(getCount(map));
		String str = JSONObject.fromObject(page,JsonConfigUtil.getJsonConfig()).toString();
		return str;
	}

	public MemActivity get(int id) {
		//memActivityDaoImpl.get(id);
		return null;
	}
	public int getCount(Map map) {
		
		return 0;
	}

	public boolean delActivity(String ids) {
		try{
			memActivityDaoImpl.delOfin(ids);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
