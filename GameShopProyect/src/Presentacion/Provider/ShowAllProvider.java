package Presentacion.Provider;

import java.util.List;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Presentacion.View.ShowAll;
import Transfers.TProvider;

/**
 * @author GameShop
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
@SuppressWarnings("serial")
public class ShowAllProvider extends ShowAll {

	private String[] _columnsId = { "ID", "Direction", "NIF", "Phone number" };
	AbstractTableModel model;
	private List<Object> _providers;

	public ShowAllProvider(String nameIdentificator, List<Object> l) {
		super(nameIdentificator);
		_providers = l;
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
				return _providers == null ? 0 : _providers.size();
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Object o = null;
				
				switch(columnIndex){
				case 0:
					o = ((TProvider)_providers.get(rowIndex)).get_id();
					break;
					
				case 1:
					o = ((TProvider)_providers.get(rowIndex)).get_address();
					break;
					
				case 2:
					o = ((TProvider)_providers.get(rowIndex)).get_nif();
					break;
					
				case 3:
					o = ((TProvider)_providers.get(rowIndex)).get_phoneNumber();
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
		_grid.getColumnModel().getColumn(1).setPreferredWidth(150);
		_grid.getColumnModel().getColumn(2).setPreferredWidth(50);
	}

	@Override
	public void update(List<Object> l) {
		_providers = l;
		model.fireTableDataChanged();
	}

}