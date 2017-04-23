package com.thinkgem.jeesite.common.utils;

import java.util.Date;
import java.util.Random;

/**
 * 获取编号的工具类
 * @author a
 *
 */
public class CodeUtils {

	
	private static CodeUtils codeUtils;
//	
	private CodeUtils() {
	}
	
	public synchronized static CodeUtils getInstance() {
		if(codeUtils == null) {
			codeUtils = new CodeUtils();
		}
		
		return codeUtils;
	}
	
	/**
	 * 年的后两位+月+日+时+分+秒+毫秒+三位随机数
	 * @return
	 */
	private String getCode() {
//		sb.append(DateUtils.getDate("yyMMddHHmmssSSS")).append(random.nextInt(10)).append(random.nextInt(10)).append(random.nextInt(10));
		return new Date().getTime()+"";
		
	}
	
	/**
	 * 获取订单编号
	 * @return
	 */
	public String getApplyCode() {
		return "A" + getCode();
	}
	
	
	/**
	 * 获取新化置单号
	 * @return
	 */
	public String getDisposalBillCode(){
		return "D"+getCode();
	}
	
	/**
	 * 获取新换车单号
	 * @return
	 */
	public String getReplaceCarBillCode(){
		return "Z"+getCode();
	}
	
	/**
	 * 获取新转派单号
	 * @return
	 */
	public String getReplaceLineBillCode(){
		return "Y"+getCode();
	}
	
	/**
	 * 获取新入厂\站核对单编号
	 * @return
	 */
	public String getEntrBillCode(){
		return "F"+getCode();
	}
	
	/**
	 * 获取出入库单号
	 * @return
	 */
	public String getDeliveryCode(){
		return "K"+getCode();
	}
	public static void main(String[] args) {
		long time = new Date().getTime();
		System.out.println(CodeUtils.getInstance().getApplyCode());
	}
}
