package org.powerSystem.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Administrator
 *	2013年9月26日
 * @param <T>
 */
public interface BaseDao<T,ID extends Serializable> {

	/**
	 * 保存信息
	 * @param t
	 * 	实体类对象
	 */
	public void save(T t);
	/**
	 * 更新信息，注意：必须有主键
	 * @param t
	 */
	public void update(T t);
	/**
	 * 查询所有的数据信息（慎用）
	 * 按照主鍵排序
	 * @return
	 */
	public List<T> query();
	/**
	 * 按照条件查询，主键order by
	 * @param map<String Object> 
	 * 条件参数
	 * 		key：实体类属性名    ==  数据库字段名
	 * 		value：条件参数	
	 * @return
	 */

	public List<T> query(Map<String, Object> map);
	/**
	 * 查询单个实体数据
	 * @param id
	 * 		主键
	 * @return
	 * 		T 实体
	 */
	public T query(ID id);
	/**
	 * 分页使用，查询所有的条数，如无参数，请传值：null
	* @param map<String,Object>
	 * 		条件参数
	 * 		key：实体类属性名    ==  数据库字段名
	 * 		value：条件参数		
	 * @return
	 * 		int
	 */
	public int getCount(Map<String,Object> map);
	/**
	 * 
	 * 分页查询，
	 * @param map<String,Object>
	 * 		条件参数
	 * 		key：实体类属性名    ==  数据库字段名
	 * 		value：条件参数
	 * @param startIndex
	 * 		开始的条数
	 * @param pageSize
	 * 		每页显示的条数
	 * @return
	 */
	public List<T> query(final Map<String, Object> map, final int startIndex, final int pageSize);
	/**
	 * 分页查询，
	 * @param map<String,Object>
	 * 		条件参数
	 * 		key：实体类属性名    ==  数据库字段名
	 * 		value：条件参数
	 * @param startIndex
	 * 		开始的条数
	 * @param pageSize
	 * 		每页显示的条数
	 * @param orderby
	 * 		需要排序的字段
	 * @return
	 */
	public List<T> query(final Map<String, Object> map, final int startIndex,final int pageSize, final String orderby);
	
	/**
	 * 删除信息
	 * @param id
	 * 		主键
	 */
	public void del(ID id);
	/**
	 * 以实体类形式删除
	 * @param t
	 */
	public void del(T t);
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delOfin(String ids);
	/**
	 * 获得总页数
	 */
	public int getAllPages(int allCount,int pageSize);
}
