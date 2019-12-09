package Negocio.Empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;

import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TTecnico;

import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleado.Tecnico.findByespecialidad", query = "select obj from Tecnico obj where :especialidad = obj.especialidad "),
		@NamedQuery(name = "Negocio.Empleado.Tecnico.findBysobresueldo", query = "select obj from Tecnico obj where :sobresueldo = obj.sobresueldo ") })
public class Tecnico extends Empleado implements Serializable {
	
	private static final long serialVersionUID = 0;
	private String especialidad;
	private Double sobresueldo;
	
	public Tecnico() {
	}

	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public Double getSobresueldo() {
		return sobresueldo;
	}
	public void setSobresueldo(Double sobresueldo) {
		this.sobresueldo = sobresueldo;
	}

	@Override
	public Double calcularSueldo() {
		return super.getSueldoBase() + sobresueldo;
	}

	@Override
	public TEmpleado toTransfer() {
		TEmpleado ret = new TTecnico(super.getNIF(), super.getNombre(), super.getTurno(), super.getSueldoBase(),
				null, sobresueldo, especialidad, Empleado.Tecnico);
		if(super.getDepartamento() != null)
			ret.setDepartamento(super.getDepartamento().getId());
		ret.setID(super.getId());
		return ret;
	}
}