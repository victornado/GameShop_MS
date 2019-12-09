package Negocio.Transfers;

import Negocio.Departamento.Departamento;

public class TComercial extends TEmpleado {
	
	public static Double COBRO_POR_VENTA = 20.50; // salariobase += nVentas * COBRO_POR_VENTA
	private Integer nVentas;
	
	public TComercial(String NIF, String nombre, String turno, Double sueldoBase, String tipo, Integer dep,  Integer ventas) {
		super(NIF, nombre, turno, sueldoBase, tipo, dep);
		nVentas = ventas;
	}

	public Integer getnVentas() {
		return nVentas;
	}
	
	public void setnVentas(Integer nVentas) {
		this.nVentas = nVentas;
	}
	
	@Override
	public String toString() {
		return ("ID: " + super.getID() + '\n' +
				"NIF: " + super.getNIF() + '\n' +
				"Nombre: " + super.getNombre() + '\n' +
				"Turno: " + super.getTurno() + '\n' +
				"Departamento: " + super.getDepartamento().toString() + '\n' +
				"Sueldo base: " + super.getSueldobase() + '\n' +
				"Tipo: " + super.getTipo() + '\n' +
				"Numero de ventas: " + nVentas + " [Cobro por cada venta = " + TComercial.COBRO_POR_VENTA + "]" + '\n');
	}
}