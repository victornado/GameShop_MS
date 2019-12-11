package Negocio.Empleado;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import javax.persistence.NamedQueries;

import Negocio.Departamento.Departamento;
import Negocio.Realiza.Realiza;
import Negocio.Transfers.TEmpleado;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByid", query = "select obj from Empleado obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByNIF", query = "select obj from Empleado obj where :NIF = obj.NIF "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findBynombre", query = "select obj from Empleado obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findBysueldoBase", query = "select obj from Empleado obj where :sueldoBase = obj.sueldoBase "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByturno", query = "select obj from Empleado obj where :turno = obj.turno "),//{)
		@NamedQuery(name = "Negocio.Empleado.Empleado.finBydepto", query = "select obj from Empleado obj where :depto = obj.depto "),
		@NamedQuery(name = "Negocio.Empleado.Empleado.findByrealiza", query = "select obj from Empleado obj where :realiza MEMBER OF obj.realiza ")})
@Inheritance(strategy=InheritanceType.JOINED) // CREAMOS 3 TABLAS, UNA PARA CADA ENTIDAD
public abstract class Empleado implements Serializable {
	private static final long serialVersionUID = 0;
	
	public static final String Comercial = "Comercial";
	public static final String Tecnico = "Tecnico";
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String NIF;
	private String nombre;
	private Double sueldoBase;
	private String turno;
	@ManyToOne
	private Departamento depto;
	private Boolean activo;
	@Version
	private Integer version;
	@OneToMany(mappedBy = "empleado")
	private List<Realiza> realiza;

	public Empleado() {
		this.activo = true;
	}

	public Empleado(Integer id){
		this.id=id;
		this.activo = true;
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
	public void setDepartamento(Departamento depto){
		this.depto = depto;
	}
	public Departamento getDepartamento(){
		return depto;
	}
	public abstract Double calcularSueldo();
	public abstract TEmpleado toTransfer();
	
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
	public List<Realiza> getRealiza() {
		return realiza;
	}
	public void setRealiza(List<Realiza> realiza) {
		this.realiza = realiza;
	}
}