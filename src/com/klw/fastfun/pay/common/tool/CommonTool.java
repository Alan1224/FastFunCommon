/**
 * FastFunCommon
 */
package com.klw.fastfun.pay.common.tool;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import com.juice.orange.game.exception.JuiceException;
import com.juice.orange.game.log.LoggerFactory;
import com.klw.fastfun.pay.common.domain.OrderReqInfo;

/**
 * @author klwplayer.com
 *
 * 2015年3月30日
 */
public class CommonTool {
	private static org.apache.log4j.Logger logger = LoggerFactory.getLogger(CommonTool.class);
	public static final String FASTSER_DAYLIMIT = "fastser_daylimit_";
	public static final String FASTSER_ORDER = "fastser_order_";
	public static final String FASTSER_MGORDER = "fastser_mgorder_";
	public static final String FASTSER_POLLORDER = "fastser_pollorder_";
	public static final String FASTSER_MGKEY = "fastser_mgkey_";
	public static final String FASTSER_POLLKEY = "fastser_pollkey_";
	public static final String FASTSER_PREFIX = "fastser_pre_";
	public static final String FASTSER_MOBILE = "fastser_mobile_";
	public static final String FASTSER_BASSTA = "fastser_bassta_";
	public static final String NUM_REGEX = "^[0-9]+$";
	
	public static char[] params_num = {'0','1','2','3','4','5','6','7','8','9'};
	
	public static char[] params_char = {'q', 'w','e',
		'r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
	
	public static char[] params_all = {'0','1','2','3','4','5','6','7','8','9', 'q', 'w','e',
		'r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
	
	public static Map<String, CPParam> cpParamMap = new HashMap<String, CPParam>();
	public static Map<String, String> imsiMap = new HashMap<String, String>();
	/*public static void main(String[] args) {
		System.out.println(getImsiSub("460078209796769"));
	}*/
	
	public static String getImsiMapKey(OrderReqInfo info, String flag) {
		String imsi = info.getImsi();
		String spId = info.getSpId();
		String key = flag+imsi+spId;
		return key;
	}
	
	/***
	 * 根据IMSI获取分区
	 */
	public static String getImsiSub(String imsi) {
		if (imsi==null || imsi.length() == 0) return "00";
		
		int total = 0;
		int length = imsi.length();
		for (int i=0; i<length; i++) {
			char c = imsi.charAt(i);
			int cInt = convertInt(c+"");
			//System.out.println(cInt);
			total += cInt;
		}
		
		//System.out.println("total="+total);
		StringBuilder sub = new StringBuilder();
		int subInt = total%12;
		if (subInt >= 10) {
			sub.append(subInt);
		} else {
			sub.append("0").append(subInt);
		}
		return sub.toString();
	}
	
	/**
	 * 获取电话制式
	 * 定位运营商类型
	 * 
	 * 5,6两位就可分辨了
	 * 00,02为移动
	 * 01为联通
	 * 03为电信
	 */
	public static final int locateOperator(String imsi) {
		try {
			if (imsi == null || imsi.length()<15) {
				throw new JuiceException("IMSI:" + imsi + " 格式错误！");
			}
			
			String mcc = imsi.substring(0, 3);
			if (!mcc.equals("460")) {
				throw new JuiceException("IMSI mcc:" + mcc + " 不属于中国!");
			}
			
			// 划定区分网址：http://en.wikipedia.org/wiki/Mobile_country_code
			String mnc = imsi.substring(3, 5);
			if (mnc.equals("00") || mnc.equals("02") || mnc.equals("04") || mnc.equals("07")) {
				return ConstantDefine.CODE_TYPE_MOBILE;
			}
			
			if (mnc.equals("01") || mnc.equals("06") || mnc.equals("09") || mnc.equals("20")) {
				return ConstantDefine.CODE_TYPE_UNICOM;
			}
			
			if (mnc.equals("03") || mnc.equals("05") || mnc.equals("11")) {
				return ConstantDefine.CODE_TYPE_TELNET;
			}
			
			throw new JuiceException("IMSI mnc:" + mnc + " 运营商不能区分！"); 
		} catch (IndexOutOfBoundsException e) {
			logger.error("根据IMSI获取手机号码类型错误");
		} catch (Exception e) {
			logger.error("根据IMSI获取手机号码类型错误");
		}
		return ConstantDefine.CODE_TYPE_MOBILE;
	}
	
	public static Random random = new Random();
	/***
	 * 生成订单号
	 */
	public static StringBuilder getklwOrderNO(String month, String spFlag) {
		StringBuilder orderNO = new StringBuilder();
		orderNO.append(month).append(spFlag).append(create11Order());
		return orderNO;
	}
	/***
	 * 生成订单号
	 */
	public static String getxsOrderNO(String spFlag) {
		StringBuilder orderNO = new StringBuilder();
		orderNO.append(spFlag).append(create12Order());
		return orderNO.toString();
	}
	
	/***
	 * 生成订单号
	 */
	public static String genrateOrderNO(String month, String spFlag) {
		StringBuilder orderNO = new StringBuilder();
		orderNO.append(month).append(spFlag).append(create12Order());
		return orderNO.toString();
	}
	
	/***
	 * 生成订单号
	 */
	public static String genratexsOrderNO(String month, String spFlag) {
		StringBuilder orderNO = new StringBuilder();
		orderNO.append(month).append(spFlag).append(create11Order());
		return orderNO.toString();
	}
	
	
	/** 
	 * 随机生成count位数字
	 * */
	public static String createNum(int count) {
		StringBuilder id = new StringBuilder();
		
		for (int i=0;i<count;i++) {
			char prefix = params_num[random.nextInt(params_num.length)];
			id.append(prefix);
		}
		
		return id.toString();
	}
	
	/** 随机生成16位订单号*/
	private static String create12Order() {
		StringBuilder id = new StringBuilder();
		char first = params_char[random.nextInt(params_char.length)];
		id.append(first);
		
		for (int i=0;i<11;i++) {
			char prefix = params_num[random.nextInt(params_num.length)];
			id.append(prefix);
		}
		
		return id.toString();
	}
	
	private static String create11Order() {
		StringBuilder id = new StringBuilder();
		
		for (int i=0;i<11;i++) {
			char prefix = params_all[random.nextInt(params_all.length)];
			id.append(prefix);
		}
		
		return id.toString();
	}
	
	
	public static int convertInt(String value) {
		int result = 0;
		try {
			result = Integer.parseInt(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static CPParam getCPParam(String cpId, int radio) {
		CPParam cpp = cpParamMap.get(cpId);
		if (cpp == null || cpp.getSynRadio() != radio) {
			StringBuilder vc = new StringBuilder();
			if (radio > 0) {
//				vc.append("0#1#2#3#4#5#6#7#8#9#");
//				for (int i = 10; i < radio; i++) {
				for (int i = 0; i < radio; i++) {
					int randomInt = getRandomInt(vc.toString());
					if (i == (radio-1)) {
						vc.append(randomInt);
					} else {
						vc.append(randomInt).append("#");
					}
				}
			}
			
			cpp = new CPParam(vc.toString());
		}
		
		cpp.setSynRadio(radio);
		return cpp;
	}
	
	public static void updateCPParam(String cpId, CPParam cpp) {
		cpParamMap.put(cpId, cpp);
	}
	
	private static int getRandomInt(String str) {
		while (true) {
			int randomInt = random.nextInt(100);
			if (!isContain(str, randomInt)) {
				return randomInt;
			}
		}
	}
	
	public static boolean isContain(String str, int i) {
		if (str == null || str.length()<=0) return false;
		
		String[] strs = str.split("\\#");
		for (String temp : strs) {
			int tempInt = convertInt(temp);
			if (tempInt == i) return true;
		}
		return false;
	}
	
	public static String createIndexKey(String key,String spId) {
		StringBuilder index = new StringBuilder();
		index.append(key).append(spId);
		return index.toString();
	}
	/*public static void main(String[] args) {
		int cou = 100;
		CPParam cpp = getCPParam("test01",cou);
		System.out.println(cpp.getVc());
		String[] sarr = cpp.getVc().split("#");
		int[] intarr = new int[cou];
		for (int i=0;i<cou;i++){
			int a = convertInt(sarr[i]);
			intarr[i] = a;
		}
		int tenmp = 0;
		for (int i=0; i<intarr.length-1; i++) {
			for (int j=0; j<intarr.length-1; j++) {
				if (intarr[j]>intarr[j+1]) {
					tenmp = intarr[j];
					intarr[j] = intarr[j+1];
					intarr[j+1] = tenmp;
				}
			}
		}
		for (int i=0;i<intarr.length;i++) {
			System.out.print(intarr[i]+" ");
		}
	}*/
	
	public static Map<String, String> parseYZ(String content) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			StringTokenizer st = new StringTokenizer(content, "&");
			while (st.hasMoreTokens()) {
				String[] pair = st.nextToken().split("=");
				String key = URLDecoder.decode(pair[0], "UTF-8");
				String value = pair.length == 1 ? null : URLDecoder.decode(
						pair[1], "UTF-8");
				params.put(key, value);
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Couldn't parse query string: "
					+ content, e);
		}
		return params;
	}
	
	public static Map<String, Object> parseObj(String content) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			StringTokenizer st = new StringTokenizer(content, "&");
			while (st.hasMoreTokens()) {
				String[] pair = st.nextToken().split("=");
				String key = URLDecoder.decode(pair[0], "UTF-8");
				String value = pair.length == 1 ? null : URLDecoder.decode(
						pair[1], "UTF-8");
				params.put(key, value);
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Couldn't parse query string: "
					+ content, e);
		}
		return params;
	}
	
	public static Map<String, String> parseHNZFB(String content) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			if (content != null) {
				content = content.replace("\r", "").replace("\n", "");
				String[] tmp = content.split("Content-Disposition: form-data");
				for (String s : tmp) {
					if (s.contains("name=")) {
						int first = s.indexOf("\"") + 1;
						int second = s.indexOf("\"", first) + 1;
						int third = s.indexOf("-") + 1;
						params.put(s.substring(first,second-1), s.substring(second,third-1));
					}
				}
				
			}
		} catch (Exception e) {
			throw new RuntimeException("Couldn't parse query string: "
					+ content, e);
		}
		return params;
	}
	
	public static String GetTelByImsi(String imsi) {
		try {
			Double.parseDouble(imsi);
		} catch (Exception e) {
			return "";
		}
		String moblie = "";
		int index = 0;
		if (imsi != null && !"".equals(imsi) && imsi.length() > 10) {
			String mnc = imsi.substring(0, 5);
			String msin = imsi.substring(5, imsi.length());
			int nMNC = Integer.parseInt(mnc);
			switch (nMNC) {
			case 46000:
				index = Integer.parseInt(msin.substring(3, 4));
				if (index >= 5 && index <= 9) {
					moblie = "13";
					moblie += index;
					moblie += "0";
					moblie += msin.substring(0, 3);
				} else {
					moblie = "13";
					moblie += index + 5;
					moblie += msin.substring(4, 5);
					moblie += msin.substring(0, 3);
				}
				break;
			case 46002:
				index = Integer.parseInt(msin.substring(0, 1));
				switch (index) {
				case 0:
					if (Integer.parseInt(msin.substring(1, 2)) <= 8) {
						moblie = "134" + msin.substring(1, 5);
					}
					break;
				case 1:
				case 2:
				case 8:
				case 9:
					moblie = "15" + index + msin.substring(1, 5);
					break;
				case 3:
					moblie = "150" + msin.substring(1, 5);
					break;
				case 5:
					moblie = "183" + msin.substring(1, 5);
					break;
				case 6:
					moblie = "182" + msin.substring(1, 5);
					break;
				case 7:
					moblie = "187" + msin.substring(1, 5);
					break;
				}
				break;
			case 46007:
				index = Integer.parseInt(msin.substring(0, 1));
				switch (index) {
				case 7:
					moblie = "157" + msin.substring(1, 5);
					break;
				case 8:
					moblie = "188" + msin.substring(1, 5);
					break;
				case 9:
					moblie = "147" + msin.substring(1, 5);
					break;
				}
				break;
			case 46001:
				index = Integer.parseInt(msin.substring(4, 5));
				switch (index) {
				case 0:
				case 1:
					moblie += "130";
					moblie += msin.substring(3, 4);
					moblie += msin.substring(0, 3);
					break;
				case 9:
					moblie += "131";
					moblie += msin.substring(3, 4);
					moblie += msin.substring(0, 3);
					break;
				case 2:
					moblie += "132";
					moblie += msin.substring(3, 4);
					moblie += msin.substring(0, 3);
					break;
				case 3:
					moblie += "156";
					moblie += msin.substring(3, 4);
					moblie += msin.substring(0, 3);
					break;
				case 4:
					moblie += "155";
					moblie += msin.substring(3, 4);
					moblie += msin.substring(0, 3);
					break;
				case 6:
					moblie += "186";
					moblie += msin.substring(3, 4);
					moblie += msin.substring(0, 3);
					break;
				case 5:
					moblie += "185";
					moblie += msin.substring(3, 4);
					moblie += msin.substring(0, 3);
					break;
				}
				break;
			case 46003:
				if (msin.substring(0, 1).equals("0")) {
					index = Integer.parseInt(msin.substring(1, 2));
					switch (index) {
					case 9:
						if ("00" == msin.substring(2, 4)) {
							moblie += "13301";
							moblie += msin.substring(4, 6);
						} else if ("53" == msin.substring(2, 4) || "54" == msin.substring(2, 4)) {
							moblie += "133";
							moblie += Integer.parseInt(msin.substring(2, 6)) + 4500;
						} else {
							moblie += "133";
							moblie += msin.substring(2, 6);
						}
						break;
					case 3:
						moblie += "133";
						moblie += Integer.parseInt(msin.substring(2, 6)) + 5000;
						break;
					}
				} else {
					moblie += "153";
					moblie += msin.substring(1, 3);
					moblie += msin.substring(4, 6);
				}
				break;

			default:
				break;
			}
		}
		return moblie;
	}
	
	/** 16进制字符串转为十进制字符串 */
	public static String hexStringToStr(String content) {
		byte[] baKeyword = new byte[content.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						content.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			content = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return content;
	}
	
	/**
	 * 调换集合中两个指定位置的元素, 若两个元素位置中间还有其他元素，需要实现中间元素的前移或后移的操作。
	 * @param list 集合
	 * @param oldPosition 需要调换的元素
	 * @param newPosition 被调换的元素
	 * @param <T>
	 */
	public static <T> void swap2(List<T> list, int oldPosition, int newPosition){
	    if(null == list){
	        throw new IllegalStateException("The list can not be empty...");
	    }

	    // 向前移动，前面的元素需要向后移动
	    if(oldPosition < newPosition){
	        for(int i = oldPosition; i < newPosition; i++){
	            Collections.swap(list, i, i + 1);
	        }
	    }

	    // 向后移动，后面的元素需要向前移动
	    if(oldPosition > newPosition){
	        for(int i = oldPosition; i > newPosition; i--){
	            Collections.swap(list, i, i - 1);
	        }
	    }
	}
	
}
