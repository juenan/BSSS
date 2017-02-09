package jueban.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import jueban.Clink;

public  class AccountModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1064010224212077499L;
	public static ArrayList<Account> array;
	private CustomerModel cusmodel;
	private FileInputStream fin;
	private ObjectInputStream in;
	private FileOutputStream fout;
	private ObjectOutputStream out;
	private Clink clink;
	
	public AccountModel(CustomerModel cusmodel,Clink clink){
		this.cusmodel = cusmodel;
		array = new ArrayList<Account>();
		Account.account = 0;
		this.clink = clink;
		/*
		try {
			fin = new FileInputStream("account.txt");
			in = new ObjectInputStream(fin);
			Account.account = in.readInt();
			array = (ArrayList<Account>) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			array = new ArrayList<Account>();
			Account.account = 0;
			e.printStackTrace();
		}
		
		*/
		
	}
	@Override
	public int getColumnCount() {
		
		return 4;
	}
	@Override
	public int getRowCount() {
		
		return 1+array.size();
	}
	@Override
	public Object getValueAt(int row, int column) {
		
		
		switch(row){
		case 0:
			switch(column){
			case 0: return "公司账户";
			case 1: return Account.account;
			default:
			case 2: return null;
			case 3: return new SimpleDateFormat("YYYY-MM-dd").format(new Date());
			}
			
		default:
			Account account = array.get(row-1);
			switch(column){
			case 0: return account.getCustomer();
			case 1: return account.getPrice();
			case 2: return account.getType();
			case 3: return new SimpleDateFormat("YYYY-MM-dd").format(account.getDate());
			default:
			case 4: return account;
			}
			
		
		}
		
	}
	
	
	public String getColumnName(int column){
		switch(column){
		case 0: return "名称";
		case 1: return "账款";
		case 2: return "收付款";
		case 3: return "日期";
		default:
			return null;
		}
	}
	

	

	
	
	/**
	 * 增加一个收付款单
	 * @param customer 客户
	 * @param price 价格
	 * @param type 收款或付款
	 * @return
	 * @throws IOException 
	 */
	public boolean add(Customer customer,int price,int type) throws IOException{
		if(customer != null || price > 0){
			Account account = new Account(customer,price,type);
			array.add(account);
			this.fireTableDataChanged();
			cusmodel.fireTableDataChanged();
			clink.upd();
			return true;
		}else
			return false;
		
	}
	
	
	/**
	 * 增加一个收付款单 重载
	 * @param name 客户名称
	 * @param price 价格
	 * @param type 收款或付款
	 * @return
	 * @throws IOException 
	 */
	public boolean add(String name,int price,int type) throws IOException{
		return add(cusmodel.fin(name),price,type);
	}
	
	
	/**
	 * 删除一个收付款单
	 * @param index 
	 * @return
	 * @throws IOException 
	 */
	public boolean del(int index) throws IOException{
		if((index-1) <= array.size()){
			array.get(index-1).delete();
			array.remove(index-1);
			clink.upd();
			return true;
		}else
			return false;
	}

	
	/**
	 * 
	 * @param index
	 * @return
	 * @throws IOException 
	 */
	public boolean del(int index[]) throws IOException{
		if(index != null){
			Account account[] = new Account[index.length];
			for(int i = 0;i<index.length;i++){
					account[i] = array.get(index[i]-1);
			}
			for(Account e:account){
				e.delete();
				array.remove(e);
			}
			clink.upd();
			
			this.fireTableDataChanged();
			cusmodel.fireTableDataChanged();
			return true;
		}else
			return false;
	}
	
	
	/*
	public void saveArray(){
		try {
			fout = new FileOutputStream("account.txt");
			out = new ObjectOutputStream(fout);
			out.writeInt(Account.account);
			out.writeObject(array);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	*/
	
	
}
