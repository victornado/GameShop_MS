package Integracion.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Transfers.TEmployee;

public class TestDAOEmployee implements DAOEmployee {

	@Override
	public Integer createEmployee(TEmployee te) {
		int id = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("INSERT INTO empleado(nombre, NIF, turno, activo) VALUES(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, te.get_name());
			ps.setString(2, te.get_nif());
			ps.setString(3, te.getTurn());
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

	@Override
	public Boolean deleteEmployee(TEmployee te) {
		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("UPDATE empleado SET activo=(?) WHERE ID=(?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, false);
			ps.setInt(2, te.get_id());
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

	@Override
	public Boolean updateEmployee(TEmployee te) {
		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("UPDATE empleado SET activo=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, te.get_activated());
			ps.setInt(2, te.get_id());
			int res = ps.executeUpdate();
			ps = con.prepareStatement("UPDATE empleado SET NIF=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, te.get_nif());
			ps.setInt(2, te.get_id());
			res = ps.executeUpdate();
			ps = con.prepareStatement("UPDATE empleado SET nombre=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, te.get_name());
			ps.setInt(2, te.get_id());
			res = ps.executeUpdate();
			ps = con.prepareStatement("UPDATE empleado SET turno=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, te.getTurn());
			ps.setInt(2, te.get_id());
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

	@Override
	public Object readEmployee(Integer id) {
		TEmployee tel = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM empleado WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			//ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				tel = new TEmployee();
				tel.set_id(rs.getInt(1));
				tel.set_name(rs.getString(2));
				tel.set_nif(rs.getString(3));
				tel.setTurn(rs.getString(4));
				tel.set_activated(rs.getBoolean(5));
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return tel;
	}

	@Override
	public List<Object> readAllEmployees() {
		List<Object> l = new ArrayList<Object>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM empleado", PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			//ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()){
				TEmployee tpl = new TEmployee();
				tpl.set_id(rs.getInt(1));
				tpl.set_name(rs.getString(2));
				tpl.set_nif(rs.getString(3));
				tpl.setTurn(rs.getString(4));
				tpl.set_activated(rs.getBoolean(5));
				l.add(tpl);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public TEmployee readEmployeeByNIF(String s) {
		TEmployee tel = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM empleado WHERE NIF=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			//ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				tel = new TEmployee();
				tel.set_id(rs.getInt(1));
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return tel;
	}
	
}