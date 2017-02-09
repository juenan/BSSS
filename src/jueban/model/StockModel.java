package jueban.model;

import javax.swing.table.AbstractTableModel;

public class StockModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5551891895669087761L;
	private CommodityModel cmodel;
	
	
	public StockModel(CommodityModel cmodel){
		this.cmodel = cmodel;
	}

	@Override
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		return 11;
	}

	@Override
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return cmodel.getRowCount();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Commodity commodity = (Commodity)cmodel.getValueAt(row, 7);
		Stock stock = commodity.getStock();
		switch(column){
		case 0: return commodity.getName();
		case 1: return commodity.getSize();
		case 2: return stock.getBuyNum();
		case 3: return stock.getMeanBuyPrice();
		case 4: return stock.getTotalBuyPrice();
		case 5: return stock.getSellNum();
		case 6: return stock.getMeanSellPrice();
		case 7: return stock.getTotalSellPrice();
		case 8: return stock.getStock();
		case 9: return stock.getMeanStockPrice();
		default:
		case 10: return stock.getTotal();
		}
	}
	
	public String getColumnName(int column){
		switch(column){
		case 0: return "��Ʒ����";
		case 1: return "�ͺ�";
		case 2: return "��������";
		case 3: return "����ƽ����";
		case 4: return "�����ܼ�";
		case 5: return "��������";
		case 6: return "����ƽ����";
		case 7: return "�����ܼ�";
		case 8: return "�������";
		case 9: return "���ƽ����";
		default:
		case 10: return "����ܼ�";
		}
	}

}
