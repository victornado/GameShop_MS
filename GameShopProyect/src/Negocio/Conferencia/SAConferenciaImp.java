package Negocio.Conferencia;

import Negocio.Transfers.TConferencia;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SAConferenciaImp implements SAConferencia {
	
	public EntityManager em;
	public SAConferenciaImp(EntityManager em)
	{
		this.em=em;
	}
	
	public Integer registrarConferencia(TConferencia data) {
		em.getTransaction().begin();
		Conferencia con = new Conferencia();
		//gestionar la creacion de id's ahora que no tocamos el dao ni la bd
		con.setNombre(data.getNombre());
		con.setTematica(data.getTematica());
		con.setAsistentes(data.getAsistentes());
		con.setFecha(data.getDate());
		em.persist(con);
		em.getTransaction().commit(); 	
		return con.getId();
	}

	public Boolean eliminarConferencia(Integer id) {
		return null;
	}

	public Boolean modificarConferencia(TConferencia data) {
		return null;
	}

	public Object mostrarConferencia(Integer id) {
		return null;
	}

	public List<Object> mostrarTodasLasConferencias() {
		return null;
	}
}