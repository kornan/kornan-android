package net.kornan.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	/**
	 * 判断手机格式是否正确
	 */
	public static final String RE_MOBILE_NO = "^((13[0-9])|(14[7])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
	/**
	 * 判断email格式是否正确
	 */
	public static final String RE_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	/**
	 * 判断是否全是数字
	 */
	public static final String RE_NUMERIC = "[0-9]*";
	/**
	 * 匹配中文字符
	 */
	public static final String RE_CHINESE_CHARACTERS = "^[\u4e00-\u9fa5]+$";
	/**
	 * 匹配邮政编码
	 */
	public static final String RE_ZIP_CODE = "[1-9]\\d{5}(?!\\d)";
	/**
	 * 匹配帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)
	 */
	public static final String RE_ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{4,15}";
	/**
	 * 匹配IP地址
	 */
	public static final String RE_IP = "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}";
	/**
	 * 匹配身份证(15位)
	 */
	public static final String RE_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	/**
	 * 匹配身份证(18位)
	 */
	public static final String RE_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";

	// 区号-号码
	/**
	 * 3位区号+8位号码
	 */
	public static final String RE_TEL_AREA3 = "\\(?(010|021|022|023|024|025|026|027|028|029|852)?\\)?-?\\d{8}";

	/**
	 * 4位区号+号码
	 */
	public static final String RE_TEL_AREA4 = "\\(?(0[3-9][0-9]{2})?\\)?-?\\d{7,8}";
	/**
	 * 区号-号码
	 */
	public static final String RE_TELPHONE = "(\\(?(010|021|022|023|024|025|026|027|028|029|852)?\\)?-?\\d{8})|(\\(?(0[3-9][0-9]{2})?\\)?-?\\d{7,8})";
	/**
	 * 3位区号+8位号码+分机
	 */
	public static final String RE_TEL_AREA3_EXT = "\\(?(010|021|022|023|024|025|026|027|028|029|852|)\\)?-?\\d{8}(\\-?[0-9]{1,4})?";
	/**
	 * 4位区号+号码+分机
	 */
	public static final String RE_TEL_AREA4_EXT = "\\(?(0[3-9][0-9]{2})\\)?-?\\d{7,8}(\\-?[0-9]{1,4})?";
	/**
	 * 区号-号码+分机
	 */
	public static final String RE_TELPHONE_EXT = "(\\(?(010|021|022|023|024|025|026|027|028|029|852|)\\)?-?\\d{8}(\\-?[0-9]{1,4})?)|(\\(?(0[3-9][0-9]{2})\\)?-?\\d{7,8}(\\-?[0-9]{1,4})?)";
	/**
	 * HTML标签
	 */
	public static final String RE_HTML = "<[^>]+>";
	/**
	 * 金额
	 */
	public static final String RE_MONEY="^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,2})?))$";
	private static Regex regex = null;

	private Regex() {

	}

	public static Regex getRegex() {
		if (regex == null)
			regex = new Regex();
		return regex;
	}

	/**
	 * 
	 * @param str
	 * @param expression
	 * @return
	 */
	public static boolean checkString(String str, String expression) {
		Pattern p = Pattern.compile(expression);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}
