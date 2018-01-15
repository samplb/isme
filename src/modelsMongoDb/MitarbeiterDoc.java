package modelsMongoDb;

import modelsSQL.BueroMitarbeiter;
import modelsSQL.Mechaniker;

public class MitarbeiterDoc {
	private String vorname,
		telefonnummer,//integer zu klein
		anstellung,
		nachname;
	private int mNr,//die unique id
		chef,
		svNr,
		gNr;//gebäudenummer wo die person arbeitet
	private double gehalt;
	public MitarbeiterDoc() {
		super();
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getTelefonnummer() {
		return telefonnummer;
	}
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
	public String getAnstellung() {
		return anstellung;
	}
	public void setAnstellung(String anstellung) {
		this.anstellung = anstellung;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public int getmNr() {
		return mNr;
	}
	public void setmNr(int mNr) {
		this.mNr = mNr;
	}
	public int getChef() {
		return chef;
	}
	public void setChef(int chef) {
		this.chef = chef;
	}
	public int getSvNr() {
		return svNr;
	}
	public void setSvNr(int svNr) {
		this.svNr = svNr;
	}
	public int getgNr() {
		return gNr;
	}
	public void setgNr(int gNr) {
		this.gNr = gNr;
	}
	public double getGehalt() {
		return gehalt;
	}
	public void setGehalt(double gehalt) {
		this.gehalt = gehalt;
	}
	
}
