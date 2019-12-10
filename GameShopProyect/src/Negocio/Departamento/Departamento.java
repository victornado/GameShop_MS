package Negocio.Departamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import Negocio.Empleado.Empleado;
import Negocio.Transfers.TDepartamento;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Departamento.Departamento.findByid", query = "select obj from Departamento obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findBynombre", query = "select obj from Departamento obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findByfacturacion", query = "select obj from Departamento obj where :facturacion = obj.facturacion "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findBynumPlanta", query = "select obj from Departamento obj where :numPlanta = obj.numPlanta ") })
public class Departamento implements Serializable {
	private static final long serialVersionUID = 0;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Double facturacion;
	private Integer numPlanta;
	@OneToMany (mappedBy="depto") // Está en el lado 1 y el poseedor en el lado N
	private Collection<Empleado> empleados;
	private Boolean activo;
	@Version
	private Integer version;

	public Departamento() {
		this.activo = true;
	}

	public Departamento(Integer id){
		this.activo = true;
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
		List<Integer> listIds=new ArrayList<Integer>();
		for (Empleado i : empleados) {
			listIds.add(i.getId());
		}
		TDepartamento sol = new TDepartamento(this.getNombre(), this.getFacturacion(), listIds,
				this.getPlanta());
		sol.setID(id);
		sol.setActivo(getActivo());
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

	public Collection<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Collection<Empleado> empleados) {
		this.empleados = empleados;
	}
}