package jueban.model;

import java.io.Serializable;

public class Commodity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7041487095314059907L;
	private String name; //��Ʒ����
	private String size; //��Ʒ�ͺ�
	private int buyprice; //Ĭ�Ͻ���
	private int sellprice; //Ĭ���ۼ�
	private int lastbuyprice; //���һ�ι���
	private int lastsellprice; //���һ���ۼ�
	private Stock stock; //���
	private boolean sold; //�Ƿ�����
	private int id; //ID
	public static int num = 0 ;
	
	public Commodity(String name,String size,int buyprice,int sellprice){
		this.name = name;
		this.size = size;
		this.buyprice = buyprice;
		this.sellprice = sellprice;
		this.stock = new Stock();
		this.lastbuyprice = 0;
		this.lastsellprice = 0;
		this.sold = false;
		this.id = num;
		num++;
	}
	

	public int getid(){
		return this.id;
	}
	
	
	public boolean isSold(){
		return sold;
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public String getSize(){
		return size;
	}
	
	
	public Stock getStock(){
		return stock;
	}
	
	
	public int getBuyPrice(){
		return buyprice;
	}
	
	
	public int getSellPrice(){
		return sellprice;
	}
	
	
	public int getLastBuyPrice(){
		return lastbuyprice;
	}
	
	
	public int getLastSellPrice(){
		return lastsellprice;
	}
	
	
	public Commodity setLastBuyPrice(int price){
		this.lastbuyprice = price;
		return this;
	}
	
	
	public Commodity setLastSellPrice(int price){
		this.lastsellprice = price;
		return this;
	}
	
	
	public String toString(){
		return new String(name+";"+size+";"+stock.getStock()+";"+buyprice+";"+sellprice+";"+lastbuyprice+";"+lastsellprice+";");
	}
	
	
	/**
	 * ����Ĭ�Ͻ���
	 * @param price Ĭ�Ͻ���
	 */
	public Commodity setBuyPrice(int price){
		this.buyprice = price;
		return this;
	}
	
	
	/**
	 * ����Ĭ���ۼ�
	 * @param price Ĭ���ۼ� 
	 */
	public Commodity setSellPrice(int price){
		this.sellprice = price;
		return this;
	}
	
	
	/**
	 * ����Ĭ�Ͻ��ۼ�
	 * @param bprice Ĭ�Ͻ���
	 * @param sprice Ĭ���ۼ�
	 */
	public Commodity setBuySellPrice(int bprice,int sprice){
		this.buyprice = bprice;
		this.sellprice = sprice;
		return this;
	}
	
	
	
	/**
	 * �����Ƿ��Ѿ������۹�
	 * @param sold �Ƿ����۹�
	 */
	public Commodity setSold(boolean sold){
		this.sold = sold;
		return this;
	}
	

}
