package jueban;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import jueban.model.*;

public class Cupd implements Runnable{
	private Socket s;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	private CommodityModel cmodel;
	private CustomerModel cusmodel;
	private ImportModel imodel;
	private ExportModel emodel;
	private StockModel smodel;
	private AccountModel amodel;
	
	private InputStream iin;

	public Cupd(ObjectInputStream in,ObjectOutputStream out,InputStream iin,CommodityModel cmodel,CustomerModel cusmodel,ImportModel imodel,ExportModel emodel,StockModel smodel,AccountModel amodel) throws IOException{
	
		this.out = out;
		this.in = in;
		this.iin = iin;
		this.cmodel = cmodel;
		this.cusmodel = cusmodel;
		this.imodel = imodel;
		this.emodel = emodel;
		this.smodel = smodel;
		this.amodel = amodel;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void run(){
		HashMap<?,?> cmp = null;
		HashMap<?,?> cusmp = null;
		ArrayList<?> iarray = null;
		ArrayList<?> earray = null;
		ArrayList<?> aarray = null;
		Command c = null;
		
		
		while(!Thread.currentThread().isInterrupted()){
			
				
			
			try {
				
				//Thread.currentThread().sleep(25);
				if(iin.available() != 0){
					
					
					c = (Command) in.readObject();
					if(c == Command.UPD){
					cmp = (HashMap<?, ?>) in.readObject();
					cusmp = (HashMap<?, ?>) in.readObject();
					iarray = (ArrayList<?>) in.readObject();
					earray = (ArrayList<?>) in.readObject();
					aarray = (ArrayList<?>) in.readObject();
					Account.account = (int) in.readObject();
					cmodel.fireTableDataChanged();
					cusmodel.fireTableDataChanged();
					imodel.fireTableDataChanged();
					emodel.fireTableDataChanged();
					smodel.fireTableDataChanged();
					amodel.fireTableDataChanged();
					
					}
					
				}
			
				
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} finally{
				
				if(c != null){
				try {
					CommodityModel.mp = (HashMap<String, Commodity>) (cmp != null?cmp:in.readObject());
					CustomerModel.mp = cusmp != null?(HashMap<String, Customer>) cusmp:(HashMap<String, Customer>) in.readObject();
					ImportModel.array = iarray != null?(ArrayList<Import>) iarray:(ArrayList<Import>) in.readObject();
					ExportModel.array = earray != null?(ArrayList<Export>) earray:(ArrayList<Export>) in.readObject();
					AccountModel.array = aarray != null?(ArrayList<Account>) aarray:(ArrayList<Account>) in.readObject();
					
				} catch (ClassNotFoundException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				cmp = null;
				cusmp = null;
				iarray = null;
				earray = null;
				aarray = null;
				c = null;
				
				}
				
				
				
			}
			
			
		}
		
		
	}

}
