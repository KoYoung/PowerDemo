package org.powerSystem.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期时间运算/转换类
 * 
 */

public class WindDate {
	private static final String[] WEEKNAME = { "星期日", "星期一", "星期二", "星期三",
			"星期四", "星期五", "星期六" };

	Calendar calendar = null;

	public static final int YEAR = 0;// 用来标识年

	public static final int MONTH = 1;

	public static final int DAY = 2;

	public static final int HOUR = 3;

	public static final int MINUTE = 4;

	public static final int SECOND = 5;

	/**
	 * 构造函数，产生一个日历对象:为当前系统时间
	 * 
	 * @param 无
	 * @return 无
	 */
	public WindDate() {
		calendar = Calendar.getInstance();

	}
	/**
	 * 构造函数，产生一个日历对象:为用户指定时间
	 * 
	 * @param Calendar
	 *            :用户指定的日历对象
	 * @return 无
	 */
	public WindDate(Calendar calendar) {
		this.calendar = calendar;
	}

	/**
	 * 构造函数，产生一个日历对象:为用户指定时间
	 * 
	 * @param Date
	 *            :用户指定的时间对象
	 * @return 无
	 */
	public WindDate(Date mDate) {
		calendar = new GregorianCalendar();
		calendar.setTime(mDate);
	}

	/**
	 * 取得年份
	 * 
	 * @param 无
	 * @return int:整数表示的年份
	 */
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 取得月份
	 * 
	 * @param 无
	 * @return int:整数表示的月份
	 */
	public int getMonth() {
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 取得日期(该月的日期,即天)
	 * 
	 * @param 无
	 * @return int:整数表示的日期
	 */
	public int getDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得数字型的星期几
	 * 
	 * @param 无
	 * @return int:整数表示的星期几(1..7)
	 */
	public int getWeek() {
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 取得汉字型的星期几
	 * 
	 * @param 无
	 * @return String:汉字表示的星期几
	 */
	public String getChweek() {
		int week = getWeek();
		return WEEKNAME[week];
	}

	/**
	 * 取得数字型的当前小时
	 * 
	 * @param 无
	 * @return int:数字型的当前小时
	 */
	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 取得数字型的当前分钟
	 * 
	 * @param 无
	 * @return int:数字型的当前分钟
	 */
	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 取得数字型的当前秒
	 * 
	 * @param 无
	 * @return int:数字型的当前秒
	 */
	public int getSecond() {
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 得到(年月日)时间格式
	 * 
	 * @param 无
	 * @return String:(年月日)时间格式
	 */
	public String getDate() {
		return getYear() + "年" + getMonth() + "月" + getDay() + "日";
	}

	/**
	 * 得到月份的天数
	 * 
	 * @param 无
	 * @return int:得到某月份的天数
	 */
	public int getMonthDay() {
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到用户指定格式的(年月日)日期样式(分割符号不同)
	 * 
	 * @param String
	 *            : 分割符号,如"-",":"等等
	 * @return String:用户指定格式的(年月日)日期样式
	 */
	public String getDate(String style) {
		if (style == null) {
			return getDate();
		}
		return getYear() + style + getMonth() + style + getDay();

	}

	/**
	 * 得到中文时分秒时间样式
	 * 
	 * @param 无
	 * @return String:中文时分秒时间样式
	 */
	public String getTime() {
		return getHour() + "时" + getMinute() + "分" + getSecond() + "秒";
	}

	/**
	 * 得到用户指定格式的(时分秒)时间样式(分割符号不同)
	 * 
	 * @param String
	 *            : 分割符号,如"-",":"等等
	 * @return String:用户指定格式的时间样式
	 */
	public String getTime(String style) {
		if (style == null) {
			return getTime();
		}
		return getHour() + style + getMinute() + style + getSecond();

	}

	public String getDateStand(String style) {
		if (style == null) {
			return String.format("%04d-%02d-%02d", getHour(), getMinute(),
					getSecond());
		}
		return String.format("%04d" + style + "%02d" + style + "%02d",
				getYear(), getMonth(), getDay());

	}

	public String getTimeStand(String style) {
		if (style == null) {
			return String.format("%02d:%02d:%02d", getHour(), getMinute(),
					getSecond());
		}
		return String.format("%02d" + style + "%02d" + style + "%02d",
				getHour(), getMinute(), getSecond());

	}

	public String toStand() {
		return String.format("%04d-%02d-%02d %02d:%02d:%02d", getYear(),
				getMonth(), getDay(), getHour(), getMinute(), getSecond());
	}

	/**
	 * 时间运算函数:年月日时分秒进行加/减运算
	 * 
	 * @param int: 增加对象(年/月/日/时/分/秒等类型)
	 * @param int: 增加值
	 * @return 无
	 */
	public void add(int types, int num) {
		switch (types) {
		case YEAR:
			calendar.add(Calendar.YEAR, num);
			break;
		case MONTH:
			calendar.add(Calendar.MONTH, num);
			break;
		case DAY:
			calendar.add(Calendar.DAY_OF_MONTH, num);
			break;
		case HOUR:
			calendar.add(Calendar.HOUR_OF_DAY, num);
			break;
		case MINUTE:
			calendar.add(Calendar.MINUTE, num);
			break;
		case SECOND:
			calendar.add(Calendar.SECOND, num);
			break;
		default:
			System.out.println("函数的第一个参数不对,不在(年/月/日/时/分/秒)中");
		}
	}

	/**
	 * 对象的toString方法:打印当前日期中文格式
	 * 
	 * @param 无
	 * @return 无
	 */
	@Override
	public String toString() {
		return getDate() + " " + getTime();

	}

	public String toYYYYMMDD() {
		return String.format("%04d%02d%02d", getYear(), getMonth(), getDay());
	}

	public String toHHMMSS() {
		return String.format("%02d%02d%02d", getHour(), getMinute(),
				getSecond());
	}

	public String toYYYYMMDDHHMMSS() {
		return toYYYYMMDD() + toHHMMSS();
	}

	/**
	 * 计算两个日期之间的间隔
	 * 
	 * @param bDate
	 *            :开始日期
	 * @param eDate
	 *            :结束日期
	 * @param mYear
	 *            :间隔年数
	 * @param mMon
	 *            :间隔月数
	 * @param mDay
	 *            :间隔天数
	 * @return
	 */
	public static int[] dateSpace(Date bDate, Date eDate) {
		Calendar bCal = Calendar.getInstance();
		Calendar eCal = Calendar.getInstance();

		bCal.setTime(bDate.after(eDate) ? eDate : bDate);
		eCal.setTime(bDate.after(eDate) ? bDate : eDate);
		int bDay, eDay, bMon, eMon, bYear, eYear;
		int mYear, mMon, mDay;
		// 计算天数差别
		bDay = bCal.get(Calendar.DATE);
		bMon = bCal.get(Calendar.MONTH);
		bYear = bCal.get(Calendar.YEAR);
		eDay = eCal.get(Calendar.DATE);
		eMon = eCal.get(Calendar.MONTH);
		eYear = eCal.get(Calendar.YEAR);
		if (eDay < bDay) {
			// 月份减少1
			eMon = eMon - 1;
			if (eMon < 0) {
				eMon = 11;
				// 年份减少1
				eYear = eYear - 1;
			}
			eCal.set(Calendar.MONTH, eMon);
			eCal.set(Calendar.YEAR, eYear);
			eDay += eCal.get(Calendar.DAY_OF_MONTH);
			mDay = eDay - bDay;
		} else {
			mDay = eDay - bDay;
		}
		// 计算月份差别
		if (eMon < bMon) {
			// 年份减1
			eYear = eYear - 1;
			mMon = 12 + eMon - bMon;
		} else {
			mMon = eMon - bMon;
		}

		// 计算年份
		mYear = eYear - bYear;
		int[] retValue = new int[3];
		retValue[0] = mYear;
		retValue[1] = mMon;
		retValue[2] = mDay;
		return retValue;
	}

	/**
	 * 计算两个日期之间的间隔
	 * 
	 * @param bDate
	 * @param eDate
	 * @return String:中文格式：xx年xx月xx天
	 */
	public static String dateSpaceYMD(Date bDate, Date eDate) {
		int[] mSpace = dateSpace(bDate, eDate);
		return mSpace[0] + "年" + mSpace[1] + "月" + mSpace[2] + "天";
	}

	/**
	 * 计算两个日期之间的间隔
	 * 
	 * @param bDate
	 * @param eDate
	 * @return String:中文格式：xx年xx月
	 */
	public static String dateSpaceYM(Date bDate, Date eDate) {
		int[] mSpace = dateSpace(bDate, eDate);
		if (mSpace[2] > 0)
			mSpace[1] += 1;
		if (mSpace[1] > 12) {
			mSpace[1] = 0;
			mSpace[0] += 1;
		}
		return mSpace[0] + "年" + mSpace[1] + "月";
	}

	/**
	 * 计算两个日期之间的间隔
	 * 
	 * @param bDate
	 * @param eDate
	 * @return String:中文格式：xx年
	 */
	public static String dateSpaceY(Date bDate, Date eDate) {
		int[] mSpace = dateSpace(bDate, eDate);
		if (mSpace[2] > 0 || mSpace[1] > 0)
			mSpace[0] += 1;

		return mSpace[0] + "年";
	}

	/**
	 * 计算两个日期之间的间隔
	 * 
	 * @param bDate
	 * @param eDate
	 * @return String:中文格式：xx年
	 */
	public static String dateSpaceM(Date bDate, Date eDate) {
		int[] mSpace = dateSpace(bDate, eDate);
		if (mSpace[2] > 0)
			mSpace[1] += 1 + 12 * mSpace[0];

		return mSpace[0] + "月";
	}

	public static String[] dateSpaceDays(String strStart, String strEnd,
			String format) {
		try {
			Calendar calStart = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance();
			calStart.setTime(new SimpleDateFormat(format).parse(strStart));
			calEnd.setTime(new SimpleDateFormat(format).parse(strEnd));

			long timeStart = calStart.getTimeInMillis();
			long timeEnd = calEnd.getTimeInMillis();
			long theday = (timeEnd - timeStart) / (1000 * 60 * 60 * 24) + 1;

			String[] days = new String[Integer.valueOf(String.valueOf(theday))];
			StringBuffer dayBuffer = null;
			for (int k = 0; k < theday; k++) {
				dayBuffer = new StringBuffer();
				if (k > 0) {
					calStart.add(Calendar.DATE, 1);
				}
				dayBuffer.append(String.format("%04d",
						calStart.get(Calendar.YEAR)));
				dayBuffer.append("-");
				dayBuffer.append(String.format("%02d",
						calStart.get(Calendar.MONTH) + 1));
				dayBuffer.append("-");
				dayBuffer.append(String.format("%02d",
						calStart.get(Calendar.DATE)));
				days[k] = dayBuffer.toString();
			}

			return days;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String getMyDate() {
		StringBuffer dayBuffer = new StringBuffer();
		Calendar calStart = Calendar.getInstance();
		dayBuffer.append(String.format("%04d", calStart.get(Calendar.YEAR)));
		dayBuffer.append("-");
		dayBuffer
				.append(String.format("%02d", calStart.get(Calendar.MONTH) + 1));
		dayBuffer.append("-");
		dayBuffer.append(String.format("%02d", calStart.get(Calendar.DATE)));
		return dayBuffer.toString();
	}

	public static String getMyTime() {
		StringBuffer dayBuffer = new StringBuffer();
		Calendar calStart = Calendar.getInstance();
		dayBuffer.append(String.format("%02d",
				calStart.get(Calendar.HOUR_OF_DAY)));
		dayBuffer.append(":");
		dayBuffer.append(String.format("%02d", calStart.get(Calendar.MINUTE)));
		dayBuffer.append(":");
		dayBuffer.append(String.format("%02d", calStart.get(Calendar.SECOND)));
		return dayBuffer.toString();
	}

	/**
	 * 判断当年是否是润年
	 * 
	 * @param 无
	 * @return true:闰年 false:非闰年
	 */
	public boolean isLeapYear() {
		if ((((getYear() % 4) == 0) && ((getYear() % 100) != 0))
				|| ((getYear() % 400) == 0))
			return true;
		else
			return false;
	}

	/**
	 * 日期(String格式)转成java.util.date格式 如给的字符串不能转换，则返回null
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date stringToDate(String dateType) {
		SimpleDateFormat formatDate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		formatDate.setLenient(false);
		Date d;
		try {
			d = formatDate.parse(dateType);
			return d;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算日期加天数，返回新的日期
	 * 
	 * @param d
	 *            字符串型原始日期
	 * @param daySum
	 *            所加的天数，减天数使用负数
	 * @return 字符串类型d新日期
	 */
	public static String getDatePlus(String d, int daySum) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = "";
		try {
			Date date = formatter.parse(d);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_WEEK, daySum);
			datetime = formatter.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datetime;
	}

	/**
	 * 计算两个时间之间的天数
	 * 
	 * @param fromDate
	 *            开始时间，必须小于toDate
	 * @param toDate
	 *            结束时间
	 * @return 相差的天数 int
	 */
	public static int dateSpaceDays(String fromDate, String toDate) {
		long date1 = stringToDate(fromDate).getTime();
		long date2 = stringToDate(toDate).getTime();
		long date = date2 - date1;
		return (int) (date / (1000 * 60 * 60 * 24));
	}

	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * 把日期对象转换成String格式（yyyy-MM-dd）
	 * @author Zongjie Chen
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String dateStandToString(Date date) {
		if (date == null) {
			return "";
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		StringBuffer dayBuffer = new StringBuffer();
		dayBuffer.append(String.format("%04d", calendar.get(Calendar.YEAR)));
		dayBuffer.append("-");
		dayBuffer
				.append(String.format("%02d", calendar.get(Calendar.MONTH) + 1));
		dayBuffer.append("-");
		dayBuffer.append(String.format("%02d", calendar.get(Calendar.DATE)));
		return dayBuffer.toString();
	}
	/**
	 * 把日期转换成String格式（年月日时分秒）
	 * @author Zongjie Chen
	 * @return 年月日时分秒
	 */
	public String dateTimeToChineseString() {
		return this.getDate() + this.getTime();
	}
	
}
