package Integracion.Querys;

import java.util.HashMap;

import javafx.util.Pair;

public class GetInfoFromTicket implements Query {

	@Override
	public Object execute(Object data) throws Exception {
		// Crear objetos de la BD y hacer consulta y lo que devuelva guardarlo en HashMap<IDProd, Pair<NombreProd, CantidadProd>>
		//
		HashMap<Integer, Pair<String, Integer>> info = null;
		String qeryString = "SELECT p.ID, p.nombre, a.cantidad"
						  + "FROM producto p JOIN asociado a"
						  + "USING p.ID"
						  + "WHERE a.IDTicket = " + (Integer)data;
		// Meter la consulta en los objetos de conexion y rellenar los Pair y el HashMap
		
		return info;
	}

}
