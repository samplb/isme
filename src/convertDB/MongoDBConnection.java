package convertDB;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import constants.ConnectionTerms;
import modelsMongoDb.Exceptions.NoDatabaseFoundException;

public class MongoDBConnection {
	private MongoClient mongoConnection;
	MongoDatabase db=null;
	
	public void connect() throws Exception {
		mongoConnection = new MongoClient( ConnectionTerms.HOST , 27017 );
	}
	public void disconnect() throws Exception {
		    if (null != mongoConnection) {
		    	mongoConnection.close();
		    }	
	}
	/**
	 * creates a Database with name of param databaseName
	 * @param databaseName
	 * @return
	 * @throws Exception
	 */
	public MongoDatabase createDatabase(String databaseName) throws Exception {
		db=mongoConnection.getDatabase(databaseName);
		return this.db;
	}
	/**
	 * drops Database x
	 * @param x
	 * @throws Exception
	 */
	public void dropDatabase(MongoDatabase x) throws Exception {
		x.drop();
	}
	public MongoClient getConnection() {
		return this.mongoConnection;
	}
	/**
	 * creates Fahrzeug-Collection
	 * @param collectionName
	 * @throws NoDatabaseFoundException
	 */
	public MongoCollection<Document> createNewFahrzeugCollection() throws NoDatabaseFoundException {
		if(db==null) throw new NoDatabaseFoundException();
		MongoCollection<Document> fahrzeug=db.getCollection(ConnectionTerms.COLLECTION_FAHRZEUG);
		if(fahrzeug != null) {
		fahrzeug.drop();
		} else {
			db.createCollection(ConnectionTerms.COLLECTION_FAHRZEUG);
			fahrzeug=db.getCollection(ConnectionTerms.COLLECTION_FAHRZEUG);
		}
		return fahrzeug;
	}
	/**
	 * creates mitarbeiter-Collection
	 * @param collectionName
	 * @throws NoDatabaseFoundException
	 */
	public MongoCollection<Document> createNewMitarbeiterCollection() throws NoDatabaseFoundException {
		if(db==null) throw new NoDatabaseFoundException();
		MongoCollection<Document> mitarbeiter=db.getCollection(ConnectionTerms.COLLECTION_MITARBEITER);
		if(mitarbeiter != null) {
			mitarbeiter.drop();
		} else {
			db.createCollection(ConnectionTerms.COLLECTION_MITARBEITER);
			mitarbeiter=db.getCollection(ConnectionTerms.COLLECTION_MITARBEITER);
		}
		return mitarbeiter;
	}
	/**
	 * creates gebaeude-Collection
	 * @param collectionName
	 * @throws NoDatabaseFoundException
	 */
	public MongoCollection<Document> createNewGebaeudeCollection() throws NoDatabaseFoundException {
		if(db==null) throw new NoDatabaseFoundException();
		MongoCollection<Document> gebaeude=db.getCollection(ConnectionTerms.COLLECTION_GEBAEUDE);
		if(gebaeude != null) {
			gebaeude.drop();
		} else {
			db.createCollection(ConnectionTerms.COLLECTION_GEBAEUDE);
			gebaeude=db.getCollection(ConnectionTerms.COLLECTION_GEBAEUDE);
		}
		return gebaeude;
	}
}
