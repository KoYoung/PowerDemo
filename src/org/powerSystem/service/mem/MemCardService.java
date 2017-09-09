package org.powerSystem.service.mem;
import java.util.List;
import java.util.Map;

import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.entity.mem.MemGift;
import org.powerSystem.entity.mem.MemMember;

public interface MemCardService {
	
	/**
	 * 查询所有卡信息
	 */
	public List<MemCard> queryMemCardAll();
	
	/**
	 * 修改卡信息
	 */
	public void updateMemCard(MemCard card);
	/**
	 * 根据ID查找单条数据
	 * @param id
	 * @return
	 */
	public MemCard getMemCard(String id);
	
	/**
	 * 删除卡
	 */
	public void delMemCard(String ids);
	
	/**
	 * 添加卡
	 */
	public void addMemCard(MemCard card);
	
	/**
	 * 通过卡号
	 * cardNo
	 * 查询出卡信息
	 */
	public MemCard query(String id);
	
	/**
	 * 自动生成一个会员卡号
	 */
	public String saveMemCardNo();
	public String  getcardno();
	
	
	/**
	 * 卡状态变更
	 * 根据卡号进行会员卡状态变更
	 * 如果会员丢失会员卡，会员提交挂失会员卡请求后，卡状态由变成"正常"变成"挂失",此时会员卡不能使用
	 * 当会员补办会员卡后，此会员卡状态由"挂失"变成"失效"
	 */
	public boolean updateMemCardStatus(String cardNo,int cardStatus);
	
	/**
	 * 卡转移
	 * 如果会员要求补办会员卡,会员必需先对原来会员卡进行挂失，然后才能够补办会员卡，补办会员卡的时候，
	 * 重新给会员一张新卡，卡号是新号，将丢失的会员卡的所有信息复制到新会员卡内,并与相应的会员绑定,并恢复新卡号的所有优惠政策
	 */
	public boolean saveNewMemCard(String cardNo);
	
	/**
	 * 通过条件查询出会员卡信息
	 * 
	 
	public List<CardUtil> queryMemCard(Map map,int page,int rows);*/

	
	/**
	 * 通过条件查询出卡数量
	 */
	public int countMemCard(Map map);
	
	/**
	 * 获取所要查询的会员信息的总条数
	 */
	public int getCount(Map<String, Object> map);
	//分页查询全部
	public List<MemCard> queryAllMemCard();
	//将List集合转换成json对象
	public String getPage(Map map, int startIndex, int pageSize);
	
	/**
	 * 根据会员id查询卡信息
	 * @param memId
	 * 		会员id
	 */
	public List<MemCard> queryCardInfoById(Map map);
	/**
	 * 查询礼品表ID
	 * @param id
	 * @return
	 */
	public MemGift getGift(Integer id);
	/**
	 * 修改卡积分
	 * @param cardno
	 * @param cardscore
	 */
	public void updatecardscore(String cardno,Integer cardscore);
}
