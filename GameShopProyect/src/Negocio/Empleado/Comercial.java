package Negocio.Empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Negocio.Empleado.Comercial.findBynVentas", query = "select obj from Comercial obj where :nVentas = obj.nVentas ")
public class Comercial extends Empleado implements Serializable {

	private static final long serialVersionUID = 0;

	public Comercial() {
	}
	private Integer nVentas;

	public Integer getnVentas() {
		return nVentas;
	}
	public void setnVentas(Integer nVentas) {
		this.nVentas = nVentas;
	}
}