package modelsMongoDb;

import java.util.List;

import modelsSQL.Buero;
import modelsSQL.Garage;
/**
 * GebäudeDoc
 * @author bs
 *
 */
public class GebaeudeDoc {
	private String name,
		strasse,
		ort,
		unternehmen;
	private int plz,
		strassenNr,
		gNr;
	private List<Buero> buerogebaudeList=null;
	private List<Garage> garagenList=null;
	
	public GebaeudeDoc() {
		super();
	}
	
	public String getUnternehmen() {
		return unternehmen;
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

	public List<Buero> getBuerogebaudeList() {
		return buerogebaudeList;
	}

	public void setBuerogebaudeList(List<Buero> buerogebaudeList) {
		this.buerogebaudeList = buerogebaudeList;
	}

	public List<Garage> getGaragenList() {
		return garagenList;
	}

	public void setGaragenList(List<Garage> garagenList) {
		this.garagenList = garagenList;
	}

	public void setUnternehmen(String unternehmen) {
		this.unternehmen = unternehmen;
	}

	public void setNull() {
		this.name = null;
		this.strasse = null;
		this.ort = null;
		this.unternehmen = null;
		this.plz = 0;
		this.strassenNr = 0;
		this.gNr = 0;
		this.buerogebaudeList.clear();
		this.garagenList.clear();
	}
	
	
}
