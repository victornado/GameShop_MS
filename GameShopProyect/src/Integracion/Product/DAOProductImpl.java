package Integracion.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Integracion.Querys.LockModeType;
import Integracion.Transacciones.Transaction;
import Integracion.Transacciones.TransactionManager;
import Negocio.Transfers.TAccessory;
import Negocio.Transfers.TGame;
import Negocio.Transfers.TProduct;

/**
 * @author GameShop
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOProductImpl implements DAOProduct {

	public Integer createProduct(TProduct tpr) throws Exception {
		int id = -1;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps;
			ps = con.prepareStatement(
					"INSERT INTO producto(nombre,descripcion,PVP,stock,IDProveedor,"
							+ "activo,unidadesProv,genero,marca,color,tipo) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, tpr.get_name());
			ps.setString(2, tpr.get_description());
			ps.setDouble(3, tpr.get_pvp());
			ps.setInt(4, tpr.get_stock());
			ps.setInt(5, tpr.get_providerId());
			ps.setBoolean(6, true);
			ps.setInt(7, tpr.get_unitsProvided());
			if (tpr.get_type().equals(TProduct.accessory)) {
				ps.setString(8, "null");
				ps.setString(9, ((TAccessory) tpr).get_brand());
				ps.setString(10, ((TAccessory) tpr).get_color());
			} else {
				ps.setString(8, ((TGame) tpr).get_gender());
				ps.setString(9, "null");
				ps.setString(10, "null");
			}
			ps.setString(11, tpr.get_type());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		}
		return id;
	}

	public Boolean deleteProduct(Integer id) throws Exception {
		boolean ret = false;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("UPDATE producto SET activo=(?) WHERE ID=(?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			;
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			int res = ps.executeUpdate();
			if (res > 0) {
				ret = true;
			}
		}
		return ret;
	}

	@SuppressWarnings("resource")
	public Boolean updateProduct(TProduct tpr) throws Exception {

		boolean ret = false;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("UPDATE producto SET activo=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, tpr.get_activated());
			ps.setInt(2, tpr.get_id());
			int res = ps.executeUpdate();

			ps = con.prepareStatement("UPDATE producto SET nombre=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(2, tpr.get_id());
			ps.setString(1, tpr.get_name());
			res = ps.executeUpdate();

			ps = con.prepareStatement("UPDATE producto SET descripcion=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(2, tpr.get_id());
			ps.setString(1, tpr.get_description());
			res = ps.executeUpdate();

			ps = con.prepareStatement("UPDATE producto SET stock=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(2, tpr.get_id());
			ps.setInt(1, tpr.get_stock());
			res = ps.executeUpdate();

			ps = con.prepareStatement("UPDATE producto SET pvp=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(2, tpr.get_id());
			ps.setDouble(1, tpr.get_pvp());
			res = ps.executeUpdate();

//			ps = con.prepareStatement("UPDATE producto SET IDPlataforma=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
//			ps.setInt(2, tpr.get_id());
//			ps.setInt(1, tpr.get_platformId());
//			res = ps.executeUpdate();

			ps = con.prepareStatement("UPDATE producto SET IDProveedor=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(2, tpr.get_id());
			ps.setInt(1, tpr.get_providerId());
			res = ps.executeUpdate();

			int sum = 0;
			ps = con.prepareStatement("SELECT * FROM producto WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tpr.get_id());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				sum = rs.getInt(8);
			ps = con.prepareStatement("UPDATE producto SET unidadesProv=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(2, tpr.get_id());
			ps.setInt(1, tpr.get_unitsProvided() + sum);
			res = ps.executeUpdate();

			if (tpr.get_type().equals(TProduct.accessory)) {
				ps = con.prepareStatement("UPDATE producto SET marca=? WHERE ID=?",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setInt(2, tpr.get_id());
				ps.setString(1, ((TAccessory) tpr).get_brand());
				res = ps.executeUpdate();

				ps = con.prepareStatement("UPDATE producto SET color=? WHERE ID=?",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setInt(2, tpr.get_id());
				ps.setString(1, ((TAccessory) tpr).get_color());
				res = ps.executeUpdate();
			} else {
				ps = con.prepareStatement("UPDATE producto SET genero=? WHERE ID=?",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setInt(2, tpr.get_id());
				ps.setString(1, ((TGame) tpr).get_gender());
				res = ps.executeUpdate();
			}

			if (res > 0) {
				ret = true;
			}

		}
		return ret;
	}

	public TProduct readProduct(Integer id, Integer lock) throws Exception {
		TProduct tp = null;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM producto WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			;
			if (lock == LockModeType.PESSIMISTIC) {
				ps = con.prepareStatement("SELECT * FROM producto WHERE ID=? FOR UPDATE",
						PreparedStatement.RETURN_GENERATED_KEYS);
				;
			}
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getString(12).equalsIgnoreCase(TProduct.game)) {
					tp = new TGame();
					((TGame) tp).set_gender(rs.getString(9));
					tp.set_type(TProduct.game);
				} else {
					tp = new TAccessory();
					((TAccessory) tp).set_brand(rs.getString(10));
					((TAccessory) tp).set_color(rs.getString(11));
					tp.set_type(TProduct.accessory);
				}

				tp.set_id(rs.getInt(1));
				tp.set_name(rs.getString(2));
				tp.set_description(rs.getString(3));
				tp.set_pvp(rs.getDouble(4));
				tp.set_stock(rs.getInt(5));
				tp.set_providerId(rs.getInt(6));
				tp.set_activated(rs.getBoolean(7));
				tp.set_unitsProvided(rs.getInt(8));
				tp.set_type(rs.getString(12));
			}

		}

		return tp;
	}

	public List<Object> readAllProducts(Integer lock) throws Exception {
		List<Object> l = new ArrayList<Object>();
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM producto",
					PreparedStatement.RETURN_GENERATED_KEYS);
			if (lock == LockModeType.PESSIMISTIC)
				ps = con.prepareStatement("SELECT * FROM producto FOR UPDATE", PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TProduct tp;
				if (rs.getString(12).equalsIgnoreCase(TProduct.game)) {
					tp = new TGame();
					((TGame) tp).set_gender(rs.getString(9));
					tp.set_type(TProduct.game);
				} else {
					tp = new TAccessory();
					((TAccessory) tp).set_brand(rs.getString(10));
					((TAccessory) tp).set_color(rs.getString(11));
					tp.set_type(TProduct.accessory);
				}

				tp.set_id(rs.getInt(1));
				tp.set_name(rs.getString(2));
				tp.set_description(rs.getString(3));
				tp.set_pvp(rs.getDouble(4));
				tp.set_stock(rs.getInt(5));
				tp.set_providerId(rs.getInt(6));
				tp.set_activated(rs.getBoolean(7));
				tp.set_unitsProvided(rs.getInt(8));
				tp.set_type(rs.getString(12));

				l.add(tp);
			}

		}
		return l;
	}

	public TProduct readProductByName(String name, Integer lock) throws Exception {
		TProduct tp = null;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps;
			ps = con.prepareStatement("SELECT ID FROM producto WHERE (nombre=?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			if (lock == LockModeType.PESSIMISTIC) {
				ps = con.prepareStatement("SELECT ID FROM producto WHERE (nombre=?) FOR UPDATE",
						PreparedStatement.RETURN_GENERATED_KEYS);
			}
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				tp = new TProduct();
				tp.set_id(rs.getInt(1));
			}

		}

		return tp;
	}
}