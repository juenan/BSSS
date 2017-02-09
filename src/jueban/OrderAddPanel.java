package jueban;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import jueban.model.CommodityModel;
import jueban.model.ImportModel;
import jueban.model.ModelFormat;
import jueban.*;
public class OrderAddPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4033366676163583151L;
	public JTable ctable;
	public JTable custable;
	private JTextField num;
	private JTextField price;
	private JTextField customer;
	private JTextField commodity;
	private JTextField size;
	
	public OrderAddPanel(AbstractTableModel cmodel,AbstractTableModel cusmodel){
		
		ctable = new JTable(new ModelFormat(cmodel,3));
		custable = new JTable(new ModelFormat(cusmodel,1));	
		num = new JTextField();
		price = new JTextField();
		customer = new JTextField();
		commodity = new JTextField();
		size = new JTextField();
		JPanel panel = new JPanel();

		JScrollPane cusspane = new JScrollPane(custable);
		JScrollPane cspane = new JScrollPane(ctable);
		JPanel tablepanel = new JPanel();
		tablepanel.setLayout(new GridLayout(1,4));
		tablepanel.add(cusspane);
		tablepanel.add(cspane);

		panel.setLayout(new GridLayout(3,4));
		
		ctable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				commodity.setText((String) cmodel.getValueAt(ctable.getSelectedRow(), 0));
				size.setText((String) cmodel.getValueAt(ctable.getSelectedRow(), 1));
			}
		});
		
		custable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				customer.setText((String) cusmodel.getValueAt(custable.getSelectedRow(),0));
			}
		});
		
		panel.add(new JLabel("数量:"));
		panel.add(num);
		panel.add(new JLabel("价格:"));
		panel.add(price);
		panel.add(new JLabel("商品名称:"));
		panel.add(commodity);
		panel.add(new JLabel("商品型号:"));
		panel.add(size);
		panel.add(new JLabel("客户名称"));
		panel.add(customer);
		setLayout(new BorderLayout());
		
		
		
		
		add(tablepanel,BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);
	}
	
	
	public int getNum(){
		int n = Integer.parseInt(num.getText());
		num.setText("");
		return n;
	}
	
	
	public int getPrice(){
		int n = Integer.parseInt(price.getText());
		price.setText("");
		return n;
	}
	
	
	public String getCommodity(){
		String name = commodity.getText();
		commodity.setText("");
		ctable.clearSelection();
		return name;
	}
	
	
	public String getsize(){
		String size = this.size.getText();
		this.size.setText("");
		return size;
	}
	
	
	public String getCustomer(){
		String name = customer.getText();
		customer.setText("");
		custable.clearSelection();
		return name;
	}
	
	

}
