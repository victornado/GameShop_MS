package Negocio.Realiza;

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
	public Boolean deleteRealiza(Integer idEmpleado) {
		//Cuando se haga el em.find de empleado y conferencia hay que añadir "LockModeType.OPTIMISTIC_FORCE_INCREMENT"
		//ya que necesitamos que el lado N (realiza) repercuta en el lado 1 (empleado y confe)
		return null;
		
	}

	@Override
	public RealizaEmbeddable updateRealiza(TRealiza tp) {
		//Cuando se haga el em.find de empleado y conferencia hay que añadir "LockModeType.OPTIMISTIC_FORCE_INCREMENT"
		//ya que necesitamos que el lado N (realiza) repercuta en el lado 1 (empleado y confe)
		// TODO Auto-generated method stub
		return null;
	}

}
