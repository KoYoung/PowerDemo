package org.powerSystem.service.mem.impl;

import org.powerSystem.dao.mem.MemActivitydetailDao;
import org.powerSystem.entity.mem.MemActivitydetail;
import org.powerSystem.service.mem.MemActivitydetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemActivitydetailServiceImpl implements MemActivitydetailService {
	@Autowired
	private MemActivitydetailDao MemActivitydetailDaoImpl;

	public boolean saveActivitydetail(MemActivitydetail detail)
			throws RuntimeException {
		try {
			detail.setActDetailId(1);
			MemActivitydetailDaoImpl.save(detail);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
