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
		case 0: return "商品名称";
		case 1: return "型号";
		case 2: return "数量";
		case 3: return "默认进价";
		case 4: return "默认售价";
		case 5: return "最后一次进价";
		default:
		case 6: return "最后一次售价";
		}
	}
	
	
	/**
	 * 增加新商品
	 * @param commodity 新商品
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
	 * 增加新商品 重载1
	 * @param name 新商品名字
	 * @param size 新商品型号
	 * @param buyprice 新商品默认进价
	 * @param sellprice 新商品默认售价
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
	 * 删除商品
	 * @param name 删除商品的名字
	 * @param size 删除商品的型号
	 * @return FALSE:不存在该商品或者该商品已经销售过
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
	 * 删除商品 重载1
	 * @param commodity 删除的商品
	 * @return FALSE:不存在该商品或者该商品已经销售过
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
	 * 更新商品信息
	 * @param name 更改商品的名字
	 * @param size 更改商品的型号
	 * @param buyprice 默认进价
	 * @param sellprice 默认售价
	 * @return FALSE:列表中无该商品
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
	 * 更新商品信息 重载1
	 * @param commodity 更改的商品
	 * @param buyprice 默认进价
	 * @param sellprice 默认售价
	 * @return FALSE:列表中无该商品
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
	 * 查找商品
	 * @param name 要查找商品的名字
	 * @param size 要查找商品的型号
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
