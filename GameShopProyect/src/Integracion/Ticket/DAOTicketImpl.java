package Integracion.Ticket;

import java.util.ArrayList;
import java.util.List;

import Transfers.TProduct;
import Transfers.TTicket;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class DAOTicketImpl implements DAOTicket {
	
	public Integer createTicket(TTicket tt) {
		int id = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("INSERT INTO ticket(IDEmpleado, fecha, precioFinal) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tt.get_employeeId());
			ps.setTimestamp(2, tt.get_date());
			ps.setDouble(3, tt.get_finalPrice());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				List<Object> l = tt.get_products();
				id = rs.getInt(1);
				for(int i = 0; i < l.size(); ++i) {
					ps = con.prepareStatement("INSERT INTO asociado(IDProducto, IDTicket, IDEmpleado, cantidad) VALUES(?,?,?,?)");
					ps.setInt(1, ((TProduct)l.get(i)).get_id());
					ps.setInt(2, id);
					ps.setInt(3, tt.get_employeeId());
					ps.setDouble(4, ((TProduct)l.get(i)).get_unitsInTicket());
					ps.executeUpdate();
					
				}
			}
			
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			id = -1;
		}
		return id;
	}
	
	public Boolean deleteTicket(Integer id) {
		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("DELETE FROM asociado WHERE IDTicket=?");
			ps.setInt(1, id);
			int res = ps.executeUpdate();
		
			if(res > 0) {
				ret = true;
			}
			
			ps = con.prepareStatement("DELETE FROM ticket WHERE ID=?");
			ps.setInt(1, id);
			int res2 = ps.executeUpdate();
		
			if(res2 > 0) {
				ret = true;
			}
			
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			ret = false;
		}
		return ret;
	}
	
	public TTicket readTicket(Integer id) {
		TTicket tp = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ticket WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				tp = new TTicket();
				tp.set_id(rs.getInt(1));
				tp.set_employeeId(rs.getInt(2));
				tp.set_date(rs.getTimestamp(3));
				tp.set_finalPrice(rs.getDouble(4));
				
				ps = con.prepareStatement("SELECT * FROM asociado WHERE IDTicket=?");
				ps.setInt(1, id);
				ResultSet rsAsociado = ps.executeQuery();
				List<Object> l = new ArrayList<Object>();
				while(rsAsociado.next()){
					TProduct pp = new TProduct();
					ps = con.prepareStatement("SELECT * FROM producto WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
					ps.setInt(1, rsAsociado.getInt(1));
					ResultSet rsProducto = ps.executeQuery();
					if(rsProducto.next()){
						pp.set_id(rsProducto.getInt(1));
						pp.set_name(rsProducto.getString(2));
						pp.set_platformId(rsProducto.getInt(7));
						pp.set_stock(rsAsociado.getInt(4));
						l.add(pp);
					}
				}
				tp.set_products(l);
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			tp = null;
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
			l.clear();
		}
		return l;
	}

}