package Negocio.Conferencia;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.sql.Timestamp;
import Negocio.Transfers.TConferencia;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByid", query = "select obj from Conferencia obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByasistentes", query = "select obj from Conferencia obj where :asistentes = obj.asistentes "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findBytematica", query = "select obj from Conferencia obj where :tematica = obj.tematica "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findBynombre", query = "select obj from Conferencia obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.Conferencia.Conferencia.findByfecha", query = "select obj from Conferencia obj where :fecha = obj.fecha ") })
public class Conferencia implements Serializable {

	private static final long serialVersionUID = 0;

	public Conferencia() {
	}

	@Id
	private Integer id;

	/**
	 * @return the id
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * 
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private Integer asistentes;

	/**
	 * @return the asistentes
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Integer getAsistentes() {
		// begin-user-code
		return asistentes;
		// end-user-code
	}

	/**
	 * @param asistentes the asistentes to set
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setAsistentes(Integer asistentes) {
		// begin-user-code
		this.asistentes = asistentes;
		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * 
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private String tematica;

	/**
	 * @return the tematica
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String getTematica() {
		// begin-user-code
		return tematica;
		// end-user-code
	}

	/**
	 * @param tematica the tematica to set
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setTematica(String tematica) {
		// begin-user-code
		this.tematica = tematica;
		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * 
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private String nombre;

	/**
	 * @return the nombre
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public String getNombre() {
		// begin-user-code
		return nombre;
		// end-user-code
	}

	/**
	 * @param nombre the nombre to set
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setNombre(String nombre) {
		// begin-user-code
		this.nombre = nombre;
		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * 
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	private Timestamp fecha;

	/**
	 * @return the fecha
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public Timestamp getFecha() {
		// begin-user-code
		return fecha;
		// end-user-code
	}

	/**
	 * @param fecha the fecha to set
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public void setFecha(Timestamp fecha) {
		// begin-user-code
		this.fecha = fecha;
		// end-user-code
	}

	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * 
	 * @return
	 * @generated "UML a JPA
	 *            (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	 */
	public TConferencia toTransfer() {
		TConferencia sol = new TConferencia(this.getNombre(), this.getTematica(), this.getAsistentes(),
				this.getFecha());
		sol.setID(this.getId());
		return sol;
	}
}