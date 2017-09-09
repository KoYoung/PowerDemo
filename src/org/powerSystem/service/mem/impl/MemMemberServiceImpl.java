package org.powerSystem.service.mem.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.powerSystem.dao.mem.MemMemberDao;
import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.mem.Combobox;
import org.powerSystem.entity.mem.MemMember;
import org.powerSystem.entity.mem.MemberUtil;
import org.powerSystem.service.mem.MemMemberService;
import org.powerSystem.util.DataDictionary;
import org.powerSystem.util.JsonConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 
 * @author 
 * @version 1.0 2013年10月11日
 */
@Service
public class MemMemberServiceImpl implements MemMemberService {
	@Autowired
	private MemMemberDao memMemberDao;
	private MemMember memMember;
	
	/**
	 * 查询所有会员
	 */
	public List<MemMember> queryMemberAll() {
		// TODO Auto-generated method stub
		return memMemberDao.query();
	}

	/**
	 * 根据条件查询所有会员
	 * @param member
	 *            条件参数
	 * @param page
	 * @param max
	 * @return
	 */
	public String query(MemMember member, int page, int max){
		System.out.println(member);
		List<MemMember> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(member!=null){
			if (memMember.getMemName()!= null) {
				ServletActionContext.getRequest().setAttribute("member",member);
				map.put("memName", "%" + memMember.getMemName()+ "%");
			}}else{
					list = memMemberDao.query(map, page, max);
					}
		JsonConfig config = JsonConfigUtil.getJsonConfig("yyyy-MM-dd");//?
		config.setExcludes(new String[] { "shareDeptement" });
		int n = memMemberDao.getCount(map);
		return JSONObject.fromObject(new PageUtil(list, n), config).toString();
	}
	

	/**
	 * 根据id
	 * 修改会员信息
	 */
	public void updateMember(MemMember member) {
		memMemberDao.update(member);
	}


	/**
	 * 添加会员
	 */
	public void saveMember(MemMember member) {
		if(member.getMemId()!=null)memMemberDao.update(member);
		else memMemberDao.save(member);
	}


	/**
	 * 根据id
	 * 查询会员
	 */
	public MemMember queryMemberById(Integer memId) {
		// TODO Auto-generated method stub
		return memMemberDao.query(memId);
	}


	/**
	 * 根据id
	 * 删除会员
	 */
	public void delMember(String ids) {
		// TODO Auto-generated method stub
		memMemberDao.delOfin(ids);
	}
	
	
	/**
	 * 条件分页查询
	 */
	private List<MemberUtil> queryMemUtil(Map<String, Object> map,
			int startIndex, int pageSize) {
		List<MemberUtil> muList = new ArrayList<MemberUtil>();
		List<MemMember> list = queryMem(map, startIndex, pageSize);
		for (MemMember memMember : list) {
			MemberUtil mu = new MemberUtil();
			mu.setMemMember(memMember);
			mu.setStatusName(memMember.getMemStatus());
			muList.add(mu);
		}
		return muList;
	}
	

	public PageUtil queryPage(Map<String, Object> map, int startIndex,
			int pageSize) {
		List<MemberUtil> list = queryMemUtil(map, startIndex, pageSize);
		int n = getCount(map);
		PageUtil p = new PageUtil();
		p.setRows(list);
		p.setTotal(n);
		return p;
	}

	public PageUtil getPage(int startIndex, int pageSize) {
		List<MemberUtil> list = queryMemUtil(null, startIndex, pageSize);
		int n = getCount(null);
		PageUtil p = new PageUtil();
		p.setRows(list);
		p.setTotal(n);
		return p;
	}

	
	public String getCombobox(){
		Map<String,List> map = new HashMap<String,List>();
		Map<String,String> mapSex = DataDictionary.dataMap.get(DataDictionary.SEX);
		Map<String,String> mapType = DataDictionary.dataMap.get(DataDictionary.CARDTYPE);
		Map<String,String> mapGrade = DataDictionary.dataMap.get(DataDictionary.CARDGRADE);
		List listSex = new ArrayList();
		List listType = new ArrayList();
		List listGrade = new ArrayList();
		for (Map.Entry<String, String> entry: mapSex.entrySet()) {
			Combobox combo = new Combobox();
			combo.setKey(entry.getKey());
			combo.setValue(entry.getValue());
			listSex.add(combo);
		}
		for (Map.Entry<String, String> entry: mapType.entrySet()) {
			Combobox combo = new Combobox();
			combo.setKey(entry.getKey());
			combo.setValue(entry.getValue());
			listType.add(combo);
		}
		for (Map.Entry<String, String> entry: mapGrade.entrySet()) {
			Combobox combo = new Combobox();
			combo.setKey(entry.getKey());
			combo.setValue(entry.getValue());
			listGrade.add(combo);
		}
		map.put("sex", listSex);
		map.put("type", listType);
		map.put("grade", listGrade);
		return JSONArray.fromObject(map).toString();
	}


	/**
	 * 分页查询
	 * @param map
	 * 		条件参数
	 * @param index
	 * 		开始条数
	 * @param size
	 * 		每页大小
	 * @return
	 * 		list<MemMember>
	 */
	public List<MemMember> queryMem(Map<String, Object> map, int index, int size) {
		List<MemMember> list=memMemberDao.query(map, index, size);
		return list;
	}
	
	public MemMemberDao getMemMemberDao() {
		return memMemberDao;
	}

	public void setMemMemberDao(MemMemberDao memMemberDao) {
		this.memMemberDao = memMemberDao;
	}

	public MemMember getMemMember() {
		return memMember;
	}

	public void setMemMember(MemMember memMember) {
		this.memMember = memMember;
	}
	/**
	 * 查询数据库中的总数据
	 * @param map按条件传参
	 * @return int
	 */
	public int getCount(Map<String, Object> map) {
		return memMemberDao.getCount(map);
	}
	//转换json对象
	public String getPage(Map map, int startIndex, int pageSize) {
		PageUtil pages=new PageUtil();
		pages.setRows(queryMem(map, startIndex, pageSize));
		pages.setTotal(getCount(map));
		JsonConfig config =JsonConfigUtil.getJsonConfig() ;
		config.setExcludes(new String[]{"memCards"});
		String str = JSONObject.fromObject(pages,config).toString();
		return str;
	}


	public List<MemMember> queryAllMem() {
		return memMemberDao.query();
	}

	/**
	 * 根据姓名查询
	 */
	public List<MemMember> queryMemberByName() {
		return null;
	}

}
