package jueban;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import jueban.model.*;
import jueban.model.Account;
import jueban.model.Commodity;
import jueban.model.CommodityModel;
import jueban.model.Customer;
import jueban.model.Export;
import jueban.model.Import;

public class Clink{
	
	private Socket s;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public Clink(ObjectInputStream in,ObjectOutputStream out) throws IOException{
		
		this.out = out;
		this.in = in;
		
	}
	
	
	
	public void upd() throws IOException{
		
		
		BSSFrame.t.interrupt();
		out.writeObject(Command.UPD);
		out.writeObject(CommodityModel.mp);
		out.writeObject(CustomerModel.mp);
		out.writeObject(ImportModel.array);
		out.writeObject(ExportModel.array);
		out.writeObject(AccountModel.array);
		out.writeObject(Account.account);
		BSSFrame.t = new Thread(BSSFrame.cupd);
		BSSFrame.t.start();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void ini() throws IOException, ClassNotFoundException{
		
		BSSFrame.t.interrupt();
		
		 
		out.writeObject(Command.INI);
		
		CommodityModel.mp = (HashMap<String, Commodity>) in.readObject();
		
		//System.out.println(in.available());
		CustomerModel.mp = (HashMap<String, Customer>) in.readObject();
		ImportModel.array = (ArrayList<Import>) in.readObject();
		ExportModel.array = (ArrayList<Export>) in.readObject();
		AccountModel.array = (ArrayList<Account>) in.readObject();
		Account.account = (int) in.readObject();
		BSSFrame.t = new Thread(BSSFrame.cupd);
		BSSFrame.t.start();
		
	}
	
	
	
	
	
	
	
	/*
	public Object get(Type t) throws IOException, ClassNotFoundException{
		BSSFrame.t.interrupt();
		out.writeObject(t);
		out.writeObject(Command.INI);
		Object obj = in.readObject();
		BSSFrame.t.start();
		return obj;
	}
	
	
	public ArrayList<?> getList(Type t) throws IOException, ClassNotFoundException{
		BSSFrame.t.interrupt();
		out.writeObject(t);
		out.writeObject(Command.INI);
		Object obj = in.readObject();
		BSSFrame.t.start();
		return (ArrayList<?>) obj;
	}
	
	
	public HashMap<?,?> getMp(Type t) throws IOException, ClassNotFoundException{
		BSSFrame.t.interrupt();
		out.writeObject(t);
		out.writeObject(Command.INI);
		Object obj = in.readObject();
		BSSFrame.t.start();
		return (HashMap<?, ?>) obj;
	}
	
	
	public void setMp(Type t,Object obj) throws IOException{
		out.writeObject(t);
		out.writeObject(Command.UPD);
		out.writeObject(obj);
	}
	
	
	public void setArray(Type t,Object obj,int n) throws IOException{
		out.writeObject(t);
		out.writeObject(Command.UPD);
		out.writeInt(n);
		out.writeObject(obj);
	}
	
	
	public void delMap(Type t,Object obj) throws IOException{
		out.writeObject(t);
		out.writeObject(Command.DEL);
		out.writeObject(obj);
	}
	
	
	public void delList(Type t,int n){
		
	}
	
	public void quit() throws IOException{
		out.writeObject(Type.COMMODITY);
		out.writeObject(Command.QUIT);
	}
	

	
	public void add(Type t,Object obj) throws IOException{
		out.writeObject(t);
		out.writeObject(Command.ADD);
		out.writeObject(obj);
		
	}



	public void upd(Type t,Object obj){
		
	}
	
	
*/
	
}
