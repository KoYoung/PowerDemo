package org.powerSystem.service.mem.impl;

import org.powerSystem.dao.mem.MemOrderDetailDao;
import org.powerSystem.entity.mem.MemOrderDetail;
import org.powerSystem.service.mem.MemOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemOrderDetailServiceImpl implements MemOrderDetailService {
	@Autowired
	private MemOrderDetailDao memOrderDetailDao;

	public void saveMemOrderDetail(MemOrderDetail detail)
			throws RuntimeException {
		// TODO Auto-generated method stub

	}

	public void saveMemOrderDetail(Object detail) throws RuntimeException {
		// TODO Auto-generated method stub

	}
	/**
	 * 根据Order_no查找订单详细表
	 * 
	 */
	public MemOrderDetail queryAllOrderDetail(String orderNo){
		return memOrderDetailDao.query(orderNo);
	}

	public MemOrderDetailDao getMemOrderDetailDao() {
		return memOrderDetailDao;
	}

	public void setMemOrderDetailDao(MemOrderDetailDao memOrderDetailDao) {
		this.memOrderDetailDao = memOrderDetailDao;
	}

	public MemOrderDetail queryAllOrderDetail(Integer orderNo) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
