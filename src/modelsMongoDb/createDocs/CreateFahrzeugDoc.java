package modelsMongoDb.createDocs;

import org.bson.Document;

import com.mongodb.BasicDBObject;

import modelsMongoDb.FahrzeugDoc;
import modelsMongoDb.Exceptions.NoFahrzeugDocException;
/**
 * creates a BasicDBObject for Collection.insert()
 * @author bs
 *
 */
public class CreateFahrzeugDoc {

	private Document doc;
	/**
	 * erzeugt das Dokument, abfrage mit getDoc()
	 * @param FahrzeugDoc
	 * @throws NoFahrzeugDocException 
	 */
	public void createFahrzeugDoc(FahrzeugDoc actual) throws NoFahrzeugDocException {
		if(actual==null) throw new NoFahrzeugDocException();
		doc=new Document();
		doc.append("marke", actual.getMarke());
		doc.append("baujahr", actual.getBaujahr());
		doc.append("kennzeichen", actual.getKennzeichen());
		doc.append("mNr_mechaniker", actual.getMechaniker());
		doc.append("mNr_bueromitarbeiter", actual.getBueromitarbeiter());
	}
	/**
	 * returns the BasicDBObject initialized in the constructor
	 * @return
	 */
	public Document getDoc() {
		return this.doc;
	}

}
