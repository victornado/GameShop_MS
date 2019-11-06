package Integracion.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Integracion.Querys.LockModeType;
import Integracion.Transacciones.Transaction;
import Integracion.Transacciones.TransactionManager;
import Transfers.TAsociated;
import Transfers.TTicket;

public class DAOTicketImpl implements DAOTicket {

	public Integer createTicket(TTicket tt) throws Exception {
		int id = -1;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;

		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("INSERT INTO ticket(fecha, precioFinal) VALUES(?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, tt.get_date());
			ps.setDouble(2, tt.get_finalPrice());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				List<Object> l = tt.get_products();
				id = rs.getInt(1);
				for (int i = 0; i < l.size(); ++i) {
					ps = con.prepareStatement(
							"INSERT INTO asociado(IDProducto, IDTicket, cantidad, precio) VALUES(?,?,?,?)");
					ps.setInt(1, ((TAsociated) l.get(i)).get_idProduct());
					ps.setInt(2, id);
					ps.setInt(3, ((TAsociated) l.get(i)).get_cantidad());
					ps.setDouble(4, ((TAsociated) l.get(i)).get_precio());
					ps.executeUpdate();

				}
			}

		}
		return id;
	}

	public Boolean deleteTicket(Integer id) throws Exception {
		boolean ret = false;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;

		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("DELETE FROM asociado WHERE IDTicket=?");
			ps.setInt(1, id);
			int res = ps.executeUpdate();

			if (res > 0) {
				ret = true;
			}

			ps = con.prepareStatement("DELETE FROM ticket WHERE ID=?");
			ps.setInt(1, id);
			int res2 = ps.executeUpdate();

			if (res2 > 0) {
				ret = true;
			}

		}
		return ret;
	}

	/*
	 * public TTicket readTicket(Integer id) { TTicket tp = null; try {
	 * Class.forName("com.mysql.cj.jdbc.Driver"); Connection con =
	 * DriverManager.getConnection("jdbc:mysql://localhost:3306/" +
	 * Main.Main.database, Main.Main.user, Main.Main.password); PreparedStatement ps
	 * = con.prepareStatement("SELECT * FROM ticket WHERE ID=?",
	 * PreparedStatement.RETURN_GENERATED_KEYS); ps.setInt(1, id); ResultSet rs =
	 * ps.executeQuery();
	 * 
	 * if(rs.next()){ tp = new TTicket(); tp.set_id(rs.getInt(1));
	 * tp.set_date(rs.getTimestamp(2)); tp.set_finalPrice(rs.getDouble(3));
	 * 
	 * ps = con.prepareStatement("SELECT * FROM asociado WHERE IDTicket=?");
	 * ps.setInt(1, id); ResultSet rsAsociado = ps.executeQuery(); List<Object> l =
	 * new ArrayList<Object>(); while(rsAsociado.next()){ TProduct pp = new
	 * TProduct(); ps = con.prepareStatement("SELECT * FROM producto WHERE ID=?",
	 * PreparedStatement.RETURN_GENERATED_KEYS); ps.setInt(1, rsAsociado.getInt(1));
	 * // Id del producto ResultSet rsProducto = ps.executeQuery();
	 * if(rsProducto.next()){ pp.set_id(rsProducto.getInt(1));
	 * pp.set_name(rsProducto.getString(2)); pp.set_stock(rsAsociado.getInt(3));
	 * l.add(pp); } } tp.set_products(l); } con.close(); } catch (SQLException |
	 * ClassNotFoundException e) { tp = null; }
	 * 
	 * return tp; }
	 */

	public TTicket readTicket(Integer id, Integer lock) throws Exception {
		TTicket tp = null;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;

		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ticket WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			if (lock == LockModeType.PESSIMISTIC) {
				ps = con.prepareStatement("SELECT * FROM ticket WHERE ID=? FOR UPDATE",
						PreparedStatement.RETURN_GENERATED_KEYS);
			}
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				tp = new TTicket();
				tp.set_id(rs.getInt(1));
				tp.set_date(rs.getTimestamp(2));
				tp.set_finalPrice(rs.getDouble(3));

				ps = con.prepareStatement("SELECT * FROM asociado WHERE IDTicket=?");
				ps.setInt(1, id);
				ResultSet rsAsociado = ps.executeQuery();
				List<Object> l = new ArrayList<Object>();
				while (rsAsociado.next()) {
					TAsociated ta = new TAsociated();
					ta.set_idProduct(rsAsociado.getInt(1));
					ta.set_ticket(id);
					ta.set_cantidad(rsAsociado.getInt(3));
					ta.set_precio(rsAsociado.getDouble(4));
					l.add(ta);
				}
				tp.set_products(l);
			}

		}

		return tp;
	}

	public List<Object> readAllTickets(Integer lock) throws Exception {
		List<Object> l = new ArrayList<Object>();
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;

		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ticket",
					PreparedStatement.RETURN_GENERATED_KEYS);
			if (lock == LockModeType.PESSIMISTIC)
				ps = con.prepareStatement("SELECT * FROM ticket FOR UPDATE", PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TTicket tt = new TTicket();
				tt.set_id(rs.getInt(1));
				tt.set_date(rs.getTimestamp(2));
				tt.set_finalPrice(rs.getDouble(3));
				l.add(tt);
			}

		}
		return l;
	}

}