package Negocio.Transfers;

import Negocio.Departamento.Departamento;

public class TEmpleado {
	
	private Integer ID;
	private String NIF;
	private String nombre;
	private String turno;
	private Double sueldobase;
	private String tipo;
	private Integer dep;
	
	public TEmpleado(String NIF, String nombre, String turno, Double sueldoBase, String tipo, Integer dept) {
		this.NIF = NIF;
		this.nombre = nombre;
		this.turno = turno;
		this.sueldobase = sueldoBase;
		this.tipo = tipo;
		this.dep = dept;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setDepartamento(Integer depto){
		this.dep = depto;
	}
	public Integer getDepartamento(){
		return dep;
	}
}