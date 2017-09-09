package org.powerSystem.service.role.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import org.powerSystem.dao.role.PermitInfoDao;
import org.powerSystem.entity.ShareActioninfo;
import org.powerSystem.entity.SharePermitinfo;
import org.powerSystem.entity.ShareRole;
import org.powerSystem.entity.util.TreeUtil;
import org.powerSystem.service.role.PermitInfoService;
import org.powerSystem.service.role.ShareActioninfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermitInfoServiceImpl implements PermitInfoService {

	@Autowired
	private PermitInfoDao permitDao;
	@Autowired
	private ShareActioninfoService actionService;

	/**
	 * 查看权限
	 */
	public String query() {
		List<TreeUtil> treeList = new ArrayList<TreeUtil>();
		List<SharePermitinfo> list = permitDao.query();
		for (SharePermitinfo info : list) { 
			TreeUtil util = new TreeUtil();
			util.setId(info.getPerId() + "");
			util.setText(info.getPerName());
			treeList.add(util);
		}
		return JSONArray.fromObject(treeList).toString();
	}

	/**
	 * 保存
	 */
	public int savePermit(SharePermitinfo info) {
		
		if (info.getPerId() == 0) {
			permitDao.save(info);
		} else {
			String infoName = info.getPerName();
			info = permitDao.query(info.getPerId());
			info.setPerName(infoName);
		}
		return info.getPerId();
	}

	/**
	 * 修改
	 */
	public void update(SharePermitinfo permit, String ids) {
		if (permit != null && permit.getPerId() != null) {
			permit = permitDao.query(permit.getPerId());
			String[] allid = ids.split(",");//从页面读取值
			Set<ShareActioninfo> actionset = new HashSet<ShareActioninfo>();
			for (String string : allid) {
				ShareActioninfo action = new ShareActioninfo();
				action.setActionId(Integer.valueOf(string));
				actionset.add(action);
			}
			permit.setShareActioninfos(actionset);
		}
	}

	/**
	 * 删除
	 */
	public void deletePermit(Integer perid) {
		SharePermitinfo info = permitDao.query(perid);
		if(info!=null){
			Set<ShareRole> set = info.getShareRoles();
			Iterator<ShareRole> it = set.iterator();
			while(it.hasNext()){
				it.next().getSharePermitinfos().remove(info);
			}
			permitDao.del(perid);
		}
	}

	/**
	 * 根据id查询
	 */
	public String query(Integer perid) {
		SharePermitinfo info = permitDao.query(perid);
		Set<ShareActioninfo> actions = info.getShareActioninfos();
		Integer ids[] = new Integer[actions.size()];
		Iterator<ShareActioninfo> it = actions.iterator();
		int i = 0;
		while (it.hasNext()) {
			ShareActioninfo action = it.next();
			ids[i] = action.getActionId();
			i++;
		}
		return JSONArray.fromObject(ids).toString();
	}

}
