package Integracion.Provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Transfers.TProvider;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class TestDAOProvider implements DAOProvider {
	
	public Integer createProvider(TProvider tp) {
		int id = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("INSERT INTO proveedor(direccion, NIF, telefono, activo) VALUES(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tp.get_address());
			ps.setString(2, tp.get_nif());
			ps.setInt(3, tp.get_phoneNumber());
			ps.setBoolean(4, true);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				id = rs.getInt(1);
			}
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return id;
	}

	public Boolean deleteProvider(TProvider tp) {
		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("UPDATE proveedor SET activo=(?) WHERE ID=(?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, false);
			ps.setInt(2, tp.get_id());
			int res = ps.executeUpdate();
		
			if(res > 0) {
				ret = true;
			}
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public Boolean updateProvider(TProvider tp) {
		
		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("UPDATE proveedor SET activo=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, tp.get_activated());
			ps.setInt(2, tp.get_id());
			int res = ps.executeUpdate();
			ps = con.prepareStatement("UPDATE proveedor SET NIF=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tp.get_nif());
			ps.setInt(2, tp.get_id());
			res = ps.executeUpdate();
			ps = con.prepareStatement("UPDATE proveedor SET direccion=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tp.get_address());
			ps.setInt(2, tp.get_id());
			res = ps.executeUpdate();
			ps = con.prepareStatement("UPDATE proveedor SET telefono=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tp.get_phoneNumber());
			ps.setInt(2, tp.get_id());
			res = ps.executeUpdate();
			
			if(res > 0) {
				ret = true;
			}
			con.close();
			
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public Object readProvider(Integer id) {
		TProvider tpl = null;
		try {
			/*
			 * STACKOVERFLOW ERROR SOLUTION: (https://stackoverflow.com/questions/26515700/mysql-jdbc-driver-5-1-33-time-zone-issue)
			 * jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
			 * Hay que descargarse el JAR executable file y añadirlo a la libreria del proyecto para solucionar ese error y en la BD poner:
			 * SET GLOBAL time_zone = '+3:00'; para arreglar el error de la zona horaria*/
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM proveedor WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				tpl = new TProvider();
				tpl.set_id(rs.getInt(1));
				tpl.set_address(rs.getString(2));
				tpl.set_nif(rs.getString(3));
				tpl.set_phoneNumber(rs.getInt(4));
				tpl.set_activated(rs.getBoolean(5));
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return tpl;
	}

	public List<Object> readAllProviders() {
		List<Object> l = new ArrayList<Object>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM proveedor", PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				TProvider tpl = new TProvider();
				tpl.set_id(rs.getInt(1));
				tpl.set_address(rs.getString(2));
				tpl.set_nif(rs.getString(3));
				tpl.set_phoneNumber(rs.getInt(4));
				tpl.set_activated(rs.getBoolean(5));
				l.add(tpl);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return l;
	}

	public TProvider readProviderByNIF(String s) {
		
		TProvider tpl = null;
		try {
			/*
			 * STACKOVERFLOW ERROR SOLUTION: (https://stackoverflow.com/questions/26515700/mysql-jdbc-driver-5-1-33-time-zone-issue)
			 * jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
			 * Hay que descargarse el JAR executable file y añadirlo a la libreria del proyecto para solucionar ese error y en la BD poner:
			 * SET GLOBAL time_zone = '+3:00'; para arreglar el error de la zona horaria*/
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT ID FROM proveedor WHERE NIF=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			//ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				tpl = new TProvider();
				tpl.set_id(rs.getInt(1));
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return tpl;
	}
}