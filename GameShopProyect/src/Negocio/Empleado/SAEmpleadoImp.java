package Negocio.Empleado;

import Negocio.Transfers.TComercial;
import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TTecnico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SAEmpleadoImp implements SAEmpleado {
	
	public Integer registrarEmpleado(TEmpleado data) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Empleado emp = null;
		TEmpleado temp = null;
		
		em.getTransaction().begin();
		
		if(data.getTipo().equalsIgnoreCase(Empleado.Comercial)) {
			temp = new TComercial(data.getNIF(), data.getNombre(), data.getTurno(), data.getSueldobase(), data.getTipo(),
					data.getDepartamento(), ((TComercial)data).getnVentas());
			emp = new Comercial();
			((Comercial)emp).setnVentas(((TComercial)temp).getnVentas());
		}
		else {
			temp = new TTecnico(data.getNIF(), data.getNombre(), data.getTurno(), data.getSueldobase(), data.getTipo(),
					data.getDepartamento(), ((TTecnico)data).getSobresueldo(), ((TTecnico)data).getEspecialidad());
			emp = new Tecnico();
			((Tecnico)emp).setEspecialidad(((Tecnico)emp).getEspecialidad());
			((Tecnico)emp).setSobresueldo(((Tecnico)emp).getSobresueldo());
		}
		
		emp.setNIF(temp.getNIF());
		emp.setNombre(temp.getNombre());
		emp.setSueldoBase(temp.getSueldobase());
		emp.setTipo(temp.getTipo());
		emp.setTurno(temp.getTurno());
				
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
			emp.setDepartamento(data.getDepartamento()); // Vitali
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
		TypedQuery<Empleado> query = em.createQuery("SELECT * FROM empleado e JOIN comercial c JOIN tecnico t "
				+ "WHERE e.id=c.idComercial=t.idTecnico", Empleado.class);
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