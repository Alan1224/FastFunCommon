package com.klw.fastfun.pay.common.domain;

import java.io.Serializable;

/**
 * 
 * @author ouyangsu
 * 20150330
 * 
 */
public class CodeInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** sp编号 */
	private String spId;
	/** sp名字 */
	private String name;
	/** sp标示 */
	private String spFlag;
	/** 计费点价格；多个计费点用符号"#"区分 */
	private String fee;
	/** 在线地址 */
	private String url;
	/** url编号 */
	private int urlNo;
	/** 优先级：值越大权重越高 */
	private int priority;
	/** 是否开放 */
	private int isOpen;
	/** 1 根据屏蔽省份判断   2根据开通身份判断 */
	private int provinceType;
	/** 屏蔽省份 */
	private String provinceHide;
	/** 开通省份 */
	private String provinceOpen;
	/** 备注 */
	private String memo;
	/** 屏蔽关键字 */
	private String keyword;
	/** 匹配正则表达式 */
	private String matchRegex;
	/** 延迟毫秒 */
	private String delayed;
	/** 1:get 2:post */
	private int reqMethod;
	/** 计费次数 */
	private int payNum;
	/** 优先级增加数 */
	private int increasePriority;
	/** 订单号 */
	private String ffId;

	public int getIncreasePriority() {
		return increasePriority;
	}
	public void setIncreasePriority(int increasePriority) {
		this.increasePriority = increasePriority;
	}
	public String getFfId() {
		return ffId;
	}
	public void setFfId(String ffId) {
		this.ffId = ffId;
	}
	public int getPayNum() {
		return payNum;
	}
	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}
	public int getReqMethod() {
		return reqMethod;
	}
	public void setReqMethod(int reqMethod) {
		this.reqMethod = reqMethod;
	}
	public String getDelayed() {
		return delayed;
	}
	public void setDelayed(String delayed) {
		this.delayed = delayed;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getMatchRegex() {
		return matchRegex;
	}
	public void setMatchRegex(String matchRegex) {
		this.matchRegex = matchRegex;
	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpFlag() {
		return spFlag;
	}
	public void setSpFlag(String spFlag) {
		this.spFlag = spFlag;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUrlNo() {
		return urlNo;
	}
	public void setUrlNo(int urlNo) {
		this.urlNo = urlNo;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	public int getProvinceType() {
		return provinceType;
	}
	public void setProvinceType(int provinceType) {
		this.provinceType = provinceType;
	}
	public String getProvinceHide() {
		return provinceHide;
	}
	public void setProvinceHide(String provinceHide) {
		this.provinceHide = provinceHide;
	}
	public String getProvinceOpen() {
		return provinceOpen;
	}
	public void setProvinceOpen(String provinceOpen) {
		this.provinceOpen = provinceOpen;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
