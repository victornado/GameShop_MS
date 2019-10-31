package Integracion.Querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javafx.util.Pair;

public class GetInfoFromTicket implements Query {

	@Override
	public Object execute(Object data) throws Exception {
		HashMap<Integer, Pair<String, Integer>> info = null;
		try {
			String queryString = "SELECT p.ID, p.nombre, a.cantidad "
					  + "FROM producto p JOIN asociado a "
					  + "ON p.ID=a.IDProducto "
					  + "WHERE a.IDTicket = ? ";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
			PreparedStatement ps = con.prepareStatement(queryString, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, (Integer)data);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				info = new HashMap<Integer, Pair<String, Integer>>();
				Pair<String, Integer> p = new Pair<String, Integer>(rs.getString(2), rs.getInt(3));
				info.put(rs.getInt(1), p);
			}
			con.close();
		} catch(Exception e) {
			info = null;
		}
		
		return info;
	}

}
