package Negocio.Conferencia;

import Negocio.Transfers.TConferencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SAConferenciaImp implements SAConferencia {
	
	public Integer registrarConferencia(TConferencia data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Conferencia con = new Conferencia();
		
		em.getTransaction().begin();
		con.setNombre(data.getNombre());
		con.setAsistentes(data.getAsistentes());
		con.setFecha(data.getDate());
		con.setTematica(data.getTematica());
		
		em.persist(con);
		em.getTransaction().commit();
		
		Integer id = con.getId();
		
		em.close();
		emf.close();
		
		return id;
	}

	public Boolean eliminarConferencia(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Boolean ret = false;
		
		em.getTransaction().begin();
		
		Conferencia con = em.find(Conferencia.class, id);
		if(con != null) {
			em.remove(con);
			ret = true;
		}		
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}

	public Boolean modificarConferencia(TConferencia data2) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Boolean ret = false;
		
		// PROVISIONAL PARA PRUEBAS ==> Funciona PERO CAMBIAR EL COMANDO
		Timestamp fecha = Timestamp.valueOf("2019-12-15 14:15:00");
		TConferencia data = new TConferencia("Changes", "Gender", 300, fecha);
		data.setID(5);
		
		em.getTransaction().begin();
		
		Conferencia con = em.find(Conferencia.class, data.getID());
		if(con != null){
			con.setNombre(data.getNombre());
			con.setTematica(data.getTematica());
			con.setAsistentes(data.getAsistentes());
			con.setFecha(data.getDate());
			ret = true;
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}

	public Object mostrarConferencia(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		TConferencia ret = null;
		
		em.getTransaction().begin();
		
		Conferencia con = em.find(Conferencia.class, 5);
		
		if(con!=null)
			ret = con.toTransfer();
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}

	public List<Object> mostrarTodasLasConferencias() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		List<Object> ret = null;
		
		em.getTransaction().begin();
		
		// Para JPQL si Conferencia es una Entity hay que hacer "createQuery" pero si no fuere una Entity seria "createNativeQuery"
		TypedQuery<Conferencia> query = em.createQuery("SELECT c FROM Conferencia c", Conferencia.class);
		List<Conferencia> aux = query.getResultList();
		
		if(aux != null) {
			ret = new ArrayList<Object>();
			for(Conferencia c : aux)
				ret.add(c.toTransfer());
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}
}