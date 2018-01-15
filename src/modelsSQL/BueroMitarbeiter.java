package modelsSQL;

public class BueroMitarbeiter {
	private int svNr,
		mNr,
		gehalt;
	private String telefonnummer;
	public BueroMitarbeiter() {
		super();
	}
	public int getSvNr() {
		return svNr;
	}
	public void setSvNr(int svNr) {
		this.svNr = svNr;
	}
	public int getmNr() {
		return mNr;
	}
	public void setmNr(int mNr) {
		this.mNr = mNr;
	}
	public int getGehalt() {
		return gehalt;
	}
	public void setGehalt(int gehalt) {
		this.gehalt = gehalt;
	}
	public String getTelefonnummer() {
		return telefonnummer;
	}
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
	
}
