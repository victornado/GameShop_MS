package Negocio.SA;

import Negocio.Conferencia.SAConferencia;
import Negocio.Departamento.SADepartamento;
import Negocio.Empleado.SAEmpleado;
import Negocio.Product.SAProduct;
import Negocio.Provider.SAProvider;
import Negocio.Realiza.SARealiza;
import Negocio.Ticket.SATicket;

public abstract class SAAbstractFactory {

	private static SAAbstractFactory instance;

	public static SAAbstractFactory getInstance() {
		if (instance == null)
			instance = new SAAbstractFactoryImpl();
		return instance;
	}

	public abstract SAProduct createSAProduct();

	public abstract SAProvider createSAProvider();

	public abstract SATicket createSATicket();

	public abstract SAConferencia createSAConferencia();

	public abstract SADepartamento createSADepartamento();

	public abstract SAEmpleado createSAEmpleado();

	public abstract SARealiza createSARealiza();
}