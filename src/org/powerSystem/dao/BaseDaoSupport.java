package org.powerSystem.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;

@SuppressWarnings("rawtypes")
public abstract class BaseDaoSupport<T, ID extends Serializable> extends
		hibernateTemplateUtil implements BaseDao<T, ID> {

	//属性名称
	private Field[] Fields = null;

	public void save(Object t) {
		hibernateTemplate.save(t);
	}

	public void update(Object t) {
		hibernateTemplate.update(t);
	}

	public int getCount(Map<String, Object> map) {
		try {
			return Integer.parseInt(hibernateTemplate
					.find(getHQL(new StringBuffer("select Count(*) from "
							+ classname), ParamAnalysis.analysis(map, Fields), null)).get(0).toString());
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 如果使用order by 不是主键， 请使用方法：public List<T> query(final Map<String, Object>
	 * map, final int startIndex, final int pageSize, final String orderby)
	 */

	public List<T> query() {
		return query(null, -1, -1, null);
	}

	public List<T> query(Map<String, Object> map) {

		return query(map, -1, -1, null);
	}

	public List<T> query(Map<String, Object> map, int startIndex, int pageSize) {
		return query(map, startIndex, pageSize, null);
	}

	public List<T> query(Map<String, Object> map, int startIndex, int pageSize,
			String orderby) {
		return get(ParamAnalysis.analysis(map, Fields), startIndex, pageSize, orderby);
	}

	@SuppressWarnings("unchecked")
	private List<T> get(final Map<String, Object> map, final int startIndex,
			final int pageSize, final String orderby) {
		List<T> list = hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(getHQL(map, orderby));
				if (startIndex >= 0) {
					query.setFirstResult(startIndex);
					query.setMaxResults(pageSize);
				}
				List<T> list = query.list();
				return list;
			}
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	public T query(ID id) {
		return (T) hibernateTemplate.get(classname, id);
	}

	public void del(ID id) {
		hibernateTemplate.delete(classname, query(id));
	}

	public void del(T t){
		hibernateTemplate.delete(classname, t);
	}
	
	public void delOfin(String ids) {
		hibernateTemplate.bulkUpdate("delete from " + classname + " where "
				+ getPk() + " in(" + ids + ")");
	}

	/**
	 * 获取实体类主键名称
	 * 
	 * @return
	 */
	public String getPk() {
		ClassMetadata meta = (ClassMetadata) hibernateTemplate
				.getSessionFactory().getClassMetadata(classname);
		return meta.getIdentifierPropertyName();
	}

	/**
	 * 获取T的属性名 实体类名称
	 */
	private String classname = null;
	
	{
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		Type[] types = pt.getActualTypeArguments();
		Class cl = (Class) types[0];
		Fields = cl.getDeclaredFields();
		classname = cl.getName();
	}

	/**
	 * 返回hql语句
	 */
	private String getHQL(Map<String, Object> map, String orderby) {
		StringBuffer hql = new StringBuffer("from " + classname);
		return getHQL(hql, map, orderby);
	}

	private String getHQL(StringBuffer hql, Map<String, Object> map,
			String orderby) {
		if (map != null && map.size() > 0) {
			hql.append(" where 1=1 ");

			for (Map.Entry<String, Object> enety : map.entrySet()) {
				hql.append(" and " + enety.getKey() + " " + enety.getValue());
			}
		}
		if (orderby != null) {
			hql.append(" order by " + orderby + " desc");
		} else {
			hql.append(" order by " + getPk() + " desc");
		}
		return hql.toString();
	}
	
	
	/**
	 * 获得总页数
	 */
	public int getAllPages(int allCount,int pageSize)
	{
		int totalPages=0;
		if(allCount%pageSize==0)
		{
			totalPages=allCount/pageSize;
		}else
		{
			totalPages=allCount/pageSize+1;

		}
		return totalPages;
	}
}
