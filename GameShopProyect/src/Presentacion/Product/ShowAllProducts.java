/**
 * 
 */
package Presentacion.Product;

import Presentacion.View.ShowAll;
import Transfers.TProduct;
import Transfers.TProvider;

import java.util.List;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


@SuppressWarnings("serial")
public class ShowAllProducts extends ShowAll {
	
	private final String[] _columnsId = {"ID","TYPE","NAME","STOCK","PVP"};
	private List<Object> _products;
	private AbstractTableModel model;

	
	public ShowAllProducts(String nameIdentificator,  List<Object> l) {
		super(nameIdentificator);
		this._products = l;
		this.initComponents();
	}
	
	private void initComponents() {
		model = new AbstractTableModel() {

			@Override
			public int getColumnCount() {
				return _columnsId.length;
			}

			@Override
			public int getRowCount() {
				return _products == null ? 0 : _products.size();
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Object o = null;
				
				switch(columnIndex){
				case 0:
					o = ((TProduct)_products.get(rowIndex)).get_id();
					break;
					
				case 2:
					o = ((TProduct)_products.get(rowIndex)).get_name();
					break;
					
				case 1:
					o = ((TProduct)_products.get(rowIndex)).get_type();
					break;
					
				case 3:
					o = ((TProduct)_products.get(rowIndex)).get_stock();
					break;
				case 4:
					o = ((TProduct)_products.get(rowIndex)).get_pvp();
					break;
				}
				return o;
			}

			@Override
			public String getColumnName(int column) {
				return _columnsId[column];
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		_grid = new JTable(model);
		setColumnsWidth();
		_grid.setVisible(true);

		this.add(new JScrollPane(_grid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
	}
	
	private void setColumnsWidth() {
		_grid.getColumnModel().getColumn(0).setPreferredWidth(20);
		_grid.getColumnModel().getColumn(1).setPreferredWidth(80);
		_grid.getColumnModel().getColumn(2).setPreferredWidth(120);
		_grid.getColumnModel().getColumn(3).setPreferredWidth(50);
	}

	@Override
	public void update(List<Object> l) {
		_products = l;
		model.fireTableDataChanged();
	}
}