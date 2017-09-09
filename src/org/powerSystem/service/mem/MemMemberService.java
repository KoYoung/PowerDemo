package org.powerSystem.service.mem;

import java.util.List;
import java.util.Map;
import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.mem.MemMember;


public interface MemMemberService{
	

	/**
	 * 查询所有的会员信息
	 */
	public List<MemMember> queryMemberAll();
	
	/**
	 * 会员信息修改
	 * @param member
	 */
	public void updateMember(MemMember member);
	/**
	 * 会员信息录入
	 * @param member
	 * @return
	 */
	public void saveMember(MemMember member);
	
	/**
	 * 依照主键查询一个会员信息
	 * @param memId
	 * @return
	 */
	public MemMember queryMemberById(Integer memId);
	
	/**
	 * 删除会员信息
	 */
	public void delMember(String ids );
	
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
	public List<MemMember> queryMem(Map<String,Object> map ,int index,int size);
	
	/**
	 * 根据条件查询所有会员
	 * 
	 * @param memMember
	 *            条件参数
	 * @param page
	 * @param max
	 * @return
	 */
	public String query(MemMember member,int page,int max);
	
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
	
	
	/**
	 * 类型，等级，性别的combobox
	 * @return
	 */
	public String getCombobox();
	
	/**
	 * 获取所要查询的会员信息的总条数
	 */
	public int getCount(Map<String, Object> map);
	//分页查询全部
	public List<MemMember> queryAllMem();
	//将List集合转换成json对象
	public String getPage(Map map, int startIndex, int pageSize);
	
	/**
	 * 根据姓名查询
	 */
	public List<MemMember> queryMemberByName();
	
	
	
}
