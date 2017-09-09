package org.powerSystem.dao.mem;

import java.util.List;

import org.powerSystem.dao.BaseDao;
import org.powerSystem.entity.mem.MemOrder;
import org.powerSystem.entity.mem.MemOrderDetail;



/**
 * 销售订单
 *
 */
public interface MemOrderDao extends BaseDao<MemOrder, String>  {
	
	
	/**
	 * 根据card_NO 查找卡所有订单
	 *//*
	public List<MemOrder> queryAllOrder(String cardNo);
	*//**
	 * 根据Order_no查找订单详细表
	 * 
	 *//*
	public MemOrderDetail queryAllOrderDetail(String orderNo);*/
	/**
	 *删除订单
	 */
	/*public void delMemOrder(int cardNo);*/
}
