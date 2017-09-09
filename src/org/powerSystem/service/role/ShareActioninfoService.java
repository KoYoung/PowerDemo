package org.powerSystem.service.role;

import java.util.List;

import org.powerSystem.entity.ShareActioninfo;

public interface ShareActioninfoService {

	public String query(List<ShareActioninfo>...actions);//查询全部
	public String query(String menuName);//根据菜单名字查询
	public void saveActions(ShareActioninfo action);//保存
	public void deleteActions(Integer id);//删除
}
