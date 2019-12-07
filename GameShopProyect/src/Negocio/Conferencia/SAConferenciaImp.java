package Negocio.Conferencia;

import Negocio.Transfers.TConferencia;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
		Conferencia con = findConferencia(id);
		if(con!=null)
		{
			em.remove(con);
			return true;
		}
		return false;
	}

	public Boolean modificarConferencia(TConferencia data) {
		
		//Al reactivar conferencia, como accedemos a la variable 'activo'
		
		Conferencia con = findConferencia(data.getID());
		if(con!=null)
		{
			con.setNombre(data.getNombre());
			con.setTematica(data.getTematica());
			con.setAsistentes(data.getAsistentes());
			con.setFecha(data.getDate());
			return true;
		}
		return false;
	}

	public Object mostrarConferencia(Integer id) {
		Conferencia con = findConferencia(id);
		if(con!=null)
			return con;
		return null;
	}

	public List<Object> mostrarTodasLasConferencias() {
		//Aqui tenemos una movida tamb
		//TypedQuery<Conferencia> query = em.createQuery("SELECT * FROM Conferencia c", Conferencia.class);
		return null;
	}
	
	
	public Conferencia findConferencia(Integer id)
	{
		return em.find(Conferencia.class, id);
	}
}