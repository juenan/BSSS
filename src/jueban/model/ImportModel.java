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
		// TODO 自动生成的方法存根
		return 7;
	}

	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
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
		case 0: return "客户名称";
		case 1: return "商品名称";
		case 2: return "型号";
		case 3: return "数量";
		case 4: return "单价";
		case 5: return "总价";
		default:
		case 6: return "日期";
		}
	}
	
	public void setChange(boolean change){
		if(array.size()>0){
			array.get(array.size()-1).setChange(change);
		}
	}
	
	
	
	/**
	 * 增加一个订单
	 * @param customername 订单客户名字
	 * @param commodityname 订单商品名字
	 * @param commoditysize 订单商品型号
	 * @param sellnum 订单数量
	 * @param sellprice 订单价格
	 * @return FALSE:无该商品或无该客户
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
	 * 删除订单
	 * @param customername 要删除订单的客户的名字
	 * @param commodityname 要删除订单的商品的数量
	 * @param commoditysize 要删除订单的商品的型号
	 * @param sellnum 要删除订单的数量
	 * @param sellprice 要删除订单的售价
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	*/

}
