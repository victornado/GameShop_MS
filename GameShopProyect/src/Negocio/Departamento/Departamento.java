package Negocio.Departamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.persistence.NamedQueries;

import Negocio.Empleado.Empleado;
import Negocio.Transfers.TDepartamento;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Departamento.Departamento.findByid", query = "select obj from Departamento obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findBynombre", query = "select obj from Departamento obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findBynumEmpleados", query = "select obj from Departamento obj where :numEmpleados = obj.numEmpleados "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findByfacturacion", query = "select obj from Departamento obj where :facturacion = obj.facturacion "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findBynumPlanta", query = "select obj from Departamento obj where :numPlanta = obj.numPlanta ") })
public class Departamento implements Serializable {
	private static final long serialVersionUID = 0;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Integer numEmpleados;
	private Double facturacion;
	private Integer numPlanta;
	@OneToMany (mappedBy="depto") // Está en el lado 1 y el poseedor en el lado N
	private Collection<Empleado> empleados;
	private Boolean activo;
	@Version
	private Integer version;

	public Departamento() {
	}

	public Departamento(Integer id){
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getNumEmpleados() {
		return numEmpleados;
	}

	public void setNumEmpleados(Integer numEmpleados) {
		this.numEmpleados = numEmpleados;
	}
	
	public Double getFacturacion() {
		return facturacion;
	}
	public void setFacturacion(Double facturacion) {
		this.facturacion = facturacion;
	}
	
	public Integer getPlanta() {
		return numPlanta;
	}

	public void setPlanta(Integer planta) {
		this.numPlanta = planta;
	}

	public TDepartamento toTransfer() {
		TDepartamento sol = new TDepartamento(this.getNombre(), this.getFacturacion(), this.getNumEmpleados(),
				this.getPlanta());
		sol.setID(id);
		return sol;
	}
	@Override
	public String toString() {
		return nombre;
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
}