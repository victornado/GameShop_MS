package Negocio.Transfers;

import java.util.ArrayList;
import java.util.List;

public class TEmpleado {
	
	private Integer ID;
	private String NIF;
	private String nombre;
	private String turno;
	private Double sueldobase;
	private Integer dep;
	private String tipo;
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
	
	private List<TRealiza> conferenciasAlasQuePertenece = new ArrayList<TRealiza>();
	
	public TEmpleado(String NIF, String nombre, String turno, Double sueldoBase, Integer dept, String tipo) {
		this.NIF = NIF;
		this.nombre = nombre;
		this.turno = turno;
		this.sueldobase = sueldoBase;
		this.dep = dept;
		this.tipo = tipo;
	}
	
	public String getNIF() {
		return NIF;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
	}

	public Integer getID() {
		return ID;
	}
	
	public void setID(Integer ID) {
		this.ID = ID;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	public Double getSueldobase() {
		return sueldobase;
	}

	public void setSueldobase(Double sueldobase) {
		this.sueldobase = sueldobase;
	}
	public void setDepartamento(Integer depto){
		this.dep = depto;
	}
	public Integer getDepartamento(){
		return dep;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}