package Transfers;

public class TGame extends TProduct {

	private String _gender;
	
	public TGame(String name, Integer stock, Double pvp, Integer provider, Integer platform, String description, String gender) {
		super(name, stock, pvp, TProduct.game, provider, platform, description);
		this._gender = gender;
	}

	public TGame() {
		super();
	}

	public String get_gender() {
		return _gender;
	}

	public void set_gender(String _gender) {
		this._gender = _gender;
	}
	
	@Override
	public String toString() {
		String act = super.get_activated() ? "Yes" : "No";
		return ("ID: " + super.get_id() + '\n' +
				"Name: " + super.get_name() + '\n'+
				"Type: " + super.get_type() + '\n' +
				"Stock: " + super.get_stock() + '\n' +
				"PVP: " + super.get_pvp() + '\n' +
				"Provider ID: " + super.get_providerId() + '\n' +
				"Platform ID: " + super.get_platformId() + '\n' +
				"Activated: " + act + '\n'+
				"Units Provided: " + super.get_unitsProvided() + '\n' +
				"Gender: " + _gender + '\n' +
				"Descripcion: " + super.get_description());
	}

}
