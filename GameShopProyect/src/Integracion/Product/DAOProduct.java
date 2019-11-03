package Integracion.Product;

import java.util.List;

import Transfers.TProduct;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface DAOProduct {
	
	public Integer createProduct(TProduct tpr);
	public Boolean deleteProduct(Integer id);
	public Boolean updateProduct(TProduct tpr);
	public TProduct readProduct(Integer id, Integer lock);
	public List<Object> readAllProducts(Integer lock);
	public TProduct readProductByName(String name, Integer lock);
	// METODO PARA DIFERENCIAR DE SI ES UN JUEGO O UN ACCESORIO
}