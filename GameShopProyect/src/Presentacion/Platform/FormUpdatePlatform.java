package Presentacion.Platform;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;
import Transfers.TPlatform;

@SuppressWarnings("serial")
public class FormUpdatePlatform extends FormPlatform {
	
	private JCheckBox _reactivate;
	private TPlatform _platform;
	
	public FormUpdatePlatform(TPlatform t) {
		super();
		_platform = t;
		this.setLocationRelativeTo(null);
		this.setTitle("Modify a Platform");
		this.setSize(new Dimension(300, 92));
		
		this._reactivate = new JCheckBox("Activated");
		//this.setBounds(new Rectangle(300, 92));
		this._reactivate.setBounds(50, 150, 140, 50);
		this.add(_reactivate);
		this._reactivate.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if(_reactivate.isSelected()) {
					_platform.set_activated(true);
					_reactivate.setEnabled(false);
				}else
					_platform.set_activated(false);
			}
			
		});
		initForm();
		
		this.add(this._reactivate);
	}
	
	private void initForm() {
		this._nameText.setText(_platform.get_name());
		
		if(_platform.get_activated()) {
			this._reactivate.setEnabled(false);
			this._reactivate.setSelected(true);
		}
	}
	
	@Override
	protected void okButtonAction(){ //LLAMAR/CREAR EVENT.UPDATE_PROVIDER
		_ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				_platform.set_name(_nameText.getText());
				Controller.getInstance().action(_platform, Event.MODIFY_PLATFORM);
				closeDialog();
			}
		});
	}
}
