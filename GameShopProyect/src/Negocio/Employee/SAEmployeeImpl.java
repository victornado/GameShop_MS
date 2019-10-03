package Negocio.Employee;

import java.util.List;

import Integracion.DAO.DAOAbstractFactory;
import Integracion.Employee.DAOEmployee;
import Transfers.TEmployee;
import Transfers.TProvider;

public class SAEmployeeImpl implements SAEmployee {

	@Override
	public Integer createEmployee(TEmployee te) {
		int id = -1;
		if(validateData(te)) {
			DAOEmployee daoEmployee = DAOAbstractFactory.getInstance().createDAOEmployee();
			TEmployee tpl = daoEmployee.readEmployeeByNIF(te.get_nif());
			if(tpl == null)
				id = daoEmployee.createEmployee(te);
		}
		return id;
	}

	@Override
	public Boolean deleteEmployee(Integer id) {
		boolean ret = false;
		DAOEmployee daoEmployee = DAOAbstractFactory.getInstance().createDAOEmployee();
		
		if(id != null) {
			TEmployee ternif = (TEmployee)daoEmployee.readEmployee(id);
			// Si devuelve un transfer significa que existe y por lo tanto se procede a borrarlo
			if(ternif != null && ternif.get_activated())
				ret = daoEmployee.deleteEmployee(ternif);
		}
		return ret;
	}

	@Override
	public Boolean updateEmployee(TEmployee te) {
		if(this.validateData(te)) {
			if(te.get_activated()!=null) {
				TEmployee t = (TEmployee) DAOAbstractFactory.getInstance().createDAOEmployee().readEmployee(te.get_id());
				if(te.get_nif().equals(t.get_nif())) {
					if(DAOAbstractFactory.getInstance().createDAOEmployee().readEmployeeByNIF(te.get_nif()) == null)
						return false;
					return DAOAbstractFactory.getInstance().createDAOEmployee().updateEmployee(te); 
				}
			}
		}
		return false;
	}

	@Override
	public Object readEmployee(Integer id) {
		TEmployee ret = null;
		DAOEmployee daoEmployee = DAOAbstractFactory.getInstance().createDAOEmployee();
		
		if(id != null)
			ret = (TEmployee)daoEmployee.readEmployee(id);
		return ret;
	}

	@Override
	public List<Object> readAllEmployees() {
		List<Object> employees = null;
		employees = DAOAbstractFactory.getInstance().createDAOEmployee().readAllEmployees();
		return employees;
	}
	
	private boolean validateData(TEmployee te) {
		if(te == null || te.get_nif() == null || te.get_name() == null || te.getTurn() == null || !checkNIF(te.get_nif()) || te.get_name().length() > 45 ||
				te.get_name().trim().isEmpty() || te.getTurn().trim().isEmpty() || te.getTurn().length() > 15)
			return false;
		else
			return true;
	}
	
	
	// ESTOS 3 METODOS SON PARA COMPROBAR LA VALIDEZ DE UN NIF
		private boolean checkNIF(String NIF) {
			String upperLetter = "";
			if(NIF.length() != 9 || !Character.isLetter(NIF.charAt(8))) 
				return false;
			
			upperLetter = (NIF.substring(8)).toUpperCase();
			
			if(NIFnumbers(NIF) && NIFletter(NIF).equals(upperLetter))
				return true;
			else return false;
		}

		private String NIFletter(String NIF) {
			int myNif = Integer.parseInt(NIF.substring(0, 8)), rest = 0;
			String letter = "";
			String[] posibilities = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
					 "V", "H", "L", "C", "K", "E"};
			rest = myNif % 23;
			
			letter = posibilities[rest];
			
			return letter;
		}

		private boolean NIFnumbers(String NIF) {
			String number = "", myNif = "";
			String[] numberRange = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
			
			for(int i = 0; i < NIF.length() - 1; ++i) {
				number = NIF.substring(i, i + 1);
				for(int j = 0; j < numberRange.length; ++j) {
					if(number.equals(numberRange[j]))
						myNif += numberRange[j];
				}
			}
			if (myNif.length() != 8)
				return false;
			else return true;
		}
}