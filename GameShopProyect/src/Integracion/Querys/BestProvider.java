package Integracion.Querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BestProvider implements Query {

	@Override
	public Object execute(Object data, Integer lock) throws Exception {
		List<Object> sol = null;
		String queryString = "SELECT p.nombre, pr.NIF, SUM(p.unidadesProv) AS unidades " + 
							"FROM producto p JOIN proveedor pr " + 
							"ON p.IDProveedor=pr.ID " + 
							"GROUP BY pr.NIF " + 
							"HAVING unidades >= ALL (SELECT SUM(unidadesProv) " + 
													"FROM producto " + 
													"GROUP BY IDProveedor) ";
		if(lock == LockModeType.PESSIMISTIC)
		{
			queryString = "SELECT p.nombre, pr.NIF, SUM(p.unidadesProv) AS unidades " + 
					"FROM producto p JOIN proveedor pr " + 
					"ON p.IDProveedor=pr.ID " + 
					"GROUP BY pr.NIF " + 
					"HAVING unidades >= ALL (SELECT SUM(unidadesProv) " + 
											"FROM producto " + 
											"GROUP BY IDProveedor) FOR UPDATE";
		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Main.Main.database,
				Main.Main.user, Main.Main.password);
		PreparedStatement ps = con.prepareStatement(queryString, PreparedStatement.RETURN_GENERATED_KEYS);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			sol = new ArrayList<Object>();
			sol.add(rs.getObject(1));
			sol.add(rs.getObject(2));
			sol.add(rs.getObject(3));
		}
		con.close();
		return sol;

	}

}
