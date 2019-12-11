package Presentacion.Conferencia;

import Presentacion.View.ShowAll;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import Negocio.Transfers.TConferencia;

@SuppressWarnings("serial")
public class ShowAllConferences extends ShowAll {
	private String[] _columnsId = { "ID", "Nombre", "Fecha" };
	private List<Object> _conferences;
	AbstractTableModel model;

	public ShowAllConferences(List<Object> l) {
		_conferences = l;
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
				return _conferences == null ? 0 : _conferences.size();
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Object o = null;

				switch (columnIndex) {
				case 0:
					o = ((TConferencia) _conferences.get(rowIndex)).getID();
					break;

				case 1:
					o = ((TConferencia) _conferences.get(rowIndex)).getNombre();
					break;

				case 2:
					o = ((TConferencia) _conferences.get(rowIndex)).getDate().toString();
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
		_conferences = l;
		model.fireTableDataChanged();
	}
}