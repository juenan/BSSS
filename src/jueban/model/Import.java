package jueban.model;

import java.io.Serializable;

public class Import extends Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7991149248739319416L;
	private Customer customer;
	private Commodity commodity;
	private boolean cisold;

	
	public Import(Customer customer, Commodity commodity, int num, int price) {
		super(num, price);
		this.customer = customer;
		this.commodity = commodity;
		this.customer.addPay(num*price);
		this.commodity.getStock().buy(num, price);
		cisold = this.commodity.isSold();
		this.commodity.setSold(true);
		//this.commodity.addStock(num);
		
	}
	
	
	
	public void setCustomer(Customer cus){
		customer = cus;
	}
	
	
	
	public void setCommodity(Commodity commodity){
		this.commodity = commodity;
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
	 * @param num 购买数量
	 * @param price 购买价格
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
		if(super.isChange()){
			return false;
		}else{
			this.customer.subPay(this.getNum()*this.getPrice());
			this.commodity.getStock().disbuy(this.getNum(),this.getPrice());
			this.commodity.setSold(cisold);
			return true;
		}

	}
	
	
	public void setChange(boolean change){
		super.setChange(change);
		if(change){
			commodity.setSold(true);
		}
	}
	

}
