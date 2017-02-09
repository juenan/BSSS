package jueban;


import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import jueban.model.*;

public class AccountPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8897882175210653893L;
	private JTable table;
	private JButton addpay;
	private JButton addcollect;
	private JButton del;
	private JButton ini;
	private JPanel buttonpanel;
	private AccountModel amodel;
	private CustomerModel cusmodel;
	private DialogPanel dialog;
	private AccountAddPanel apanel;
	private Frame frame;
	
	public AccountPanel(AccountModel amodel,CustomerModel cusmodel,Frame frame){
		this.amodel = amodel;
		this.cusmodel = cusmodel;
		table = new JTable(amodel);
		JScrollPane spane = new JScrollPane(table);
		addpay = new JButton("增加付款单");
		addcollect = new JButton("增加收款单");
		del = new JButton("删除订单");
		ini = new JButton("初始化账目");
		buttonpanel = new JPanel();
		buttonpanel.add(addpay);
		buttonpanel.add(addcollect);
		buttonpanel.add(del);
		buttonpanel.add(ini);
		this.frame = frame;
		apanel = new AccountAddPanel(cusmodel);
		dialog = new DialogPanel(apanel);
		addpay.addActionListener(new AddAction());
		addcollect.addActionListener(new AddAction());
		del.addActionListener(new DelAction());
		ini.addActionListener(new IniAction());
		
		setLayout(new BorderLayout());
		add(spane,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
	}
	
	
	public class AddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("增加付款单")){
				dialog.showDialog(frame,"付款:");
				try {
					if(!amodel.add(cusmodel.fin(apanel.getName()),apanel.getPrice(),Account.OUT)){
						JOptionPane.showMessageDialog(frame,"添加失败");
					}
				} catch (HeadlessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}else if(e.getActionCommand().equals("增加收款单")){
				dialog.showDialog(frame,"收款:");
				try {
					if(!amodel.add(cusmodel.fin(apanel.getName()),apanel.getPrice(),Account.IN)){
						JOptionPane.showMessageDialog(frame,"添加失败");
					}
				} catch (HeadlessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	
	public class DelAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRowCount()>1){
				try {
					if(!amodel.del(table.getSelectedRows())){
						JOptionPane.showMessageDialog(frame,"删除失败");
					}
				} catch (HeadlessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			} else
				try {
					if(!amodel.del(table.getRowCount())){
						JOptionPane.showMessageDialog(frame,"删除失败");
					}
				} catch (HeadlessException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			
		}
		
	}
	
	public class IniAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panel = new JPanel();
			JTextField price = new JTextField();
			panel.setLayout(new GridLayout(1,2));
			panel.add(new JLabel("金额:"));
			panel.add(price);
			DialogPanel dialog = new DialogPanel(panel);
			dialog.showDialog(frame,"初始化公司账户");
			
			Account.setInitial(Integer.parseInt(price.getText()));
			
			amodel.fireTableDataChanged();
			
			
			
		}
		
	}
	
	

}
