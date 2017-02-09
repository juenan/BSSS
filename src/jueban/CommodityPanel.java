package jueban;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jueban.model.Commodity;
import jueban.model.CommodityModel;
import javax.swing.*;
public class CommodityPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3426181855761563393L;
	private CommodityAddDialog commoditydialog;
	private CommodityModel cmodel;
	private JTable table;
	private JPanel buttonpanel;
	private JButton add;
	private JButton del;
	private Frame frame;
	public CommodityPanel(CommodityModel cmodel,Frame frame){
		this.frame = frame;
		commoditydialog = new CommodityAddDialog();
		this.cmodel = cmodel;
		table = new JTable(cmodel);
		buttonpanel = new JPanel();
		add = new JButton("增加");
		del = new JButton("删除");
		add.addActionListener(new AddAction());
		del.addActionListener(new DelAction());
		
		
		
		
		buttonpanel.add(add);
		buttonpanel.add(del);
		table.setAutoCreateRowSorter(true);
		JScrollPane spane = new JScrollPane(table);
		setLayout(new BorderLayout());
		add(spane,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
	}
	
	
	
	
	public class AddAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			if(commoditydialog == null)commoditydialog = new CommodityAddDialog();
			if(commoditydialog.showDialog(frame,"请输入商品信息:")){
				Commodity commodity = commoditydialog.getCommodity();
				if(commodity != null){
					try {
						if(!cmodel.add(commodity))
							JOptionPane.showMessageDialog(frame,"该商品已存在！");
					} catch (HeadlessException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
				}else
					JOptionPane.showMessageDialog(frame,"商品名称或型号不能为空！");
				
			}
			
		}
		
	}
	
	
	public class DelAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int n[] = table.getSelectedRows();
			Commodity commodity[] = new Commodity[n.length];
			for(int i = 0;i<n.length;i++){
				commodity[i] = (Commodity)cmodel.getValueAt(n[i],7);
			}
			for(int i = 0;i<n.length;i++){
			try {
				try {
					if(!cmodel.del(commodity[i])){
						JOptionPane.showMessageDialog(frame,commodity[i].getName()+","+commodity[i].getSize()+"删除失败!");
					}
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			} catch (HeadlessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			
			}
			
		}
		
	}


}
