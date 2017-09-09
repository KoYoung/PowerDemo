package org.powerSystem.service.mem;

import java.util.List;
import java.util.Map;

import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.mem.MemOrder;
import org.powerSystem.entity.mem.MemOrderDetail;


/**
 * 销售订单
 *
 */
public interface MemOrderService  {
	/**
	 * 查询所有的订单信息
	 */
	public List<MemOrder> queryOrderAll();
	
	/**
	 * 会员信息修改
	 * @param member
	 */
	public void updateMember(MemOrder memOrder);
	/**
	 * 订单信息录入
	 * @param Orderr
	 * @return
	 */
	public void saveOrder(MemOrder memOrder);
	
	/**
	 * 依照主键查询一个订单信息
	 * @param memId
	 * @return
	 */
	public MemOrder queryOrderById(String OrderNo);
	
	/**
	 * 删除订单信息
	 */
	public void delOrder(String ids);
	
	/**
	 * 分页查询
	 * @param map
	 * 		条件参数
	 * @param index
	 * 		开始条数
	 * @param size
	 * 		每页大小
	 * @return
	 * 		list<MemOrder>
	 */
	public List<MemOrder> queryOrder(Map<String,Object> map ,int index,int size) ;
	
	/**
	 * 会员信息分页查询 对分页进行封装
	 * @param map
	 * 		条件参数
	 * @param index
	 * 		开始条数
	 * @param size
	 * 		每页大小
	 * @return
	 * 		PageUtil
	 */
	public PageUtil queryPage(Map<String, Object> map,int startIndex,int pageSize);
	public PageUtil getPage(int startIndex, int pageSize) ;
	//public List<MemberUtil> queryMemUtil(int startIndex,int pageSize);
	//public List<MemberUtil> queryMemUtil(Map<String, Object> map,int startIndex,int pageSize);
	
	
/*	*//**
	 * 类型，等级，性别的combobox
	 * @return
	 *//*
	public String getCombobox();
	*/
	/**
	 * 获取所要查询的订单信息的总条数
	 */
	public int getCount(Map<String, Object> map);
	//分页查询全部
	public List<MemOrder> queryAllOrder();
	//将List集合转换成json对象
	public String getPage(Map map, int startIndex, int pageSize);
	//转换json对象
	public String getDeptJson();
}
