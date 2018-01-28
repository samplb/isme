package modelsMongoDb.createDocs;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.sun.accessibility.internal.resources.accessibility_zh_HK;

import modelsMongoDb.GebaeudeDoc;
import modelsMongoDb.Exceptions.NoGebaudeDocException;
import modelsSQL.Buero;
import modelsSQL.Garage;
/**
 * creates a BasicDBObject for Collection.insert()
 * @author bs
 *
 */
public class CreateGebaeudeDocBasicVersion {

	private List<Document> docList;
	private Document doc;
	int buerosCounter=0;
	int mitarbeiterCounter=0;
	/**
	 * erzeugt das Dokument, abfrage mit getDoc()
	 * @param GebaeudeDoc
	 * @throws NoGebaudeDocException 
	 */
	public void createGebaeudeDoc(GebaeudeDoc actual) throws NoGebaudeDocException {
		if(actual==null) throw new NoGebaudeDocException();
		docList=new ArrayList<Document>();
		List<Buero> bueroList=actual.getBuerogebaudeList();
		// wenn bueros dabei, mit mitarbeiterliste hinzufügen
		if(bueroList!=null) {
			for( Buero bueroTemp:bueroList) {
				assert (actual.getgNr()!=bueroTemp.getgNr()):"Falsches Buero in GebaeudeDoc";
				int bnum=bueroTemp.getbNr();
				for( Buero Temp:bueroList) {
					if(bnum==Temp.getbNr() ) { 
						doc=new Document();
						doc.append("name", actual.getName());
						doc.append("strasse", actual.getStrasse());
						doc.append("strassenNr", actual.getStrassenNr());
						doc.append("ort", actual.getOrt());
						doc.append("plz", actual.getPlz());
						doc.append("unternehmen", actual.getUnternehmen());
						doc.append("buero", bnum);
						doc.append("garage", null);
						doc.append("mitarbeiter",Temp.getBueroangestellter());
						docList.add(doc);
					}
				}
			}
		}
		List<Garage> garageList=actual.getGaragenList();
		// wenn garagen dabei, mit mitarbeiterliste hinzufügen
		if(garageList!=null) {
			for( Garage garageTemp:garageList) {
				assert (actual.getgNr()!=garageTemp.getgNr()):"Falsches Buero in GebaeudeDoc";
				int gnr=garageTemp.getGaragenr();
				for(Garage x : garageList) {
					if(gnr==x.getGaragenr()	) {
						doc=new Document();
						doc.append("name", actual.getName());
						doc.append("strasse", actual.getStrasse());
						doc.append("strassenNr", actual.getStrassenNr());
						doc.append("ort", actual.getOrt());
						doc.append("plz", actual.getPlz());
						doc.append("unternehmen", actual.getUnternehmen());
						doc.append("buero", null);
						doc.append("garage", gnr);
						doc.append("mitarbeiter",x.getMechaniker());
						docList.add(doc);
					}
				}
			}
		}
	}
	/**
	 * returns the BasicDBObject initialized in the constructor
	 * @return
	 */
	public List<Document> getDoc() {
		return this.docList;
	}

}
