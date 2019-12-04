package Negocio.Conferencia;

import Negocio.Transfers.TConferencia;
import Negocio.Transfers.TProvider;
import java.util.List;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author carlo
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public interface SAConferencia {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param data
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer registrarConferencia(TConferencia data);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Boolean eliminarConferencia(Integer id);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param data
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Boolean modificarConferencia(TProvider data);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Object mostrarConferencia(Integer id);

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public List mostrarTodasLasConferencias();
}