package jueban.model;

import java.io.Serializable;

/**
 * 销售单子
 * @author jueban
 *
 */
public class Export extends Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -682488231289062867L;
	private Customer customer;
	private Commodity commodity;
	private boolean cisold;

	public Export(Customer customer,Commodity commodity,int num, int price) {
		super(num, price);
		this.customer = customer;
		this.commodity = commodity;
		this.customer.addCollect(num*price);
		this.commodity.getStock().sell(num, price);
		cisold = this.commodity.isSold();
		this.commodity.setSold(true);
	}
	
	
	public String getCustomerName(){
		return this.customer.getName();
	}
	
	
	public String getCommodityName(){
		return this.commodity.getName();
	}
	
	
	public String getCommoditySize(){
		return this.commodity.getSize();
	}
	
	
	/**
	 * 检测销售单子是否相同
	 * @param customer 客户
	 * @param commodity 商品
	 * @param num 销售数量
	 * @param price 销售价格
	 * @return
	 */
	public boolean isSame(Customer customer, Commodity commodity, int num, int price){
		return super.isSame(num, price) && this.customer == customer && this.commodity == commodity;
	}
	
	/**
	 * 删除订单
	 * @param delete 是否删除
	 */
	public boolean Delete(){
		if(super.setDelete(true)){
			this.customer.subCollect(this.getNum()*this.getPrice());
			this.commodity.getStock().disell(this.getNum(),this.getPrice());
			this.commodity.setSold(cisold);
			return true;
		}else
			return false;
	}
	
	
	
}
 