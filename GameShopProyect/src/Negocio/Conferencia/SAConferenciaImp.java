package Negocio.Conferencia;

import Negocio.Empleado.Empleado;
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
				TypedQuery<Conferencia> q = em.createNamedQuery("Negocio.Conferencia.Conferencia.findBynombre", Conferencia.class);
				q.setParameter("nombre",data.getNombre());
				if(q.getResultList().isEmpty()) {//vemos que no existe la conferencia
					con.setNombre(data.getNombre());
					con.setAsistentes(data.getAsistentes());
					con.setFecha(data.getDate());
					con.setTematica(data.getTematica());
					em.persist(con);
					em.getTransaction().commit();
					id = con.getId();
				}
				else {//si existe
					em.getTransaction().rollback();
				}
				
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
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();

			Conferencia con = em.find(Conferencia.class, id);
			
			if (con != null) {
				
				con.setActivo(false);
				if(con.getRealiza()==null || !con.getRealiza().isEmpty()) {
					for (Realiza r : con.getRealiza()) {
						em.remove(r);
					}
					//TODO llamar funcion realiza->borrar
				
				}
				ret = true;
				em.getTransaction().commit();
			}
			else em.getTransaction().rollback();
			

			em.close();
			emf.close();

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean modificarConferencia(TConferencia data2) {
		Boolean ret = false;

		try {
			if (validezDeDatos(data2)) {
				EntityManagerFactory emf = Persistence
						.createEntityManagerFactory("GameShopPersistence");
				EntityManager em = emf.createEntityManager();

				// PROVISIONAL PARA PRUEBAS ==> Funciona PERO CAMBIAR EL COMANDO
				Timestamp fecha = Timestamp.valueOf("2019-12-17 14:15:00");
				TConferencia data = new TConferencia("CAMBIOS", "HOLAAA", 300,
						fecha);
				data.setID(8);
				//fin
				
				em.getTransaction().begin();

				Conferencia con = em.find(Conferencia.class, data.getID());
				
				if (con != null) {
					con.setNombre(data.getNombre());
					con.setTematica(data.getTematica());
					con.setAsistentes(data.getAsistentes());
					con.setFecha(data.getDate());
					Realiza real = em.find(Realiza.class, data.getID());
					RealizaEmbeddable ids = null;
					if(real!=null)//TODO llamar al realiza-->MODIFICAR
					ret = true;
					/*
					for (TRealiza tr : data.getEmpleadosEnConferencias()) {
						real.setDuracion(tr.getDuracion());
						ids = new RealizaEmbeddable();
						ids.setConferencia(tr.getIdConf());
						ids.setEmpleado(tr.getIdEmp());
						real.setIds(ids);
					}
					*/
				

				em.getTransaction().commit();
				}
				else em.getTransaction().rollback();
				em.close();
				emf.close();
			}
			return ret;
		} catch (Exception e) {
			return false;
		}
	}

	public Object mostrarConferencia(Integer id) {
		TConferencia ret = null;

		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();

			Conferencia con = em.find(Conferencia.class, id);

			// Buscamos en Realiza los empleados de esta conferencia para
			// guardarlos en la lista de TConferencia
			// y luego mostrar todos
			if (con != null){
				ret = con.toTransfer();
				em.getTransaction().commit();
			}
			else em.getTransaction().rollback();
			em.close();
			emf.close();

			return ret;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Object> mostrarTodasLasConferencias() {
		List<Object> ret = null;

		try {
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();

			// Para JPQL si Conferencia es una Entity hay que hacer
			// "createQuery" pero si no fuere una Entity seria
			// "createNativeQuery"
			TypedQuery<Conferencia> query = em.createQuery(
					"SELECT c FROM Conferencia c", Conferencia.class);
			List<Conferencia> aux = query.getResultList();

			if (aux != null) {
				ret = new ArrayList<Object>();
				for (Conferencia c : aux)
					ret.add(c.toTransfer());
				em.getTransaction().commit();
			}
			else em.getTransaction().rollback();

			em.close();
			emf.close();

			return ret;
		} catch (Exception e) {
			return null;
		}
	}

	private Boolean validezDeDatos(TConferencia data) {
		Boolean validos = true;

		if (data.getAsistentes() < 0)
			validos = false;
		else if (data.getNombre() == null || data.getNombre().length() > 50)
			validos = false;
		else if (data.getTematica() == null || data.getTematica().length() > 50)
			validos = false;
		else if (validos) {
			// Miramos que el formato de fecha sea el correcto
			String[] fullDate = data.getStringFecha().split(" ");
			String[] date = fullDate[0].split("-"); // yyyy-mm-dd
			String[] hours = fullDate[1].split(":");// hh:mm:ss

			if ((date[0].length() != 4 || Integer.parseInt(date[0]) < 2019)
					|| (Integer.parseInt(date[1]) < 1 || Integer
							.parseInt(date[1]) > 12)
					|| (Integer.parseInt(date[2]) < 1 || Integer
							.parseInt(date[2]) > 31))
				validos = false;
			else if ((Integer.parseInt(hours[0]) > 23 || Integer
					.parseInt(hours[0]) < 0)
					|| (Integer.parseInt(hours[1]) > 59 || Integer
							.parseInt(hours[1]) < 0)
					|| (Integer.parseInt(hours[2]) > 59 || Integer
							.parseInt(hours[2]) < 0))
				validos = false;
		}

		return validos;
	}
}