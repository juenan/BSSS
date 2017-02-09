package jueban.model;

import java.io.*;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import jueban.Clink;

@SuppressWarnings("serial")
public class CustomerModel extends AbstractTableModel{
	public static HashMap<String, Customer> mp;
	private FileInputStream fin;
	private FileOutputStream fout;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Clink clink;
	
	public CustomerModel(Clink clink){
		mp = new HashMap<String,Customer>();
		this.clink = clink;
		
		/*
		try {
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
		case 0: return "�ͻ�����";
		case 1: return "��ϵ��ʽ";
		case 2: return "Ӧ�տ���";
		case 3: return "Ӧ������";
		default:
		case 4: return "�ϼ�";
		}
	}
	
	
	
	
	/**
	 * ����һ���¿ͻ�
	 * @param customer �¿ͻ�
	 * @return FALSE:�Ѵ��ڸÿͻ�
	 * @throws IOException 
	 */
	public boolean add(Customer customer) throws IOException{
		if(mp.get(customer.getName()) == null){
			mp.put(customer.getName(), customer);
			this.fireTableDataChanged();
			clink.upd();
			return true;
		}else if(mp.get(customer.getName()).isDelete()){
			mp.get(customer.getName()).setDelete(false);
			mp.get(customer.getName()).setPhonenum(customer.getPhonenum());
			this.fireTableDataChanged();
			clink.upd();
			return true;
		}
			return false;
	}
	
	
	
	/**
	 * ����һ���¿ͻ�
	 * @param name �¿ͻ�����
	 * @param phonenum �¿ͻ��绰
	 * @return FALSE:�Ѵ��ڸÿͻ�
	 * @throws IOException 
	 */
	public boolean add(String name,String phonenum) throws IOException{
		Customer customer = new Customer(name,phonenum);
		return add(customer);
	}
	
	
	/**
	 * ɾ��һ���ͻ�
	 * @param customer  �ͻ����� 
	 * @return FALSE:�����ڸÿͻ���ÿͻ�����δ�����δ�տ���Ŀ
	 * @throws IOException 
	 */
	public boolean del(String customer) throws IOException{
		if(mp.get(customer) != null){
			boolean temp = mp.get(customer).setDelete(true);
			this.fireTableDataChanged();
			clink.upd();
			return temp;
		}else
			return false;
	}
	
	
	/**
	 * ����һ���ͻ���Ϣ
	 * @param name �ͻ�����
	 * @param phonenum �ͻ��µ绰
	 * @return FALSE:�����ڸ��û�
	 * @throws IOException 
	 */
	public boolean upd(String name,String phonenum) throws IOException{
		if(mp.get(name) != null){
			mp.get(name).setPhonenum(phonenum);
			this.fireTableDataChanged();
			clink.upd();
			return true;
		}else
			return false;
		
	}
	
	
	/**
	 * ����һ���ͻ�
	 * @param name �ͻ�����
	 * @return ���ҵĿͻ� NULLΪ�����ڸÿͻ�
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	*/

}
