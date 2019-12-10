package Negocio.Realiza;

import java.io.Serializable;
import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class RealizaEmbeddable implements Serializable {

	private static final long serialVersionUID = 0;
	
	private Integer Empleado;
	private Integer Conferencia;

	public RealizaEmbeddable() {
	}
	
	public RealizaEmbeddable(Integer emp, Integer conf) {
		this.Empleado = emp;
		this.Conferencia = conf;
	}

	public Integer getConferencia() {
		return Conferencia;
	}

	public void setConferencia(Integer Conferencia) {
		this.Conferencia = Conferencia;
	}

	public Integer getEmpleado() {
		return Empleado;
	}

	public void setEmpleado(Integer Empleado) {
		this.Empleado = Empleado;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof RealizaEmbeddable))
			return false;
		RealizaEmbeddable pk = (RealizaEmbeddable) obj;
		if ((Conferencia == null && pk.Conferencia != null)
				|| (Conferencia != null && !Conferencia.equals(pk.Conferencia)))
			return false;
		if ((Empleado == null && pk.Empleado != null) || (Empleado != null && !Empleado.equals(pk.Empleado)))
			return false;
		return true;
	}

	private UUID uuid;

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		if (Conferencia != null) {
			hash = hash * prime + Conferencia.hashCode();
		}
		if (Empleado != null) {
			hash = hash * prime + Empleado.hashCode();
		}
		if (hash == 17) {
			if (uuid == null) {
				uuid = UUID.randomUUID();
			}
			hash = uuid.hashCode();
		}
		return hash;
	}
}