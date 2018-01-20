/**
 * 
 */
package convertDB;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import constants.ConnectionTerms;
import modelsMongoDb.FahrzeugDoc;
import modelsMongoDb.GebaeudeDoc;
import modelsMongoDb.MitarbeiterDoc;
import modelsMongoDb.Exceptions.NoFahrzeugDocException;
import modelsMongoDb.Exceptions.NoGebaeudeDocException;
import modelsMongoDb.Exceptions.NoGebaudeDocException;

/**
 * @author bs
 *
 */
public class Verwaltung {

	/**
	 * Liest die Daten der Mysql DB aus und fügt sie als mongodb ein
	 * @param args
	 */
	public static void main(String[] args) {
		final MongoDBConnection mongoConnection= new MongoDBConnection();
		MongoCollection<Document> fahrzeugColl=null;
		MongoCollection<Document> mitarbeiterColl=null;
		MongoCollection<Document> gebaeudeColl=null;
		try {
			mongoConnection.connect();
			mongoConnection.createDatabase(ConnectionTerms.DATABASENAME);
			fahrzeugColl=mongoConnection.createNewFahrzeugCollection();
			gebaeudeColl=mongoConnection.createNewGebaeudeCollection();
			mitarbeiterColl=mongoConnection.createNewMitarbeiterCollection();
		} catch (Exception e) {
			System.err.println("MongoDBConnectionError: "+e.getMessage());
		}
		System.out.println("Verbindung mit MongoDB Datenbank hergestellt....."  
				+"\n Datenconvertierung von mySql amstec erfolgt");
		Converter conv=new Converter();
		try {
			List<GebaeudeDoc> gList=conv.loadCollectionGebaeude();
			List<MitarbeiterDoc> mList=conv.loadMongoCollectionMitarbeiter();
			List<FahrzeugDoc> fList=conv.loadMongoCollectionFahrzeug();
			ArrayList<Document> gDocList=new ArrayList<>();
			ArrayList<Document> mDocList=new ArrayList<>();
			ArrayList<Document> fDocList=new ArrayList<>();
			for (GebaeudeDoc a:gList) {
				gDocList.add(conv.createGebaeudeDocument(a));
			}
			for(MitarbeiterDoc b:mList) {
				mDocList.add(conv.createMitarbeiterDocument(b));
			}
			for(FahrzeugDoc c:fList) {
				fDocList.add(conv.createFahrzeugDocument(c));
			}
			gebaeudeColl.insertMany(gDocList);
			System.out.println("insertGebaeudeCollection");
			mitarbeiterColl.insertMany(mDocList);
			System.out.println("insertMitarbeiterCollection");
			fahrzeugColl.insertMany(fDocList);
			System.out.println("insertFahrzeugCollection");
		}catch (NoGebaudeDocException e) {
			e.printStackTrace(System.err);
		} catch (NoGebaeudeDocException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
		} catch (NoFahrzeugDocException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
		}
	}

}
