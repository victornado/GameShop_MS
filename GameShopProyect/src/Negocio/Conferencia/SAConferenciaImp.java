package Negocio.Conferencia;

import Negocio.Realiza.Realiza;
import Negocio.Realiza.RealizaEmbeddable;
import Negocio.Transfers.TConferencia;
import Negocio.Transfers.TRealiza;
import utils.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
				//TypedQuery<Conferencia> 
				con.setNombre(data.getNombre());
				con.setAsistentes(data.getAsistentes());
				con.setFecha(data.getDate());
				con.setTematica(data.getTematica());
				em.persist(con);
				em.getTransaction().commit();
				
				id = con.getId();
				
				// Metemos en la lista de TRealiza el idEmp que teniamos y su duracion pero como idConf era null, metems en esa misma
				// posicion los mismos datos que teniamos pero con el idConf que acabamos de dar de alta
				for(int i = 0; i < data.getEmpleadosEnConferencias().size(); ++i) {
					if(data.getEmpleadosEnConferencias().get(i).getIdConf() == null)
						data.getEmpleadosEnConferencias().set
						(i, new TRealiza(data.getEmpleadosEnConferencias().get(i).getIdEmp(),
								id, data.getEmpleadosEnConferencias().get(i).getDuracion()));
				}
				
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
			
			//RealizaEmbeddable embe = new RealizaEmbeddable(em.find(, ), id);
			
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
			e.printStackTrace();
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
				Timestamp fecha = Timestamp.valueOf("2019-12-17 14:15:00");
				TConferencia data = new TConferencia("CAMBIOS", "HOLAAA", 300, fecha);
				data.setID(8);
				
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

	@SuppressWarnings({ "unchecked", "null" })
	public Object mostrarConferencia(Integer id) {
		TConferencia ret = null;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			Conferencia con = em.find(Conferencia.class, 5);
			
			// Buscamos en Realiza los empleados de esta conferencia para guardarlos en la lista de TConferencia
			// y luego mostrar todos
			Query query = em.createNativeQuery("SELECT empleado, duracion FROM realiza WHERE conferencia=?");
			query.setParameter(1, id);
			List<Pair<Integer, Integer>> aux = query.getResultList();
			
			List<TRealiza> lista = new Vector<TRealiza>();
			for(int i = 0; i < aux.size(); ++i) {
				Pair<Integer, Integer> a = aux.get(i);
				lista.add(new TRealiza(a.getKey(), id, a.getValue()));
			}
				
			ret.setEmpleadosEnConferencias(lista);
			
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
			String[] date = fullDate[0].split("-"); // yyyy-mm-dd
			String[] hours = fullDate[1].split(":");// hh:mm:ss
			
			if((date[0].length() != 4 || Integer.parseInt(date[0]) < 2019) ||
					(Integer.parseInt(date[1]) < 1 || Integer.parseInt(date[1]) > 12) ||
					(Integer.parseInt(date[2]) < 1 || Integer.parseInt(date[2]) > 31))
				validos = false;
			else if((Integer.parseInt(hours[0]) > 23 || Integer.parseInt(hours[0]) < 0) ||
					(Integer.parseInt(hours[1]) > 59 || Integer.parseInt(hours[1]) < 0) ||
					(Integer.parseInt(hours[2]) > 59 || Integer.parseInt(hours[2]) < 0))
				validos = false;
		}
		
		return validos;
	}
}