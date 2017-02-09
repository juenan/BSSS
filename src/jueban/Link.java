package jueban;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import jueban.model.Account;
import jueban.model.AccountModel;
import jueban.model.Commodity;
import jueban.model.CommodityModel;
import jueban.model.Customer;
import jueban.model.CustomerModel;
import jueban.model.Export;
import jueban.model.ExportModel;
import jueban.model.Import;
import jueban.model.ImportModel;

public class Link implements Runnable{
	Socket incoming;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	
	
	
	public Link(Socket incoming){
		this.incoming = incoming;
		
		
	}

	
	@SuppressWarnings("unchecked")
	public void run(){
		try {
			in = new ObjectInputStream(incoming.getInputStream());
			out = new ObjectOutputStream(incoming.getOutputStream());
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		boolean sign = true;
		while(sign){
			try {

				Command c = (Command) in.readObject();
				switch(c){
				case INI:
					out.writeObject(Server.cmp);
					out.writeObject(Server.cusmp);
					out.writeObject(Server.iarray);
					out.writeObject(Server.earray);
					out.writeObject(Server.aarray);
					out.writeObject(Account.account);
					break;
				case UPD:
					Server.cmp = (HashMap<String, Commodity>) in.readObject();
					Server.cusmp = (HashMap<String, Customer>) in.readObject();
					Server.iarray = (ArrayList<Import>) in.readObject();
					Server.earray = (ArrayList<Export>) in.readObject();
					Server.aarray = (ArrayList<Account>) in.readObject();
					Account.account = (int) in.readObject();
					Server.allUpd();
					break;
				default:
					sign = false; break;
				}
				
				
			} catch (IOException | ClassNotFoundException e) {
				
				try {
					incoming.close();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
				e.printStackTrace();
			} 
			
			if(incoming.isClosed()){
				sign = false;
				
			}
		
		
		}
		
		Server.larray.remove(this);
		try {
			incoming.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
	}
		
		
	public void upd() throws IOException{
		out.writeObject(Command.UPD);
		out.writeObject(Server.cmp);
		out.writeObject(Server.cusmp);
		out.writeObject(Server.iarray);
		out.writeObject(Server.earray);
		out.writeObject(Server.aarray);
		out.writeObject(Account.account);
	}
	
	
	
	
	
		
	
		//难QAQ
		/*
		try{
			
		
		in = new ObjectInputStream(incoming.getInputStream());
		out = new ObjectOutputStream(incoming.getOutputStream());
		
		boolean sign = true;
		//sin = new Scanner(incoming.getInputStream());
		while(sign){
			
			Type t = (Type) in.readObject();
			
			Object o = new Object();
			switch(t){
			case COMMODITY: o = cmp; break;
			case CUSTOMER: o = cusmp; break;
			case IMPORT: o = iarray; break;
			case EXPORT: o = earray; break;
			case ACCOUNT: o = aarray; break;
			case ACCOUNTINI: o = Account.account;
			}
			Command c = (Command) in.readObject();
			switch(c){
			
			

			
			case INI: out.writeObject(o); 
			System.out.println("数据发送成功");break;
			
			
			
			case ADD:
				switch(t){
				case COMMODITY: 
					Commodity commodity = (Commodity) in.readObject();
					cmp.put(commodity.getName()+commodity.getSize(), commodity);
					Server.sendAllMap(t,c,commodity);
					break;
				case CUSTOMER:
					Customer customer = (Customer) in.readObject();
					cusmp.put(customer.getName(),customer);
					Server.sendAllMap(t,c,customer);
					break;
				case IMPORT:
					Import im = (Import) in.readObject();
					iarray.add(im);
					Server.sendAllMap(t,c,im);
					break;
				case EXPORT:
					Export ex = (Export) in.readObject();
					earray.add(ex);
					Server.sendAllMap(t,c,ex);
					break;
				case ACCOUNT:
					Account account = (Account) in.readObject();
					aarray.add(account);
					Server.sendAllMap(t,c,account);
					break;
				}
				break;
				
				
				
			case DEL:
				Object obj = in.readObject();
				switch(t){
				case COMMODITY:
					 ((HashMap<String, Commodity>) o).remove(((Commodity) o).getName()+((Commodity) o).getSize());
					 Server.sendAllMap(t, c, obj);
					 break;
				case CUSTOMER:
					((HashMap<String, Customer>) o).remove(((Customer) o).getName());
					Server.sendAllMap(t, c, obj);
					break;
				default:
				case IMPORT:
				case EXPORT:
				case ACCOUNT:
					int n = in.readInt();
					((ArrayList<?>)o).remove(n);
					Server.sendAllList(t, c, obj, n);
					break;
				
				}
				
				
				
			case UPD:
				
				switch(t){
				
				case COMMODITY:					
					Commodity commodity = (Commodity) in.readObject();
					cmp.put(commodity.getName()+commodity.getSize(),commodity);
					Server.sendAllMap(t,c,commodity);
					break;
				case CUSTOMER:
					Customer customer = (Customer) in.readObject();
					cusmp.put(customer.getName(), customer);
					Server.sendAllMap(t,c,customer);
					break;
				default:
					break;
					
				}
				break;
			
			default:
			case QUIT:incoming.close(); sign = false; break;
			
			
			}
			
				
		}
		Server.larray.remove(this);
		
			
		}catch(IOException | ClassNotFoundException e){
			Server.larray.remove(this);
			try {
				incoming.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void sendMap(Type t,Command c,Object obj) throws IOException{
		out = new ObjectOutputStream(incoming.getOutputStream());
		out.writeObject(t);
		out.writeObject(c);
		out.writeObject(obj);
	}
	
	public void sendList(Type t,Command c,Object obj,int n) throws IOException{
		out = new ObjectOutputStream(incoming.getOutputStream());
		out.writeObject(t);
		out.writeObject(c);
		out.writeInt(n);
		out.writeObject(obj);
	}
	
	
	
	
	public void updCommodity(){
		
	}
	
	
	public void updCustomer(){
		
	}
	
	*/

}
