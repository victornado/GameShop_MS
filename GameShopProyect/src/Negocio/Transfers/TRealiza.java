package Negocio.Transfers;

public class TRealiza {
	
	private Integer duracion;
	private Integer idEmp;
	private Integer idConf;
	private Integer version;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public TRealiza(Integer idEmp, Integer idConf, Integer duracion) {
		this.idConf = idConf;
		this.idEmp = idEmp;
		this.duracion = duracion;
	}

	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	public Integer getIdEmp() {
		return idEmp;
	}
	public void setIdEmp(Integer idEmp) {
		this.idEmp = idEmp;
	}
	public Integer getIdConf() {
		return idConf;
	}
	public void setIdConf(Integer idConf) {
		this.idConf = idConf;
	}
}