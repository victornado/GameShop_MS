package Negocio.Empleado;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Negocio.Departamento.Departamento;
import Negocio.Realiza.Realiza;
import Negocio.Transfers.TComercial;
import Negocio.Transfers.TEmpleado;
import Negocio.Transfers.TTecnico;

public class SAEmpleadoImp implements SAEmpleado {

	public Integer registrarEmpleado(TEmpleado data) {
		Integer idRet = -1;

		if (validezDeDatos(data)) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();
			Empleado emp = null;

			em.getTransaction().begin();
			TypedQuery<Empleado> q = em.createNamedQuery("Negocio.Empleado.Empleado.findByNIF", Empleado.class);
			q.setParameter("NIF", data.getNIF());
			List<Empleado> l = q.getResultList();

			if (l.isEmpty()) {// comprobamos que no exista el empleado

				if (data.getTipo().equalsIgnoreCase(Empleado.Comercial)) {
					emp = new Comercial();
					((Comercial) emp).setnVentas(((TComercial) data).getnVentas());
				} else {
					emp = new Tecnico();
					((Tecnico) emp).setEspecialidad(((TTecnico) data).getEspecialidad());
					((Tecnico) emp).setSobresueldo(((TTecnico) data).getSobresueldo());
				}

				emp.setNIF(data.getNIF());
				emp.setNombre(data.getNombre());
				emp.setSueldoBase(data.getSueldobase());
				emp.setTurno(data.getTurno());
				emp.setRealiza(null);
				if (data.getDepartamento() == null || !em.find(Departamento.class, data.getDepartamento()).getActivo())
					emp.setDepartamento(null);
				else
					emp.setDepartamento(em.find(Departamento.class, data.getDepartamento(),
							LockModeType.OPTIMISTIC_FORCE_INCREMENT));

				em.persist(emp);
				em.getTransaction().commit();
				idRet = emp.getId();

			} else// si existe
				em.getTransaction().rollback();

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

		Empleado con = em.find(Empleado.class, id);
		if (con != null) {// si existe el empleado
			em.lock(con, LockModeType.OPTIMISTIC);
			if (con.getRealiza() != null) {// buscamos los realiza
				for (Realiza r : con.getRealiza()) {
					em.remove(r);
				}
			}
			Departamento d = null;
			if (con.getDepartamento() != null)
				d = em.find(Departamento.class, con.getDepartamento().getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (d != null) {
				d.getEmpleados().remove(con);
				con.setDepartamento(null);
			}

			con.setActivo(false);

			em.getTransaction().commit();
			ret = true;
		} else // si no existe
			em.getTransaction().rollback();

		em.close();
		emf.close();

		return ret;
	}

	public Boolean modificarEmpleado(TEmpleado data) {
		Boolean ret = false;

		if (validezDeDatos(data)) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("GameShopPersistence");
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();

			Empleado emp = em.find(Empleado.class, data.getID());

			if (emp != null) {

				emp.setNIF(data.getNIF());
				emp.setActivo(data.getActivo());
				emp.setNombre(data.getNombre());
				emp.setSueldoBase(data.getSueldobase());
				emp.setTurno(data.getTurno());
				if (data.getDepartamento() != null)
					emp.setDepartamento(em.find(Departamento.class, data.getDepartamento(),
							LockModeType.OPTIMISTIC_FORCE_INCREMENT));
				else
					emp.setDepartamento(null);

				if (data.getTipo().equalsIgnoreCase(Empleado.Comercial))
					((Comercial) emp).setnVentas(((TComercial) data).getnVentas());
				else {
					((Tecnico) emp).setEspecialidad(((TTecnico) data).getEspecialidad());
					((Tecnico) emp).setSobresueldo(((TTecnico) data).getSobresueldo());
				}
				em.getTransaction().commit();
				ret = true;
			} else
				em.getTransaction().rollback();

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

		// Buscamos en Realiza la conferencia a la que pertenece este empleado
		/*
		 * Query query = em.
		 * createNativeQuery("SELECT conferencia, duracion FROM realiza WHERE empleado=?"
		 * ); query.setParameter(1, id); List<Pair<>> aux = query.getResultList(); //
		 * Todas las conferencia a las que pertenece este empleado
		 */

		if (emp != null) {
			em.lock(emp, LockModeType.OPTIMISTIC);
			ret = emp.toTransfer();
		}

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

		// Para JPQL si Conferencia es una Entity hay que hacer "createQuery" pero si no
		// fuere una Entity seria "createNativeQuery"
		TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
		List<Empleado> aux = query.getResultList();

		if (aux != null) {
			ret = new ArrayList<Object>();
			for (Empleado e : aux) {
				em.lock(e, LockModeType.OPTIMISTIC);
				ret.add(e.toTransfer());
			}

		}

		em.getTransaction().commit();

		em.close();
		emf.close();

		return ret;
	}

	private Boolean validezDeDatos(TEmpleado data) {
		Boolean validos = true;

		if (data.getNIF().length() != 9)
			validos = false;
		else if (data.getNombre() == null || data.getNombre().length() > 70)
			validos = false;
		else if (!(data.getTipo().equalsIgnoreCase(Empleado.Comercial)
				|| data.getTipo().equalsIgnoreCase(Empleado.Tecnico)))
			validos = false;
		else if (!(data.getTurno().equalsIgnoreCase("Morning") || data.getTurno().equalsIgnoreCase("Afternoon")))
			validos = false;

		return validos;
	}
}