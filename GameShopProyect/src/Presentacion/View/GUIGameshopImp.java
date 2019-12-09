package Presentacion.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import Presentacion.Conferencia.GUIConferencia;
import Presentacion.Conferencia.GUIConferenciaImp;
import Presentacion.Departamento.GUIDepartment;
import Presentacion.Departamento.GUIDepartmentImp;
import Presentacion.Empleado.GUIEmployee;
import Presentacion.Empleado.GUIEmployeeImp;
import Presentacion.Product.GUIProduct;
import Presentacion.Product.GUIProductImp;
import Presentacion.Provider.GUIProvider;
import Presentacion.Provider.GUIProviderImp;
import Presentacion.Ticket.GUITicket;
import Presentacion.Ticket.GUITicketImp;

@SuppressWarnings("serial")
public class GUIGameshopImp extends GUIGameShop {

	public static final String TAB_PROVIDER = "Provider";
	public static final String TAB_DEPARTMENT = "Department";
	public static final String TAB_EMPLOYEE = "Employee";
	public static final String TAB_PRODUCT = "Product";
	public static final String TAB_TICKET = "Ticket";
	public static final String TAB_CONFERENCE = "Conference";
	private static List<Object> _guis = new ArrayList<Object>();

	private JTabbedPane _tabs;

	public GUIGameshopImp() {
		super.setTitle(Main.Main.applicationName);
	}

	private void initGUI() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			System.err.println(
					"Error. The look and feel that is set is not available for your current operating system.");
		}
		super.setName(Main.Main.applicationName);
		this.setSize(new Dimension(700, 500));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure that you want to close the program?", "Exit",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("resources/GameShopLogo.png").getImage());

		this.setLayout(new BorderLayout());
		this.add(new JLabel("  GameShop Application. Developed by UCM FdI students. "
				+ "Department of Software Engineering and artificial intelligence."), BorderLayout.SOUTH);

		this.setVisible(true);
	}

	private void initTabs() {
		_tabs = new JTabbedPane(JTabbedPane.TOP);
		_tabs.setPreferredSize(new Dimension(700, 500));
		_tabs.setMinimumSize(new Dimension(700, 500));
		_tabs.setMaximumSize(new Dimension(700, 500));

		_tabs.addTab(GUIGameshopImp.TAB_PROVIDER, null, (GUIProviderImp) _guis.get(0), "Provider tab");

		_tabs.addTab(GUIGameshopImp.TAB_PRODUCT, null, (GUIProductImp) _guis.get(1), "Product tab");

		_tabs.addTab(GUIGameshopImp.TAB_TICKET, null, (GUITicketImp) _guis.get(2), "Ticket tab");

		_tabs.addTab(GUIGameshopImp.TAB_CONFERENCE, null, (GUIConferenciaImp) _guis.get(3), "Conference tab");

		_tabs.addTab(GUIGameshopImp.TAB_DEPARTMENT, null, (GUIDepartmentImp) _guis.get(4), "Department tab");

		_tabs.addTab(GUIGameshopImp.TAB_EMPLOYEE, null, (GUIEmployeeImp) _guis.get(5), "Employee tab");

		this.add(_tabs, BorderLayout.CENTER);
	}

	public IGUI getGuiAt(int index) {
		return (IGUI) _tabs.getComponentAt(index);
	}

	@Override
	public void initGameShop(Integer event) {
		initGUI();
		_guis.add(GUIProvider.getInstance());
		_guis.add(GUIProduct.getInstance());
		_guis.add(GUITicket.getInstance());
		_guis.add(GUIConferencia.getInstance());
		_guis.add(GUIDepartment.getInstance());
		_guis.add(GUIEmployee.getInstance());
		initTabs();
		GUIDepartment.getInstance().getOpPanel().addInfoToComboBox();
		GUIConferencia.getInstance().getOpPanel().addInfoToComboBox();
		GUIEmployee.getInstance().getOpPanel().addInfoToComboBox();
	}

}