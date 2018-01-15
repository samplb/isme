package modelsSQL;

public class Fahrzeug {
	private String marke;
	private int baujahr,
		kennzeichen,
		mNr_Mechaniker,
		mNr_BueroMitarbeiter;
	public Fahrzeug() {
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
	public int getmNr_Mechaniker() {
		return mNr_Mechaniker;
	}
	public void setmNr_Mechaniker(int mNr_Mechaniker) {
		this.mNr_Mechaniker = mNr_Mechaniker;
	}
	public int getmNr_BueroMitarbeiter() {
		return mNr_BueroMitarbeiter;
	}
	public void setmNr_BueroMitarbeiter(int mNr_BueroMitarbeiter) {
		this.mNr_BueroMitarbeiter = mNr_BueroMitarbeiter;
	}
	
}
