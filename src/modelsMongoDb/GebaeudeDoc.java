package modelsMongoDb;

import java.util.List;

import modelsSQL.Buero;
import modelsSQL.Garage;

public class GebaeudeDoc {
	private String name,
		strasse,
		ort;
	private int plz,
		strassenNr,
		gNr,
		uNr;
	private List<Buero> buerogebaude;
	private List<Garage> garagen;
	public GebaeudeDoc() {
		super();
	}
	
	public int getuNr() {
		return uNr;
	}

	public void setuNr(int uNr) {
		this.uNr = uNr;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public int getStrassenNr() {
		return strassenNr;
	}
	public void setStrassenNr(int strassenNr) {
		this.strassenNr = strassenNr;
	}
	public int getgNr() {
		return gNr;
	}
	public void setgNr(int gNr) {
		this.gNr = gNr;
	}
	public List<Buero> getBuerogebaude() {
		return buerogebaude;
	}
	public void setBuerogebaude(List<Buero> buerogebaude) {
		this.buerogebaude = buerogebaude;
	}
	public List<Garage> getGaragen() {
		return garagen;
	}
	public void setGaragen(List<Garage> garagen) {
		this.garagen = garagen;
	}
	
}
