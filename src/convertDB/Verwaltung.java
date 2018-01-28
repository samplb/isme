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
	public static void main(String args) {
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
			ArrayList<Document> gDocList=new ArrayList<>();
			ArrayList<Document> mDocList=new ArrayList<>();
			ArrayList<Document> fDocList=new ArrayList<>();
			if(args.matches("basicmongo")) {
				for (GebaeudeDoc a:conv.loadCollectionGebaeude()) {
					gDocList.addAll(conv.createBasicGebaeudeDocument(a));
				}
				for(MitarbeiterDoc b:conv.loadMongoCollectionMitarbeiter()) {
					mDocList.add(conv.createMitarbeiterDocument(b));
				}
				for(FahrzeugDoc c:conv.loadMongoCollectionFahrzeug()) {
					fDocList.add(conv.createFahrzeugDocument(c));
				}
				gebaeudeColl.insertMany(gDocList);
				System.out.println("insertBasicGebaeudeCollection");
				mitarbeiterColl.insertMany(mDocList);
				System.out.println("insertBasicMitarbeiterCollection");
				fahrzeugColl.insertMany(fDocList);
				System.out.println("insertBasicFahrzeugCollection");
			} else if(args.matches("prettymongo")) {
				for (GebaeudeDoc a:conv.loadCollectionGebaeude()) {
					gDocList.add(conv.createPrettyGebaeudeDocument(a));
				}
				for(MitarbeiterDoc b:conv.loadMongoCollectionMitarbeiter()) {
					mDocList.add(conv.createMitarbeiterDocument(b));
				}
				for(FahrzeugDoc c:conv.loadMongoCollectionFahrzeug()) {
					fDocList.add(conv.createFahrzeugDocument(c));
				}
				gebaeudeColl.insertMany(gDocList);
				System.out.println("insertPrettyGebaeudeCollection");
				mitarbeiterColl.insertMany(mDocList);
				System.out.println("insertPrettyMitarbeiterCollection");
				fahrzeugColl.insertMany(fDocList);
				System.out.println("insertPrettyFahrzeugCollection");
			} else {
				System.out.println("no option available");
			}
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
	public static void reset() throws Exception {
		Converter conv=new Converter();
		conv.reset();
	}
}
