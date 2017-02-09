package jueban.model;

import java.io.Serializable;

public class Commodity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7041487095314059907L;
	private String name; //商品名称
	private String size; //商品型号
	private int buyprice; //默认进价
	private int sellprice; //默认售价
	private int lastbuyprice; //最后一次购价
	private int lastsellprice; //最后一次售价
	private Stock stock; //库存
	private boolean sold; //是否被销售
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
	 * 设置默认进价
	 * @param price 默认进价
	 */
	public Commodity setBuyPrice(int price){
		this.buyprice = price;
		return this;
	}
	
	
	/**
	 * 设置默认售价
	 * @param price 默认售价 
	 */
	public Commodity setSellPrice(int price){
		this.sellprice = price;
		return this;
	}
	
	
	/**
	 * 设置默认进售价
	 * @param bprice 默认进价
	 * @param sprice 默认售价
	 */
	public Commodity setBuySellPrice(int bprice,int sprice){
		this.buyprice = bprice;
		this.sellprice = sprice;
		return this;
	}
	
	
	
	/**
	 * 设置是否已经被销售过
	 * @param sold 是否被销售过
	 */
	public Commodity setSold(boolean sold){
		this.sold = sold;
		return this;
	}
	

}
