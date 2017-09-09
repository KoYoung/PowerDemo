package org.powerSystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.powerSystem.dao.ShareDataBaseDao;
import org.powerSystem.entity.ShareDataBase;
import org.powerSystem.util.DataDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareDataBaseServiceImpl  {

	@Autowired
	private ShareDataBaseDao shareDataBaseDaoImpl;
	
	public void updateData(ShareDataBase data) {

	}

	public void saveData(ShareDataBase data) {

	}

	/**
	 * 把数据字典装入内存格式为Map<Integer,Map<Integer,string>>
	 * 
	 * Map<类型ID , map< 子类型ID  类型名称>>
	 */
	public void queryData() {
		List<ShareDataBase> data = shareDataBaseDaoImpl.query();
		for (ShareDataBase d : data) {
			if (DataDictionary.dataMap.get(d.getBaseParent()) != null)
				continue;
			if (d.getBaseParent() == null) {
				Map<String, String> map = new HashMap<String, String>();
				for (ShareDataBase shareDataBase : data) {
					if (d.getBaseNo() .equals(shareDataBase.getBaseParent())) {
						map.put(shareDataBase.getBaseNo().toString(),
								shareDataBase.getBaseName());
					}
				}
				DataDictionary.dataMap.put(d.getBaseNo(), map);
			}
		}
	}
}
