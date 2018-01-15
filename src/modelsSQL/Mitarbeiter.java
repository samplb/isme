package modelsSQL;

public class Mitarbeiter {
	private int mNr,
		uNr;
	private String name,
		vorname;
	public Mitarbeiter() {
		super();
	}
	public int getmNr() {
		return mNr;
	}
	public void setmNr(int mNr) {
		this.mNr = mNr;
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
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	
}
