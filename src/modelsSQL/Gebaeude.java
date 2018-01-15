package modelsSQL;

public class Gebaeude {
	private int gNr,
		plz,
		strassenNr,
		uNr;
	private String name,
	 	strasse,
	 	ort;
	public Gebaeude() {
		super();
	}
	public int getgNr() {
		return gNr;
	}
	public void setgNr(int gNr) {
		this.gNr = gNr;
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
	
	
	}
