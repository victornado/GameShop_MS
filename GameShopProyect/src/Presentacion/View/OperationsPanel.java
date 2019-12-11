package Presentacion.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Negocio.SA.SAAbstractFactory;
import Negocio.Transfers.TConferencia;
import Negocio.Transfers.TDepartamento;
import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TProduct;
import Negocio.Transfers.TProvider;
import Negocio.Transfers.TTicket;
import Presentacion.Conferencia.FormConferencia;
import Presentacion.Conferencia.FormUpdateConferencia;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Presentacion.Departamento.FormDepartment;
import Presentacion.Departamento.FormUpdateDepartment;
import Presentacion.Empleado.FormEmployee;
import Presentacion.Empleado.FormUpdateEmployee;
import Presentacion.Product.FormProduct;
import Presentacion.Product.FormUpdateProduct;
import Presentacion.Provider.FormProvider;
import Presentacion.Provider.FormUpdateProvider;
import Presentacion.Ticket.FormTicket;

@SuppressWarnings("serial")
public class OperationsPanel extends JPanel {

	private JButton _register;
	private JComboBox<Object> _election;
	private JButton _remove;
	private JButton _update;
	private JLabel _registerLabel;
	private JLabel _updaterLabel;

	private List<Object> _electionForm = null;
	private Object _entityToUse = null;

	private String nameIdentificator;

	public OperationsPanel(String guiNameIdentificator) {
		this.nameIdentificator = guiNameIdentificator.toLowerCase();
		initPanel();
	}

	public void initPanel() {
		this.setPreferredSize(new Dimension(300, 700));
		this.setMinimumSize(new Dimension(300, 700));
		this.setMaximumSize(new Dimension(300, 700));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Operations",
				TitledBorder.LEFT, TitledBorder.TOP));

		initComponents();

		this.setVisible(true);
	}

	private void registerButtonAction() {
		_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (nameIdentificator) {
				case "provider":
					new FormProvider();
					break;
				case "product":
					new FormProduct();
					break;
				case "ticket":
					new FormTicket();
					break;
				/***************************************************************************************************************************************/

				case "conference":
					new FormConferencia();
					break;
				case "department":
					new FormDepartment();
					break;
				case "employee":
					new FormEmployee();
					break;
				}
			}
		});
	}

	private void updateButtonAction() {

		this._update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object id;

				if (_election.getItemCount() > 0) {
					id = Integer.parseInt(_election.getSelectedItem().toString().split(" - ")[0]);
					switch (nameIdentificator) {
					case "provider":

						TProvider tpr = (TProvider) (SAAbstractFactory.getInstance().createSAProvider())
								.readProvider((Integer) id);
						if (tpr != null)
							new FormUpdateProvider(tpr);
						else
							JOptionPane.showMessageDialog(null, "Error al leer un proveedor de la base de datos.",
									"Failed", JOptionPane.ERROR_MESSAGE);
						break;
					case "product":
						TProduct tprd = (TProduct) (SAAbstractFactory.getInstance().createSAProduct())
								.readProduct((Integer) id);
						if (tprd != null)
							new FormUpdateProduct(tprd);
						else
							JOptionPane.showMessageDialog(null, "Error when reading a product from the database.",
									"Failed", JOptionPane.ERROR_MESSAGE);
						break;

					/***************************************************************************************************************************************/
					case "conference":
						Controller.getInstance().action(id, Event.READ_CONFERENCE_FORM);
						new FormUpdateConferencia((TConferencia) _entityToUse);
						break;
					case "department":
						Controller.getInstance().action(id, Event.READ_DEPARTMENT_FORM);
						new FormUpdateDepartment((TDepartamento) _entityToUse);
						break;
					case "employee":
						Controller.getInstance().action(id, Event.READ_EMPLOYEE_FORM);
						new FormUpdateEmployee((TEmpleado) _entityToUse);
						break;
					}
				}
			}
		});
	}

	private void deleteButtonAction() {
		_remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (_election.getItemCount() > 0) {
					Integer id = Integer.parseInt(_election.getSelectedItem().toString().split(" - ")[0]);

					switch (nameIdentificator) {
					case "provider":
						Controller.getInstance().action(id, Event.UNSUBSCRIBE_PROVIDER);
						break;
					case "product":
						Controller.getInstance().action(id, Event.UNSUBSCRIBE_PRODUCT);
						break;
					case "ticket":
						Controller.getInstance().action(id, Event.UNSUBSCRIBE_TICKET);
						break;
					/***************************************************************************************************************************************/

					case "conference":
						Controller.getInstance().action(id, Event.UNSUBSCRIBE_CONFERENCE);
						break;
					case "department":
						Controller.getInstance().action(id, Event.UNSUBSCRIBE_DEPARTMENT);
						break;
					case "employee":
						Controller.getInstance().action(id, Event.UNSUBSCRIBE_EMPLOYEE);
						break;
					}
				}
			}
		});
	}

	public void addInfoToComboBox() {
		_election.removeAllItems();
		switch (nameIdentificator) {
		case "provider":
			for (Object tpro : SAAbstractFactory.getInstance().createSAProvider().readAllProviders())
				_election.addItem(((TProvider) tpro).get_id() + " - " + ((TProvider) tpro).get_nif());
			break;
		case "product":
			for (Object temp : SAAbstractFactory.getInstance().createSAProduct().readAllProducts())
				_election.addItem(((TProduct) temp).get_id() + " - " + ((TProduct) temp).get_type() + " - "
						+ ((TProduct) temp).get_name());
			break;
		case "ticket":
			for (Object tt : SAAbstractFactory.getInstance().createSATicket().readAllTickets())
				_election.addItem(((TTicket) tt).get_id() + " - " + ((TTicket) tt).get_date());
			break;
		/****************************************************************************************************************************/
		case "conference":
			Controller.getInstance().action(null, Event.UPDATE_LIST_CONFERENCE);
			if (this._electionForm != null) {
				for (Object tc : this._electionForm)
					_election.addItem(((TConferencia) tc).getID() + " - " + ((TConferencia) tc).getNombre());
			}
			break;
		case "department":
			Controller.getInstance().action(null, Event.UPDATE_LIST_DEPARTMENT);
			if (this._electionForm != null) {
				for (Object tc : this._electionForm)
					_election.addItem(((TDepartamento) tc).getID() + " - " + ((TDepartamento) tc).getNombre());
			}
			break;
		case "employee":
			Controller.getInstance().action(null, Event.UPDATE_LIST_EMPLOYEE);
			if (this._electionForm != null) {
				for (Object tc : this._electionForm)
					_election.addItem(((TEmpleado) tc).getID() + " - " + ((TEmpleado) tc).getNombre());
			}
			break;
		}

	}

	private void initComponents() {
		this.add(Box.createVerticalGlue());
		_registerLabel = new JLabel("Register a new " + nameIdentificator);
		_registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		_registerLabel.setVisible(true);
		this.add(_registerLabel);

		this.add(Box.createRigidArea(new Dimension(1, 10)));

		_register = new JButton("Register " + nameIdentificator);
		_register.setAlignmentX(Component.CENTER_ALIGNMENT);
		_register.setSize(new Dimension(100, 50));
		_register.setVisible(true);
		this.add(_register);

		this.add(Box.createVerticalGlue());

		String modifyRemoveTextLabel = "";
		if (!this.nameIdentificator.equalsIgnoreCase(GUIGameshopImp.TAB_TICKET))
			modifyRemoveTextLabel += "Modify or ";
		modifyRemoveTextLabel += "Remove a " + nameIdentificator;

		_updaterLabel = new JLabel(modifyRemoveTextLabel);
		_updaterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		_updaterLabel.setVisible(true);
		this.add(_updaterLabel);

		this.add(Box.createRigidArea(new Dimension(1, 10)));

		_election = new JComboBox<Object>();
		_election.setAlignmentX(Component.CENTER_ALIGNMENT);
		_election.setSize(new Dimension(200, 50));
		_election.setBounds(new Rectangle(200, 150));
		_election.setMinimumSize(new Dimension(200, 50));
		_election.setMaximumSize(new Dimension(200, 50));
		_election.setEditable(false);
		_election.setVisible(true);
		this.add(_election);

		this.add(Box.createRigidArea(new Dimension(1, 20)));

		if (!this.nameIdentificator.equalsIgnoreCase(GUIGameshopImp.TAB_TICKET)) {
			_update = new JButton("Modify " + nameIdentificator);
			_update.setAlignmentX(Component.CENTER_ALIGNMENT);
			_update.setSize(new Dimension(100, 50));
			_update.setVisible(true);
			this.add(_update);
			updateButtonAction();
		}

		this.add(Box.createRigidArea(new Dimension(1, 10)));

		_remove = new JButton("Remove " + nameIdentificator);
		_remove.setAlignmentX(Component.CENTER_ALIGNMENT);
		_remove.setSize(new Dimension(100, 50));
		_remove.setVisible(true);
		this.add(_remove);

		this.add(Box.createVerticalGlue());

		registerButtonAction();
		deleteButtonAction();

	}

	public List<Object> getElectionForm() {
		return _electionForm;
	}

	public void setElectionForm(List<Object> _electionForm) {
		this._electionForm = _electionForm;
	}

	public Object getEntityToUse() {
		return _entityToUse;
	}

	public void setEntityToUse(Object _entityToUse) {
		this._entityToUse = _entityToUse;
	}

}