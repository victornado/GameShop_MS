package Negocio.Empleado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Negocio.Departamento.Departamento;
import Negocio.Transfers.TComercial;
import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TTecnico;

public class SAEmpleadoImp implements SAEmpleado {
	
	public Integer registrarEmpleado(TEmpleado data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Empleado emp = null;
		
		em.getTransaction().begin();
		
		if(data.getTipo().equalsIgnoreCase(Empleado.Comercial)) {
			emp = new Comercial();
			((Comercial)emp).setnVentas(((TComercial)data).getnVentas());
		}
		else {
			emp = new Tecnico();
			((Tecnico)emp).setEspecialidad(((TTecnico)data).getEspecialidad());
			((Tecnico)emp).setSobresueldo(((TTecnico)data).getSobresueldo());
		}
		
		emp.setNIF(data.getNIF());
		emp.setNombre(data.getNombre());
		emp.setSueldoBase(data.getSueldobase());
		emp.setTipo(data.getTipo());
		emp.setTurno(data.getTurno());
				
		em.persist(emp);
		em.getTransaction().commit();
		
		Integer idRet = emp.getId();
		
		em.close();
		emf.close();
		
		return idRet;
	}

	public Boolean eliminarEmpleado(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Boolean ret = false;
		
		em.getTransaction().begin();
		
		// TODO Ver de que tabla se elimina pq hay 3 tablas de empleado
		Empleado con = em.find(Empleado.class, id);
		if(con != null) {
			em.remove(con);
			ret = true;
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}

	public Boolean modificarEmpleado(TEmpleado data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Boolean ret = false;
		
		// PROVISIONAL PARA PRUEBAS ==> Funciona PERO CAMBIAR EL COMANDO
		//TEmpleado data = new TComercial("48223456S", "Carlos", "Morning", 1025.2, Empleado.Comercial, 127);
		
		em.getTransaction().begin();
		
		Empleado emp = em.find(Empleado.class, data.getID());
		
		if(emp != null) {
			ret = true;
			emp.setNIF(data.getNIF());
			emp.setNombre(data.getNombre());
			emp.setSueldoBase(data.getSueldobase());
			emp.setTipo(data.getTipo());
			emp.setTurno(data.getTurno());
			emp.setDepartamento(em.find(Departamento.class, data.getID()));
			if(data.getTipo().equalsIgnoreCase(Empleado.Comercial))
				((Comercial)emp).setnVentas(((TComercial)data).getnVentas());
			else {
				((Tecnico)emp).setEspecialidad(((TTecnico)data).getEspecialidad());
				((Tecnico)emp).setSobresueldo(((TTecnico)data).getSobresueldo());
			}
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}

	public Object mostrarEmpleado(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		TEmpleado ret = null;
		
		em.getTransaction().begin();
		
		Empleado emp = em.find(Empleado.class, id);
		
		if(emp != null)
			ret = emp.toTransfer();
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}

	public List<Object> mostrarTodosLosEmpleados() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		List<Object> ret = null;
		
		em.getTransaction().begin();
		
		// Para JPQL si Conferencia es una Entity hay que hacer "createQuery" pero si no fuere una Entity seria "createNativeQuery"
		TypedQuery<Empleado> query = em.createQuery("SELECT * FROM empleado e JOIN comercial c ON e.id = c.idComercial "
				+ "JOIN tecnico t ON e.id = t.idTecnico", Empleado.class);
		List<Empleado> aux = query.getResultList();
		
		if(aux != null) {
			ret = new ArrayList<Object>();
			for(Empleado e : aux)
				ret.add(e.toTransfer());
		}
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return ret;
	}
}