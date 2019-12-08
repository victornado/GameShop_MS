package Negocio.Transfers;

public class TComercial extends TEmpleado {
	
	private static Double COBRO_POR_VENTA = 20.50; // salariobase += nVentas * COBRO_POR_VENTA
	private Integer nVentas;
	
	public TComercial(String NIF, String nombre, String turno, Double sueldoBase, Integer ventas) {
		super(NIF, nombre, turno, sueldoBase);
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
				"Sueldo base: " + super.getSueldobase() + '\n' +
				"Numero de ventas: " + nVentas + " [Cobro por cada venta = " + TComercial.COBRO_POR_VENTA + "]" + '\n');
	}
}