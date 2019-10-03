package Integracion.Ticket;

import java.util.ArrayList;
import java.util.List;

import Transfers.TProduct;
import Transfers.TProvider;
import Transfers.TTicket;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class TestDAOTicket implements DAOTicket {
	
	public Integer createTicket(TTicket tt) {
		int id = -1;
		try {
			List<Object> prod = tt.get_products();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			
			for(int i = 0 ; i < prod.size(); ++i) {
				PreparedStatement ps = con.prepareStatement("INSERT INTO ticket(idEmpl, fecha, precioFinal, idProd , nombre, cantidad, precio) VALUES(?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
				TProduct pr = (TProduct) prod.get(0);
				ps.setInt(1, tt.get_employeeId());
				ps.setTimestamp(2, tt.get_date()); 
				ps.setDouble(3,tt.get_finalPrice());
				ps.setInt(4, pr.get_id());
				ps.setString(5, pr.get_name());
				//ps.setInt(6,pr.get_platformId());
				ps.setInt(6, pr.get_unitsProvided());
				ps.setDouble(7, pr.get_pvp());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()){
					id = rs.getInt(1);
				}
			}
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return id;
	}
	public Boolean deleteTicket(TTicket tt) {
		boolean ret = false;
		try {
			//java.sql.Date date = new java.sql.Date(tt.get_date().getTime()); //CREEEEEEEEEEEEEEEEO QUE FUNCIONA (?)
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("DELETE ticket WHERE IDEmpleado=(?) AND fecha =(?) AND precio_final = (?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tt.get_employeeId());
			ps.setTimestamp(2, tt.get_date());
			ps.setDouble(3, tt.get_finalPrice());
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
	public Boolean updateTicket(TTicket tt) {
		/*boolean ret = false;
		try {
			deleteTicket(tt);
			createTicket(tt);
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("UPDATE ticket SET =? WHERE =?", PreparedStatement.RETURN_GENERATED_KEYS);
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
		return ret;*/
		return null;
	}
	public TTicket readTicket(TTicket tt) {
		TTicket tp = null;
		List<Object> listprod = new ArrayList<Object>();
		TProduct tprod = new TProduct();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ticket WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tt.get_id());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				tp = new TTicket();
				tp.set_id(rs.getInt(1));
				tp.set_employeeId(rs.getInt(2));
				tp.set_date(rs.getTimestamp(3));
				tp.set_finalPrice(rs.getDouble(4));
				
				while(rs.next())
				{ //IDticket, idEmpl, fecha, precioFinal, idProd , nombre , idPlat, cantidad, precio
					tprod.set_id(rs.getInt(5));
					tprod.set_name(rs.getString(6));
					tprod.set_platformId(rs.getInt(7));
					tprod.set_unitsProvided(rs.getInt(8));
					tprod.set_pvp(rs.getDouble(9));
					listprod.add(tprod);
				}
				tp.set_products(listprod);
					
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return tp;
	}
	public List<Object> readAllTickets() {
		List<Object> l = new ArrayList<Object>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ticket", PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				TTicket tt = new TTicket();
				tt.set_id(rs.getInt(1));
				tt.set_employeeId(rs.getInt(2));
				tt.set_date(rs.getTimestamp(3));
				tt.set_finalPrice(rs.getDouble(4));
				l.add(tt);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return l;
}

	public TTicket readByDate(Timestamp d) {
		TTicket tp = null;
		List<Object> listprod = new ArrayList<Object>();
		TProduct tprod = new TProduct();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ticket WHERE fecha=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, d);
			ResultSet rs = ps.executeQuery();
			//ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				tp = new TTicket();
				tp.set_id(rs.getInt(1));
				tp.set_employeeId(rs.getInt(2));
				tp.set_date(rs.getTimestamp(3));
				tp.set_finalPrice(rs.getDouble(4));
				
				while(rs.next())
				{ //IDticket, idEmpl, fecha, precioFinal, idProd , nombre , idPlat, cantidad, precio
					tprod.set_id(rs.getInt(5));
					tprod.set_name(rs.getString(6));
					tprod.set_platformId(rs.getInt(7));
					tprod.set_unitsProvided(rs.getInt(8));
					tprod.set_pvp(rs.getDouble(9));
					listprod.add(tprod);
				}
				tp.set_products(listprod);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return tp;
	}
}