package Negocio.Transfers;

import java.sql.Timestamp;

public class TConferencia {
	
	private Integer ID;
	private Integer asistentes;
	private Timestamp fecha;
	private String tematica;
	private String nombre;
	private Boolean activo;
	
	public TConferencia(String n, String t, Integer a, Timestamp d) {
		nombre = n;
		tematica = t;
		asistentes = a;
		fecha = d;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getID() {
		return ID;
	}
	public void setID(Integer ID) {
		this.ID = ID;
	}
	
	public Timestamp getDate() {
		return fecha;
	}

	public void setDate(Timestamp fecha) {
		this.fecha = fecha;
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

	public Integer getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(Integer asistentes) {
		this.asistentes = asistentes;
	}
}