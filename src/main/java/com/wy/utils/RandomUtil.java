package com.wy.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/9/18 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;随机数工具类 <br/>
 */
public class RandomUtil {

	/**
	 * 描述: 返回min-max之间的随机整数（包含min和max值） <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: min-开始值,max-最大值 <br/>
	 * 返回值: 范围内的整数 <br/>
	 */
	public static int random(int min, int max){
		/*
		 * nextInt方法生成整数准则是从0开始小于等于给定数
		 * 因此如5到10之间，目标是生成5 6 7 8 9 10
		 * 但是方法中只用一个值且还是从0开始
		 * 因此先相减结果代表可以生成多少个数字
		 * 同时每一个生成的数就是“可变量”
		 * 之所以加一是因为生成的数最大值比给定上线小1
		 * 最后生成的数字与最小值相加，这时最小数就是“不变量”
		 * 不变量+可变量=最终数
		 */
		return new Random().nextInt(max-min+1)+min;
	}

	/**
	 * 描述: 在最小值min与最大值max之间截取subs个不重复的随机数 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: min-最小值,max-最大值,subs-个数 <br/>
	 * 返回值: 包含随机数的int数组 <br/>
	 */
	public static int[] subRandom (int min, int max, int subs){
		//控制个数安全
		if(subs>(max-min)+1){
			throw new RuntimeException("范围异常");
		}

		Set<Integer> set=new HashSet<>();
		while(set.size()<subs){
			set.add(random(min, max));
		}

		int[] result=new int[subs];
		int index=0;
		for (int i : set) {
			result[index]=i;
			index++;
		}
		return result;
	}

	/**
	 * 描述: 返回1个1-9,a-Z之间的随机字符 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: NULL <br/>
	 * 返回值: 随机字符 <br/>
	 */
	public static char randomCharacter (){
		String st="123456789"
				+ "qwertyuiopasdfghjklzxcvbnm"
				+ "QWERTYUIOPASDFGHJKLZXCVBNM";
		int index = random(0, st.length()-1);
		return st.charAt(index);
	}

	/**
	 * 描述: 返回1个1-9之间的随机字符组成的字符串 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: length-字符串的长度 <br/>
	 * 返回值: 包含随机字符的字符串 <br/>
	 */
	public static String randomNum (Integer length){
		String st="123456789";
		StringBuffer result=new StringBuffer();
		for (int i = 0; i < length; i++) {
			int index = random(0, st.length()-1);
			result.append(st.charAt(index));
		}
		return result.toString();
	}

	/**
	 * 描述: 返回包含1-9,a-Z之间的随机字符组成的字符串 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: length-个数 <br/>
	 * 返回值: 包含随机字符的字符串 <br/>
	 */
	public static String randomString(int length){
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(randomCharacter());
		}
		return sb.toString();
	}

}