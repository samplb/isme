package convertDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import constants.ConnectionTerms;
import constants.QueryTerms;
import database.DBConnection;
import modelsMongoDb.*;
import modelsMongoDb.Exceptions.NoFahrzeugDocException;
import modelsMongoDb.Exceptions.NoGebaeudeDocException;
import modelsMongoDb.Exceptions.NoGebaudeDocException;
import modelsMongoDb.createDocs.CreateFahrzeugDoc;
import modelsMongoDb.createDocs.CreateGebaeudeDoc;
import modelsMongoDb.createDocs.CreateGebaeudeDocBasicVersion;
import modelsMongoDb.createDocs.CreateMitarbeiterDoc;
import modelsSQL.Buero;
import modelsSQL.Garage;

public class Converter {
	final private DBConnection x=new DBConnection();
	final private MongoDBConnection mongoConnection=new MongoDBConnection();
	private MongoClient mongoclient=null;
	private MongoDatabase mongoDb=null;
	private Connection sqlconnection=null;
	public Converter() {
		try {
			x.connect();
			sqlconnection=x.getConnection();
			mongoConnection.connect();
			mongoDb=mongoConnection.createDatabase(ConnectionTerms.DATABASENAME);
//un-comment if database is already filled and you want to reset.
			//mongoConnection.dropDatabase(mongoDb);
			mongoclient=mongoConnection.getConnection();
			
		} catch (Exception e) {
			System.out.println("ConnectionError: "+e.getMessage());
		} 
	}
	/**
	 * creates a List with GebaudeDoc objects according to sql connection specified in DBConnection.getConnection().
	 * @return List<GebaeudeDoc>
	 * @throws Exception 
	 */
	public void reset() throws Exception {
			mongoConnection.dropDatabase(mongoDb);
	}
	public List<GebaeudeDoc> loadCollectionGebaeude() {
		List<GebaeudeDoc> gebaeudeDocsList=new ArrayList<>();;
		GebaeudeDoc created=null;
		PreparedStatement gebaeudeStmt=null,
			 bueroMitStmt=null,
			 garageMitStmt=null;
		try {
			Garage x=null;
			Buero y=null;
			ArrayList<Garage> g=null;
			ArrayList<Buero> b=null;
			gebaeudeStmt=sqlconnection.prepareStatement(QueryTerms.queryAllGebaeude);
			bueroMitStmt=sqlconnection.prepareStatement(QueryTerms.queryBueroMit);
			garageMitStmt=sqlconnection.prepareStatement(QueryTerms.queryGarageMit);
			ResultSet gebaeudeDoc=gebaeudeStmt.executeQuery();
			while(gebaeudeDoc.next()) {
				b= new ArrayList<>();
				g=new ArrayList<>();
				if(created!=null) {gebaeudeDocsList.add(created);}
				created=new GebaeudeDoc();
				created.setgNr(gebaeudeDoc.getInt(2));
				created.setName(gebaeudeDoc.getString(1));
				created.setStrasse(gebaeudeDoc.getString(3));
				created.setOrt(gebaeudeDoc.getString(6));
				created.setPlz(gebaeudeDoc.getInt(5));
				created.setStrassenNr(gebaeudeDoc.getInt(4));
				created.setUnternehmen(ConnectionTerms.UNTERNEHMENSNAME);
				//fragt nach gnr ab
				garageMitStmt.setInt(1, gebaeudeDoc.getInt(2));
				ResultSet garageMitSet=garageMitStmt.executeQuery();
				boolean foundGarage=false;
				while(garageMitSet.next()) {
					x=new Garage();
					x.setGaragenr(garageMitSet.getInt(3));
					x.setgNr(garageMitSet.getInt(1));
					x.setMechaniker(garageMitSet.getInt(2));
					g.add(x);
					foundGarage=true;
				}
				//fragt nach gnr ab
				bueroMitStmt.setInt(1, gebaeudeDoc.getInt(2));
				ResultSet bueroMitSet=bueroMitStmt.executeQuery();
				boolean foundBuero=false;
				while(bueroMitSet.next()) {
					y=new Buero();
					y.setbNr(bueroMitSet.getInt(1));
					y.setgNr(bueroMitSet.getInt(3));
					y.setBueroangestellter(bueroMitSet.getInt(2));
					b.add(y);
					foundBuero=true;
				}
				if(foundBuero) {
					created.setGaragenList(null);
					created.setBuerogebaudeList(b);
				} else if(foundGarage) {
					created.setGaragenList(g);
					created.setBuerogebaudeList(null);
				} else {
					created.setGaragenList(null);
					created.setBuerogebaudeList(null);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error in createMongoCollectionGebaeude:"+e.getMessage());
		}
		return gebaeudeDocsList;
	}

	/**
	 * creates a List with FahrzeugDoc objects according to Mysql connection specified in DBConnection.getConnection().
	 * @return List<FahrzeugDoc>
	 */
	public List<FahrzeugDoc> loadMongoCollectionFahrzeug() {
		ArrayList<FahrzeugDoc> fahrzeugDocsList=new ArrayList<>();
		FahrzeugDoc fahrzeugDoc= null;
		PreparedStatement fahrzeugStmt=null;
		try {
			fahrzeugStmt=sqlconnection.prepareStatement(QueryTerms.queryAllFahrzeug);
			ResultSet fahrzeugSet=fahrzeugStmt.executeQuery();
			while(fahrzeugSet.next()) {
				if(fahrzeugDoc!=null) fahrzeugDocsList.add(fahrzeugDoc);
				fahrzeugDoc=new FahrzeugDoc();
				fahrzeugDoc.setMarke(fahrzeugSet.getString(1));
				fahrzeugDoc.setBaujahr(fahrzeugSet.getInt(2));
				fahrzeugDoc.setKennzeichen(fahrzeugSet.getInt(3));
				fahrzeugDoc.setBueromitarbeiter(fahrzeugSet.getInt(5));
				fahrzeugDoc.setMechaniker(fahrzeugSet.getInt(4));
			}
		} catch(SQLException e) {
			System.err.println("Error in loadMongoCollectionFahrzeug: "+e.getMessage());
		}
		return fahrzeugDocsList;
	}

	/**
	 * creates a List with GebaudeDoc objects according to Mysql connection specified in DBConnection.getConnection().
	 * @return List<MitarbeiterDoc>
	 */
	public List<MitarbeiterDoc> loadMongoCollectionMitarbeiter() {
		MitarbeiterDoc mitarbeiterDoc = null;
		ArrayList<MitarbeiterDoc> mitarbeiterDocsList=new ArrayList<>();
		PreparedStatement mitarbeiterStmt=null,
				mechanikerStmt=null,
				chefStmt=null,
				bueromitarbeiterStmt=null;
		try {
			mitarbeiterStmt=sqlconnection.prepareStatement(QueryTerms.queryAllMitarbeiter);
			ResultSet mitRes=mitarbeiterStmt.executeQuery();
			while(mitRes.next()) {
				if(mitarbeiterDoc!=null) {mitarbeiterDocsList.add(mitarbeiterDoc);}
				mitarbeiterDoc=new MitarbeiterDoc();
				mitarbeiterDoc.setmNr(mitRes.getInt(3));
				mitarbeiterDoc.setUnternehmen(ConnectionTerms.UNTERNEHMENSNAME);
				mitarbeiterDoc.setNachname(mitRes.getString(2));
				mitarbeiterDoc.setVorname(mitRes.getString(1));
				//getChef
				chefStmt=sqlconnection.prepareStatement(QueryTerms.queryChef);
				ResultSet chefSet=chefStmt.executeQuery();
				while(chefSet.next()) {
					mitarbeiterDoc.setChef(chefSet.getInt(1));
				}
				boolean mechanikerBoolean=false;
				//get as Mechaniker
				mechanikerStmt=sqlconnection.prepareStatement(QueryTerms.queryMechaniker);
				mechanikerStmt.setInt(1, mitRes.getInt(3));
				ResultSet mechanikerSet=mechanikerStmt.executeQuery();
				while(mechanikerSet.next()) {
					if(mechanikerSet.getInt(4)==mitarbeiterDoc.getmNr()) {
						mechanikerBoolean=true;
						mitarbeiterDoc.setAnstellung(QueryTerms.MECHANIKER);
						mitarbeiterDoc.setSvNr(mechanikerSet.getInt(1));
						mitarbeiterDoc.setGehalt(mechanikerSet.getInt(2));
						mitarbeiterDoc.setTelefonnummer(((Integer)mechanikerSet.getInt(3)).toString());
					}
				}
				if(mechanikerBoolean) {continue;}
				//get as Bueromitarbeiter
				bueromitarbeiterStmt=sqlconnection.prepareStatement(QueryTerms.queryBueroMitarbeiter);
				bueromitarbeiterStmt.setInt(1, mitRes.getInt(3));
				ResultSet bueroMitarbeiterSet=bueromitarbeiterStmt.executeQuery();
				while(bueroMitarbeiterSet.next()) {
					mitarbeiterDoc.setAnstellung(QueryTerms.BUEROMITARBEITER);
					mitarbeiterDoc.setSvNr(bueroMitarbeiterSet.getInt(3));
					mitarbeiterDoc.setGehalt(bueroMitarbeiterSet.getInt(1));
					mitarbeiterDoc.setTelefonnummer(((Integer)bueroMitarbeiterSet.getInt(2)).toString());
				}
			}
		} catch(SQLException e) {
			System.err.println("Error in loadMongoCollectionMitarbeiter: "+e.getMessage());
		} 
		return mitarbeiterDocsList;
	}
	/**
	 * erzeugt ein Fahrzeug-Dokument, dass in einer mongoDB Datenbank genutzt werden kann
	 * @param fahrzeugDoc
	 * @return BasicDBObject
	 * @throws NoFahrzeugDocException 
	 */
	public Document createFahrzeugDocument(FahrzeugDoc fahrzeugDoc) throws NoFahrzeugDocException {
		CreateFahrzeugDoc fx=new CreateFahrzeugDoc();
		fx.createFahrzeugDoc(fahrzeugDoc);
		return fx.getDoc();
	}
	/**
	 * erzeugt ein Mitarbeiter-Dokument, dass in einer mongoDB Datenbank genutzt werden kann
	 * @param mitarbeiterDoc
	 * @return
	 * @throws NoGebaeudeDocException 
	 */
	public Document createMitarbeiterDocument(MitarbeiterDoc mitarbeiterDoc) throws NoGebaeudeDocException {
			CreateMitarbeiterDoc mx=new CreateMitarbeiterDoc();
			mx.createMitarbeiterDoc(mitarbeiterDoc);
			return mx.getDoc();
		}
	/**
	 * erzeugt ein Gebaeude-Dokument, dass in einer mongoDB Datenbank genutzt werden kann
	 * @param gebaeudeDoc
	 * @return BasicDBObject
	 * @throws NoGebaudeDocException 
	 */
	public Document createPrettyGebaeudeDocument(GebaeudeDoc gebaeudeDoc) throws NoGebaudeDocException {
		CreateGebaeudeDoc gx=new CreateGebaeudeDoc();
		gx.createGebaeudeDoc(gebaeudeDoc);
		return gx.getDoc();
	}
	
	public List<Document> createBasicGebaeudeDocument(GebaeudeDoc gebaeudeDoc) throws NoGebaudeDocException {
		CreateGebaeudeDocBasicVersion gx=new CreateGebaeudeDocBasicVersion();
		gx.createGebaeudeDoc(gebaeudeDoc);
		return gx.getDoc();
	}
	
	
//Depracated Methods:
	/**
	 * @deprecated
	 * Erzeugt einen Hexadezimalstring vom integer für _id
	 * @param x
	 * @return
	 */
	private String convertDecTo24Hex(int x) {
		String f="";
		ArrayList<Integer> hexTemp=new ArrayList<>();
		int l=x;
		//aufbrechen zur konvertierung
		for(;l>0;) {
			hexTemp.add(l%16);
			l=l/16;
		}
		//erzeugung des24 byte hexadecimal strings. funktioniert
		Collections.reverse(hexTemp); 
		for(int i=0;i<24;i++) {
			if(hexTemp.size()>i) {
				int tempInt=hexTemp.get(i);
				if(tempInt<10) {
					f=f+tempInt;
				} else {
					switch(tempInt) {
					case(10): {f+="a";break;}
					case(11) : {f+="b";break;}
					case(12) : {f+="c";break;}
					case(13) : {f+="d";break;}
					case(14) : {f+="e";break;}
					case(15) : {f+="f";break;}
					}
				}
			} else {
				f+=0;
			}
		} 
		return f;
	}
	/**
	 * @deprecated
	 * erzeugt ein ObjectId aus dem int
	 * @param x
	 * @return
	 */
	private ObjectId get_ID(int x) {
		return new ObjectId(convertDecTo24Hex(x));
	}
	/**
	 * @deprecated
	 * konvertiert24 byte Hexadezimal zu Dezimal
	 * @param x
	 * @return
	 */
	private int convert24HexToDec(ObjectId x) {
		String convert=x.toString();
		new ArrayList<>();
		char [] t=convert.toCharArray();
		convert="";
		for(int i=0;i<24;i++) {
			if(checkNull(t, i)) {
				convert+=t[i];
			} else {
				break;
			}
		}
		return Integer.parseInt(convert, 16);
	}
	/**
	 * @deprecated
	 * returns true if the next 9 positions aren't just zeros.
	 * @param x
	 * @param i
	 * @return
	 */
	private boolean checkNull(char[] x, int i) {
		if(    ((String)(""+x[i])).equalsIgnoreCase("0") 
			&& ((String)(""+x[i+1])).equalsIgnoreCase("0") 
			&& ((String)(""+x[i+2])).equalsIgnoreCase("0") 
			&& ((String)(""+x[i+3])).equalsIgnoreCase("0") 
			&& ((String)(""+x[i+4])).equalsIgnoreCase("0") 
			&& ((String)(""+x[i+5])).equalsIgnoreCase("0") 
			&& ((String)(""+x[i+6])).equalsIgnoreCase("0") 
			&& ((String)(""+x[i+7])).equalsIgnoreCase("0") 
			&& ((String)(""+x[i+8])).equalsIgnoreCase("0") 
			&& ((String)(""+x[i+9])).equalsIgnoreCase("0") ){
				return false;
			} else {
				return true;
			}
	}
}
