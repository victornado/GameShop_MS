package Negocio.Realiza;

import Negocio.Transfers.TRealiza;

public interface SARealiza {
	public RealizaEmbeddable createRealiza(TRealiza r);
	public Boolean deleteRealiza(Integer idEmpleado);
	public RealizaEmbeddable updateRealiza(TRealiza tp);
	
}
