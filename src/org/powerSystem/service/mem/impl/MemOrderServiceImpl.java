package org.powerSystem.service.mem.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.powerSystem.dao.mem.MemOrderDao;
import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemOrder;
import org.powerSystem.entity.mem.MemOrderDetail;
import org.powerSystem.entity.mem.MemOrderUtil;

import org.powerSystem.service.mem.MemOrderDetailService;
import org.powerSystem.service.mem.MemOrderService;
import org.powerSystem.util.JsonConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class MemOrderServiceImpl implements MemOrderService {
	@Autowired
	private MemOrderDao memOrderDao;
	private MemOrder memOrder;
	
	
	public MemOrderDao getMemOrderDao() {
		return memOrderDao;
	}
	public void setMemOrderDao(MemOrderDao memOrderDao) {
		this.memOrderDao = memOrderDao;
	}
	public MemOrder getMemOrder() {
		return memOrder;
	}
	public void setMemOrder(MemOrder memOrder) {
		this.memOrder = memOrder;
	}
	/**
	 * 查询所有订单
	 */
	public List<MemOrder> queryOrderAll() {
		return memOrderDao.query();
	}
	/**
	 * 修改订单
	 */
	public void updateMember(MemOrder memOrder) {
		// TODO Auto-generated method stub
		memOrderDao.update(memOrder);
	}
	/**
	 * 保存订单
	 */
	public void saveOrder(MemOrder memOrder) {
		System.out.println(memOrder.getOrderRemark()+"serviceimpl");
		memOrderDao.save(memOrder);
	}
	/**
	 * 根据订单号查询
	 */
	public MemOrder queryOrderById(String OrderNo) {
		// TODO Auto-generated method stub
		memOrderDao.query(OrderNo);
		return null;
	}
	/**
	 * 根据订单号删除
	 */
	public void delOrder(String ids) {
		// TODO Auto-generated method stub
		 memOrderDao.delOfin(ids);
	}
	/**
	 * 订单分页查询
	 */
	public List<MemOrder> queryOrder(Map<String, Object> map, int index,
			int size) {
		// TODO Auto-generated method stub
		List<MemOrder> list=memOrderDao.query(map, index, size);
		return list;
	}
	
	public PageUtil queryPage(Map<String, Object> map, int startIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		List<MemOrderUtil> list = queryMemOrderUtil(map, startIndex, pageSize);
		int n = getCount(map);
		PageUtil p = new PageUtil();
		p.setRows(list);
		p.setTotal(n);
		return p;
	}
	
	
	public PageUtil getPage(int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		List<MemOrderUtil> list = queryMemOrderUtil(null, startIndex, pageSize);
		int n = getCount(null);
		PageUtil p = new PageUtil();
		p.setRows(list);
		p.setTotal(n);
		return p;
	}
private List<MemOrderUtil> queryMemOrderUtil(Map<String, Object> map,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
	List<MemOrderUtil> mList = new ArrayList<MemOrderUtil>();
	List<MemOrder> list = queryOrder(map, startIndex, pageSize);
	for (MemOrder memOrder : list) {
		MemOrderUtil m = new MemOrderUtil();
		m.setMemOrder(memOrder);
		m.setStatusName(pageSize);
		mList.add(m);
	}
	return mList;
	}
	/*	public String getCombobox() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/**
	 * 查询总条数
	 */
	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return memOrderDao.getCount(map);
	}
	/**
	 * 查询所有订单
	 */
	public List<MemOrder> queryAllOrder() {
		// TODO Auto-generated method stub
		return memOrderDao.query();
	}
	/**
	 * 转换json对象
	 */
	public String getPage(Map map, int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		PageUtil pages=new PageUtil();
		pages.setRows(queryOrder(map, startIndex, pageSize));
		pages.setTotal(getCount(map));
		String str = JSONObject.fromObject(pages,JsonConfigUtil.getJsonConfig()).toString();
		return str;
	}
	public String getDeptJson() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
