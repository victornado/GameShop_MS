package Negocio.Empleado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Negocio.Conferencia.Conferencia;
import Negocio.Departamento.Departamento;
import Negocio.Realiza.Realiza;
import Negocio.Realiza.RealizaEmbeddable;
import Negocio.Transfers.TComercial;
import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TTecnico;

public class SAEmpleadoImp implements SAEmpleado {
	
	public Integer registrarEmpleado(TEmpleado data) {
		Integer idRet = -1;
		
		if(validezDeDatos(data)) {
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
			emp.setTurno(data.getTurno());
			if(data.getDepartamento() ==  null)
				emp.setDepartamento(null);
			else
				emp.setDepartamento(em.find(Departamento.class, data.getDepartamento()));
					
			em.persist(emp);
			em.getTransaction().commit();
			
			idRet = emp.getId();
			
			em.close();
			emf.close();
		}
		
		return idRet;
	}

	public Boolean eliminarEmpleado(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
		EntityManager em = emf.createEntityManager();
		Boolean ret = false;
		
		em.getTransaction().begin();
		
		Realiza real = em.find(Realiza.class, id);
		if(real != null) {
			Conferencia c = em.find(Conferencia.class, real.getIds().getConferencia());
			em.remove(real);
			if(em.find(Realiza.class, c.getId()) == null) { // No hay empleados en esa conferecia ==> Eliminar la conferencia
				em.remove(c);
			}
		}
		
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
		Boolean ret = false;
		
		if(validezDeDatos(data)) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();
			
			
			em.getTransaction().begin();
			
			Empleado emp = em.find(Empleado.class, data.getID());
			
			if(emp != null) {
				ret = true;
				emp.setNIF(data.getNIF());
				emp.setNombre(data.getNombre());
				emp.setSueldoBase(data.getSueldobase());
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
		}
		
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
		TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
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
	
	private Boolean validezDeDatos(TEmpleado data) {
		 Boolean validos = true;
		 
		 if(data.getNIF().length() != 9)
			 validos = false;
		 else if(data.getNombre() == null || data.getNombre().length() > 70)
			 validos = false;
		 else if(!(data.getTipo().equalsIgnoreCase(Empleado.Comercial) || data.getTipo().equalsIgnoreCase(Empleado.Tecnico)))
			 validos = false;
		 else if(!(data.getTurno().equalsIgnoreCase("Morning") || data.getTurno().equalsIgnoreCase("Afternoon")))
			 validos = false;
		 
		 return validos;
	}
}