package Presentacion.Empleado;

import Presentacion.View.ShowAll;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Negocio.Transfers.TEmpleado;

@SuppressWarnings("serial")
public class ShowAllEmployees extends ShowAll {

	private String[] _columnsId = { "ID", "NIF", "Salary" };
	AbstractTableModel model;
	private List<Object> _employees;

	public ShowAllEmployees(List<Object> l) {
		_employees = l;
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
				return _employees == null ? 0 : _employees.size();
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Object o = null;

				switch (columnIndex) {
				case 0:
					o = ((TEmpleado) _employees.get(rowIndex)).getID();
					break;

				case 1:
					o = ((TEmpleado) _employees.get(rowIndex)).getNIF();
					break;

				case 2:
					o = ((TEmpleado) _employees.get(rowIndex)).getSueldobase();
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

		this.add(new JScrollPane(_grid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

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