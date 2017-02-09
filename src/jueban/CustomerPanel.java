package jueban;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jueban.model.Commodity;
import jueban.model.Customer;
import jueban.model.CustomerModel;

public class CustomerPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2501005897216951470L;
	private JTable table;
	private CustomerModel cusmodel;
	private JScrollPane spane;
	private JButton add;
	private JButton del;
	private JButton upd;
	private JPanel buttonpanel;
	private CustomerAddDialog dialog;
	private Frame frame;
	public CustomerPanel(CustomerModel cusmodel,Frame frame){
		
	
		this.frame = frame;
		this.cusmodel = cusmodel;
		add = new JButton("添加");
		del = new JButton("删除");
		upd = new JButton("修改联系方式");
		buttonpanel = new JPanel();
		
		table = new JTable(this.cusmodel);
		table.updateUI();
		buttonpanel.add(add);
		buttonpanel.add(del);
		buttonpanel.add(upd);
		
		
		add.addActionListener(new AddListener());
		del.addActionListener(new DelListener());
		
		
		table.setAutoCreateRowSorter(true);
		spane = new JScrollPane(table);
		
		
		
		
		
		setLayout(new BorderLayout());
		add(spane,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
		
	
	}
	
	public class AddListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(dialog == null)dialog = new CustomerAddDialog();
			if(dialog.showDialog(frame,"请输入商品信息:")){
				Customer customer = dialog.getCustomer();
				if(customer != null){
					try {
						try {
							if(!cusmodel.add(customer))
								JOptionPane.showMessageDialog(frame,"该商品已存在！");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					} catch (HeadlessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					
				}else
					JOptionPane.showMessageDialog(frame,"商品名称或型号不能为空！");
		}
		
	}
	}
	
	
	public class DelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int n[] = table.getSelectedRows();
			String customer[] = new String[n.length];
			
			for(int i = 0;i<n.length;i++){
				customer[i] = (String) cusmodel.getValueAt(n[i],0);
			}
			for(int i = 0;i<n.length;i++){
			try {
				try {
					if(!cusmodel.del(customer[i])){
						JOptionPane.showMessageDialog(frame,customer[i]+","+"删除失败!");
					}
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			} catch (HeadlessException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			table.updateUI();
			
			}
			
		}
		
	}

}
