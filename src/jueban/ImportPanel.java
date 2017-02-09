package jueban;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jueban.model.CommodityModel;
import jueban.model.CustomerModel;
import jueban.model.Import;
import jueban.model.ImportModel;

public class ImportPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5601618708954500631L;
	private JTable table;
	private JButton im;
	private JButton reim;
	private ImportModel imodel;
	private CommodityModel cmodel;
	private CustomerModel cusmodel;
	private DialogPanel dialog;
	private OrderAddPanel importpanel;
	private Frame frame;
	public ImportPanel(ImportModel imodel,CommodityModel cmodel,CustomerModel cusmodel,Frame frame){
		this.frame = frame;
		this.imodel = imodel;
		this.cmodel = cmodel;
		this.cusmodel = cusmodel;
		table = new JTable(imodel);
		importpanel = new OrderAddPanel(cmodel,cusmodel);
		dialog = new DialogPanel(importpanel);
		JPanel buttonpanel = new JPanel();
		
		
		JScrollPane spane = new JScrollPane(table);
		im = new JButton("进货");
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
			importpanel.ctable.updateUI();
			importpanel.custable.updateUI();
			if(dialog.showDialog(frame,"进货:")){
				try {
					imodel.add(importpanel.getCustomer(), importpanel.getCommodity(), 
							importpanel.getsize(), importpanel.getNum(), importpanel.getPrice());
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
					Import im = (Import) imodel.getValueAt(i, 7);
					try {
						if(!imodel.del(im.getCustomerName(),im.getCommodityName(),im.getCommoditySize(), im.getNum(), im.getPrice())){
							JOptionPane.showMessageDialog(frame,"第"+(i+1)+"个单子"+"删除失败");
						}
					} catch (HeadlessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
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
