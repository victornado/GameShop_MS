package Negocio.Realiza;

import Negocio.Transfers.TRealiza;

public interface SARealiza {
	public RealizaEmbeddable createRealiza(TRealiza r);

	public Boolean deleteRealiza(RealizaEmbeddable ids);

	public RealizaEmbeddable updateRealiza(TRealiza r);
}
