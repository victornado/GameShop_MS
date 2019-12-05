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
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FormConferencia extends JDialog {
	
	private final JLabel _name = new JLabel("Name:");
	private final JLabel _thematic = new JLabel("Thematic:");
	private final JLabel _assistants = new JLabel("Assistants:");
	private final JLabel _date = new JLabel("Date:");
	protected JTextField _nameText;
	protected JTextField _thematicText;
	protected JTextField _assistantsText;
	// AQUI UN JCalendar
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
		this.setBounds(new Rectangle(300, 140));
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
		
		_assistantsText = new JTextField();
		_assistantsText.setPreferredSize(new Dimension(220,20));
		_assistantsText.setMaximumSize(new Dimension(220,20));
		_assistantsText.setMinimumSize(new Dimension(220,20));
		
		// AQUI VA LA INICIALIZACION DEL JCalendar
		
		_ok = new JButton("OK");
		_ok.setPreferredSize(new Dimension(70,20));
		_ok.setMaximumSize(new Dimension(70,20));
		_ok.setMinimumSize(new Dimension(70,20));
		
		_cancel = new JButton("Cancel");
		_cancel.setPreferredSize(new Dimension(90,20));
		_cancel.setMaximumSize(new Dimension(90,20));
		_cancel.setMinimumSize(new Dimension(90,20));
		
		this.add(_name);
		this.add(Box.createRigidArea(new Dimension(16, 1)));
		this.add(_nameText);
		this.add(_thematic);
		this.add(_thematicText);
		this.add(_assistants);
		this.add(Box.createRigidArea(new Dimension(5, 1)));
		this.add(_assistantsText);
		this.add(_date);
		this.add(_ok);
		this.add(_cancel);
	}
}