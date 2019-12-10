/**
 * 
 */
package Negocio.Transfers;

import java.util.List;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlo
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class TDepartamento {
	
	public TDepartamento(String nombre, Double fact, List<Integer> employees, Integer floor) {
		this.empleados = employees;
		this.factura = fact;
		this.nombre= nombre;
		this.planta = floor;
	}
	
	private Integer ID;
	private String nombre;
	private  List<Integer> empleados;
	private Double factura;
	private Integer planta;
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
	public List<Integer> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Integer> nEmpleados) {
		this.empleados = nEmpleados;
	}
	public Double getFactura() {
		return factura;
	}
	public void setFactura(Double factura) {
		this.factura = factura;
	}
	public Integer getPlanta() {
		return planta;
	}
	public void setPlanta(Integer planta) {
		this.planta = planta;
	}
	
	@Override
	public String toString() {
		return ("ID: " + ID + '\n' +
				"Activo: " + getActivo()+ '\n' +
				"Nombre: " + nombre + '\n' +
				"num Empleados: " + empleados.size() + '\n' +
				"Facturacion: " + factura + '\n' +
				"Planta: " + planta + '\n' +
				"empleados: " +'\n' + empleados);
				
	}
}