package jueban;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import jueban.model.CustomerModel;
import jueban.model.ModelFormat;

public class AccountAddPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6549930262796533103L;
	private JTable table;
	private JTextField name;
	private JTextField price;
	private CustomerModel cusmodel;
	private JPanel panel;
	
	public AccountAddPanel(CustomerModel cusmodel){
		this.cusmodel = cusmodel;
		
		table = new JTable(new ModelFormat(cusmodel,1));
		JScrollPane spane = new JScrollPane(table);
		panel = new JPanel();
		name = new JTextField();
		price = new JTextField();
		panel.setLayout(new GridLayout(1,4));
		panel.add(new JLabel("客户名称:"));
		panel.add(name);
		panel.add(new JLabel("金额:"));
		panel.add(price);
		
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				name.setText((String) cusmodel.getValueAt(table.getSelectedRow(),0));
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
		});
		
		setLayout(new BorderLayout());
		add(spane,BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);
	}
	
	
	
	public String getName(){
		String name = this.name.getText();
		this.name.setText("");
		return name;
	}
	
	
	public int getPrice(){
		try{
			int price = Integer.parseInt(this.price.getText());
			this.price.setText("");
			return price;
		}catch(NumberFormatException e){
			this.price.setText("");
			return 0;
		}
		
		
	}
	
	
	
	

}
