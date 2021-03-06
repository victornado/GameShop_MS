package Negocio.Transfers;

public class TTecnico extends TEmpleado {

	public static Double SOBRESUELDO = 500.50; // sueldoBase += SOBRESUELDO

	private String especialidad;
	private Double sobresueldo;

	public TTecnico(String NIF, String nombre, String turno, Double sueldoBase, Integer dep, Double ss, String e,
			String tipo) {
		super(NIF, nombre, turno, sueldoBase, dep, tipo);
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

	@Override
	public String toString() {
		return ("ID: " + super.getID() + '\n' + "Activo: " + super.getActivo() + '\n' + "NIF: " + super.getNIF() + '\n'
				+ "Nombre: " + super.getNombre() + '\n' + "Turno: " + super.getTurno() + '\n' + "Departamento: "
				+ super.getDepartamento() + '\n' + "Sueldo base: " + super.getSueldobase() + '\n' + "Especialidad: "
				+ especialidad + '\n' + "Sobresueldo: " + sobresueldo + '\n');
	}
}