package org.powerSystem.service.dept;

import org.powerSystem.entity.ShareStaff;

public interface ShareStaffService {
	/**
	 * 根据条件查询所有员工
	 * 
	 * @param staff
	 *            条件参数
	 * @param page
	 * @param max
	 * @return
	 */
	public String query(ShareStaff staff, int page, int max);
	/**
	 * 通过部门ID查询所有员工组装成树格式
	 * 主要用于角色配置
	 * @param deptid
	 * @return
	 */
	public String queryForTree(Integer deptid);
	/**
	 * 添加修改在这一个页面
	 * 
	 * @param staff
	 */
	public void saveStaff(ShareStaff staff);

	/**
	 * 在数据库允许删除的情况下，直接删除数据
	 * 不然报异常
	 * @param ids
	 */
	public void deleteStaff(String ids);

	/**
	 * 根据部门ID验证是否存在员工
	 * 返回大于0的数，就说明有员工存在
	 * 这个主要用于删除部门时的验证
	 * @param deptid
	 * @return
	 */
	public int queryCount(Integer deptid);

	/**
	 * 对员工进行冻结与解锁操作
	 * @param ids
	 */
	public void updateLock(String ids);
	/**
	 * 验证登陆名是否对应用户，是否被冻结
	 * @param adminName
	 */
	public void queryLoginStaff(String adminName);
	
	/**
	 * 更换员工部门
	 * @param ids
	 * @param deptid
	 */
	public void updateDeptRemove(String ids,Integer deptid);
}