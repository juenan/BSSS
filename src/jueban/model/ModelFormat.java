package jueban.model;

import javax.swing.table.AbstractTableModel;

public class ModelFormat extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1502852772270852611L;
	private AbstractTableModel model;
	private int n;
	public ModelFormat(AbstractTableModel model,int n){
		this.model = model;
		this.n = n;
	}

	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return n;
	}

	@Override
	public int getRowCount() {
		
		return model.getRowCount();
	}

	@Override
	public Object getValueAt(int row, int column) {
		return model.getValueAt(row, column);
	}
	
	public String getColumnName(int column){
		return model.getColumnName(column);
	}

}
