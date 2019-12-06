package Negocio.Conferencia;

import Negocio.Transfers.TConferencia;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SAConferenciaIMP implements SAConferencia {

	//Mirar donde meter lo de 
	//EntityManagerFactory emf = Persistence.createEntityManagerFactory("Conferencia");
	// EntityManager em = emf.createEntityManager();
	
	
	public EntityManager em;
	public SAConferenciaIMP(EntityManager em)
	{
		this.em=em;
	}
	
	public Integer registrarConferencia(TConferencia data) {
		Conferencia con = new Conferencia(data.getID());//getID llegará vacio, mirar como
		//gestionar la creacion de id's ahora que no tocamos el dao ni la bd
		con.setNombre(data.getNombre());
		con.setTematica(data.getTematica());
		con.setAsistentes(data.getAsistentes());
		con.setFecha(data.getDate());
		em.persist(con);
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