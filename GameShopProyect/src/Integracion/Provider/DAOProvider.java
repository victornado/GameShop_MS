package Integracion.Provider;

import java.util.List;

import Transfers.TProvider;

/** 
* @author GameShop
* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface DAOProvider {

	public Integer createProvider(TProvider tp) throws Exception;
	public Boolean deleteProvider(TProvider tp) throws Exception;
	public Boolean updateProvider(TProvider tp) throws Exception;
	public Object readProvider(Integer id, Integer lock) throws Exception;
	public List<Object> readAllProviders(Integer lock) throws Exception;
	public Object readProviderByNIF(String s, Integer lock) throws Exception;
}