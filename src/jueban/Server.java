package jueban;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import jueban.model.*;

public class Server {
	static HashMap<String,Commodity> cmp;
	static HashMap<String,Customer> cusmp;
	static ArrayList<Import> iarray;
	static ArrayList<Export> earray;
	static ArrayList<Account> aarray;
	private static FileInputStream fin;
	private static ObjectInputStream in;
	private FileOutputStream fout;
	private ObjectOutputStream out;
	static ArrayList<Runnable> larray = new ArrayList<Runnable>();
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]){
		cmp = new HashMap<String,Commodity>();
		cusmp = new HashMap<String,Customer>();
		earray = new ArrayList<Export>();
		iarray = new ArrayList<Import>();
		aarray = new ArrayList<Account>();
		try {
			fin = new FileInputStream("storehouse.txt");
			in = new ObjectInputStream(fin);
			cmp =(HashMap<String, Commodity>) in.readObject();
			Commodity.num = cmp.size();
			fin = new FileInputStream("customer.txt");
			in = new ObjectInputStream(fin);
			cusmp = (HashMap<String, Customer>) in.readObject();
			fin = new FileInputStream("export.txt");
			in = new ObjectInputStream(fin);
			earray = (ArrayList<Export>) in.readObject();
			fin = new FileInputStream("import.txt");
			in= new ObjectInputStream(fin);
			iarray = (ArrayList<Import>) in.readObject();
			fin = new FileInputStream("account.txt");
			in = new ObjectInputStream(fin);
			Account.account = in.readInt();
			aarray = (ArrayList<Account>) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		ObjectInputStream in;
		ObjectOutputStream out;
		Scanner sin;
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(4487);
			while(true){
				Socket incoming = socket.accept();
				System.out.println("Á¬½ÓÊý:"+(larray.size()+1));
				Runnable r = new Link(incoming);
				larray.add(r);
				Thread t = new Thread(r);
				t.start();
				
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void allUpd(){
		Iterator<Runnable> it =  larray.iterator();
		try{
		while(it.hasNext()){
			Link l = (Link) it.next();
			l.upd();
		}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
	
	/*
	public static void sendAllMap(Type t,Command c,Object obj){
		Iterator<Runnable> it =  larray.iterator();
		try{
		while(it.hasNext()){
			Link l = (Link) it.next();
			l.sendMap(t,c, obj);
		}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	public static void sendAllList(Type t,Command c,Object obj,int n){
		Iterator<Runnable> it =  larray.iterator();
		try{
		while(it.hasNext()){
			Link l = (Link) it.next();
			l.sendList(t,c,obj,n);
		}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	public static void updAllCommodity(){
		
	}
	
	
	
	public static void updAllCustomer(){
		
	}
	
	*/

}
