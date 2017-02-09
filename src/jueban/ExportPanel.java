package jueban;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jueban.ImportPanel.AddListener;
import jueban.ImportPanel.DelListener;
import jueban.model.CommodityModel;
import jueban.model.CustomerModel;
import jueban.model.Export;
import jueban.model.ExportModel;
import jueban.model.Import;
import jueban.model.ImportModel;

public class ExportPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5824296956905852813L;
	private JTable table;
	private JButton im;
	private JButton reim;
	private ExportModel emodel;
	private ImportModel imodel;
	private CommodityModel cmodel;
	private CustomerModel cusmodel;
	private DialogPanel dialog;
	private OrderAddPanel panel;
	private Frame frame;
	public ExportPanel(ExportModel emodel,ImportModel imodel,CommodityModel cmodel,CustomerModel cusmodel,Frame frame){
		this.frame = frame;
		this.emodel = emodel;
		this.imodel = imodel;
		this.cmodel = cmodel;
		this.cusmodel = cusmodel;
		table = new JTable(emodel);
		panel = new OrderAddPanel(cmodel,cusmodel);
		dialog = new DialogPanel(panel);
		JPanel buttonpanel = new JPanel();
		
		
		JScrollPane spane = new JScrollPane(table);
		im = new JButton("销售");
		im.addActionListener(new AddListener());
		reim = new JButton("退货");
		reim.addActionListener(new DelListener());
		buttonpanel.add(im);
		buttonpanel.add(reim);
		setLayout(new BorderLayout());
		
		
		add(spane,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
	}
	
	public class AddListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel.ctable.updateUI();
			panel.custable.updateUI();
			if(dialog.showDialog(frame,"销售:")){
				try {
					emodel.add(panel.getCustomer(), panel.getCommodity(), 
							panel.getsize(), panel.getNum(), panel.getPrice());
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public class DelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int n[] = table.getSelectedRows();
			for(int i:n){
				if(i == table.getRowCount()-1){
					Export ex = (Export) emodel.getValueAt(i, 7);
					try {
						emodel.del(ex.getCustomerName(),ex.getCommodityName(),ex.getCommoditySize(),ex.getNum(), ex.getPrice());
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}else
					JOptionPane.showMessageDialog(frame,"第"+(i+1)+"个单子"+"删除失败");
			}
			
		}
	}
}
