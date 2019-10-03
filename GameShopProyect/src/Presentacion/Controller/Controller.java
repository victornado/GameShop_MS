package Presentacion.Controller;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public abstract class Controller {

	private static Controller instance;

	public static Controller getInstance() {
		if(instance == null)
			instance = new ControllerImpl();
		return instance;
	}
	
	public abstract void action(Object data, Integer event);
}