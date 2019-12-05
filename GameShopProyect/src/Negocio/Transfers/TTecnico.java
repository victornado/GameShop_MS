package Negocio.Transfers;

public class TTecnico extends TEmpleado {
	
	private String especialidad;
	private Double sobresueldo;
	
	public TTecnico(String NIF, String nombre, String turno, Double sueldoBase, Double ss, String e) {
		super(NIF, nombre, turno, sueldoBase);
		this.sobresueldo = ss;
		this.especialidad = e;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Double getSobresueldo() {
		return sobresueldo;
	}

	public void setSobresueldo(Double sobresueldo) {
		this.sobresueldo = sobresueldo;
	}
}