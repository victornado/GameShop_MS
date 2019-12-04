/**
 * 
 */
package modelodediseño.Presentacion.Empleado;

import modelodediseño.Presentacion.View.ShowPanel;
import modelodediseño.Presentacion.View.OperationsPanel;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author carlo
* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
*/
public class GUIEmployeeImp extends GUIEmployee {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private ShowPanel showPanel;

	/** 
	* @return the showPanel
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public ShowPanel getShowPanel() {
		// begin-user-code
		return showPanel;
		// end-user-code
	}

	/** 
	* @param showPanel the showPanel to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setShowPanel(ShowPanel showPanel) {
		// begin-user-code
		this.showPanel = showPanel;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	private OperationsPanel operationsPanel;

	/** 
	* @return the operationsPanel
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public OperationsPanel getOperationsPanel() {
		// begin-user-code
		return operationsPanel;
		// end-user-code
	}

	/** 
	* @param operationsPanel the operationsPanel to set
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setOperationsPanel(OperationsPanel operationsPanel) {
		// begin-user-code
		this.operationsPanel = operationsPanel;
		// end-user-code
	}
}