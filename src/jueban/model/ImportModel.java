package jueban.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import jueban.Clink;

public class ImportModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 10187933788364801L;
	public static ArrayList<Import> array;
	private CustomerModel cusmodel;
	private CommodityModel cmodel;
	private FileInputStream fin;
	private ObjectInputStream in;
	private FileOutputStream fout;
	private ObjectOutputStream out;
	private Clink clink;
	
	
	public ImportModel(CommodityModel cmodel,CustomerModel cusmodel,Clink clink){
		this.cmodel = cmodel;
		this.cusmodel = cusmodel;
		array = new ArrayList<Import>();
		this.clink = clink;
		/*
		try {
			fin = new FileInputStream("import.txt");
			in= new ObjectInputStream(fin);
			array = (ArrayList<Import>) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			array = new ArrayList<Import>();
			e.printStackTrace();
		} catch (IOException e) {
			array = new ArrayList<Import>();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			array = new ArrayList<Import>();
			e.printStackTrace();
		}
		*/
	}

	@Override
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		return 7;
	}

	@Override
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return array.size();
	}

	@Override
	public Object getValueAt(int row, int cloumn) {
		Import im = array.get(row);
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		
		switch(cloumn){
		case 0: return im.getCustomerName();
		case 1: return im.getCommodityName();
		case 2: return im.getCommoditySize();
		case 3: return im.getNum();
		case 4: return im.getPrice();
		case 5: return im.getTotal();
		default:
		case 6: return format.format(im.getDate());
		case 7: return im;
		}
	}
	
	
	public String getColumnName(int column){
		switch(column){
		case 0: return "�ͻ�����";
		case 1: return "��Ʒ����";
		case 2: return "�ͺ�";
		case 3: return "����";
		case 4: return "����";
		case 5: return "�ܼ�";
		default:
		case 6: return "����";
		}
	}
	
	public void setChange(boolean change){
		if(array.size()>0){
			array.get(array.size()-1).setChange(change);
		}
	}
	
	
	
	/**
	 * ����һ������
	 * @param customername �����ͻ�����
	 * @param commodityname ������Ʒ����
	 * @param commoditysize ������Ʒ�ͺ�
	 * @param sellnum ��������
	 * @param sellprice �����۸�
	 * @return FALSE:�޸���Ʒ���޸ÿͻ�
	 * @throws IOException 
	 */
	public boolean add(String customername,String commodityname,String commoditysize,int sellnum,int sellprice) throws IOException{
		if(this.cmodel.fin(commodityname, commoditysize) != null &&
				this.cusmodel.fin(customername) != null){
			Import im = new Import(this.cusmodel.fin(customername),
					this.cmodel.fin(commodityname, commoditysize),sellnum,sellprice);
			if(array.size()>0){
				array.get(array.size()-1).setChange(true);
			}
			array.add(im);
			clink.upd();
			this.fireTableDataChanged();
			cmodel.fireTableDataChanged();
			cusmodel.fireTableDataChanged();
			return true;
		}else
			return false;
	}
	
	
	
	/**
	 * ɾ������
	 * @param customername Ҫɾ�������Ŀͻ�������
	 * @param commodityname Ҫɾ����������Ʒ������
	 * @param commoditysize Ҫɾ����������Ʒ���ͺ�
	 * @param sellnum Ҫɾ������������
	 * @param sellprice Ҫɾ���������ۼ�
	 * @return
	 * @throws IOException 
	 */
	public boolean del(String customername,String commodityname,String commoditysize,int sellnum,int sellprice) throws IOException{
		Customer customer = this.cusmodel.fin(customername);
		Commodity commodity = this.cmodel.fin(commodityname,commoditysize);
		for(Import im:array){
			if(im.isSame(customer, commodity, sellnum, sellprice)){
				if(im.Delete()){
					array.remove(im);
					this.fireTableDataChanged();
					cmodel.fireTableDataChanged();
					cusmodel.fireTableDataChanged();
					clink.upd();
					return true;
				}else
					return false;
			}
		}
		return false;
	}
	
	
	
	/*
	public void saveArray(){
		try {
			fout = new FileOutputStream("import.txt");
			out = new ObjectOutputStream(fout);
			out.writeObject(array);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	
	*/

}
