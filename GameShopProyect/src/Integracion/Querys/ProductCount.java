package Integracion.Querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class ProductCount implements Query {

	@Override
	public Object execute(Object data, Integer lock) throws Exception {
		List<Pair<String,Integer>> sol = null;
		String queryString = "SELECT p.tipo,  COUNT(*) AS numero " + 
							"FROM producto p " +  
							"GROUP BY p.tipo " ;		
		if(lock == LockModeType.PESSIMISTIC)
		{
			queryString = "SELECT p.tipo,  COUNT(*) AS numero " + 
					"FROM producto p " +  
					"GROUP BY p.tipo FOR UPDATE" ;
		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database,
				Main.Main.user, Main.Main.password);
		PreparedStatement ps = con.prepareStatement(queryString, PreparedStatement.RETURN_GENERATED_KEYS);
		ResultSet rs = ps.executeQuery();
		sol = new ArrayList<Pair<String,Integer>>();
		while (rs.next()) {
			Pair<String,Integer> aux= new Pair<String,Integer>(rs.getString(1),rs.getInt(2));
			sol.add(aux);
		}
		con.close();
		return sol;

	}
	

}
