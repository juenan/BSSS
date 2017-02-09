package jueban.model;
import java.io.Serializable;
import java.util.Date;
/**
 * ����
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
	 * �ж��Ƿ�Ϊ�������
	 * @param customer �ͻ�
	 * @param commodity ��Ʒ
	 * @param sellnum ��������
	 * @param sellprice ���ۼ۸�
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
	 * ���ö����Ƿ�ɾ��
	 * @param delete �Ƿ�ɾ��
	 * @return FALSE:�����ѸĶ��޷�ɾ���򶩵�û��ɾ���޷��ָ�
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
