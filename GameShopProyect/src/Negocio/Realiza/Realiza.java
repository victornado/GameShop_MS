package Negocio.Realiza;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@NamedQuery(name = "Realiza.Realiza.findByids", query = "select obj from Realiza obj where :main = obj.ids "),
		@NamedQuery(name = "Realiza.Realiza.findByduracion", query = "select obj from Realiza obj where :duracion = obj.duracion ") })
public class Realiza implements Serializable {
	private static final long serialVersionUID = 0;
	
	@EmbeddedId
	private RealizaEmbeddable ids;
	private Integer duracion;

	public Realiza() {
	}
	
	public RealizaEmbeddable getIds() {
		return ids;
	}
	
	public void setIds(RealizaEmbeddable ids) {
		this.ids = ids;
	}
	
	public Integer getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
}