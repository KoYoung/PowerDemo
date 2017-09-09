package org.powerSystem.service.dept.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.Action;
import org.powerSystem.dao.dept.ShareStaffDao;
import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.ShareAdmin;
import org.powerSystem.entity.ShareDeptement;
import org.powerSystem.entity.ShareStaff;
import org.powerSystem.entity.util.TreeUtil;
import org.powerSystem.service.dept.ShareStaffService;
import org.powerSystem.service.role.ShareAdminService;
import org.powerSystem.util.DataDictionary;
import org.powerSystem.util.JsonConfigUtil;
import org.powerSystem.util.PinyingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareStaffServiceImpl implements ShareStaffService {

	@Autowired
	private ShareStaffDao staffdao;
	@Autowired
	private ShareAdminService adminService;

	/**
	 * 根据条件查询所有员工
	 * 
	 * @param staff
	 *            条件参数
	 * @param page
	 * @param max
	 * @return
	 */
	public String query(ShareStaff staff, int page, int max) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (staff != null) {
			if (staff.getShareDeptement() != null
					&& staff.getShareDeptement().getDeptId() != null) {
				map.put("shareDeptement.deptId", staff.getShareDeptement().getDeptId());
			}
			if (staff.getStaffName() != null) {
				map.put("staffName", "%" + staff.getStaffName() + "%");
			}
		}
		List<ShareStaff> list = staffdao.query(map, page, max);
		JsonConfig config = JsonConfigUtil.getJsonConfig("yyyy-MM-dd");
		config.setExcludes(new String[] { "shareDeptement" });
		int n = staffdao.getCount(map);
		return JSONObject.fromObject(new PageUtil(list, n), config).toString();
	}

	public String queryForTree(Integer deptid) {
		if (deptid != null) {
			List<TreeUtil> treeList = new ArrayList<TreeUtil>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("shareDeptement.deptId", deptid);
			List<ShareStaff> list = staffdao.query(map);
			for (ShareStaff shareStaff : list) {
				TreeUtil util = new TreeUtil();
				util.setId(shareStaff.getStaffId() + "");
				util.setText(shareStaff.getStaffName());
				Map<String, String> attributes = new HashMap<String, String>();
				attributes.put("adminName", shareStaff.getAdminName());
				util.setAttributes(attributes);
				treeList.add(util);
			}
			return JSONArray.fromObject(treeList).toString();
		}
		return null;
	}

	public void saveStaff(ShareStaff staff) {
		if (staff != null) {
			if (staff.getStaffId() != null) {
				staffdao.update(staff);
			} else {
				staff.setStatus(100201);
				ShareAdmin admin = new ShareAdmin();
				admin.setAdminName(staff.getAdminName());
				admin.setAdminPassword(DataDictionary.DEFAULTPASS);
				adminService.saveAdmin(admin);
				staffdao.save(staff);
			}
		}
	}

	@Action("deleteStaff")
	public void deleteStaff(String ids) {
		if (ids != null) {
			try {
				String[] staffs = ids.split(",");
				for (String string : staffs) {
					ShareStaff staff = staffdao.query(Integer.valueOf(string));
					adminService.deleteAdmin(staff.getAdminName());//删除登录账号
					staffdao.del(staff);
				}
			} catch (Exception e) {
				throw new RuntimeException("其他项目正在使用此用户,不能进行删除操作"+e.getMessage());
			}

		}
	}

	public int queryCount(Integer deptid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shareDeptement.deptId", deptid);
		int n = staffdao.getCount(map);
		return n;
	}

	public void updateLock(String ids) {
		if (ids != null) {
			try {
				String[] staffids = ids.split(",");
				for (String string : staffids) {
					ShareStaff staff = staffdao.query(Integer.valueOf(string));
					if (staff.getStatus() != null
							&& staff.getStatus() == 100201) {
						staff.setStatus(100202);
					} else {
						staff.setStatus(100201);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("冻结/解冻员工失败");
			}
		}
	}
	
	public void queryLoginStaff(String adminName){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminName", adminName);
		List<ShareStaff> list = staffdao.query(map);
		if(list.size()>0){
			ShareStaff staff = list.get(0);
			if(staff.getStatus().equals(DataDictionary.EMPSTATUS2)){
				throw new RuntimeException("登陆员工已被冻结，请及时联系管理员");
			}
		}else{
			throw new RuntimeException("无此员工信息，请查证后登陆。");
		}
	}

	public void updateDeptRemove(String ids, Integer deptid) {
		String staffids[] = ids.split(",");
		for (String string : staffids) {
			ShareStaff staff = staffdao.query(Integer.valueOf(string));
			ShareDeptement dept = new ShareDeptement();
			dept.setDeptId(deptid);
			staff.setShareDeptement(dept);
		}
		
	}
}
