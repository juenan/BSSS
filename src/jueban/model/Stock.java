package jueban.model;

import java.io.Serializable;

public class Stock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8329789032789143169L;
	private int buynum = 0; //��������
	private double meanbuyprice = 0;  //����ƽ����
	private int totalbuyprice = 0;  //�����ܼ�
	private int sellnum = 0;  //��������
	private double meansellprice = 0; //����ƽ����
	private int totalsellprice = 0; //�����ܼ�
	private int stock = 0;  //�������
	private double meanstockprice = 0;  //���ƽ����
	private double total = 0;  //����ܼ�
	
	
	public int getStock(){
		return this.stock;
	}
	
	
	public int getBuyNum(){
		return this.buynum;
	}
	
	
	public double getMeanBuyPrice(){
		return this.meanbuyprice;
	}
	
	
	public int getTotalBuyPrice(){
		return this.totalbuyprice;
	}
	
	
	public int getSellNum(){
		return this.sellnum;
	}
	
	
	public double getMeanSellPrice(){
		return this.meansellprice;
	}
	
	
	public int getTotalSellPrice(){
		return this.totalsellprice;
	}
	
	
	public double getMeanStockPrice(){
		return this.meansellprice;
	}
	
	
	public void disbuy(int num,int price){
		
		this.buynum -=num;
		this.totalbuyprice -= num*price;
		if(this.buynum == 0){
			this.meanbuyprice = 0;
		}else
			this.meanbuyprice = this.totalbuyprice / this.buynum;
		this.stock -= num;
		this.total -= num*price;
		if(this.stock == 0){
			this.meanstockprice = 0;
		}else
			this.meanstockprice = this.total / this.stock;
	}
	
	
	
	/**
	 * ����
	 * @param num ��������
	 * @param price �����۸�
	 */
	public void buy(int num,int price){
		this.buynum += num;
		this.totalbuyprice += num*price;
		this.meanbuyprice = this.totalbuyprice / this.buynum;
		this.stock += num;
		this.total += num*price;
		if(this.stock == 0){
			this.meanstockprice = 0;
		}else
			this.meanstockprice = this.total / this.stock;
		
	}
	
	
	
	public double getTotal(){
		return this.total;
	}
	
	
	public void disell(int num,int price){
		
		this.sellnum += num;
		this.totalsellprice += num*price;
		if(this.sellnum == 0){
			this.meansellprice = 0;
		}else
			this.meansellprice = this.totalbuyprice / this.buynum;
		this.stock += num;
		this.total += num*this.meanbuyprice;
		if(this.stock == 0){
			this.meanstockprice = 0;
		}else
			this.meanstockprice = this.total / this.stock;
	}
	
	
	
	/**
	 * �˻�
	 * @param num �˻�����
	 * @param price �˻��۸� 
	 */
	public void sell(int num,int price){
		this.sellnum -= num;
		this.totalsellprice -= num*price;
		this.meansellprice = this.totalsellprice / this.sellnum;
		this.stock -= num;
		this.total -= num*this.meanbuyprice;
		if(this.stock == 0){
			this.meanstockprice = 0;
		}else
			this.meanstockprice = this.total / this.stock;
	}
	
	
	public String toString(){
		return buynum+";"+meanbuyprice+";"+totalbuyprice+";"+
	sellnum+";"+meansellprice+";"+totalsellprice+";"+stock+";"+
	meanstockprice+";"+total;
	}

}
