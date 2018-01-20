package modelsMongoDb.createDocs;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import modelsMongoDb.GebaeudeDoc;
import modelsMongoDb.Exceptions.NoGebaudeDocException;
import modelsSQL.Buero;
import modelsSQL.Garage;
/**
 * creates a BasicDBObject for Collection.insert()
 * @author bs
 *
 */
public class CreateGebaeudeDoc {

	private Document doc;
	/**
	 * erzeugt das Dokument, abfrage mit getDoc()
	 * @param GebaeudeDoc
	 * @throws NoGebaudeDocException 
	 */
	public void createGebaeudeDoc(GebaeudeDoc actual) throws NoGebaudeDocException {
		if(actual==null) throw new NoGebaudeDocException();
		doc=new Document();
		Document gmit=null,
				bmit=null;
		doc.append("gNr", actual.getgNr());
		doc.append("name", actual.getName());
		doc.append("strasse", actual.getStrasse());
		doc.append("strassenNr", actual.getStrassenNr());
		doc.append("ort", actual.getOrt());
		doc.append("plz", actual.getPlz());
		doc.append("unternehmen", actual.getUnternehmen());
		List<Buero> bueroList=actual.getBuerogebaudeList();
		ArrayList<Document> geb=new ArrayList<>();
		ArrayList<Integer> mitList=new ArrayList<>();
		// wenn bueros dabei, mit mitarbeiterliste hinzufügen
		if(bueroList==null) {
			doc.append("buero", null);
		} else {
			for( Buero bueroTemp:bueroList) {
				assert (actual.getgNr()!=bueroTemp.getgNr()):"Falsches Buero in GebaeudeDoc";
				bmit=new Document();
				mitList=new ArrayList<>();
				int bnum=bueroTemp.getbNr();
				bmit.append("bNr", bnum);
				for( Buero Temp:bueroList) {
					if(bnum==Temp.getbNr() ) { 
						mitList.add(Temp.getBueroangestellter());
					}
				}
				bmit.append("mitarbeiter_buero", mitList);
				geb.add(bmit);
			}
			doc.append("buero", geb);
			//geb.clear();
		}
		List<Garage> garageList=actual.getGaragenList();
		// wenn garagen dabei, mit mitarbeiterliste hinzufügen
		if(garageList==null) {
			doc.put("garage", null);
		} else {
			for( Garage garageTemp:garageList) {
				gmit=new Document();
				mitList=new ArrayList<>();
				assert (actual.getgNr()!=garageTemp.getgNr()):"Falsches Buero in GebaeudeDoc";
				int gnr=garageTemp.getGaragenr();
				gmit.append("garageNr", gnr);
				for(Garage x : garageList) {
					if(gnr==x.getGaragenr()	) {
						mitList.add(x.getMechaniker());
					}
				}
				gmit.append("mitarbeiter_garage", mitList);
				geb.add(gmit);
			}
			doc.append("garage", geb);
			//geb.clear();
		}
	}
	/**
	 * returns the BasicDBObject initialized in the constructor
	 * @return
	 */
	public Document getDoc() {
		return this.doc;
	}

}
