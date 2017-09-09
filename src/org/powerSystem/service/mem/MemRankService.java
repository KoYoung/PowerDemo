package org.powerSystem.service.mem;

import java.util.List;
import java.util.Map;

import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemRank;
import org.powerSystem.entity.mem.MemScoreRule;

public interface MemRankService {
	/**
	 * 查询所有折扣规则
	 */
	public List<MemRank> queryMemRankAll();
	/**
	 * 查询单条折扣规则
	 */
	public MemRank getRank(Integer id);
	/**
	 * 获取卡的ID
	 * @param id
	 * @return
	 */
	public MemCard getCard(String id);
	/**
	 * 添加折扣规则
	 */
	public void savaMemRank(MemRank mr);
	/**
	 * 修改折扣规则
	 */
	public void updateRank(MemRank mr);
	/**
	 * 折扣查询
	 */
	public List<MemRank> getMemRank(Map<String,Object> map,int startIndex,int pageSize);
	
	/**
	 * 统计折扣规则总条数
	 * @param map
	 * @return
	 */
	public int getCount(Map<String,Object> map);
	/**
	 * 删除
	 * @param rankId
	 */
	public void delMemRank(String ids);
	
//	//转换Json
	public String getDeptJson();
	/**
	 * 更新
	 * @param mr
	 */
	public void updateMemRank(MemRank mr);
	
	/**
	 * 通过卡号查询卡的折扣
	 * @param cardno
	 * @return
	 */
	public float query(Integer rankId);
}
