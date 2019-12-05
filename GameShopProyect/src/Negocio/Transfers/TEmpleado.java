package Negocio.Transfers;

public class TEmpleado {
	
	public static final String Comercial = "Comercial";
	public static final String Tecnico = "Technician";
	
	private Integer ID;
	private String NIF;
	private String nombre;
	private String turno;
	private Double sueldobase;
	
	public TEmpleado(String NIF, String nombre, String turno, Double sueldoBase) {
		this.NIF = NIF;
		this.nombre = nombre;
		this.turno = turno;
		this.sueldobase = sueldoBase;
	}
	
	public String getNIF() {
		return NIF;
	}

	public void setNIF(String NIF) {
		this.NIF = NIF;
	}

	public Integer getID() {
		return ID;
	}
	
	public void setID(Integer ID) {
		this.ID = ID;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	public Double getSueldobase() {
		return sueldobase;
	}

	public void setSueldobase(Double sueldobase) {
		this.sueldobase = sueldobase;
	}
}