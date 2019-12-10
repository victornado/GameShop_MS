package Integracion.Querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utils.Pair;



public class GetBestProductSelled implements Query {

	@Override
	public Object execute(Object data, Integer lock) throws Exception {
		@SuppressWarnings("unchecked")
		//Pair<Timestamp,Timestamp>dates=(Pair<Timestamp,Timestamp>)data;
		Pair<String, String> dates = (Pair<String, String>)data;
		String dateFrom = "SELECT from_unixtime(unix_timestamp('" + dates.getKey() + "'))";
		String dateTo = "SELECT from_unixtime(unix_timestamp('" + dates.getValue() + "'))";
		List<Object[]> sol = null;
		String queryString = "SELECT p.nombre, a.cantidad, t.fecha "
				  + "FROM producto p JOIN asociado a "
				  + "ON p.ID=a.IDProducto "
				  + "JOIN ticket t "
				  + "ON t.ID=a.IDTicket "
				  + "WHERE t.fecha BETWEEN (?) AND (?)";
		if(lock == LockModeType.PESSIMISTIC)
		{
			queryString = "SELECT p.nombre, a.cantidad, t.fecha "
					  + "FROM producto p JOIN asociado a "
					  + "ON p.ID=a.IDProducto "
					  + "JOIN ticket t "
					  + "ON t.ID=a.IDTicket "
					  //+ "WHERE t.fecha BETWEEN (?) AND (?) FOR UPDATE";
					  + "WHERE t.fecha BETWEEN (" + dateFrom + ") AND (" + dateTo + ") FOR UPDATE";
		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database, Main.Main.user, Main.Main.password);
		PreparedStatement ps = con.prepareStatement(queryString, PreparedStatement.RETURN_GENERATED_KEYS);
		//ps.setTimestamp(1, dates.getKey());
		//ps.setTimestamp(2, dates.getValue());
		ResultSet rs = ps.executeQuery();
		sol = new ArrayList<Object[]>();
		while(rs.next()) {
			Object aux[] = {rs.getObject(1),rs.getObject(2),rs.getObject(3)};//,rs.getObject(4)};
			sol.add(aux);
		}
		con.close();
	
		return sol;
	}
}
