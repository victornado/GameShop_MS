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
	public TProduct readProduct(Integer id);
	public List<Object> readAllProducts();
	public TProduct readProductByNameAndPlatform(String name, Integer idp);
	// METODO PARA DIFERENCIAR DE SI ES UN JUEGO O UN ACCESORIO
}