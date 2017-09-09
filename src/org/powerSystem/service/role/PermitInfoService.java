package org.powerSystem.service.role;

import org.powerSystem.entity.SharePermitinfo;

public interface PermitInfoService {
	
	public String query();//查看权限
	public int savePermit(SharePermitinfo info);//保存权限
	public void deletePermit(Integer perid);//删除权限
	public String query(Integer perid);//根据id查询
	public void update(SharePermitinfo permit, String ids) ;//根据id修改
}
