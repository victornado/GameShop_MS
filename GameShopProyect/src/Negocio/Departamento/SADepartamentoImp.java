package Negocio.Departamento;

import Negocio.Empleado.Empleado;
import Negocio.Transfers.TDepartamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SADepartamentoImp implements SADepartamento {

	public Integer registrarDepartamento(TDepartamento data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Departamento dep = new Departamento();
		Integer id=-1;
		
		TypedQuery<Departamento> q = em.createNamedQuery("Negocio.Departamento.Departamento.findBynombre", Departamento.class);
		q.setParameter("nombre",data.getNombre());
		if(q.getResultList().isEmpty()) {
			em.getTransaction().begin();
			dep.setNombre(data.getNombre());
			dep.setPlanta(data.getPlanta());
			dep.setFacturacion(data.getFactura());
			dep.setEmpleados(null);
			em.persist(dep);
			em.getTransaction().commit();

			id = dep.getId();
		}
		else em.getTransaction().rollback();
		em.close();
		emf.close();

		return id;
	}

	public Boolean eliminarDepartamento(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Boolean ret = false;

		em.getTransaction().begin();

		Departamento dep = em.find(Departamento.class, id);
		if (dep != null && dep.getActivo()) {
			em.lock(dep, LockModeType.OPTIMISTIC);
			Collection<Empleado> e = dep.getEmpleados();
			for (Empleado empleado : e) { //Segun los apuntes no hace falta hacer bloqueo sobre los empleados 
				empleado.setDepartamento(null);//TODO ver si no peta
			}
			dep.setActivo(false);
			ret = true;
		}

		em.getTransaction().commit();

		em.close();
		emf.close();

		return ret;
	}

	public Boolean modificarDepartamento(TDepartamento data) {
		Boolean ret = false;
		
		if(validarDatos(data)){
			//no hace falta bloqueo segun los apuntes
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();

			Departamento dep = em.find(Departamento.class, data.getID());
			if (dep != null) {
				dep.setFacturacion(data.getFactura());
				dep.setNombre(data.getNombre());
				dep.setPlanta(data.getPlanta());
				dep.setActivo(data.getActivo());
				ret = true;
			}

			em.getTransaction().commit();

			em.close();
			emf.close();
		}
		
		return ret;
	}

	public Object mostrarDepartamento(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		TDepartamento ret = null;

		em.getTransaction().begin();

		Departamento dep = em.find(Departamento.class, id);

		if (dep != null)
		{
			em.lock(dep, LockModeType.OPTIMISTIC);
			ret = dep.toTransfer();
		}

		em.getTransaction().commit();

		em.close();
		emf.close();

		return ret;
	}

	public List<Object> mostrarTodosLosDepartamentos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		List<Object> ret = null;

		em.getTransaction().begin();

		TypedQuery<Departamento> query = em.createQuery("SELECT d FROM Departamento d", Departamento.class);
		List<Departamento> aux = query.getResultList();

		if (aux != null) {
			ret = new ArrayList<Object>();
			for (Departamento c : aux) {
				em.lock(c, LockModeType.OPTIMISTIC);
				ret.add(c.toTransfer());
			}
			
		}

		em.getTransaction().commit();

		em.close();
		emf.close();

		return ret;
	}

	public Double calcularNomina(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Double nominaFinal = 0.0;

		em.getTransaction().begin();

		Departamento dep = em.find(Departamento.class, id,LockModeType.OPTIMISTIC);

		if (dep != null) {
			Collection<Empleado> e = dep.getEmpleados();
			for (Empleado empleado : e) {
				em.lock(dep, LockModeType.OPTIMISTIC);
				nominaFinal += empleado.calcularSueldo();
			}
			em.getTransaction().commit();
		} else
			em.getTransaction().rollback();

		em.close();
		emf.close();

		return nominaFinal;
	}
	
	private Boolean validarDatos(TDepartamento td) {
		Boolean ret = true; 
		
		//if(td)
		
		return ret;
	}
}