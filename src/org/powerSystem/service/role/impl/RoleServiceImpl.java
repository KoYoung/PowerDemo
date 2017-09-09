package org.powerSystem.service.role.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import org.powerSystem.dao.role.ShareRoleDao;
import org.powerSystem.entity.ShareActioninfo;
import org.powerSystem.entity.ShareAdmin;
import org.powerSystem.entity.SharePermitinfo;
import org.powerSystem.entity.ShareRole;
import org.powerSystem.entity.util.TreeUtil;
import org.powerSystem.service.role.ShareRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements ShareRoleService {
	@Autowired
	private ShareRoleDao roleDao;

	/**
	 * 查询
	 */
	public String query() {
		List<ShareRole> list = roleDao.query();
		List<TreeUtil> treeList = new ArrayList<TreeUtil>(0);
		for (ShareRole shareRole : list) {
			TreeUtil util = new TreeUtil();
			util.setId(shareRole.getRoleId() + "");
			util.setText(shareRole.getRoleName());
			treeList.add(util);
		}
		return JSONArray.fromObject(treeList).toString();
	}

	/**
	 * 保存角色
	 */
	public int saveRole(ShareRole info) {
		if (info.getRoleId() == 0) {
			roleDao.save(info);
		} else {
			String infoName = info.getRoleName();
			info = roleDao.query(info.getRoleId());
			info.setRoleName(infoName);
		}
		return info.getRoleId();
	}

	public void deleteRole(Integer perid) {
		ShareRole info = roleDao.query(perid);
		if(info!=null){
			Set<ShareAdmin> set = info.getShareAdmins();
			Iterator<ShareAdmin> it = set.iterator();
			while(it.hasNext()){
				it.next().getShareRoles().remove(info);
			}
			roleDao.del(perid);
		}
	}

	public String query(Integer perid) {
		ShareRole info = roleDao.query(perid);
		Set<SharePermitinfo> permits = info.getSharePermitinfos();
		Integer ids[] = new Integer[permits.size()];
		Iterator<SharePermitinfo> it = permits.iterator();
		int i = 0;
		while (it.hasNext()) {
			SharePermitinfo per = it.next();
			ids[i] = per.getPerId();
			i++;
		}
		return JSONArray.fromObject(ids).toString();
	}

	/**
	 * 
	 */
	public void update(ShareRole role, String ids) {
		if (role != null && role.getRoleId() != null) {
			role = roleDao.query(role.getRoleId());
			String[] allid = ids.split(",");
			Set<SharePermitinfo> perset = new HashSet<SharePermitinfo>();
			for (String string : allid) {
				SharePermitinfo per = new SharePermitinfo();
				per.setPerId(Integer.valueOf(string));
				perset.add(per);
			}
			role.setSharePermitinfos(perset);
		}
	}

}
