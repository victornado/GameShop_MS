package Presentacion.Conferencia;

import javax.swing.JDialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import Negocio.Transfers.TConferencia;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Event;

@SuppressWarnings("serial")
public class FormConferencia extends JDialog {
	
	private final JLabel _name = new JLabel("Name:");
	private final JLabel _thematic = new JLabel("Thematic:");
	private final JLabel _assistants = new JLabel("Assistants:");
	private final JLabel _date = new JLabel("Date:");
	protected JTextField _nameText;
	protected JTextField _thematicText;
	protected JSpinner _assistantsElection = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
	protected JTextField _dateText;
	protected JButton _ok;
	private JButton _cancel;
	
	public FormConferencia() {
		this.setTitle("Register a conference");
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
		this.setBounds(new Rectangle(300, 160));
		this.setLocationRelativeTo(null);
		
		initComponents();
		okButtonAction();
		cancelButtonAction();
		
		this.setVisible(true);
	}
	
	protected void okButtonAction(){
		_ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = _nameText.getText();
					String thematic = _thematicText.getText();
					Integer assistants = (Integer)_assistantsElection.getValue();
					TConferencia tc = new TConferencia(name, thematic, assistants, null);
					String date = _dateText.getText();
					tc.setStringFecha(date);
					Controller.getInstance().action(tc, Event.REGISTER_CONFERENCE);
					closeDialog();
				}catch(Exception ex) {
					closeDialog();
					Controller.getInstance().action(null, Event.REGISTER_CONFERENCE);
				}
			}
		});
	}
	
	private void cancelButtonAction(){
		_cancel.addActionListener(new ActionListener(){
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
	
	private void initComponents() {
		_nameText = new JTextField();
		_nameText.setPreferredSize(new Dimension(220,20));
		_nameText.setMaximumSize(new Dimension(220,20));
		_nameText.setMinimumSize(new Dimension(220,20));
		
		_thematicText = new JTextField();
		_thematicText.setPreferredSize(new Dimension(220,20));
		_thematicText.setMaximumSize(new Dimension(220,20));
		_thematicText.setMinimumSize(new Dimension(220,20));
		
		_assistantsElection.setPreferredSize(new Dimension(220,20));
		_assistantsElection.setMaximumSize(new Dimension(220,20));
		_assistantsElection.setMinimumSize(new Dimension(220,20));
		
		_dateText = new JTextField();
		_dateText.setPreferredSize(new Dimension(100,20));
		_dateText.setMaximumSize(new Dimension(100,20));
		_dateText.setMinimumSize(new Dimension(100,20));
		_dateText.setText("yyyy-mm-dd");
		
		_ok = new JButton("OK");
		_ok.setPreferredSize(new Dimension(70,20));
		_ok.setMaximumSize(new Dimension(70,20));
		_ok.setMinimumSize(new Dimension(70,20));
		
		_cancel = new JButton("Cancel");
		_cancel.setPreferredSize(new Dimension(90,20));
		_cancel.setMaximumSize(new Dimension(90,20));
		_cancel.setMinimumSize(new Dimension(90,20));
		
		this.add(_name);
		this.add(Box.createRigidArea(new Dimension(15, 1)));
		this.add(_nameText);
		this.add(_thematic);
		this.add(_thematicText);
		this.add(_assistants);
		this.add(Box.createRigidArea(new Dimension(5, 1)));
		this.add(_assistantsElection);
		this.add(_date);
		this.add(Box.createRigidArea(new Dimension(5, 1)));
		this.add(_dateText);
		this.add(Box.createRigidArea(new Dimension(120, 1)));
		this.add(_ok);
		this.add(_cancel);
	}
}