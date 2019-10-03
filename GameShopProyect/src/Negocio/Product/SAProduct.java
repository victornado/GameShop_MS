package Negocio.Product;

import java.util.List;

import Transfers.TProduct;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface SAProduct {
	
	public Integer createProduct(TProduct tpr);
	public Boolean deleteProduct(Integer id);
	public Boolean updateProduct(TProduct tpr);
	public Object readProduct(Integer id);
	public List<Object> readAllProducts();
}