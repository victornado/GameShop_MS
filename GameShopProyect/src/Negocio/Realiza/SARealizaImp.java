package Negocio.Realiza;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Negocio.Conferencia.Conferencia;
import Negocio.Empleado.Empleado;
import Negocio.Transfers.TRealiza;

public class SARealizaImp implements SARealiza{

	@Override
	public RealizaEmbeddable createRealiza(TRealiza r) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		RealizaEmbeddable rE= new RealizaEmbeddable();
		rE.setConferencia(r.getIdConf());
		rE.setEmpleado(r.getIdEmp());
		TypedQuery<Realiza> q=em.createNamedQuery("Realiza.Realiza.findByids", Realiza.class);
		q.setParameter("ids", rE);
		
		if(q.getResultList().isEmpty()) {
			Realiza real = new Realiza();
			real.setIds(rE);
			real.setDuracion(r.getDuracion());
			real.setConferencia(em.find(Conferencia.class, r.getIdConf(),LockModeType.OPTIMISTIC_FORCE_INCREMENT));
			real.setEmpleado(em.find(Empleado.class, r.getIdEmp(),LockModeType.OPTIMISTIC_FORCE_INCREMENT));
			em.persist(real);
			em.getTransaction().commit();
		}
		else {
			rE=null;
			em.getTransaction().rollback();
		}
		return rE;
	}
	@Override
	public Boolean deleteRealiza(RealizaEmbeddable ids) {
		//Cuando se haga el em.find de empleado y conferencia hay que añadir "LockModeType.OPTIMISTIC_FORCE_INCREMENT"
		//ya que necesitamos que el lado N (realiza) repercuta en el lado 1 (empleado y confe)
		boolean ok=false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Realiza real=em.find(Realiza.class, ids);
		if(real!=null) {//esta en la base de datos
			Empleado e= em.find(Empleado.class, ids.getEmpleado(),LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			Set<Realiza>aux1 = e.getRealiza();
			aux1.remove(real);
			e.setRealiza(aux1);
			Conferencia c= em.find(Conferencia.class, ids.getConferencia(),LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			Set<Realiza>aux2 = c.getRealiza();
			aux2.remove(real);
			c.setRealiza(aux2);
			em.remove(real);
			em.getTransaction().commit();
			ok=true;
		}
		else em.getTransaction().rollback();
		
		return ok;
	}

	@Override
	public RealizaEmbeddable updateRealiza(TRealiza r) {
		//Cuando se haga el em.find de empleado y conferencia hay que añadir "LockModeType.OPTIMISTIC_FORCE_INCREMENT"
		//ya que necesitamos que el lado N (realiza) repercuta en el lado 1 (empleado y confe)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		RealizaEmbeddable rE= new RealizaEmbeddable();
		rE.setConferencia(r.getIdConf());
		rE.setEmpleado(r.getIdEmp());
		Realiza real= em.find(Realiza.class, rE);
		if(real!=null) {//esta en la base de datos
			real.setDuracion(r.getDuracion());
			em.getTransaction().commit();
		}
		else {//no esta en la base de datos
			rE=null;
			em.getTransaction().rollback();
		}
		
		return rE;
	}
	
}
