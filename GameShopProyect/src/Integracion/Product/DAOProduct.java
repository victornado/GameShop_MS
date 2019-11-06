package Integracion.Product;

import java.util.List;

import Transfers.TProduct;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface DAOProduct {
	
	public Integer createProduct(TProduct tpr) throws Exception;
	public Boolean deleteProduct(Integer id) throws Exception;
	public Boolean updateProduct(TProduct tpr) throws Exception;
	public TProduct readProduct(Integer id, Integer lock) throws Exception;
	public List<Object> readAllProducts(Integer lock) throws Exception;
	public TProduct readProductByName(String name, Integer lock) throws Exception;
	// METODO PARA DIFERENCIAR DE SI ES UN JUEGO O UN ACCESORIO
}