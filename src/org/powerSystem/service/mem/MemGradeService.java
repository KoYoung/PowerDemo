package org.powerSystem.service.mem;

import java.util.List;

import org.powerSystem.entity.mem.MemRank;


public interface MemGradeService {
	/**
	 * 等级定制:向数据库添加卡等级,等级对应的折扣,等级对应的限定积分
	 */
	public boolean saveGrade(MemRank memRank);
	/**
	 *更新等级:主要是更新等级对应的折扣和限定积分
	 */
	public boolean update(MemRank memRank);
	/**
	 *获取所有等级
	 */
	public List<MemRank>  getAllRank();
	/**
	 *通过id查询等级
	 */
	public MemRank  getRankById(int id);
	/**
	 *卡升级 ,通过卡号获取卡总积分与等级表中的指定积分比较,判断是否升级,升几级. 
	 */
	public void upgrade(String cardNo);
	
}	
