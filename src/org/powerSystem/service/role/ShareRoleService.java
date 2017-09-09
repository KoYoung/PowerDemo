package org.powerSystem.service.role;

import org.powerSystem.entity.ShareRole;

public interface ShareRoleService {

	public String query();

	public int saveRole(ShareRole info);

	public void deleteRole(Integer perid);

	public String query(Integer perid);

	public void update(ShareRole permit, String ids);
}
