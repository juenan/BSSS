package jueban.model;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6814868746076304472L;
	public static final int IN = 1;
	public static final int OUT = 2;
	private int price;
	private Customer customer;
	private int type;
	private Date date;
	public static int account;
	public Account(Customer customer,int price,int type){
		this.customer = customer;
		this.price = price;
		this.type = type;
		date = new Date();
		if(type == IN){
			customer.subCollect(price);
			account += price;
		}else if(type == OUT){
			customer.subPay(price);
			account -= price;
		}
		
	}
	
	public int getPrice(){
		return price;
	}
	
	
	public String getType(){
		if(type == IN){
			return "收款";
		}else if(type == OUT){
			return "付款";
		}else{
			return null;
		}
	}
	
	
	public Date getDate(){
		return date;
	}
	
	
	public String getCustomer(){
		return customer.getName();
	}
	
	
	public void delete(){
		if(type == IN){
			customer.addCollect(price);
			account -= price;
		}else if(type == OUT){
			customer.addPay(price);
			account += price;
		}
		
	}
	
	/**
	 * 初始化账目
	 * @param ini
	 */
	public static void setInitial(int ini){
		account = ini;
	}

}
