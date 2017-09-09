package org.powerSystem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.powerSystem.entity.ShareActioninfo;

public class DataDictionary {

	public static Map<Integer, Map<String, String>> dataMap = new HashMap<Integer, Map<String, String>>();

	public static List<ShareActioninfo> actionList = new ArrayList<ShareActioninfo>();
	public final static Integer SEX = 1001;// 姓别
	public final static Integer EMPSTATUS=1002;//员工状态100201正常100202冻结
	public final static Integer EMPSTATUS1=100201;//正常
	public final static Integer EMPSTATUS2=100202;//冻结
	public final static Integer CARDGRADE = 1002;// 会员等级
	public final static Integer CARDSTATUS = 1003;// 会员卡状态
	public final static Integer CARDTYPE = 1004;// 会员卡类型
	public final static Integer SCORESULE = 1005;// 积分规则类型
	public final static Integer MEMBERSTATUS = 100701; //会员状态（正常）
	
	public final static String DEFAULTPASS="888888";
}
