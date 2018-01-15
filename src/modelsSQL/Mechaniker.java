package modelsSQL;

public class Mechaniker {
	private int mNr,
		svNr,
		gehalt;
	private String telefonnummer;
	public Mechaniker() {
		super();
	}
	public int getmNr() {
		return mNr;
	}
	public void setmNr(int mNr) {
		this.mNr = mNr;
	}
	public int getSvNr() {
		return svNr;
	}
	public void setSvNr(int svNr) {
		this.svNr = svNr;
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
