package Integracion.Querys;

import java.sql.Timestamp;

import Transfers.TProduct;

/*
 * QUERY PARA MOSTRAR TODOS LOS PRODUCTOS QUE HAN SIDO VENDIDOS EN UN RANGO DE FECHAS
*/
public class ProductsSoldDateRange implements Query {
	
	private String _queryString;
	private TProduct _data;		// TOA ?????????
	private Timestamp _from;
	private Timestamp _to;
	
	public ProductsSoldDateRange(Object data, Timestamp from, Timestamp to) {
		_data = (TProduct)data;
		_from = from;
		_to = to;
		_queryString = "";
	}

	@Override
	public Object execute(Object data) throws Exception {
		return null;
	}

}
