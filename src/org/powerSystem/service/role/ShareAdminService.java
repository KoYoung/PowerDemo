package org.powerSystem.service.role;

import java.util.List;

import org.powerSystem.entity.ShareActioninfo;
import org.powerSystem.entity.ShareAdmin;

public interface ShareAdminService {

	public List<ShareAdmin> query();

	public String queryMenus(ShareAdmin admin);

	public ShareAdmin queryLogin(String id, String pass);

	public String queryAdminRoles(String adminName);

	/*
	 * 登陆
	 */
	// public ShareAdmin login(String adminName,String adminPassword);

	public void saveAdmin(ShareAdmin admin);

	/**
	 * 通过中文获取汉语拼音
	 * @param adminname
	 * @return
	 */
	public String queryAdminCheckName(String adminname);

	/**
	 * 分配角色
	 * @param admin
	 * @param ids
	 */
	public void update(ShareAdmin admin, String ids);

	/**
	 * 删除登录账号
	 * @param adminName
	 */
	public void deleteAdmin(String adminName);
	/**
	 * 通过用户名修改密码
	 * @param admin
	 * @param newPass
	 */
	public void updatePass(String username ,String newPass);
}
