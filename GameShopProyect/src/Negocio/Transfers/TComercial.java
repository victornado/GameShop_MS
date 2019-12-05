package Negocio.Transfers;

public class TComercial extends TEmpleado {
	
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
}