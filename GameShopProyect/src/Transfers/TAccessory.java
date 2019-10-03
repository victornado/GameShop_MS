package Transfers;

public class TAccessory extends TProduct {
	
	private String _brand;
	private String _color;

	public TAccessory(String name, Integer stock, Double pvp, Integer provider, Integer platform, String brand, String color, String desc) {
		super(name, stock, pvp, TProduct.accessory, provider, platform, desc);
		this.set_brand(brand);
		this.set_color(color);
	}

	public TAccessory() {
		super();
	}

	public String get_brand() {
		return _brand;
	}

	public void set_brand(String _brand) {
		this._brand = _brand;
	}

	public String get_color() {
		return _color;
	}

	public void set_color(String _color) {
		this._color = _color;
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
				"Brand: " + _brand + '\n' +
				"Color: " + _color + '\n' +
				"Descripcion: " + super.get_description());
	}

}
