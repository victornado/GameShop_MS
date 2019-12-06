package Negocio.Conferencia;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.sql.Timestamp;
import Negocio.Transfers.TConferencia;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByid", query = "select obj from Conferencia obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByasistentes", query = "select obj from Conferencia obj where :asistentes = obj.asistentes "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findBytematica", query = "select obj from Conferencia obj where :tematica = obj.tematica "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findBynombre", query = "select obj from Conferencia obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByfecha", query = "select obj from Conferencia obj where :fecha = obj.fecha ") })
public class Conferencia implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer asistentes; //sql numero < 9999
	private String tematica;
	private String nombre;
	private Timestamp fecha;
	
	
	public Conferencia() {
	}
	
	public Conferencia(Integer id)
	{
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAsistentes() {
		return asistentes;
	}
	public void setAsistentes(Integer asistentes) {
		this.asistentes = asistentes;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public TConferencia toTransfer() {
		TConferencia sol = new TConferencia(this.getNombre(), this.getTematica(), this.getAsistentes(),
				this.getFecha());
		sol.setID(this.getId());
		return sol;
	}
}