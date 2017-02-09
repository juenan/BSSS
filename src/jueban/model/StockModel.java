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
		// TODO 自动生成的方法存根
		return 11;
	}

	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
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
		case 0: return "商品名称";
		case 1: return "型号";
		case 2: return "进货数量";
		case 3: return "进货平均数";
		case 4: return "进货总价";
		case 5: return "销售数量";
		case 6: return "销售平均价";
		case 7: return "销售总价";
		case 8: return "库存数量";
		case 9: return "库存平均价";
		default:
		case 10: return "库存总价";
		}
	}

}
