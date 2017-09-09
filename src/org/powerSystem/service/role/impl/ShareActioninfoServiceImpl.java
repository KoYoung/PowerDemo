package org.powerSystem.service.role.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.powerSystem.dao.role.ShareActioninfoDao;
import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.ShareActioninfo;
import org.powerSystem.entity.SharePermitinfo;
import org.powerSystem.entity.util.ActionInfoUtil;
import org.powerSystem.service.role.ShareActioninfoService;
import org.powerSystem.util.ComparatorAction;
import org.powerSystem.util.DataDictionary;
import org.powerSystem.util.JsonConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareActioninfoServiceImpl implements ShareActioninfoService {
	@Autowired
	private ShareActioninfoDao actionDao;

	public void queryTostatic() {
		DataDictionary.actionList = actionDao.query();// 取全部信息
		Collections.sort(DataDictionary.actionList, new ComparatorAction());
	}

	/**
	 * 查询
	 */
	public String query(List<ShareActioninfo>... actions) {
		List<ShareActioninfo> actionList = null;
		if (actions.length <= 0) {
			actionList = DataDictionary.actionList;
		} else {
			actionList = actions[0];
		}
		List<ActionInfoUtil> parentList = new ArrayList<ActionInfoUtil>();// 取父菜单
		for (ShareActioninfo shareActioninfo : actionList) {
			if (shareActioninfo.getActionParent() == null
					|| shareActioninfo.getActionParent() == 0) {
				ActionInfoUtil infoUtil = new ActionInfoUtil();// 声明对象
				infoUtil.setActionInfo(shareActioninfo);// 向对象添加值
				infoUtil.setParentName(shareActioninfo.getActionname());
				infoUtil.setChildren(addChildren(actionList, infoUtil));
				parentList.add(infoUtil);
			}
		}
		PageUtil util = new PageUtil(parentList, actionList.size());
		String str = JSONObject.fromObject(util).toString();
		return str;
	}


	/**
	 * 保存
	 */
	public void saveActions(ShareActioninfo action) {
		if(action!= null){
			if(action.getActionId()!=null){
				actionDao.update(action);
				int index =0;
				for (ShareActioninfo info : DataDictionary.actionList) {
					if(info.getActionId().equals(action.getActionId())){
						break;
					}
					index++;
				}
				DataDictionary.actionList.remove(index);
				DataDictionary.actionList.add(action);
			}else{
				actionDao.save(action);
				DataDictionary.actionList.add(action);
			}
		}
	}

	/**
	 * 删除
	 */
	public void deleteActions(Integer id) {
		for (ShareActioninfo actioninfo : DataDictionary.actionList) {
			if (id.equals(actioninfo.getActionParent())) {
				throw new RuntimeException("有下级菜单，请先删除下级菜单");
			}
		}
		ShareActioninfo action1 = null;
		// 删除缓存数据
		for (ShareActioninfo actioninfo : DataDictionary.actionList) {
			if (actioninfo.getActionId().equals(id)) {
				action1 = actioninfo;
				break;
			}
		}
		DataDictionary.actionList.remove(action1);
		try {
			// 删除数据库数据
			ShareActioninfo action = actionDao.query(id);//把action变成持久状态
			if (action != null) {
				Set<SharePermitinfo> set = action.getSharePermitinfos();
				Iterator<SharePermitinfo> it = set.iterator();
				while (it.hasNext()) {
					it.next().getShareActioninfos().remove(action);
				}
				actionDao.del(action);
			}
		} catch (Exception e) {
			if (action1 != null)
				DataDictionary.actionList.add(action1);
			throw new RuntimeException(e.getMessage());
		}
	}

	private List<ActionInfoUtil> addChildren(List<ShareActioninfo> list,
			ActionInfoUtil parent) {
		/*
		 * 装载子菜单
		 */
		List<ActionInfoUtil> childList = new ArrayList<ActionInfoUtil>();
		parent.setChildren(childList);
		for (ShareActioninfo info : list) {
			if (parent.getActionId().equals(info.getActionParent())) {
				ActionInfoUtil infoUtil1 = new ActionInfoUtil();// 声明对象
				infoUtil1.setActionInfo(info);// 向对象添加值
				infoUtil1.setParentName(parent.getActionname());
				childList.add(infoUtil1);
				addChildren(list, infoUtil1);// 自身调用自身 递归
			}
		}
		return childList;
	}

	/**
	 * 根据菜单名字查询
	 */
	public String query(String menuName) {
		List<ShareActioninfo> list = new ArrayList<ShareActioninfo>();
		for (ShareActioninfo shareActioninfo : DataDictionary.actionList) {
			if(shareActioninfo.getActionname().indexOf(menuName)>=0){
				list.add(shareActioninfo);
			}
		}
		return query(list);
	}

}
