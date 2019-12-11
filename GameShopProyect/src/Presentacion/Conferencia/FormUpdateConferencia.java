package Presentacion.Conferencia;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Transfers.TConferencia;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;


@SuppressWarnings("serial")
public class FormUpdateConferencia extends FormConferencia {

	private JCheckBox _reactivate;
	private TConferencia tc;
	
	public FormUpdateConferencia(TConferencia tc) {
		super();
		this.tc = tc;
		this.setTitle("Modify a conference");
		this.setSize(new Dimension(300, 160));
		
		this._reactivate = new JCheckBox("Activated");
		this._reactivate.setBounds(50, 150, 140, 50);
		this.add(_reactivate);
		this._reactivate.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(_reactivate.isSelected()) {
					tc.setActivo(true);
					_reactivate.setEnabled(false);
				}
				else
					tc.setActivo(false);
			}
			
		});
		initForm();
		
		this.add(this._reactivate);
	}
	
	private void initForm() {
		_nameText.setText(tc.getNombre());
		_thematicText.setText(tc.getTematica());
		_assistantsElection.setValue(tc.getAsistentes());
		_dateText.setText(tc.getDate().toString());
		
		if(tc.getActivo()) {
			_reactivate.setEnabled(false);
			_reactivate.setSelected(true);
		}
	}
	
	@Override
	protected void okButtonAction(){
		_ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = _nameText.getText();
				String tematica = _thematicText.getText();
				Integer asistentes = (Integer)_assistantsElection.getValue();
				String fecha = _dateText.getText();
				TConferencia newTc = new TConferencia(nombre, tematica, asistentes, null);
				newTc.setStringFecha(fecha);
				newTc.setID(tc.getID());
				newTc.setActivo(tc.getActivo());
				Controller.getInstance().action(newTc, Event.MODIFY_CONFERENCE);
				closeDialog();
			}
		});
	}
}