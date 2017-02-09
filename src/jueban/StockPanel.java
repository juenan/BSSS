package jueban;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jueban.model.CommodityModel;
import jueban.model.StockModel;
public class StockPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7392452707360062856L;
	private JTable table;
	private CommodityModel cmodel;
	private StockModel smodel;
	public StockPanel(StockModel smodel,CommodityModel cmodel){
		this.smodel = smodel;
		table = new JTable();
		table.setModel(this.smodel);
		table.updateUI();
		
		
		
		
		
		table.setAutoCreateRowSorter(true);
		JScrollPane spane = new JScrollPane(table);
		setLayout(new BorderLayout());
		add(spane,BorderLayout.CENTER);
		
	}
	
	public void tableupdate(){
		table.updateUI();
	}


}
