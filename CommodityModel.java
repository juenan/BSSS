package jueban.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.IIOException;
import javax.swing.table.AbstractTableModel;

import jueban.BSSFrame;
import jueban.Clink;
import jueban.Type;

public class CommodityModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2863691481410738540L;
	private final Type T = Type.COMMODITY;
	private FileInputStream fin;
	private ObjectInputStream in;
	private FileOutputStream fout;
	private ObjectOutputStream out;
	HashMap<String,Commodity> mp;
	private Clink clink;
	
	@SuppressWarnings("unchecked")
	public CommodityModel(Clink clink) throws ClassNotFoundException, IOException{
		
		mp = (HashMap<String, Commodity>) clink.getMp(T);
		Commodity.num = mp.size();
		this.clink = clink;
		
		/*
		try {
			fin = new FileInputStream("storehouse.txt");
			in = new ObjectInputStream(fin);
			mp =(HashMap<String, Commodity>) in.readObject();
			Commodity.num = mp.size();
			
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			mp = new HashMap<String,Commodity>();
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
		return mp.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Commodity commodity = null;
		int i = 0;
		TreeMap<String, Commodity> tmp = new TreeMap<String, Commodity>();
		tmp.putAll(mp);
		for(Commodity e:tmp.values()){
			if(i == row){
				commodity = e;
				break;
			}
			i++;
		}
		
		if(commodity == null){
			return null;
		}
		switch(column){
		case 0: return commodity.getName();
		case 1: return commodity.getSize();
		case 2: return commodity.getStock().getStock();
		case 3: return commodity.getBuyPrice();
		case 4: return commodity.getSellPrice();
		case 5: return commodity.getLastBuyPrice();
		default:
		case 6: return commodity.getLastSellPrice();
		case 7: return commodity;
		}
		
		
	}
	
	
	public boolean isCellEditable(int row,int column){
		if(column == 3||column == 4){
			return true;
		}
		return false;
		
	}
	
	
	public String getColumnName(int column){
		switch(column){
		case 0: return "��Ʒ����";
		case 1: return "�ͺ�";
		case 2: return "����";
		case 3: return "Ĭ�Ͻ���";
		case 4: return "Ĭ���ۼ�";
		case 5: return "���һ�ν���";
		default:
		case 6: return "���һ���ۼ�";
		}
	}
	
	
	/**
	 * ��������Ʒ
	 * @param commodity ����Ʒ
	 * @return
	 * @throws IOException 
	 */
	public boolean add(Commodity commodity) throws IOException{
		if(ADD(commodity)){
			clink.add(T,commodity);
			
			return true;
		}else
			return false;
	}
	
	
	
	
	/**
	 * ��������Ʒ ����1
	 * @param name ����Ʒ����
	 * @param size ����Ʒ�ͺ�
	 * @param buyprice ����ƷĬ�Ͻ���
	 * @param sellprice ����ƷĬ���ۼ�
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public boolean add(String name,String size,int buyprice,int sellprice) throws IOException{
			Commodity commodity = new Commodity(name,size,buyprice,sellprice);
			
			return this.add(commodity);
	}
	
	
	
	
	public boolean ADD(Commodity commodity){
		String name = commodity.getName()+commodity.getSize();
		if(mp.get(name)==null){
			mp.put(name, commodity);
			this.fireTableDataChanged();
			return true;
		}else
			return false;
	}
	
	
	

	/**
	 * ɾ����Ʒ
	 * @param name ɾ����Ʒ������
	 * @param size ɾ����Ʒ���ͺ�
	 * @return FALSE:�����ڸ���Ʒ���߸���Ʒ�Ѿ����۹�
	 * @throws IOException 
	 */
	public boolean del(String name,String size) throws IOException{
		if(DEL(name,size)){
			clink.delMap(T, mp.get(name+size));
			return true;
		}else
			return false;
		
	}
	
	
	
	/**
	 * ɾ����Ʒ ����1
	 * @param commodity ɾ������Ʒ
	 * @return FALSE:�����ڸ���Ʒ���߸���Ʒ�Ѿ����۹�
	 * @throws IOException 
	 */
	public boolean del(Commodity commodity) throws IOException{
		clink.delMap(T, commodity);
		return del(commodity.getName(),commodity.getSize());
		
	}
	
	
	
	public boolean DEL(String name,String size){
		if(mp.get(name+size) != null && !mp.get(name+size).isSold()){
			mp.remove(name+size);
			this.fireTableDataChanged();
			return true;
		}else
			return false;
	}


	
	
	/**
	 * ������Ʒ��Ϣ
	 * @param name ������Ʒ������
	 * @param size ������Ʒ���ͺ�
	 * @param buyprice Ĭ�Ͻ���
	 * @param sellprice Ĭ���ۼ�
	 * @return FALSE:�б����޸���Ʒ
	 * @throws IOException 
	 */
	public boolean upd(String name,String size,int buyprice,int sellprice) throws IOException{
		if(UPD(name,size,buyprice,sellprice)){
			clink.upd(T, mp.get(name+size));
			return true;
		}else
			return false;
	}
	
	
	
	/**
	 * ������Ʒ��Ϣ ����1
	 * @param commodity ���ĵ���Ʒ
	 * @param buyprice Ĭ�Ͻ���
	 * @param sellprice Ĭ���ۼ�
	 * @return FALSE:�б����޸���Ʒ
	 * @throws IOException 
	 */
	public boolean upd(Commodity commodity,int buyprice,int sellprice) throws IOException{
		return upd(commodity.getName(),commodity.getSize(),buyprice,sellprice);
	}
	
	
	public boolean UPD(String name,String size,int buyprice,int sellprice){
		if(mp.get(name+size) != null){
			Commodity commodity = mp.get(name+size);
			commodity.setBuyPrice(buyprice);
			commodity.setSellPrice(sellprice);
			mp.put(name+size, commodity);
			this.fireTableDataChanged();
			return true;
		}else
			return false;
	}
	
	
	/**
	 * ������Ʒ
	 * @param name Ҫ������Ʒ������
	 * @param size Ҫ������Ʒ���ͺ�
	 * @return
	 */
	public Commodity fin(String name,String size){
		return mp.get(name+size);
	}
	
	

	
	public void iniMp(HashMap<String,Commodity> mp){
		this.mp = mp;
	}
	
	
	
	
	
	
	
	
	/*
	public void saveMap(){
		
			try {
				fout = new FileOutputStream("storehouse.txt");
				out = new ObjectOutputStream(fout);
				out.writeObject(mp);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	
	}
	*/
	
	

}
