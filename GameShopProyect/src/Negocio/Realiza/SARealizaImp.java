package Negocio.Realiza;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Negocio.Departamento.Departamento;
import Negocio.Transfers.TRealiza;

public class SARealizaImp implements SARealiza{

	@Override
	public RealizaEmbeddable createRealiza(TRealiza r) {
		Integer id=-1;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		
		RealizaEmbeddable rE;
		rE.setConferencia(r.);
		
		TypedQuery<Realiza> q=em.createNamedQuery("Realiza.Realiza.findByids", Realiza.class);
		q.setParameter("ids", rE);
		if(q.getResultList().isEmpty()) {
			
		}
	}
	@Override
	public Boolean deleteRealiza(Integer idEmpleado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RealizaEmbeddable updateRealiza(TRealiza tp) {
		// TODO Auto-generated method stub
		return null;
	}

}
