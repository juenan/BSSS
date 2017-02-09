package jueban.model;

import java.io.Serializable;

/**
 * ���۵���
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
	 * ������۵����Ƿ���ͬ
	 * @param customer �ͻ�
	 * @param commodity ��Ʒ
	 * @param num ��������
	 * @param price ���ۼ۸�
	 * @return
	 */
	public boolean isSame(Customer customer, Commodity commodity, int num, int price){
		return super.isSame(num, price) && this.customer == customer && this.commodity == commodity;
	}
	
	/**
	 * ɾ������
	 * @param delete �Ƿ�ɾ��
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
 