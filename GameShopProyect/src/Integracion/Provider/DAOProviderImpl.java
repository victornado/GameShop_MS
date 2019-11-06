package Integracion.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Integracion.Querys.LockModeType;
import Integracion.Transacciones.Transaction;
import Integracion.Transacciones.TransactionManager;
import Transfers.TProvider;

/**
 * @author GameShop
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class DAOProviderImpl implements DAOProvider {

	public Integer createProvider(TProvider tp) throws Exception {
		int id = -1;

		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;

		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO proveedor(direccion, NIF, telefono, activo) VALUES(?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tp.get_address());
			ps.setString(2, tp.get_nif());
			ps.setInt(3, tp.get_phoneNumber());
			ps.setBoolean(4, true);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		}

		return id;
	}

	public Boolean deleteProvider(TProvider tp) throws Exception {
		boolean ret = false;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("UPDATE proveedor SET activo=(?) WHERE ID=(?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, false);
			ps.setInt(2, tp.get_id());
			int res = ps.executeUpdate();

			if (res > 0) {
				ret = true;
			}
		}

		return ret;
	}

	public Boolean updateProvider(TProvider tp) throws Exception {

		boolean ret = false;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("UPDATE proveedor SET activo=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, tp.get_activated());
			ps.setInt(2, tp.get_id());
			int res = ps.executeUpdate();
			ps = con.prepareStatement("UPDATE proveedor SET NIF=? WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tp.get_nif());
			ps.setInt(2, tp.get_id());
			res = ps.executeUpdate();
			ps = con.prepareStatement("UPDATE proveedor SET direccion=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, tp.get_address());
			ps.setInt(2, tp.get_id());
			res = ps.executeUpdate();
			ps = con.prepareStatement("UPDATE proveedor SET telefono=? WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tp.get_phoneNumber());
			ps.setInt(2, tp.get_id());
			res = ps.executeUpdate();

			if (res > 0) {
				ret = true;
			}
		}

		return ret;
	}

	public Object readProvider(Integer id, Integer lock) throws Exception {
		TProvider tpl = null;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM proveedor WHERE ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			if (lock == LockModeType.PESSIMISTIC) {
				ps = con.prepareStatement("SELECT * FROM proveedor WHERE ID=? FOR UPDATE",
						PreparedStatement.RETURN_GENERATED_KEYS);
			}
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				tpl = new TProvider();
				tpl.set_id(rs.getInt(1));
				tpl.set_address(rs.getString(2));
				tpl.set_nif(rs.getString(3));
				tpl.set_phoneNumber(rs.getInt(4));
				tpl.set_activated(rs.getBoolean(5));
			}

		}

		return tpl;
	}

	public List<Object> readAllProviders(Integer lock) throws Exception  {
		List<Object> l =null;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			l= new ArrayList<Object>();
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM proveedor",
					PreparedStatement.RETURN_GENERATED_KEYS);
			if (lock == LockModeType.PESSIMISTIC)
				ps = con.prepareStatement("SELECT * FROM proveedor FOR UPDATE",
						PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TProvider tpl = new TProvider();
				tpl.set_id(rs.getInt(1));
				tpl.set_address(rs.getString(2));
				tpl.set_nif(rs.getString(3));
				tpl.set_phoneNumber(rs.getInt(4));
				tpl.set_activated(rs.getBoolean(5));
				l.add(tpl);
			}
		}
		return l;
	}

	public TProvider readProviderByNIF(String s, Integer lock) throws Exception {

		TProvider tpl = null;
		Transaction t = TransactionManager.getInstance().getTransaction();
		Connection con = null;
		if (t != null) {
			con = (Connection) t.getResource();
			PreparedStatement ps = con.prepareStatement("SELECT ID FROM proveedor WHERE NIF=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			if (lock == LockModeType.PESSIMISTIC) {
				ps = con.prepareStatement("SELECT ID FROM proveedor WHERE NIF=? FOR UPDATE",
						PreparedStatement.RETURN_GENERATED_KEYS);
			}
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			// ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				tpl = new TProvider();
				tpl.set_id(rs.getInt(1));
			}
		}

		return tpl;
	}
}