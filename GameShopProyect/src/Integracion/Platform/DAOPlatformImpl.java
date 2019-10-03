package Integracion.Platform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Transfers.TAccessory;
import Transfers.TGame;
import Transfers.TPlatform;
import Transfers.TProduct;


public class DAOPlatformImpl implements DAOPlatform {

	@Override
	public Integer createPlatform(TPlatform tpla) {
		int id = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + 
					Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("INSERT INTO plataforma(nombre, activo) VALUES(?,?)", 
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tpla.get_name());
			ps.setBoolean(2, true);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				id = rs.getInt(1);
			}
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			id = -1;
		}
		return id;
	}

	@Override
	public Boolean deletePlatform(Integer id) {
		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
					Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("UPDATE plataforma SET activo=(?) WHERE ID=(?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			int res = ps.executeUpdate();
		
			if(res > 0) {
				ret = true;
			}
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			ret = false;
		}
		return ret;
	}

	@Override
	public Boolean updatePlatform(TPlatform tpla) {
		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
					Main.Main.database, Main.Main.user, Main.Main.password);
			
			PreparedStatement ps = con.prepareStatement("UPDATE plataforma SET activo=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, tpla.get_activated());
			ps.setInt(2, tpla.get_id());
			int res = ps.executeUpdate();
			
			ps = con.prepareStatement("UPDATE plataforma SET nombre=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tpla.get_name());
			ps.setInt(2, tpla.get_id());
			res = ps.executeUpdate();
			
			if(res > 0) {
				ret = true;
			}
			con.close();
			
		}catch (SQLException | ClassNotFoundException e) {
			ret = false;
		}
		return ret;
	}

	@Override
	public TPlatform readPlatform(Integer id) {
		TPlatform tel = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
					Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM plataforma WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				tel = new TPlatform();
				tel.set_id(rs.getInt(1));
				tel.set_name(rs.getString(2));
				tel.set_activated(rs.getBoolean(3));
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			tel = null;
		}
		
		return tel;
	}

	@Override
	public List<Object> readAllPlatforms() {
		List<Object> l = new ArrayList<Object>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
					Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM plataforma",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				TPlatform tpl = new TPlatform();
				tpl.set_id(rs.getInt(1));
				tpl.set_name(rs.getString(2));
				tpl.set_activated(rs.getBoolean(3));
				l.add(tpl);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			l.clear();
		}
		return l;
	}

	@Override
	public List<Object> readAllProductsOfAPlatform(Integer id){
		List<Object> l = new ArrayList<Object>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
					Main.Main.database, Main.Main.user, Main.Main.password);
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM accesorio WHERE IDPlataforma=?",
			PreparedStatement ps = con.prepareStatement("SELECT * FROM producto WHERE IDPlataforma=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			String typeOfProduct;
			while(rs.next()){
				typeOfProduct = rs.getString(13);
				if(typeOfProduct.equalsIgnoreCase(TProduct.game)){
					TGame tg = new TGame();
					tg.set_id(rs.getInt(1));
					tg.set_name(rs.getString(2));
					tg.set_description(rs.getString(3));
					tg.set_pvp(rs.getDouble(4));
					tg.set_stock(rs.getInt(5));
					tg.set_providerId(rs.getInt(6));
					tg.set_platformId(rs.getInt(7));
					tg.set_activated(rs.getBoolean(8));
					tg.set_unitsProvided(rs.getInt(9));
					tg.set_gender(rs.getString(10));
					tg.set_type(typeOfProduct);
					l.add(tg);
				}
				else {
					TAccessory ta = new TAccessory();
					ta.set_id(rs.getInt(1));
					ta.set_name(rs.getString(2));
					ta.set_description(rs.getString(3));
					ta.set_pvp(rs.getDouble(4));
					ta.set_stock(rs.getInt(5));
					ta.set_providerId(rs.getInt(6));
					ta.set_platformId(rs.getInt(7));
					ta.set_activated(rs.getBoolean(8));
					ta.set_unitsProvided(rs.getInt(9));
					ta.set_brand(rs.getString(11));
					ta.set_color(rs.getString(12));
					ta.set_type(typeOfProduct);
					l.add(ta);
				}
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			l.clear();
		}
		return l;
	}
	
	@Override
	public TPlatform readByName(String s) {
		TPlatform tel = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT nombre FROM plataforma WHERE nombre=?",	PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				tel = new TPlatform();
				tel.set_name(rs.getString(1));
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			tel = null;
		}
		
		return tel;
	}

	
}