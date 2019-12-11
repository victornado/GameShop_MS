package Presentacion.Realiza;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Negocio.Transfers.TConferencia;

@SuppressWarnings("serial")
public class PanelRealiza extends JPanel {
	private JButton asignar;
	private JButton desasignar;
	private JButton modificar;
	
	private JLabel etiquetaAsignar;
	private JLabel etiquetaDesasignar;
	private JLabel etiquetaModificar;
	
	/******** LIST AUXILIAR PARA COMBOBOX ********/
	private List<Object> _electionForm = null;
	private TConferencia _entityToUse = null;
	
	public PanelRealiza() {
		initPanel();
	}
	
	private void initPanel() {
		this.setPreferredSize(new Dimension(300, 700));
		this.setMinimumSize(new Dimension(300, 700));
		this.setMaximumSize(new Dimension(300, 700));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Asignar un empleado a una conferencia",
				TitledBorder.LEFT, TitledBorder.TOP));
		
		initComponents();
		
		this.setVisible(true);
	}
	
	private void addModificarButtonAction() {
		modificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FormModificarRealiza();
			}
		});
	}

	private void addDesasignarButtonAction() {
		desasignar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FormDesasignarRealiza();
			}
		});
	}

	private void addAsiganrButtonAction() {
		asignar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new FormRealiza();
			}
		});
	}

	private void initComponents() {
		etiquetaAsignar = new JLabel("Asignar conferencia - empleado");
		etiquetaAsignar.setAlignmentX(Component.CENTER_ALIGNMENT);
		etiquetaAsignar.setVisible(true);
		etiquetaDesasignar = new JLabel("Desasignar conferencia - empleado");
		etiquetaDesasignar.setAlignmentX(Component.CENTER_ALIGNMENT);
		etiquetaDesasignar.setVisible(true);
		etiquetaModificar = new JLabel("Modificar conferencia - empleado");
		etiquetaModificar.setAlignmentX(Component.CENTER_ALIGNMENT);
		etiquetaModificar.setVisible(true);
		
		asignar = new JButton("Asignar");
		asignar.setAlignmentX(Component.CENTER_ALIGNMENT);
		asignar.setSize(new Dimension(100, 50));
		asignar.setVisible(true);
		desasignar = new JButton("Desasignar");
		desasignar.setAlignmentX(Component.CENTER_ALIGNMENT);
		desasignar.setSize(new Dimension(100, 50));
		desasignar.setVisible(true);
		modificar = new JButton("Modificar");
		modificar.setAlignmentX(Component.CENTER_ALIGNMENT);
		modificar.setSize(new Dimension(100, 50));
		modificar.setVisible(true);
		
		this.add(Box.createRigidArea(new Dimension(1, 50)));
		this.add(etiquetaAsignar);
		this.add(asignar);
		this.add(Box.createRigidArea(new Dimension(1, 50)));
		this.add(etiquetaDesasignar);
		this.add(desasignar);
		this.add(Box.createRigidArea(new Dimension(1, 50)));
		this.add(etiquetaModificar);
		this.add(modificar);
		this.add(Box.createRigidArea(new Dimension(1, 50)));
		
		addAsiganrButtonAction();
		addDesasignarButtonAction();
		addModificarButtonAction();
	}
	
	public void setElectionForm(List<Object> l) {
		this._electionForm = l;
	}
	
	public List<Object> getElectionForm(){
		return this._electionForm;
	}
	
	public TConferencia get_entityToUse() {
		return _entityToUse;
	}

	public void set_entityToUse(Object object) {
		this._entityToUse = (TConferencia) object;
	}
}
