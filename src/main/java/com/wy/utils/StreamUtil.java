package com.wy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * @作者 wangyang
 * @创建时间 2022/9/18
 * @描述
 *  文件流工具类
 */
public class StreamUtil {

	/**
	 *@描述 读取一个文件的全部信息，以每一行为单位，存放到list集合中
	 *@作者 wangyang
	 *@创建时间 2022/10/25
	 *@参数 input-目标文件的字节输入流
	 *@返回值 包含每行内容的List<String>集合
	 */
	public static List<String> loadFile(InputStream input) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(input));
		List<String> result=new ArrayList<String>();
		String buffer;
		while( (buffer=br.readLine())!=null ){
			result.add(buffer);
		}
		return result;
	}
}
