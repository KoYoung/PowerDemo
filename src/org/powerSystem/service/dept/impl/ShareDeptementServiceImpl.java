package org.powerSystem.service.dept.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.powerSystem.dao.dept.ShareDeptementDao;
import org.powerSystem.entity.DeptUtil;
import org.powerSystem.entity.ShareDeptement;
import org.powerSystem.service.dept.ShareDeptementService;
import org.powerSystem.service.dept.ShareStaffService;
import org.powerSystem.util.ComparatorDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareDeptementServiceImpl implements ShareDeptementService {
	@Autowired
	private ShareDeptementDao deptemantDao;
	@Autowired
	private ShareStaffService staffService;

	public String query(ShareDeptement dept) {
		List<ShareDeptement> list = deptemantDao.query();
		Collections.sort(list, new ComparatorDept());
		List<DeptUtil> topDept = null;
		if (dept != null && dept.getDeptId() != null) {
			topDept = getChildren(list, dept);
		} else {
			topDept = getChildren(list, null);
		}
		return JSONArray.fromObject(topDept).toString();
	}

	public int saveDept(ShareDeptement dept) {
		if (dept.getDeptId() != null && dept.getDeptId()!=0)
			deptemantDao.update(dept);
		else
			deptemantDao.save(dept);
		
		return dept.getDeptId();

	}

	private List<DeptUtil> getChildren(List<ShareDeptement> list,
			ShareDeptement dept) {
		List<DeptUtil> listChild = new ArrayList<DeptUtil>();

		for (ShareDeptement shareDeptement : list) {
			if (dept == null) {
				if (shareDeptement.getDeptParent() == null || shareDeptement.getDeptParent().equals(0) ) {
					DeptUtil util = new DeptUtil();
					util.setId(shareDeptement.getDeptId());
					util.setText(shareDeptement.getDeptName());
					Map map = new HashMap();
					map.put("parentid", shareDeptement.getDeptParent());// (shareDeptement.getDeptParent());
					util.setAttributes(map);
					util.setChildren(getChildren(list, shareDeptement));
					listChild.add(util);
				}
			} else if (shareDeptement.getDeptParent() != null
					&& shareDeptement.getDeptParent().equals(dept.getDeptId())) {
				DeptUtil util = new DeptUtil();
				util.setId(shareDeptement.getDeptId());
				util.setText(shareDeptement.getDeptName());
				Map map = new HashMap();
				map.put("parentid", shareDeptement.getDeptParent());// (shareDeptement.getDeptParent());
				util.setAttributes(map);
				util.setChildren(getChildren(list, shareDeptement));
				listChild.add(util);
			}
		}
		return listChild;
	}

	public void deleteDept(Integer deptId) {
		if(deptId!=null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("deptParent", deptId);
			int n  = deptemantDao.getCount(map);
			if(n>0)throw new RuntimeException("存在下级部门，请先删除下级部门");
			n = staffService.queryCount(deptId);
			if(n>0)throw new RuntimeException("部门里存在员工，请先处理员工数据");
			deptemantDao.del(deptId);
		}
	}

}
