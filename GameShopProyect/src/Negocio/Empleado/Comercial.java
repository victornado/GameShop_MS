package Negocio.Empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;

import Negocio.Transfers.TComercial;
import Negocio.Transfers.TEmpleado;

@Entity
@NamedQuery(name = "Negocio.Empleado.Comercial.findBynVentas", query = "select obj from Comercial obj where :nVentas = obj.nVentas ")
public class Comercial extends Empleado implements Serializable {

	private static final long serialVersionUID = 0;
	
	private Integer nVentas;

	public Comercial() {
	}

	public Integer getnVentas() {
		return nVentas;
	}
	public void setnVentas(Integer nVentas) {
		this.nVentas = nVentas;
	}
	@Override
	public Double calcularSueldo() {
		Double ret = 0.0;
		ret += super.getSueldoBase() + (nVentas * TComercial.COBRO_POR_VENTA);
		return ret;
	}
	@Override
	public TEmpleado toTransfer() {
		TEmpleado ret = new TComercial(super.getNIF(), super.getNombre(), super.getTurno(), super.getSueldoBase(),
				null, nVentas, Empleado.Comercial);
		if(super.getDepartamento() != null)
			ret.setDepartamento(super.getDepartamento().getId());
		ret.setID(super.getId());
		ret.setActivo(getActivo());
		return ret;
	}
}