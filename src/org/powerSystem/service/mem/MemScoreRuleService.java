package org.powerSystem.service.mem;

import java.util.List;
import java.util.Map;

import org.powerSystem.entity.mem.MemScoreRule;

/**
 * 积分规则业务
 * @author Administrator
 *
 * @param <MemScoreRule>
 * @param <Integer>
 */
public interface MemScoreRuleService {
	/**
	 * 查询所有积分规则
	 * @param id
	 * @return
	 */
	public List<MemScoreRule> getAll();
	/**
	 * 查询单条积分规则
	 * @param id
	 * @return
	 */
	public MemScoreRule get(Integer id);
	/**
	 * 添加积分规则
	 * 
	 */
	public void savaMemScoreRule(MemScoreRule m);
	/**
	 * 修改积分规则
	 * @param m
	 */
	public void updateScoreRule(MemScoreRule memScoreRule);
	/**
	 * 积分查询
	 */
	public List<MemScoreRule> getMemScoreRule(Map<String,Object> map,int startIndex,int pageSize);
	/**
	 * 拿一堆json  String
	 */
	public String getPage();
	/**
	 * 统计积分规则表表总条数
	 * @param map
	 * @return
	 */
	public int getCount(Map<String,Object> map);
	/**
	 *转换Json
	 */
	public String getDeptJson();
	/**
	 * 删除
	 * @param rankId
	 */
	public void delMemScoreRank(String ids);
	/**
	 * 更新
	 * @param msr
	 */
	public void updateMemSr(MemScoreRule msr);
}
