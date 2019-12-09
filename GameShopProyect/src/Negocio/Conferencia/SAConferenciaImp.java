package Negocio.Conferencia;

import Negocio.Realiza.Realiza;
import Negocio.Realiza.RealizaEmbeddable;
import Negocio.Transfers.TConferencia;
import Negocio.Transfers.TRealiza;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SAConferenciaImp implements SAConferencia {
	
	public Integer registrarConferencia(TConferencia data) {
		Integer id = -1;
		try {
			if(validezDeDatos(data)) {
				data.setDate(data.toTimestamp(data.getStringFecha()));
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
				EntityManager em = emf.createEntityManager();
				Conferencia con = new Conferencia();
				Realiza real = null;
				RealizaEmbeddable losIds = null;
				
				em.getTransaction().begin();
				con.setNombre(data.getNombre());
				con.setAsistentes(data.getAsistentes());
				con.setFecha(data.getDate());
				con.setTematica(data.getTematica());
				em.persist(con);
				em.getTransaction().commit();
				
				id = con.getId();
				
				em.getTransaction().begin();
				for(TRealiza tr : data.getEmpleadosEnConferencias()) {
					real = new Realiza();
					losIds = new RealizaEmbeddable();
					real.setDuracion(tr.getDuracion());
					losIds.setConferencia(tr.getIdConf());
					losIds.setEmpleado(tr.getIdEmp());
					real.setIds(losIds);
					em.persist(real);
				}
				em.getTransaction().commit();
				
				em.close();
				emf.close();
			}
			return id;
		}catch(Exception e) {
			return -1;
		}
	}

	public Boolean eliminarConferencia(Integer id) {
		Boolean ret = false;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			Conferencia con = em.find(Conferencia.class, id);
			Realiza real = em.find(Realiza.class, id);
			if(con != null && real != null) {
				em.remove(con);
				em.remove(real);
				ret = true;
			}		
			
			em.getTransaction().commit();
			
			em.close();
			emf.close();
			
			return ret;
		}catch(Exception e) {
			return false;
		}
	}

	public Boolean modificarConferencia(TConferencia data2) {
		Boolean ret = false;
		
		try {
			if(validezDeDatos(data2)) {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
				EntityManager em = emf.createEntityManager();
				
				// PROVISIONAL PARA PRUEBAS ==> Funciona PERO CAMBIAR EL COMANDO
				Timestamp fecha = Timestamp.valueOf("2019-12-15 14:15:00");
				TConferencia data = new TConferencia("Changes", "Gender", 300, fecha);
				data.setID(5);
				
				em.getTransaction().begin();
				
				Conferencia con = em.find(Conferencia.class, data.getID());
				Realiza real = em.find(Realiza.class, data.getID());
				RealizaEmbeddable ids = null;
				if(con != null && real != null){
					con.setNombre(data.getNombre());
					con.setTematica(data.getTematica());
					con.setAsistentes(data.getAsistentes());
					con.setFecha(data.getDate());
					ret = true;
					for(TRealiza tr : data.getEmpleadosEnConferencias()) {
						real.setDuracion(tr.getDuracion());
						ids = new RealizaEmbeddable();
						ids.setConferencia(tr.getIdConf());
						ids.setEmpleado(tr.getIdEmp());
						real.setIds(ids);
					}
				}
				
				em.getTransaction().commit();
				
				em.close();
				emf.close();
			}
			return ret;
		}catch(Exception e) {
			return false;
		}
	}

	public Object mostrarConferencia(Integer id) {
		TConferencia ret = null;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			Conferencia con = em.find(Conferencia.class, 5);
			
			if(con != null)
				ret = con.toTransfer();
			
			em.getTransaction().commit();
			
			em.close();
			emf.close();
			
			return ret;
		}catch(Exception e){
			return null;
		}
	}

	public List<Object> mostrarTodasLasConferencias() {
		List<Object> ret = null;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();
			
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
		}catch(Exception e) {
			return null;
		}
	}
	
	private Boolean validezDeDatos(TConferencia data){
		Boolean validos = true;
		
		if(data.getAsistentes() < 0)
			validos = false;
		else if(data.getNombre() == null || data.getNombre().length() > 50)
			validos = false;
		else if(data.getTematica() == null || data.getTematica().length() > 50)
			validos = false;
		else if(validos) {
			// Miramos que el formato de fecha sea el correcto
			String[] fullDate = data.getStringFecha().split(" ");
			String[] date = fullDate[0].split("-");
			String[] hours = fullDate[1].split(":");
			
			if(date[0].length() != 4 || (date[1].length() < 1 || date[1].length() > 2) || (date[2].length() < 1 || date[2].length() > 2))
				validos = false;
			else if((Integer.parseInt(hours[0]) > 23 || Integer.parseInt(hours[0]) < 0) ||
					(Integer.parseInt(hours[1]) > 59 || Integer.parseInt(hours[1]) < 0) ||
					(Integer.parseInt(hours[2]) > 59 || Integer.parseInt(hours[2]) < 0))
				validos = false;
		}
		
		return validos;
	}
}