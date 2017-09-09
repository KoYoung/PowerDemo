package org.powerSystem.service.mem.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.powerSystem.dao.mem.MemGiftDao;
import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.mem.MemGift;
import org.powerSystem.service.mem.MemGiftService;
import org.powerSystem.util.JsonConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemGiftServiceImpl implements MemGiftService {
	@Autowired
	public MemGiftDao memGiftDao;

	//转换成json对象
		public String getDeptJson(Map map) {
			JSONArray ja=JSONArray.fromObject(queryMemGiftAll(map));//转换成json对象
			String str=ja.toString();
			return str;
		}

		/**
		 * 分页查询
		 * @param map
		 * 		条件参数
		 * @param index
		 * 		开始条数
		 * @param size
		 * 		每页大小
		 * @return
		 * 		list<MemGift>
		 */
		public List<MemGift> queryMemGift(Map<String, Object> map, int index, int size){
			List<MemGift> list=memGiftDao.query(map, index, size);
			return list;
		}
		
		
		/**
		 * 查询全部
		 */
		public List<MemGift> queryAllMem() {
			return memGiftDao.query();
		}

		/**
		 * 转换json对象
		 */
		public String getPage(Map map, int startIndex, int pageSize) {
			PageUtil pages=new PageUtil();
			pages.setRows(queryMemGift(map, startIndex, pageSize));
			pages.setTotal(getCount(map));
			JsonConfig config =JsonConfigUtil.getJsonConfig() ;
//			config.setExcludes(new String[]{"memCards"}); //屏蔽对象
			String str = JSONObject.fromObject(pages,config).toString();
			return str;
		}

		/**
		 * 分页查询
		 * @param map
		 * 		条件参数
		 * @param index
		 * 		开始条数
		 * @param size
		 * 		每页大小
		 * @return
		 * 		list<MemMember>
		 */
		public List<MemGift> queryMem(Map<String, Object> map, int index,int size) {
			List<MemGift> list=memGiftDao.query(map, index, size);
			return list;
		}
		
		/**
		 * 查询数据库中的总数据
		 * @param map按条件传参
		 * @return int
		 */
		public int getCount(Map<String, Object> map) {
			return memGiftDao.getCount(map);
		}
		
		/**
		 * 查询全部
		 */
		public List<MemGift> queryMemGiftAll(Map map) {
			// TODO Auto-generated method stub
			List list=memGiftDao.query(map);
			System.out.println(list.size()+"=====size========");
			return list;
		}

		/**
		 * 按照id查找
		 * @param gift_id
		 * @return
		 */
		public MemGift get(Integer id) {
			// TODO Auto-generated method stub
			return memGiftDao.query(id);
		}

		/**
		 * 保存
		 */
		public void savaMemGift(MemGift mg) {
			memGiftDao.save(mg);
		}

		/**
		 * 修改
		 */
		public void updateGift(MemGift mg) {
			memGiftDao.update(mg);
		}

		/**
		 * 删除
		 */
		public void delMemGift(String ids) {
			memGiftDao.delOfin(ids);
		}

		public void updateMemGift(MemGift mg) {
			memGiftDao.update(mg);
		}
}
