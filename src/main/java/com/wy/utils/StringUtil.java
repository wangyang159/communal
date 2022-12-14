package com.wy.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/9/18 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;字符串工具类
 */
public class StringUtil {

	/**
	 * 描述: 判断源字符串是否有值,空串也算没值,空格算有值 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: src-字符串 <br/>
	 * 返回值: true表示有值,false表示空 <br/>
	 */
	public static boolean hasLength(String src) {
		return src != null && !src.equals("");
	}

	/**
	 * 描述: 判断源字符串,空串和空格都算没有值 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: src-字符串 <br/>
	 * 返回值: true表示有值,false表示空 <br/>
	 */
	public static boolean hasText(String src) {
		return src != null && !src.trim().equals("");
	}

	/**
	 * 描述: 随机生成单个中文，默认字符集GB2312 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: NULL <br/>
	 * 返回值 单个中文汉字 <br/>
	 */
	public static String getOneChineseStr() {
		int q, w;
		Random random = new Random();
		q = 176 + random.nextInt(40);
		w = 161 + random.nextInt(94);

		byte[] b = new byte[2];
		b[0] = (byte) q;
		b[1] = (byte) w;

		String s = null;
		try {
			s = new String(b, "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 描述: 返回参数length个中文汉字字符串，字符集GB2312 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: length-汉字个数 <br/>
	 * 返回值: 汉字组成的字符串 <br/>
	 */
	public static String randomChineseString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(getOneChineseStr());
		}
		return sb.toString();
	}

	/**
	 * 描述: 随机获取一个百家姓中的姓氏 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: NULL <br/>
	 * 返回值: 中国姓氏 <br/>
	 */
	public static String generateChineseFamily() {
		String[] surname = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "楮", "卫", "蒋", "沈", "韩", "杨", "朱", "秦",
				"尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦",
				"章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
				"酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐",
				"于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵",
				"湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
				"屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闽", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭",
				"梅", "盛", "林", "刁", "锺", "徐", "丘", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝",
				"管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸",
				"左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "於", "惠", "甄", "麹", "家",
				"封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷",
				"车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "斜", "厉", "戎", "祖",
				"武", "符", "刘", "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "薄", "印", "宿", "白", "怀", "蒲", "邰",
				"从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能", "苍", "双", "闻", "莘", "党",
				"翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍", "郤", "璩", "桑", "桂", "濮", "牛", "寿", "通",
				"边", "扈", "燕", "冀", "郏", "浦", "尚", "农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦",
				"艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国",
				"文", "寇", "广", "禄", "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾", "敖",
				"融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯", "相",
				"查", "后", "荆", "红", "游", "竺", "权", "逑", "盖", "益", "桓", "公", "晋", "楚", "阎", "法", "汝", "鄢", "涂", "钦", "岳",
				"帅", "缑", "亢", "况", "后", "有", "琴", "商", "牟", "佘", "佴", "伯", "赏", "墨", "哈", "谯", "笪", "年", "爱", "阳", "佟",
				"万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方", "赫连", "皇甫", "尉迟", "公羊", "澹台", "公冶", "宗政", "濮阳", "淳于",
				"单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "锺离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空", "丌官", "司寇",
				"仉", "督", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "乐正", "壤驷", "公良", "拓拔", "夹谷", "宰父", "谷梁", "段干", "百里",
				"东郭", "南门", "呼延", "归", "海", "羊舌", "微生", "梁丘", "左丘", "东门", "西门", "南宫" };

		return  surname[RandomUtil.random(0, surname.length - 1)];
	}

	/**
	 * 描述: 获取一个中国姓名 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: NULL <br/>
	 * 返回值: 中国姓名 <br/>
	 */
	public static String generateChineseName(){
		return generateChineseFamily()+randomChineseString(RandomUtil.random(1, 2));
	}

	/**
	 * 描述: 判断字符串是否是邮箱格式 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: email-邮箱 <br/>
	 * 返回值: true是邮箱,false不是 <br/>
	 */
	public static boolean isEmail(String email) {
		String regex="^[\\d|\\w]+ [\\d|\\w]+\\.[\\d|\\w]+$";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(email);

		return m.matches();

	}

	/**
	 * 描述: 字符串中是否手机号，只能判断开头为1、3、4、5、6、8的手机号 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: phone-手机号 <br/>
	 * 返回值: true是,false不是 <br/>
	 */
	public static boolean isPhoneNumber(String phone) {
		String regex="^1[3|4|5|6|8]{1}\\d{9}$";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(phone);

		boolean isMatch = m.matches();

		return isMatch ;
	}

	/**
	 * 描述: 字符串中的值是否是数字,包含正负数，小数 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: number-目标字符串 <br/>
	 * 返回值: true是,false不是 <br/>
	 */
	public static boolean isNumber(String number) {
		String regex="^[\\-]{0,1}\\d+[\\.]{0,1}\\d+$";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(number);

		return m.matches();
	}

	/**
	 * 描述: 实现判断传入的字符串是否为包含http的url地址 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: src-目标字符串 <br/>
	 * 返回值: true是,false不是 <br/>
	 */
	public static boolean isHttpUrl(String src){
		try {
			URL url = new URL(src);
			url.openStream();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 描述: 把unicode转换成中文 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: dataStr-需要装换的字符串，例如\u7F6E\u7F6E\u7F6E <br/>
	 * 返回值: 返回已转成中文的字符串 <br/>
	 */
	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

	/**
	 * 描述: 中文转unicode编码 <br/>
	 * 作者: wangyang <br/>
	 * 创建时间: 2022/10/25 <br/>
	 * 参数: gbString-包含中文的字符串 <br/>
	 * 返回值: unicode编码，例如\u7F6E\u7F6E\u7F6E <br/>
	 */
	public static String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int i = 0; i < utfBytes.length; i++) {
			String hexB = Integer.toHexString(utfBytes[i]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		return unicodeBytes;
	}

}