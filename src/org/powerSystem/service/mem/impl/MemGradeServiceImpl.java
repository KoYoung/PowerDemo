package org.powerSystem.service.mem.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.powerSystem.dao.mem.MemGradeDao;
import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemRank;
import org.powerSystem.service.mem.MemCardService;
import org.powerSystem.service.mem.MemGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemGradeServiceImpl implements MemGradeService {
	
	@Autowired
	private MemCardService memCardServiceImpl;
	@Autowired
	private MemGradeDao memGradeDaoImpl;
	public boolean saveGrade(MemRank memRank) {
		
		try {
			memGradeDaoImpl.save(memRank);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
//		}
		 
	}

	/*public boolean update(MemRank memRank) {
		// TODO Auto-generated method stub
		try {
			memGradeDaoImpl.save(memRank);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}*/

	public List<MemRank> getAllRank() {
		// TODO Auto-generated method stub
		return memGradeDaoImpl.query();
	}

	public MemRank getRankById(int id) {
		// TODO Auto-generated method stub
		return memGradeDaoImpl.query(id);
	}

//	public void upgrade(String cardNo) {
//		// TODO Auto-generated method stub
//		MemCard memCard=memCardServiceImpl.queryMemCard(cardNo);
//		List<MemRank> list=getAllRank();
//		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
//		for (MemRank memRank : list) {
//			map.put(memRank.getScoreLimit(),memRank.getRankId() );
//		}
//		int[] score=new int[5];
//		int i=0;
//		for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
//			score[i++]=entry.getKey();
//		}
//		Integer level=0;
//		for(int j=0;j<score.length;j++){
//			if(memCard.getCardScore()>=score[j]&&memCard.getCardScore()<score[j+1]){
//				level=map.get(score[j]);
//				break;
//			}
//		}
//		if(memCard.getCardLevel()!=level){
//			memCard.setCardLevel(level);
//			memCardServiceImpl.saveMemCard(memCard);
//		}
//		 
//	}

	public boolean update(MemRank memRank) {
		// TODO Auto-generated method stub
		return false;
	}

	public void upgrade(String cardNo) {
		// TODO Auto-generated method stub
		
	}

	
}
