package org.powerSystem.service.mem.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.powerSystem.dao.mem.MemCardDao;
import org.powerSystem.dao.mem.MemGiftDao;
import org.powerSystem.entity.PageUtil;
import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemGift;
import org.powerSystem.entity.mem.MemMember;
import org.powerSystem.service.mem.MemCardService;
import org.powerSystem.util.JsonConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemCardServiceImpl implements MemCardService {
	@Autowired
	private MemCardDao memCardDao;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	private MemCard memCard;

	public MemCardDao getMemCardDao() {
		return memCardDao;
	}

	public void setMemCardDao(MemCardDao memCardDao) {
		this.memCardDao = memCardDao;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Autowired
	private MemGiftDao memGiftDao;
	
	/**
	 * 查询所有卡信息
	 */
	public List<MemCard> queryMemCardAll() {
		// TODO Auto-generated method stub
		return memCardDao.query();
	}

	/**
	 * 修改卡信息
	 */
	public void updateMemCard(MemCard card) {
		
		// TODO Auto-generated method stub
		memCardDao.update(card);
	}

	/**
	 * 删除卡
	 */
	public void delMemCard(String ids) {
		// TODO Auto-generated method stub
		memCardDao.delOfin(ids);
	}

	/**
	 * 添加卡
	 */
	public void addMemCard(MemCard card) {
		// TODO Auto-generated method stub
		memCardDao.save(card);
	}
	
	/**
	 * 通过卡号
	 * 显示卡信息
	 */
	public MemCard queryMemCard(String cardNo) {
		return memCardDao.query(cardNo);
	}
	
	
	

	/*public List<CardUtil> queryMemCard(Map map, int startIndex, int pageSize) {
		List<MemCard> list = memCardDaoImpl.query(map, startIndex, pageSize);
		List<CardUtil> culist = new ArrayList<CardUtil>();
		for (MemCard m : list) {
			CardUtil cardUtil = new CardUtil();
			cardUtil.setMemCard(m);
			cardUtil.setCardStatus(String.valueOf(m.getCardStatus()));
			culist.add(cardUtil);
		}
		return culist;
	}*/

	public int countMemCard(Map map) {
		return memCardDao.getCount(map);
	}

	/**
	 * 自动生成一个会员卡号
	 */
	public String saveMemCardNo() {
		String cardNo=null;
		String hql="select cardNo from MemCard";
		List list=hibernateTemplate.find(hql);
		int n=list.size();
		if (n == 0) {
			cardNo = "QY3600001";
		} else {
			String m = list.get(n - 1).toString();
			int a = Integer.parseInt(m.substring(4)) + 1;
			String b = "QY36";
			String c = String.valueOf(a);
			String d = null;
			if (c.length() == 1) {
				d = "0000" + c;
			} else if (c.length() == 2) {
				d = "000" + c;
			} else if (c.length() == 3) {
				d = "00" + c;
			} else if (c.length() == 4) {
				d = "0" + c;
			} else if (c.length() == 5) {
				d = c;
			}
			cardNo = b + d;
		}
		return cardNo;
	}

	public String getcardno()
	{
		String cardno=new SimpleDateFormat("yyMMss").format(new Date());
		return cardno;
	}

	/**
	 * 卡状态变更
	 * 根据卡号进行会员卡状态变更
	 * 如果会员丢失会员卡，会员提交挂失会员卡请求后，卡状态由变成"正常"变成"挂失",此时会员卡不能使用
	 * 当会员补办会员卡后，此会员卡状态由"挂失"变成"失效"
	 */
	public boolean updateMemCardStatus(String cardNo, int cardStatus) {
		try {
			MemCard memCard = memCardDao.query(cardNo);
			memCard.setCardStatus(cardStatus);
			memCardDao.update(memCard);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 卡转移
	 * 如果会员要求补办会员卡,会员必需先对原来会员卡进行挂失，然后才能够补办会员卡，补办会员卡的时候，
	 * 重新给会员一张新卡，卡号是新号，将丢失的会员卡的所有信息复制到新会员卡内,并与相应的会员绑定,并恢复新卡号的所有优惠政策
	 */
	public boolean saveNewMemCard(String cardNo) {
		try {
			MemCard memCard = memCardDao.query(cardNo);
			memCard.setCardNo(cardNo);
			memCardDao.update(memCard);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
	public List<MemCard> queryAllMemCard(Map<String, Object> map, int index, int size) {
		List<MemCard> list=memCardDao.query(map,index,size);
		return list;
	}

	/**
	 * 查询数据库中的总数据
	 * @param map按条件传参
	 * @return int
	 */
	public int getCount(Map<String, Object> map) {
		return memCardDao.getCount(map);
	}
	
	
	//转换json对象
	public String getPage(Map map, int startIndex, int pageSize) {
		PageUtil pages=new PageUtil();
		pages.setRows(queryAllMemCard(map, startIndex, pageSize));
		pages.setTotal(getCount(map));
		JsonConfig con =JsonConfigUtil.getJsonConfigInstanceByFormatDate();
		con.setExcludes(new String[] {"memMember"});
		String str = JSONObject.fromObject(pages,con).toString();
	    return str;
	}

	/**
	 * 查询全部
	 */
	public List<MemCard> queryAllMemCard() {
		// TODO Auto-generated method stub
		return memCardDao.query();
	}

		/**
		 * 根据会员id查询卡信息
		 * @param memId
		 * 		会员id
		 */
		public List<MemCard> queryCardInfoById(Map map) {
			return memCardDao.query(map);
		}

		public MemCard getMemCard(String id)
		{
			// TODO Auto-generated method stub
			return memCardDao.query(id);
		}

		public MemCard getMemCard() {
			return memCard;
		}

		public void setMemCard(MemCard memCard) {
			this.memCard = memCard;
		}
		
		public MemCard query(String id)
		{
			return memCardDao.query(id);
		}
		
		public void updatecardscore(String cardno,Integer cardscore)
		{
			MemCard memCard = memCardDao.query(cardno);
			memCard.setCardScore(cardscore);
			memCardDao.update(memCard);
		}
		


		public MemGift getGift(Integer id) {
			// TODO Auto-generated method stub
			return memGiftDao.query(id);
		
		}

		public MemGiftDao getMemGiftDao() {
			return memGiftDao;
		}

		public void setMemGiftDao(MemGiftDao memGiftDao) {
			this.memGiftDao = memGiftDao;
		}
}
