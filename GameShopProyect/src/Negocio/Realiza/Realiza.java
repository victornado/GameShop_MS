package Negocio.Realiza;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import Negocio.Conferencia.Conferencia;
import Negocio.Empleado.Empleado;

import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "Realiza.Realiza.findByids", query = "select obj from Realiza obj where :ids = obj.ids "),
		@NamedQuery(name = "Realiza.Realiza.findByduracion", query = "select obj from Realiza obj where :duracion = obj.duracion "),
		@NamedQuery(name = "Realiza.Realiza.findByconferencia", query = "select obj from Realiza obj where :conferencia = obj.conferencia "),
		@NamedQuery(name = "Realiza.Realiza.findByempleado", query = "select obj from Realiza obj where :empleado = obj.empleado "),
		@NamedQuery(name = "Realiza.Realiza.findByversion", query = "select obj from Realiza obj where :version = obj.version ")})
public class Realiza implements Serializable {
	private static final long serialVersionUID = 0;
	
	@EmbeddedId
	private RealizaEmbeddable ids;
	private Integer duracion;
	@ManyToOne
	private Conferencia conferencia;
	@ManyToOne
	private Empleado empleado;
	@Version
	private Integer version;

	public Realiza() {
	}
	
	public RealizaEmbeddable getIds() {
		return ids;
	}
	
	public void setIds(RealizaEmbeddable ids) {
		this.ids = ids;
	}
	
	public Integer getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Conferencia getConferencia() {
		return conferencia;
	}

	public void setConferencia(Conferencia conferencia) {
		this.conferencia = conferencia;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}