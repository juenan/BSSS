package jueban;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.*;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.*;

import jueban.model.AccountModel;
import jueban.model.Commodity;
import jueban.model.CommodityModel;
import jueban.model.Customer;
import jueban.model.CustomerModel;
import jueban.model.ExportModel;
import jueban.model.ImportModel;
import jueban.model.StockModel;
public class BSSFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1067657052951605939L;
	private final int DEFAULT_HEIGHT = 1000;
	private final int DEFAULT_WIDTH = 600;
	
	
	private CommodityModel cmodel;
	private StockModel smodel;
	private CustomerModel cusmodel;
	private ImportModel imodel;
	private ExportModel emodel;
	private AccountModel amodel;

	private JMenuBar menubar;
	private CommodityPanel commoditytab;
	private JTabbedPane tabbedpane;
	private StockPanel stocktab;
	private ImportPanel importab;
	private ExportPanel exportab;
	private CustomerPanel customertab;
	private AccountPanel accounttab;
	private DialogPanel dialog;
	private LoginPanel lpanel;
	
	
	private Clink clink;
	private Socket socket;
	private ObjectOutputStream out;
	private InputStream iin;
	private ObjectInputStream in;
	
	
	public static Cupd cupd;
	public static Thread t;
	
	public BSSFrame(){
		this.setLocation(450, 250);
		lpanel = new LoginPanel();
		dialog = new DialogPanel(lpanel);
		menubar = new JMenuBar();
		JMenu news = new JMenu("新建");
		JMenuItem connect = new JMenuItem("连接");
		connect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
				
			}
			
		});
		JMenu help = new JMenu("帮助");
		JMenuItem about = new JMenuItem("关于");
		about.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(BSSFrame.this,"version: 0.0.1 (BATE)\n author: jueban");
				
			}
			
		});
		help.add(about);
		news.add(connect);
		menubar.add(news);
		menubar.add(help);
		this.setJMenuBar(menubar);
		
		
		
		cmodel = new CommodityModel(clink);
		cusmodel = new CustomerModel(clink);
		imodel = new ImportModel(cmodel,cusmodel,clink);
		emodel = new ExportModel(cmodel, cusmodel, imodel,clink);
		smodel = new StockModel(cmodel);
		amodel = new AccountModel(cusmodel,clink);
		

		
		commoditytab = new CommodityPanel(cmodel,BSSFrame.this);
		stocktab = new StockPanel(smodel,cmodel);
		importab = new ImportPanel(imodel,cmodel,cusmodel,BSSFrame.this);
		exportab = new ExportPanel(emodel, imodel, cmodel, cusmodel, BSSFrame.this);
		customertab = new CustomerPanel(cusmodel,BSSFrame.this);
		accounttab = new AccountPanel(amodel,cusmodel,BSSFrame.this);
		tabbedpane = new JTabbedPane();
		setSize(DEFAULT_HEIGHT,DEFAULT_WIDTH);
		setVisible(true);
		
		
		tabbedpane.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				stocktab.tableupdate();
				
			}
			
		});
		
		connect();
		
		
	
		
		add(tabbedpane,BorderLayout.CENTER);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	public void connect(){
		dialog.showDialog(this,"登陆");

		try {
			socket = new Socket(lpanel.getIp(),4487);
			out = new ObjectOutputStream(socket.getOutputStream());
			iin = socket.getInputStream();
			in = new ObjectInputStream(iin);
			clink = new Clink(in,out);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this,"连接失败请重新连接");
			e.printStackTrace();
		}
		
		try {
			cupd = new Cupd(in,out,iin,cmodel,cusmodel,imodel,emodel,smodel,amodel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		t = new Thread(cupd);
		
		try {
			
			clink.ini();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String s = lpanel.getIdentity();
		
		if(s.equals("库存管理员")){
			tabbedpane.add("商品管理",commoditytab);
			tabbedpane.add("库存管理", stocktab);
		}else if(s.equals("销售人员")){
			tabbedpane.add("进货管理", importab);
			tabbedpane.add("销售管理", exportab);
			tabbedpane.add("客户管理", customertab);
		}else if(s.equals("财务人员")){
			tabbedpane.add("账目管理", accounttab);
		}
		
		
	}
}
