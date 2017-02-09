package jueban.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import jueban.Clink;
import jueban.Type;

public class ExportModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3668922207921270742L;
	private final Type T = Type.EXPORT;
	private ArrayList<Export> array;
	private CommodityModel cmodel;
	private CustomerModel cusmodel;
	private ImportModel imodel;
	private FileInputStream fin;
	private ObjectInputStream in;
	private FileOutputStream fout;
	private ObjectOutputStream out;
	private Clink clink;
	
	
	@SuppressWarnings("unchecked")
	public ExportModel(CommodityModel cmodel,CustomerModel cusmodel,ImportModel imodel,Clink clink) throws ClassNotFoundException, IOException{
		this.clink = clink;
		this.cmodel = cmodel;
		this.cusmodel = cusmodel;
		this.imodel = imodel;
		
		array = (ArrayList<Export>) clink.getList(T);
		
		
		
		
		/*
		try {
			fin = new FileInputStream("export.txt");
			in = new ObjectInputStream(fin);
			array = (ArrayList<Export>) in.readObject();
			in.close();
		} catch (IOException e) {
			array = new ArrayList<Export>();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			array = new ArrayList<Export>();
			e.printStackTrace();
		}
		*/
		
		
		
	}
	
	
	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return array.size();
	}



	@Override
	public Object getValueAt(int row, int column) {
		Export ex = array.get(row);
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		
		switch(column){
		case 0: return ex.getCustomerName();
		case 1: return ex.getCommodityName();
		case 2: return ex.getCommoditySize();
		case 3: return ex.getNum();
		case 4: return ex.getPrice();
		case 5: return ex.getTotal();
		default:
		case 6: return format.format(ex.getDate());
		case 7: return ex;
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
	public boolean add(String customername,String commodityname,String commoditysize,int buynum,int buyprice) throws IOException{
		if(this.cmodel.fin(commodityname, commoditysize) != null &&
				this.cusmodel.fin(customername) != null){
			Export ex = new Export(this.cusmodel.fin(customername),
					this.cmodel.fin(commodityname, commoditysize),buynum,buyprice);
			imodel.setChange(true);
			if(array.size()>0){
				array.get(array.size()-1).setChange(true);
			}
			array.add(ex);
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
	 */
	public boolean del(String customername,String commodityname,String commoditysize,int buynum,int buyprice){
		Customer customer = this.cusmodel.fin(customername);
		Commodity commodity = this.cmodel.fin(commodityname,commoditysize);
		for(Export ex:array){
			if(ex.isSame(customer, commodity, buynum, buyprice)){
				if(ex.Delete()){
					array.remove(ex);
					this.fireTableDataChanged();
					cmodel.fireTableDataChanged();
					cusmodel.fireTableDataChanged();
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
			fout = new FileOutputStream("export.txt");
			out = new ObjectOutputStream(fout);
			out.writeObject(array);
			out.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	*/

	

}
