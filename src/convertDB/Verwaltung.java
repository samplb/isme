/**
 * 
 */
package convertDB;

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
		/*final MongoDBConnection connection= new MongoDBConnection();
		try {
			connection.connect();
		} catch (Exception e) {
			System.err.println("MongoDBConnectionError: "+e.getMessage());
		}
		System.out.println("Verbindung mit MongoDB Datenbank hergestellt....."  
				+"\n Datenconvertierung von mySql amstec erfolgt");*/
		Converter hexTest=new Converter();
		hexTest.createMongoCollectionGebaeude();
		System.out.println(3^2);

	}

}
