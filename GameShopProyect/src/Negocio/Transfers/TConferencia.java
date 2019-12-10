package Negocio.Transfers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TConferencia {
	
	private Integer ID = null;
	private Integer asistentes;
	private Timestamp fecha;
	private String tematica;
	private String nombre;
	private Integer version;
	private Boolean activo;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	// Lista de TRealiza que contiene los empleados que van a hacer es conferencia
	private List<TRealiza> empleadosEnConferencias = new ArrayList<TRealiza>();
	
	private String stringFecha; // String para almacenar la fecha en fomrato string y ver en el SA que es un formate de fecha correcto
	
	public TConferencia(String n, String t, Integer a, Timestamp d) {
		nombre = n;
		tematica = t;
		asistentes = a;
		fecha = d;
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
	
	public Timestamp toTimestamp(String timeData) {
		return Timestamp.valueOf(timeData);
	}
	
	public String getStringFecha() {
		return stringFecha;
	}

	public void setStringFecha(String stringFecha) {
		this.stringFecha = stringFecha;
	}

	public List<TRealiza> getEmpleadosEnConferencias() {
		return empleadosEnConferencias;
	}
	
	public void addEmployeeToConference(Integer idEmp, Integer duracion) {
		TRealiza in = new TRealiza(idEmp, ID, duracion);
		empleadosEnConferencias.add(in);
	}

	public void setEmpleadosEnConferencias(List<TRealiza> empleadosEnConferencias) {
		this.empleadosEnConferencias = empleadosEnConferencias;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder('\n');
		for(TRealiza tr : empleadosEnConferencias)
			str.append('\t' + tr.getIdEmp() + " - " + tr.getDuracion() + " mins" + '\n');
			
		return ("ID: " + ID + '\n' +
				"Nombre: " + nombre + '\n' +
				"Tematica: " + tematica + '\n' +
				"Fecha: " + fecha.toString() + '\n' +
				"Asistentes: " + asistentes + '\n' +
				"Empleados: " + str.toString());
	}
}