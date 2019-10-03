package Presentacion.View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Integracion.DAO.*;
import Presentacion.Controller.Controller;



@SuppressWarnings("serial")
public class InfoDB extends JDialog {
	
	private JLabel _bd;
	private JTextField _bdText;
	private JLabel _name;
	private JTextField _nameText;
	private JLabel _passw;
	private JPasswordField _passwText;
	private JButton _ok;
	private JCheckBox _create;
	private JPanel panel;
	
	public InfoDB() {
		try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception ex){
        	System.err.println("ERROR lookAndFeel");
        }
		this.setTitle("DB information");
		this.setIconImage(new ImageIcon("resources/GameShopLogo.png").getImage());
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.setBounds(new Rectangle(220, 230));
		this.setLocationRelativeTo(null);
		
		initGUI();
		
		this.setVisible(true);
	}
	
	private void initGUI() {
		
		_bd = new JLabel("Database ");
		_bd.setAlignmentX(Component.CENTER_ALIGNMENT);
		_bd.setVisible(true);
		
		_bdText = new JTextField();
		_bdText.setAlignmentX(Component.CENTER_ALIGNMENT);
		_bdText.setText("gameshop");
		_bdText.setToolTipText("Write the database that you want to use");
		_bdText.setSize(250, 30);
		_bdText.setPreferredSize(new Dimension(200,30));
		_bdText.setMaximumSize(new Dimension(200,30));
		_bdText.setMinimumSize(new Dimension(200,30));
		
		_name = new JLabel("User ");
		_name.setAlignmentX(Component.CENTER_ALIGNMENT);
		_name.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		_nameText = new JTextField();
		_nameText.setAlignmentX(Component.CENTER_ALIGNMENT);
		_nameText.setText("root");
		_nameText.setToolTipText("Write the username who will use the database");
		_nameText.setSize(250, 30);
		_nameText.setPreferredSize(new Dimension(200,30));
		_nameText.setMaximumSize(new Dimension(200,30));
		_nameText.setMinimumSize(new Dimension(200,30));
		
		_passw = new JLabel("Password ");
		_passw.setAlignmentX(Component.CENTER_ALIGNMENT);
		_passw.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		_passwText = new JPasswordField();
		_passwText.setAlignmentX(Component.CENTER_ALIGNMENT);
		_passwText.setToolTipText("Write the password of the selected database");
		_passwText.setSize(250, 30);	
		_passwText.setPreferredSize(new Dimension(200,30));
		_passwText.setMaximumSize(new Dimension(200,30));
		_passwText.setMinimumSize(new Dimension(200,30));
		
		_ok = new JButton("Confirm");
		_ok.setAlignmentX(Component.CENTER_ALIGNMENT);
		_ok.setPreferredSize(new Dimension(150,30));
		_ok.setMaximumSize(new Dimension(150,30));
		_ok.setMinimumSize(new Dimension(150,30));
		
		_create = new JCheckBox("Create the new database");
		_create.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		createOkButtonAction();
		
		panel.add(_bd);
		panel.add(_bdText);
		panel.add(Box.createRigidArea(new Dimension(112, 1)));
		panel.add(_name);
		panel.add(_nameText);
		panel.add(Box.createRigidArea(new Dimension(70, 1)));
		panel.add(_passw);
		panel.add(_passwText);
		panel.add(_create);
		panel.add(_ok);
	}
	
	private void createOkButtonAction() {
		this._ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.Main.database = _bdText.getText();
				Main.Main.user = _nameText.getText();
				Main.Main.password = String.valueOf(_passwText.getPassword());
				Statement Stmt = null;
				Connection conn = null;
				try {
					conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", Main.Main.user, Main.Main.password);
					Stmt = conn.createStatement();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
				
					Stmt.execute("SET GLOBAL time_zone = '+3:00'");
					if (_create.isSelected()) {
	
						Stmt.execute("CREATE DATABASE " + Main.Main.database);
						Stmt.execute("USE " + Main.Main.database);
						
						String aSQLScriptFilePath = "resources/GameShopTables.sql";			   
						PrintWriter prnt = new PrintWriter(System.out);
						Reader rd = new FileReader(new File(aSQLScriptFilePath));
						SqlRunner r = new SqlRunner(conn,prnt,prnt,true,false);
						r.runScript(rd);
					}
					dispose();
					Controller.getInstance();
				}
				catch (Exception e){
					JOptionPane.showMessageDialog(null, "Error JDBC connection.","Failed",JOptionPane.ERROR_MESSAGE);
					try {
						Stmt.execute("DROP SCHEMA " + Main.Main.database);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} finally {
					try {
						if(Stmt!=null) 
							Stmt.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Error while creating the database.","Failed",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

}
