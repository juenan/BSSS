package jueban.model;
import java.io.Serializable;
import java.util.Date;
/**
 * 单子
 * @author jueban
 *
 */
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6838547814093464679L;
	private int num;
	private int price;
	private int total;
	private Date date;
	private boolean change;
	private boolean delete;
	
	
	public Order(int num,int price){

		this.num = num;
		this.price = price;
		this.total = num*price;
		this.date = new Date();
		this.change = false;
		this.delete = false;
	}
	
	
	
	
	/**
	 * 判断是否为这个订单
	 * @param customer 客户
	 * @param commodity 商品
	 * @param sellnum 出售数量
	 * @param sellprice 出售价格
	 * @return
	 */
	public boolean isSame(int num,int price){
		if(this.num == num && this.price == price){
			return true;
		}else
			return false;
	}
	
	
	
	public boolean isChange(){
		return this.change;
	}
	
	
	public boolean isdelete(){
		return this.delete;
	}
	
	
	public int getNum(){
		return this.num;
	}
	
	
	public int getPrice(){
		return this.price;
	}
	
	
	public Date getDate(){
		return this.date;
	}
	
	
	public int getTotal(){
		return this.total;
	}
	

	
	/**
	 * 设置订单是否被删除
	 * @param delete 是否删除
	 * @return FALSE:订单已改动无法删除或订单没有删除无法恢复
	 */
	public boolean setDelete(boolean delete){
		if(!this.change && delete){
			this.delete = delete;
			return true;
		}else
			return false;

			
	}
	
	


	
	public void setChange(boolean change){
		this.change = change;
	}
	
	
	
}
