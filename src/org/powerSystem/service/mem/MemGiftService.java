package org.powerSystem.service.mem;

import java.util.List;
import java.util.Map;

import org.powerSystem.entity.mem.MemGift;
import org.powerSystem.entity.mem.MemMember;

public interface MemGiftService {
		/**
		 * 查询所有
		 * @return
		 */
	public List<MemGift> queryMemGiftAll(Map map);
	/**
	 * 按照id查找
	 * @param id
	 * @return
	 */
	public MemGift get(Integer id);
	/**
	 * 添加
	 * @param mg
	 */
	public void savaMemGift(MemGift mg);
	/**
	 * 修改
	 * @param mg
	 */
	public void updateGift(MemGift mg);
	/**
	 * 删除
	 * @param ids
	 */
	public void delMemGift(String ids);
	/**
	 * 更新
	 * @param mg
	 */
	public void updateMemGift(MemGift mg);
	
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
	public List<MemGift> queryMem(Map<String,Object> map ,int index,int size);
	
	//分页查询全部
	public List<MemGift> queryAllMem();
	//将List集合转换成json对象
	public String getPage(Map map, int startIndex, int pageSize);
//	//转换Json
	public String getDeptJson(Map map);
	/**
	 * 获取所要查询的会员信息的总条数
	 */
	public int getCount(Map<String, Object> map);
}
