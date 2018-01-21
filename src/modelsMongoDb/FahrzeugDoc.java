package modelsMongoDb;

public class FahrzeugDoc {
	private String marke;
	private int baujahr,
		kennzeichen,
		mechaniker,
		bueromitarbeiter;
	public FahrzeugDoc() {
		super();
	}
	public String getMarke() {
		return marke;
	}
	public void setMarke(String marke) {
		this.marke = marke;
	}
	public int getBaujahr() {
		return baujahr;
	}
	public void setBaujahr(int baujahr) {
		this.baujahr = baujahr;
	}
	public int getKennzeichen() {
		return kennzeichen;
	}
	public void setKennzeichen(int kennzeichen) {
		this.kennzeichen = kennzeichen;
	}
	public int getMechaniker() {
		return mechaniker;
	}
	public void setMechaniker(int mechaniker) {
		this.mechaniker = mechaniker;
	}
	public int getBueromitarbeiter() {
		return bueromitarbeiter;
	}
	public void setBueromitarbeiter(int bueromitarbeiter) {
		this.bueromitarbeiter = bueromitarbeiter;
	}
	public void setNull() {
		this.marke = null;
		this.baujahr = 0;
		this.kennzeichen = 0;
		this.mechaniker = 0;
		this.bueromitarbeiter = 0;
	}
	
}
