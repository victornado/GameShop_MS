package Presentacion.Realiza;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import Negocio.Transfers.TRealiza;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormRealiza extends JDialog {
	private final JLabel elegirEmpleado = new JLabel("Empleados:");
	private final JLabel elegirConferencia = new JLabel("Conferencias:");
	protected final JLabel elegirDuracion = new JLabel("Duracion:");
	protected JComboBox<Object> empleados = new JComboBox<Object>();
	protected JComboBox<Object> conferencias = new JComboBox<Object>();
	
	protected JTextField duracion = new JTextField();
	
	protected JButton ok;
	private JButton cancel;
	
	// Tabla para empleados
	private String[]_columnIds = {"IDEmp", "IDConf", "Duracion"};
	protected JButton add;
	protected JButton quitar;
	protected AbstractTableModel model;
	protected JTable grid;
	protected JScrollPane jsp;
	
	// Lista que contiene los empleados que queremos asociar a esa conferencia
	protected List<TRealiza> empleadosEnConferencia = new ArrayList<TRealiza>();
	
	public FormRealiza() {
		this.setTitle("Asisgnar empleados a conferencias");
		this.setIconImage(new ImageIcon("resources/GameShopLogo.png").getImage());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				closeDialog();
			}
		});
		
		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(320, 290));
		this.setLocationRelativeTo(null);
		
		initComponents();
		
		okButtonAction();
		cancelButtonAction();
		
		addButtonAction();
		removeButtonAction();
		
		//rellenarListaEmpleadosAsociados();
	}

	protected void removeButtonAction() {
		quitar.addActionListener(new ActionListener() {
			@SuppressWarnings("unlikely-arg-type")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(grid.getSelectedRow() != -1) {
					Integer empleadoAeliminar = grid.getSelectedColumn();
					empleadosEnConferencia.remove(empleadoAeliminar);
					model.fireTableDataChanged();
				}
			}
		});
	}

	private void addButtonAction() {
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer idEmp = Integer.parseInt((((String)empleados.getSelectedItem()).split(" - "))[0]);
				Integer idConf = Integer.parseInt((((String)conferencias.getSelectedItem()).split(" - "))[0]);
				Integer d = Integer.parseInt(duracion.getText());
				TRealiza in = new TRealiza(idEmp, idConf, d);
				empleadosEnConferencia.add(in);
				model.fireTableDataChanged();
			}
		});
	}

	protected void okButtonAction() {
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(empleadosEnConferencia.size() > 0) {
					closeDialog();
					
					Controller.getInstance().action(empleadosEnConferencia, Event.REALIZA_ASIGNAR);
				}
			}
		});
	}
	
	/*private void addInfoToComboBox() {
		conferencias.removeAllItems();
		empleados.removeAllItems();
		
		Controller.getInstance().action(null, Event.UPDATE_LIST_CONFERENCE);
		if(_electionForm != null) {
			for(Object tc : this._electionForm)
				conferencias.addItem(((TConferencia)tc).getID() + " - " + ((TConferencia)tc).getNombre());
		}
		
		Controller.getInstance().action(null, Event.UPDATE_LIST_EMPLOYEE);
		if(_electionForm != null) {
			for(Object tc : this._electionForm)
				empleados.addItem(((TEmpleado)tc).getID() + " - " + ((TEmpleado)tc).getNombre());
		}
	}*/

	private void initComponents() {
		elegirEmpleado.setAlignmentX(Component.CENTER_ALIGNMENT);
		elegirConferencia.setAlignmentX(Component.CENTER_ALIGNMENT);
		elegirDuracion.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		sizeComponent(empleados, new Dimension(150, 20));
		sizeComponent(conferencias, new Dimension(150, 20));
		
		add = new JButton("Anadir empleado");
		sizeComponent(add, new Dimension(80, 20));
		quitar = new JButton("Quitar empleado");
		sizeComponent(quitar, new Dimension(80, 20));
		
		initTable();
		
		ok = new JButton("Accept");
		sizeComponent(ok, new Dimension(75, 20));
		cancel = new JButton("Cancel");
		sizeComponent(cancel, new Dimension(75, 20));
		sizeComponent(duracion, new Dimension(75, 20));
		
		//this.add(Box.createRigidArea(new Dimension(20, 1)));
		addComponentToDialog(elegirConferencia);
		addComponentToDialog(conferencias);
		this.add(Box.createRigidArea(new Dimension(30, 1)));
		addComponentToDialog(elegirEmpleado);
		addComponentToDialog(empleados);
		this.add(Box.createRigidArea(new Dimension(50, 1)));
		addComponentToDialog(elegirDuracion);
		addComponentToDialog(duracion);
		addComponentToDialog(add);
		addComponentToDialog(quitar);
		addComponentToDialog(jsp);
		addComponentToDialog(ok);
		addComponentToDialog(cancel);
		this.setVisible(true);
	}
	
	private void initTable() {
		model = new AbstractTableModel() {
			@Override
			public int getColumnCount() {
				return _columnIds.length;
			}
			@Override
			public int getRowCount() {
				return empleadosEnConferencia.size() == 0 ? 0 : empleadosEnConferencia.size();
			}
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Object o = null;
				
				switch(columnIndex){
				case 0:
					o = ((TRealiza)empleadosEnConferencia.get(rowIndex)).getIdEmp();
					break;
				case 1:
					o = ((TRealiza)empleadosEnConferencia.get(rowIndex)).getIdConf();
					break;
				case 2:
					Integer.parseInt(duracion.getText());
					break;
				}
				return o;
			}
			@Override
			public String getColumnName(int column) {
				return _columnIds[column];
			}
			@Override
			public boolean isCellEditable(int row, int col) {
				grid.setEditingColumn(2);
				return true;
			}
		};
		
		grid = new JTable(model);
		jsp = new JScrollPane(grid, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sizeComponent(grid, new Dimension(300, 150));
		sizeComponent(jsp, new Dimension(300, 150));
		setColumnsWidth();
		grid.setVisible(true);
	}
	
	protected void addComponentToDialog(JComponent c) {
		this.add(c);
	}
	
	protected void sizeComponent(JComponent c, Dimension d) {
		c.setPreferredSize(d);
		c.setMinimumSize(d);
		c.setMaximumSize(d);
		c.setAlignmentX(Component.CENTER_ALIGNMENT);
		c.setVisible(true);
	}
	
	private void setColumnsWidth() {
		columnWidth(0, 40);
		columnWidth(1, 140);
		columnWidth(2, 50);
	}
	
	private void columnWidth(int column, int width) {
		grid.getColumnModel().getColumn(column).setPreferredWidth(width);
		grid.getColumnModel().getColumn(column).setMinWidth(width);
		grid.getColumnModel().getColumn(column).setMaxWidth(width);
	}

	private void cancelButtonAction() {
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
	}
	
	protected void closeDialog() {
		setVisible(false);
		dispose();
	}


}
