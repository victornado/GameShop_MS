package Presentacion.Departamento;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TConferencia;
import Negocio.Transfers.TDepartamento;
import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TProduct;
import Negocio.Transfers.TProvider;
import Negocio.Transfers.TTicket;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class CalcularNominasPanel extends JPanel {
	private JLabel label;
	private JComboBox<Object> departamentos;
	private JButton mostrar;
	private JTextArea info;
	
	public CalcularNominasPanel() {
		this.setPreferredSize(new Dimension(400, 300));
		this.setMinimumSize(new Dimension(400, 300));
		this.setMaximumSize(new Dimension(400, 300));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		initComponents();
		
		this.setVisible(true);
	}

	private void addMostrarButtonAction() {
		mostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(departamentos.getItemCount() > 0) {
					Integer idDep = Integer.parseInt(((String)departamentos.getSelectedItem()).split(" - ")[0]);
					Controller.getInstance().action(idDep, Event.CALCULAR_NOMINA_DEPARTAMENTO);
				}
			}
		});
	}

	private void initComponents() {
		label = new JLabel("Selecciona un departamento");
		this.add(label);
		
		departamentos = new JComboBox<Object>();
		departamentos.setPreferredSize(new Dimension(200, 20));
		departamentos.setMinimumSize(new Dimension(200, 20));
		departamentos.setMaximumSize(new Dimension(200, 20));
		departamentos.setEditable(false);
		departamentos.setVisible(true);
		this.add(departamentos);
		
		mostrar = new JButton("Mostrar");
		mostrar.setPreferredSize(new Dimension(75, 20));
		mostrar.setMinimumSize(new Dimension(75, 20));
		mostrar.setMaximumSize(new Dimension(75, 20));
		mostrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		mostrar.setVisible(true);
		this.add(mostrar);
		
		info = new JTextArea();
		info.setWrapStyleWord(true);
		info.setLineWrap(true);
		info.setPreferredSize(new Dimension(300, 50));
		info.setMinimumSize(new Dimension(300, 50));
		info.setMaximumSize(new Dimension(300, 50));
		info.setEditable(false);
		info.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Nomina total",
				TitledBorder.LEFT, TitledBorder.TOP));
		info.setFont(new Font("Arial", 0, 11));
		info.setVisible(true);
		this.add(info);
		
		addMostrarButtonAction();
	}
	
	public void fillList(List<Object> l) {
		departamentos.removeAllItems();
		if(l != null) {
			for(Object o : l)
				departamentos.addItem(((TDepartamento)o).getID() + " - " + ((TDepartamento)o).getNombre());
		}
	}
	
	public void update(List<Object> l) {
		fillList(l);
	}

	public void set_info(String text) {
		info.setText("Nomina = " + text + " euros");
	}
}
