/**
 * 
 */
package Negocio.Transfers;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author carlo
 * @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
 */
public class TDepartamento {
	
	public TDepartamento(String nombre, Double fact, Integer numEmployees, Integer floor) {
		this.nEmpleados = numEmployees;
		this.factura = fact;
		this.nombre= nombre;
		this.planta = floor;
	}
	
	private Integer ID;
	private String nombre;
	private Integer nEmpleados;
	private Double factura;
	private Integer planta;


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
	public Integer getnEmpleados() {
		return nEmpleados;
	}
	public void setnEmpleados(Integer nEmpleados) {
		this.nEmpleados = nEmpleados;
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
				"Nombre: " + nombre + '\n' +
				"Empleados: " + nEmpleados + '\n' +
				"Facturacion: " + factura + '\n' +
				"Planta: " + planta + '\n');
	}
}