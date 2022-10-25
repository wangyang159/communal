package com.wy.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @作者 wangyang
 * @创建时间 2022/10/24
 * @描述
 *     时间工具类
 */
public class DateUtil {

	/**
	 *@描述 随机生成一个给定范围的日期
	 *@作者 wangyang
	 *@创建时间 2022/10/25
	 *@参数 beginDate-开始时间,enDate-结束时间
	 *@返回值
	 */
	public static Date randomDate(Date beginDate, Date endDate) {
		long begin = beginDate.getTime();
		long end = endDate.getTime();
		if (end <= begin) {
			throw new RuntimeException("时间异常");
		}
		long l = (long) (Math.random() * (end - begin + 1) + begin);
		Date result = new Date(l);
		return result;
	}

	/**
	 *@描述 返回一个月的月初
	 *@作者 wangyang
	 *@创建时间 2022/10/25
	 *@参数 date-某月中的任意一天
	 *@返回值 传入日期所在月的月初
	 */
	public static Date getMonthBegin(Date date) {
		Calendar c=Calendar.getInstance();
		//初始化日期
		c.setTime(date);
		//修改日期
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 *@描述 返回一个月的月末
	 *@作者 wangyang
	 *@创建时间 2022/10/25
	 *@参数 date-某月中的任意一天
	 *@返回值 传入日期所在月的月末
	 */
	public static Date getMonthEnd(Date date) {
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		Date monthBegin = getMonthBegin(c.getTime());
		c.setTime(monthBegin);
		c.add(Calendar.SECOND, -1);
		return c.getTime();
	}

	/**
	 *@描述 根据出生年月得到岁数
	 *@作者 wangyang
	 *@创建时间 2022/10/25
	 *@参数 dateOfBirth-出生年月
	 *@返回值 Integer类型的岁数
	 */
	public static Integer getAge(Date dateOfBirth){
		Calendar c=Calendar.getInstance();
		//得到系统年月日
		int s_year = c.get(Calendar.YEAR);
		int s_month = c.get(Calendar.MONTH);
		int s_day = c.get(Calendar.DAY_OF_MONTH);
		//得到出生年月日
		c.setTime(dateOfBirth);
		int b_year = c.get(Calendar.YEAR);
		int b_month = c.get(Calendar.MONTH);
		int b_day = c.get(Calendar.DAY_OF_MONTH);
		//开始计算
		int age = s_year-b_year;
		//开始完善结果
		if(s_month<b_month){
			age--;
		}
		if(s_month==b_month && s_day<b_day){
			age--;
		}
		return age;
	}
}
