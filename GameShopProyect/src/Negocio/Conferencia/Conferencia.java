package Negocio.Conferencia;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Version;

import Negocio.Realiza.Realiza;
import Negocio.Transfers.TConferencia;
import Negocio.Transfers.TRealiza;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByid", query = "select obj from Conferencia obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByasistentes", query = "select obj from Conferencia obj where :asistentes = obj.asistentes "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findBytematica", query = "select obj from Conferencia obj where :tematica = obj.tematica "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findBynombre", query = "select obj from Conferencia obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByfecha", query = "select obj from Conferencia obj where :fecha = obj.fecha "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByrealiza", query = "select obj from Conferencia obj where :realiza MEMBER OF obj.realiza "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByversion", query = "select obj from Conferencia obj where :version = obj.version ")})
public class Conferencia implements Serializable {

	private static final long serialVersionUID = 0;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer asistentes;
	private String tematica;
	private String nombre;
	private Timestamp fecha;
	private Boolean activo;
	@Version
	private Integer version;
	@OneToMany(mappedBy = "conferencia")
	private Set<Realiza> realiza;
	
	
	public Conferencia() {
		this.activo = true;
	}
	
	public Conferencia(Integer id){
		this.activo = true;
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
		TConferencia sol = new TConferencia(nombre, tematica, asistentes, fecha);
		sol.setID(id);
		List<TRealiza>aux= new ArrayList<TRealiza>();
		for (Realiza t : realiza) {
			aux.add(t.toTransfer());
		}
		sol.setEmpleadosEnConferencias(aux);
		sol.setActivo(getActivo());
		return sol;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Set<Realiza> getRealiza() {
		return realiza;
	}
	
	public void setRealiza(Set<Realiza> realiza) {
		this.realiza = realiza;
	}
	
}