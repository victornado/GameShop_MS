package Negocio.Empleado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import Negocio.Departamento.Departamento;
import Negocio.Transfers.TEmpleado;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByid", query = "select obj from Empleado obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByNIF", query = "select obj from Empleado obj where :NIF = obj.NIF "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findBynombre", query = "select obj from Empleado obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findBysueldoBase", query = "select obj from Empleado obj where :sueldoBase = obj.sueldoBase "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByturno", query = "select obj from Empleado obj where :turno = obj.turno "),//{)
		@NamedQuery(name = "Negocio.Empleado.Empleado.finBydepto", query = "select obj from Empleado obj where :depto = obj.depto ") })
@Inheritance(strategy=InheritanceType.JOINED) // CREAMOS 3 TABLAS, UNA PARA CADA ENTIDAD
public abstract class Empleado implements Serializable {
	private static final long serialVersionUID = 0;
	
	public static final String Comercial = "Comercial";
	public static final String Tecnico = "Technician";
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String NIF;
	private String nombre;
	private Double sueldoBase;
	private String turno;
	private String tipo;
	@ManyToOne
	private Departamento depto;

	public Empleado() {}

	public Empleado(Integer id){
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setDepartamento(Departamento depto){
		this.depto = depto;
	}
	public Departamento getDepartamento(){
		return depto;
	}
	public abstract Double calcularSueldo();
	public abstract TEmpleado toTransfer();
}