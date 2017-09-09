package org.powerSystem.service.role.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.powerSystem.dao.role.ShareAdminDao;
import org.powerSystem.entity.Menus;
import org.powerSystem.entity.ShareActioninfo;
import org.powerSystem.entity.ShareAdmin;
import org.powerSystem.entity.SharePermitinfo;
import org.powerSystem.entity.ShareRole;
import org.powerSystem.service.dept.ShareStaffService;
import org.powerSystem.service.role.ShareActioninfoService;
import org.powerSystem.service.role.ShareAdminService;
import org.powerSystem.util.PinyingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareAdminServiceImpl implements ShareAdminService {
	@Autowired
	private ShareAdminDao shareAdminDao;
	@Autowired
	private ShareActioninfoService actionService;
	@Autowired
	private ShareStaffService staffService;

	public List<ShareAdmin> query() {
		return shareAdminDao.query();
	}

	public ShareAdmin queryLogin(String id, String pass) {//登录  传入登录名及密码
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminName", id);
		map.put("adminPassword", pass);
		List<ShareAdmin> list = shareAdminDao.query(map);
		if (list != null && list.size() > 0) {//当有登录人员
			ShareAdmin admin = list.get(0);//拿到第一个值
			if (!admin.getAdminName().equals("admin"))//看是否是admin登录
				staffService.queryLoginStaff(id);//如果过是获取其id
			return admin;//返回admin
		} else {
			throw new RuntimeException("用户名密码不匹配");
		}
	}

	private List<ShareActioninfo> query(ShareAdmin admin) {
		Set<ShareActioninfo> list = new HashSet<ShareActioninfo>();
		// 变成持久状态
		admin = queryLogin(admin.getAdminName(), admin.getAdminPassword());
		Set<ShareRole> set = admin.getShareRoles();// 取这个用户的所有的角色
		Iterator<ShareRole> it = set.iterator();// 变成迭代对象
		while (it.hasNext()) {//迭代角色
			ShareRole role = it.next();//获得其中的一个角色
			Set<SharePermitinfo> perset = role.getSharePermitinfos();//根据这个角色获取其权限
			Iterator<SharePermitinfo> perit = perset.iterator();
			while (perit.hasNext()) {//迭代权限
				SharePermitinfo per = perit.next();//获取其中的一个权限
				Set<ShareActioninfo> actionset = per.getShareActioninfos();//获取该权限的所有菜单
				Iterator<ShareActioninfo> actionit = actionset.iterator();
				while (actionit.hasNext()) {//迭代菜单
					list.add(actionit.next());//向list中添加菜单
				}
			}
		}
		List<ShareActioninfo> actionList = new ArrayList<ShareActioninfo>();
		actionList.addAll(list);
		return actionList;
	}

	public String queryMenus(ShareAdmin admin) {//加载菜单
		if (admin != null) {//登录人是否存在
			if (admin.getAdminName().equals("admin")) {//是否是admin，是，加载所有菜单
				return actionService.query();
			}
			List<ShareActioninfo> list = query(admin);//查看这个人的菜单
			return actionService.query(list);//查到加载
		} else {
			return null;
		}
	}

	/**
	 * 登陆
	 */
	/*
	 * public ShareAdmin login(String adminName, String adminPassword) {
	 * Map<String, Object> map = new HashMap<String, Object>();
	 * map.put("adminName", adminName); map.put("adminPassword", adminPassword);
	 * List<ShareAdmin> list = shareAdminDao.query(map); if (list != null &&
	 * list.size() > 0){ ShareAdmin admin = list.get(0);
	 * staffService.checkLoginStaff(adminName); return admin; }else{ throw new
	 * RuntimeException("用户名密码不匹配"); } }
	 */

	public void saveAdmin(ShareAdmin admin) {
		if (admin != null && admin.getAdminName() != null) {
			if (shareAdminDao.query(admin.getAdminName()) != null) {
				throw new RuntimeException("用户名被占用");
			} else {
				shareAdminDao.save(admin);
			}
		}
	}

	public String queryAdminCheckName(String adminname) {
		if (shareAdminDao.query(adminname.trim()) != null)
			throw new RuntimeException("用户名被占用");
		return "";
	}

	public String queryAdminRoles(String adminName) {//根据用户名查找该用户的所有角色
		ShareAdmin admin = shareAdminDao.query(adminName);
		Set<ShareRole> roles = admin.getShareRoles();
		Iterator<ShareRole> it = roles.iterator();
		Integer ids[] = new Integer[roles.size()];
		int i = 0;
		while (it.hasNext()) {
			ids[i] = it.next().getRoleId();
			i++;
		}
		return JSONArray.fromObject(ids).toString();
	}

	public void update(ShareAdmin admin, String ids) {//修改用户角色和权限
		if (admin != null && admin.getAdminName() != null) {//判断用户是否为空
			admin = shareAdminDao.query(admin.getAdminName());//查询出该用户所有信息
			String[] allid = ids.split(",");//将穿过来的字符串分隔成数组
			Set<ShareRole> perset = new HashSet<ShareRole>();//
			for (String string : allid) {//遍历这个数组
				ShareRole per = new ShareRole();
				per.setRoleId(Integer.valueOf(string));//如果spring在该角色中，那么将该角色设置到该权限中
				perset.add(per);
			}
			admin.setShareRoles(perset);//将该角色的权限设置到该用户中
		}
	}

	public void deleteAdmin(String adminName) {//删除用户
		shareAdminDao.del(adminName);
	}

	public void updatePass(String username, String newPass) {//修改新密码
		ShareAdmin adminold = shareAdminDao.query(username);
		adminold.setAdminPassword(newPass);
	}
}
