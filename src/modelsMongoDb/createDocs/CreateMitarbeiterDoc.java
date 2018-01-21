package modelsMongoDb.createDocs;

import org.bson.Document;

import modelsMongoDb.MitarbeiterDoc;
import modelsMongoDb.Exceptions.NoGebaeudeDocException;
/**
 * creates a BasicDBObject for Collection.insert()
 * @author bs
 *
 */
public class CreateMitarbeiterDoc {

	private Document doc;
	/**
	 * erzeugt das Dokument, abfrage mit getDoc()
	 * @param MitarbeiterDoc
	 */
	/**
	 * @param MitarbeiterDoc
	 */
	public void createMitarbeiterDoc(MitarbeiterDoc actual) throws NoGebaeudeDocException {
		if(actual==null) throw new NoGebaeudeDocException();
		doc=new Document();
		doc.append("mNr", actual.getmNr());
		doc.append("name", actual.getVorname());
		doc.append("nachname", actual.getNachname());
		doc.append("unternehmen", actual.getUnternehmen());
		doc.append("chef", actual.getChef());
		doc.append("anstellung", actual.getAnstellung());
		doc.append("svnr", actual.getSvNr());
		doc.append("gehalt", actual.getGehalt());
		doc.append("telefonnummmer", actual.getTelefonnummer());
	}
	/**
	 * returns the BasicDBObject initialized in the constructor
	 * @return
	 */
	public Document getDoc() {
		return this.doc;
	}

}
