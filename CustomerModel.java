package jueban.model;

import java.io.*;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import jueban.Clink;
import jueban.Type;

public class CustomerModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7633445452428333270L;
	HashMap<String, Customer> mp= new HashMap<String,Customer>();
	private final Type T = Type.CUSTOMER;
	private FileInputStream fin;
	private FileOutputStream fout;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Clink clink;
	@SuppressWarnings("unchecked")
	public CustomerModel(Clink clink) throws ClassNotFoundException, IOException{
		this.clink = clink;
		mp = (HashMap<String, Customer>) clink.getMp(T);
		
		/*try {
			fin = new FileInputStream("customer.txt");
			in = new ObjectInputStream(fin);
			mp = (HashMap<String, Customer>) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			mp = new HashMap<String,Customer>();
			e.printStackTrace();
		}
		*/
		
	}
	
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	
	
	@Override
	public int getRowCount() {
		int i = 0;
		for(Customer e:mp.values()){
			if(e.isDelete()){
				i++;
			}
		}
		return mp.size()-i;
	}

	
	
	@Override
	public Object getValueAt(int row, int column) {
		Customer customer = null;
		int i = 0;
		for(Customer e:mp.values()){
			if(e.isDelete()){
				i--;
			}
			if(i == row){
				customer = e;
				break;
			}
			i++;
		}
		if(customer == null){
			return null;
		}
		switch(column){
		case 0: return customer.getName();
		case 1: return customer.getPhonenum();
		case 2: return customer.getCollect();
		case 3: return customer.getPay();
		default:
		case 4: return customer.getTotal();
		case 5: return customer;
		}
	}
	
	
	
	public String getColumnName(int column){
		switch(column){
		case 0: return "客户名称";
		case 1: return "联系方式";
		case 2: return "应收款金额";
		case 3: return "应付款金额";
		default:
		case 4: return "合计";
		}
	}
	
	
	
	
	/**
	 * 增加一个新客户 并储存
	 * @param customer 新客户
	 * @return FALSE:已存在该客户
	 * @throws IOException 
	 */
	public boolean add(Customer customer) throws IOException{
		Boolean sign = ADD(customer);
		if(sign){
			clink.add(T, customer);
			return true;
		}else if(sign == null){
			clink.setMp(T, mp.get(customer.getName()));
			return true;
		}else{
			return false;
		}
			
	}
	
	
	
	/**
	 * 增加一个新客户
	 * @param name 新客户名字
	 * @param phonenum 新客户电话
	 * @return FALSE:已存在该客户
	 * @throws IOException 
	 */
	public boolean add(String name,String phonenum) throws IOException{
		Customer customer = new Customer(name,phonenum);
		return add(customer);
	}
	
	
	/**
	 * 增加一个新客户
	 * @param customer
	 * @return null为已删除客户恢复
	 */
	public Boolean ADD(Customer customer){
		if(mp.get(customer.getName()) == null){
			mp.put(customer.getName(), customer);
			this.fireTableDataChanged();
			return true;
		}else if(mp.get(customer.getName()).isDelete()){
			mp.get(customer.getName()).setDelete(false);
			mp.get(customer.getName()).setPhonenum(customer.getPhonenum());
			this.fireTableDataChanged();
			return  null;
		}
			return false;
	}
	
	
	
	
	/**
	 * 删除一个客户
	 * @param name  客户名字 
	 * @return FALSE:不存在该客户或该客户存在未付款或未收款账目
	 * @throws IOException 
	 */
	public boolean del(String name) throws IOException{
		if(DEL(name)){
			clink.setMp(T, mp.get(name));
			return true;
		}else
			return false;
	}
	
	
	
	
	public boolean DEL(String name){
		if(mp.get(name) != null){
			boolean temp = mp.get(name).setDelete(true);
			this.fireTableDataChanged();
			return temp;
		}else
			return false;
	}
	
	/**
	 * 更新一个客户信息
	 * @param name 客户名字
	 * @param phonenum 客户新电话
	 * @return FALSE:不存在该用户
	 */
	public boolean upd(String name,String phonenum){
		if(mp.get(name) != null){
			mp.get(name).setPhonenum(phonenum);
			return true;
		}else
			return false;
		
	}
	
	
	/**
	 * 查找一个客户
	 * @param name 客户名字
	 * @return 查找的客户 NULL为不存在该客户
	 */
	public Customer fin(String name){
			return mp.get(name);
	}
	
	
	
	
	
	
	
	
	/*
	public void SaveMap(){
		try {
			fout = new FileOutputStream("customer.txt");
			out = new ObjectOutputStream(fout);
			out.writeObject(mp);
			out.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	*/

}
