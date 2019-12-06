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
	private Object especialidad;
	private Object sobresueldo;
	
	public Tecnico() {
	}

	public Object getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Object especialidad) {
		this.especialidad = especialidad;
	}
	public Object getSobresueldo() {
		return sobresueldo;
	}
	public void setSobresueldo(Object sobresueldo) {
		this.sobresueldo = sobresueldo;
	}
}