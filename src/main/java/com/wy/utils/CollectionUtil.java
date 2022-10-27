package com.wy.utils;

import java.util.Collection;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/10/25 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;Collection工具类
 */
public class CollectionUtil {

	/**
	 * 描述: 判断一个Collection接口下的实现子类容器是否为空 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: coll-Collection接口下的实现子类 <br/>
	 * 返回值: 为空则返回true,不为空返回false <br/>
	 */
	public static boolean isNull(Collection coll){
		if(coll==null || coll.isEmpty()){
			return true;
		}
		return false;
	}
}