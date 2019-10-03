/**
 * 
 */
package Presentacion.Employee;

import Presentacion.View.ShowAll;
import Transfers.TEmployee;
import java.util.List;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ShowAllEmployees extends ShowAll {

	private String[] _columnsId = { "ID", "Name", "NIF", "Round" };
	AbstractTableModel model;
	private List<Object> _employees;

	public ShowAllEmployees(String nameIdentificator, List<Object> l) {
		super(nameIdentificator);
		_employees = l;
		initComponents();
	}

	private void initComponents() {
		model = new AbstractTableModel() {

			@Override
			public int getColumnCount() {
				return _columnsId.length;
			}

			@Override
			public int getRowCount() {
				return _employees == null ? 0 : _employees.size();
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Object o = null;
				
				switch(columnIndex){
				case 0:
					o = ((TEmployee)_employees.get(rowIndex)).get_id();
					break;
					
				case 1:
					o = ((TEmployee)_employees.get(rowIndex)).get_name();
					break;
					
				case 2:
					o = ((TEmployee)_employees.get(rowIndex)).get_nif();
					break;
					
				case 3:
					o = ((TEmployee)_employees.get(rowIndex)).getTurn();
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

		this.add(new JScrollPane(_grid,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
	}
	
	private void setColumnsWidth() {
		_grid.getColumnModel().getColumn(0).setPreferredWidth(20);
		_grid.getColumnModel().getColumn(1).setPreferredWidth(150);
		_grid.getColumnModel().getColumn(2).setPreferredWidth(50);
	}
	
	@Override
	public void update(List<Object> l) {
		_employees = l;
		model.fireTableDataChanged();
	}

}