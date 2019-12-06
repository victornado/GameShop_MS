/**
 * 
 */
package Negocio.Empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import Negocio.Transfers.TEmpleado;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByid", query = "select obj from Empleado obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByNIF", query = "select obj from Empleado obj where :NIF = obj.NIF "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findBynombre", query = "select obj from Empleado obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findBysueldoBase", query = "select obj from Empleado obj where :sueldoBase = obj.sueldoBase "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByturno", query = "select obj from Empleado obj where :turno = obj.turno ") })
public class Empleado implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	@Id
	private Integer id;
	private String NIF;
	private String nombre;
	private Double sueldoBase;
	private String turno;
	

	public Empleado() {
	}

	public Empleado(Integer id)
	{
		this.id=id;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNIF() {
		return NIF;
	}
	public void setNIF(String NIF) {
		this.NIF = NIF;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getSueldoBase() {
		return sueldoBase;
	}
	public void setSueldoBase(Double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public Double calcularSueldo() {
		// TODO Auto-generated method stub
		return null;
	}
	public TEmpleado toTransfer() {
		// TODO Auto-generated method stub
		return null;
	}
}