/**
 * 
 */
package Negocio.Departamento;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import Negocio.Transfers.TDepartamento;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author carlo
 * @generated "UML a JPA
 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Departamento.Departamento.findByid", query = "select obj from Departamento obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findBynombre", query = "select obj from Departamento obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findBynumEmpleados", query = "select obj from Departamento obj where :numEmpleados = obj.numEmpleados "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findByfacturacion", query = "select obj from Departamento obj where :facturacion = obj.facturacion "),
		@NamedQuery(name = "Negocio.Departamento.Departamento.findByplanta", query = "select obj from Departamento obj where :planta = obj.planta ") })
public class Departamento implements Serializable {
	
	private static final long serialVersionUID = 0;
	@Id
	private Integer id;
	private String nombre;
	private Integer numEmpleados;
	private Double facturacion;
	private Integer planta;

	public Departamento() {
	}

	public Departamento(Integer id)
	{
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
		return planta;
	}

	public void setPlanta(Integer planta) {
		this.planta = planta;
	}

	public TDepartamento toTransfer() {
		TDepartamento sol = new TDepartamento(this.getNombre(), this.getFacturacion(), this.getNumEmpleados(),
				this.getPlanta());
		sol.setID(this.getId());
		return sol;
	}
}