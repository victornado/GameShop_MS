package Negocio.Conferencia;

import Negocio.Transfers.TConferencia;
import java.util.List;

public interface SAConferencia {
	public Integer registrarConferencia(TConferencia data);

	public Boolean eliminarConferencia(Integer id);

	public Boolean modificarConferencia(TConferencia data);

	public Object mostrarConferencia(Integer id);

	public List<Object> mostrarTodasLasConferencias();
}