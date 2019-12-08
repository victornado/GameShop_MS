/**
 * 
 */
package Negocio.Empleado;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;
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
}