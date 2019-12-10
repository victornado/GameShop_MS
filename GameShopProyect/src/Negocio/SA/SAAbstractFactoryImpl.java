package Negocio.SA;

import Negocio.Conferencia.SAConferencia;
import Negocio.Conferencia.SAConferenciaImp;
import Negocio.Departamento.SADepartamento;
import Negocio.Departamento.SADepartamentoImp;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.SAEmpleadoImp;
import Negocio.Product.SAProduct;
import Negocio.Product.SAProductImpl;
import Negocio.Provider.SAProvider;
import Negocio.Provider.SAProviderImpl;
import Negocio.Realiza.SARealiza;
import Negocio.Realiza.SARealizaImp;
import Negocio.Ticket.SATicket;
import Negocio.Ticket.SATicketImpl;

public class SAAbstractFactoryImpl extends SAAbstractFactory {
	
	@Override
	public SAProduct createSAProduct() {
		return new SAProductImpl();
	}
	
	@Override
	public SAProvider createSAProvider() {
		return new SAProviderImpl();
	}

	@Override
	public SATicket createSATicket() {
		return new SATicketImpl();
	}

	@Override
	public SAConferencia createSAConferencia() {
		return new SAConferenciaImp();
	}

	@Override
	public SADepartamento createSADepartamento() {
		return new SADepartamentoImp();
	}

	@Override
	public SAEmpleado createSAEmpleado() {
		return new SAEmpleadoImp();
	}

	@Override
	public SARealiza createSARealiza() {
		return new SARealizaImp();
	}
	
}