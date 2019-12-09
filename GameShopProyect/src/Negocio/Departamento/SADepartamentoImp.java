package Negocio.Departamento;

import Negocio.Transfers.TDepartamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SADepartamentoImp implements SADepartamento {

	public Integer registrarDepartamento(TDepartamento data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Departamento dep = new Departamento();
		
		em.getTransaction().begin();
		dep.setNombre(data.getNombre());
		dep.setNumEmpleados(data.getnEmpleados());
		dep.setPlanta(data.getPlanta());
		dep.setFacturacion(data.getFactura());
		
		em.persist(dep);
		em.getTransaction().commit();
		
		Integer id = dep.getId();
		
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
		if(dep != null) {
			// TODO LLAMAR A TODOS LOS EMPLEADOS PERTENECIENTES A ESE DPTO PARA PONERLO A NULL
			
			
			
			
			
			
			em.remove(dep);
			ret = true;
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}
	
	public Boolean modificarDepartamento(TDepartamento data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Boolean ret = false;
		
		em.getTransaction().begin();
		
		Departamento dep = em.find(Departamento.class, data.getID());
		if(dep != null){
			dep.setFacturacion(data.getFactura());
			dep.setNombre(data.getNombre());
			dep.setNumEmpleados(data.getnEmpleados());
			dep.setPlanta(data.getPlanta());
			ret = true;
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}

	public Object mostrarDepartamento(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		TDepartamento ret = null;
		
		em.getTransaction().begin();
		
		Departamento dep = em.find(Departamento.class, id);
		
		if(dep!=null)
			ret = dep.toTransfer();
		
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
		
		if(aux != null) {
			ret = new ArrayList<Object>();
			for(Departamento c : aux)
				ret.add(c.toTransfer());
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}
	
//falta
	public Double calcularNomina(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Double nominaFinal=0.0;
		
		em.getTransaction().begin();
		
		Departamento dep = em.find(Departamento.class, id);
		
		if(dep!=null)
		{
			//Aqui deberia recorrer la collection o hashmap de empleados sumando sueldos
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		return nominaFinal;
	}
}