package modelsMongoDb;

import java.util.List;

public class UnternehmenDoc {
	private String name;
	private int uNr;
	private List<Integer> gNummern;
	public UnternehmenDoc() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getuNr() {
		return uNr;
	}
	public void setuNr(int uNr) {
		this.uNr = uNr;
	}
	public List<Integer> getgNummern() {
		return gNummern;
	}
	public void setgNummern(List<Integer> gNummern) {
		this.gNummern = gNummern;
	}
	
}
