package myself.wy.utils;

import java.util.Collection;

/**
 * @作者 wangyang
 * @创建时间 2022/10/25
 * @描述
 *     Collection工具类
 */
public class CollectionUtil {

	/**
	 *@描述 判断一个Collection接口下的实现子类容器是否为空
	 *@作者 wangyang
	 *@创建时间 2022/10/25
	 *@参数 coll-Collection接口下的实现子类
	 *@返回值 为空则返回true,不为空返回false
	 */
	public static boolean isNull(Collection coll){
		if(coll==null || coll.isEmpty()){
			return true;
		}
		return false;
	}
}
