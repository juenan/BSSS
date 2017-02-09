package jueban.model;

import java.io.Serializable;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6439333479001479800L;
	private String name;
	private String phonenum;
	private int pay;
	private int collect;
	private int total;
	private boolean delete;
	
	public Customer(String name,String phonenum){
		this.name = name;
		this.phonenum = phonenum;
		this.pay = 0;
		this.collect = 0;
		this.total = 0;
		this.delete = false;
	}
	
	
	
	public boolean isDelete(){
		return this.delete;
	}
	
	public String getName(){
		return this.name;
	}
	
	
	public String getPhonenum(){
		return this.phonenum;
	}
	
	
	public int getPay(){
		return this.pay;
	}
	
	
	public int getCollect(){
		return this.collect;
	}
	
	
	public int getTotal(){
		return this.total;
	}
	
	
	/**
	 * 设置客户是否被删除
	 * @param delete 是否删除
	 * @return FALSE:设置失败该客户存在未付款或未收款账目
	 */
	public boolean setDelete(boolean delete){
		if(this.pay == 0 && this.collect == 0 && delete){
			this.delete = delete;
			return true;
		}else if(!delete){
			this.delete = delete;
			return true;
		}else
			return false;
			
	}
	
	
	
	public Customer setPhonenum(String phonenum){
		this.phonenum = phonenum;
		return this;
	}
	
	/**
	 * 设置应付款金额
	 * @param pay 应付款金额
	 */
	public Customer setPay(int pay){
		this.pay = pay;
		this.total = this.collect - this.pay;
		return this;
	}
	
	
	/**
	 * 增加应付款金额
	 * @param pay 增加应付款金额
	 */
	public Customer addPay(int pay){
		this.pay += pay;
		this.total = this.collect - this.pay;
		return this;
	}
	
	/**
	 * 减少应付款金额
	 * @param pay 减少应付款金额
	 */
	public Customer subPay(int pay){
		this.pay -= pay;
		this.total = this.collect - this.pay;
		return this;
	}
	/**
	 * 设置应收款金额
	 * @param collect 应收款金额
	 */
	public Customer setCollect(int collect){
		this.collect = collect;
		this.total = this.collect - this.pay;
		return this;
	}
	
	
	/**
	 * 增加应收款金额
	 * @param collect 增加应收款金额
	 */
	public Customer addCollect(int collect){
		this.collect += collect;
		this.total = this.collect - this.pay;
		return this;
	}
	
	
	/**
	 * 减少应收款金额
	 * @param collect 减少应收款金额
	 */
	public Customer subCollect(int collect){
		this.collect -= collect;
		this.total = this.collect - this.pay;
		return this;
	}
	
	
}
