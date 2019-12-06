package Negocio.SA;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Negocio.Conferencia.SAConferencia;
import Negocio.Conferencia.SAConferenciaImp;
import Negocio.Departamento.SADepartamento;
import Negocio.Departamento.SADepartamentoIMP;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.SAEmpleadoIMP;
import Negocio.Product.SAProduct;
import Negocio.Product.SAProductImpl;
import Negocio.Provider.SAProvider;
import Negocio.Provider.SAProviderImpl;
import Negocio.Ticket.SATicket;
import Negocio.Ticket.SATicketImpl;

/** 
 * @author GameShop
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SAAbstractFactoryImpl extends SAAbstractFactory {

	public SAProduct createSAProduct() {
		return new SAProductImpl();
	}

	public SAProvider createSAProvider() {
		return new SAProviderImpl();
	}

	public SATicket createSATicket() {
		return new SATicketImpl();
	}

	@Override
	public SAConferencia createSAConferencia() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		emf.close();
		return new SAConferenciaImp(em);
	}

	@Override
	public SADepartamento createSADepartamento() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		emf.close();
		return new SADepartamentoIMP(em);
	}

	@Override
	public SAEmpleado createSAEmpleado() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		emf.close();
		return new SAEmpleadoIMP(em);
	}

	
	
}