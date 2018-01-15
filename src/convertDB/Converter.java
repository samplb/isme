package convertDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;

import database.DBConnection;
import modelsMongoDb.*;
import modelsSQL.*;

public class Converter {
	final private DBConnection x=new DBConnection();
	final private MongoDBConnection mongoConnection=new MongoDBConnection();
	private MongoClient mongoclient=null;
	private Connection connection=null;
	private PreparedStatement stm=null;
	public Converter() {
		try {
			x.connect();
			connection=x.getConnection();
			mongoclient=mongoConnection.getConnection();
		} catch (Exception e) {
			System.out.println("ConnectionError: "+e.getMessage());
		}
	}
	private ResultSet loadMySQL(String query) {
		ResultSet ret=null;
		try {
			stm=connection.prepareStatement(query);
			ret= stm.executeQuery();
		} catch (SQLException e) {
			System.out.println("StatementGebaeude: "+e.getMessage());
		}
			return ret;
	}
	public GebaeudeDoc createMongoCollectionGebaeude() {
		/*Document r=new Document();
		GebaeudeDoc created=new GebaeudeDoc();
		try {
			String query="SELECT * FROM mitarbeiter";
			Garage x=null;
			Buero y=null;
			ArrayList<Garage> g=new ArrayList<>();
			ArrayList<Buero> b= new ArrayList<>();
			ResultSet gebaeudeDoc=loadMySQL(query);
			gebaeudeDoc.first();
			created.setgNr(gebaeudeDoc.getInt(2));
			created.setName(gebaeudeDoc.getString(1));
			created.setStrasse(gebaeudeDoc.getString(3));
			created.setOrt(gebaeudeDoc.getString(6));
			created.setPlz(gebaeudeDoc.getInt(5));
			created.setStrassenNr(gebaeudeDoc.getInt(4));
			created.setuNr(gebaeudeDoc.getInt(7));
			query="SELECT * FROM garagemit";
			gebaeudeDoc=loadMySQL(query);
			while(gebaeudeDoc.next()) {
				x=new Garage();
				x.setGaragenr(gebaeudeDoc.getInt(3));
				x.setgNr(gebaeudeDoc.getInt(1));
				x.setMechaniker(gebaeudeDoc.getInt(2));
				g.add(x);
			}
			query="SELECT * FROM bueromit";
			gebaeudeDoc=loadMySQL(query);
			while(gebaeudeDoc.next()) {
				y=new Buero();
				y.setbNr(gebaeudeDoc.getInt(1));
				y.setgNr(gebaeudeDoc.getInt(3));
				y.setBueroangestellter(gebaeudeDoc.getInt(2));
				b.add(y);
			}
			created.setGaragen(g);
			created.setBuerogebaude(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		*/
		System.out.println(convertDecTo24Hex(9999));
		System.out.println(convert24HexToDec(get_ID(9999)));
		return null;
	}
	public Document createMongoCollectionFahrzeug() {
		//TODO 
		return null;
	}
	public Document createMongoCollectionMitarbeiter() {
		//TODO 
		return null;
	}
	public Document createMongoCollectionUnternehmen() {
		//TODO 
		return null;
	}
	/**
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
	 * erzeugt ein ObjectId aus dem int
	 * @param x
	 * @return
	 */
	private ObjectId get_ID(int x) {
		return new ObjectId(convertDecTo24Hex(x));
	}
	private int convert24HexToDec(ObjectId x) {
		int summe=0;
		String convert=x.toString();
		ArrayList<String> tempChar= new ArrayList<>();
		char [] t=convert.toCharArray();
		convert="";
		for(int i=0;i<24;i++) {
			String chart=""+t[i];
			if(checkNull(t, i)) {
				convert+=t[i];
			} else {
				break;
			}
		}
		return Integer.parseInt(convert, 16);
	}
	/**
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
