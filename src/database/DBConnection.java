/**

 * 
 */
package database;

import java.sql.*;

import constants.MySqlTerms;

/**
 * @author bs
 *
 */
public class DBConnection {


private final String url = "jdbc:mysql://127.0.0.1:3306/amstec";
private final String user = "imse", pass = "imse";
private Connection conn = null;

	public void connect() throws SQLException, InstantiationException, IllegalAccessException
	{
	  try
	  {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    conn = DriverManager.getConnection(url, user, pass);
	  }
	  catch(ClassNotFoundException x)
	  {
		  x.getMessage();
	    System.err.println("com.mysql.jdbc.Driver nicht installiert");
	  }
	}
	public void createTables() throws SQLException {
		String[] create=MySqlTerms.createStatementsMySQL;
		Statement stm=conn.createStatement();
		for(String x:create) {
			stm.executeUpdate(x);
		}
	}
	public void disconnect() throws SQLException
	{
	
	  if(conn != null)
	  {
	    if(!conn.isClosed())
	      conn.close();
	    conn = null;
	  }
	}
	public void dropTables() throws SQLException {
		String[] dropStatements= MySqlTerms.dropStatementsMySQL;
		Statement stm=conn.createStatement();
		for(String x:dropStatements) {
			stm.executeUpdate(x);
		}
	}
	public Connection getConnection() {
		return this.conn;
	}
	public int neuerMitarbeiter(String vorname, String nachname, int unr) {
		
		//tet
		try {
			final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
					+ "`mitarbeiter`(`name`, `nachname`, `unr`) VALUES (?, ?, ?)");
		    insert.setString(1, vorname);
		    insert.setString(2, nachname);
		    insert.setInt(3, unr);
		    insert.execute();
		    insert.close();
		    
		    final PreparedStatement kundennummerQuery = conn.prepareStatement("SELECT MAX(mnr) AS mnr FROM mitarbeiter");
		    ResultSet generatedKeys = kundennummerQuery.executeQuery();
		    generatedKeys.next();
		    int lastKundennummer = generatedKeys.getInt("mnr");
		    generatedKeys.close();
		    kundennummerQuery.close();
		    return lastKundennummer;
		  
			
		} catch(SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
		}
		return -1;
	}
	public int neuesUnternehmen(String name, int nr) {
		try {
			final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
					+ "`unternehmen`(`name`,`unr`) VALUES (?, ?)");
		    insert.setString(1, name);
		    insert.setInt(2, 123);
		    insert.execute();
		    insert.close();
		    
		    final PreparedStatement kundennummerQuery = conn.prepareStatement("SELECT MAX(unr) "
		    		+ "AS unr FROM unternehmen");
		    ResultSet generatedKeys = kundennummerQuery.executeQuery();
		    generatedKeys.next();
		    int lastUnrnummer = generatedKeys.getInt("unr");
		    generatedKeys.close();
		    kundennummerQuery.close();
		    return lastUnrnummer;
		  
			
		} catch(SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
		}
		return -1;
	}
	public int neuesGebaeude(String name, String strasse, String ort, int plz, int snr, int unr) {
		try {
			final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
					+ "`gebaeude`(`name`, `strasse`, `snr`, `plz`, `ort`, `unr`) VALUES (?, ?, ?, ?, ?, ?)");
		    insert.setString(1, name);
		    insert.setString(2, strasse);
		    insert.setString(5, ort);
		    insert.setInt(3, snr);
		    insert.setInt(4, plz);
		    insert.setInt(6, unr);
		    insert.execute();
		    insert.close();
		    
		    final PreparedStatement nummerQuery = conn.prepareStatement("SELECT "
		    		+ "MAX(gnr) AS gnr FROM gebaeude");
		    ResultSet generatedKeys = nummerQuery.executeQuery();
		    generatedKeys.next();
		    int nummer = generatedKeys.getInt("gnr");
		    generatedKeys.close();
		    nummerQuery.close();
		    return nummer;
		  
			
		} catch(SQLException e) {
			System.err.println("SQLExceptionGeb: "+e.getMessage()+"_state:_"+e.getSQLState());
		}
		return -1;
	}
	public int neuesBuero(int bnr, int anzahlmit, int gebäudeNr) {
		try {
			final PreparedStatement insert = conn.prepareStatement("INSERT INTO `buero`(`bnr`, `anzahlmit`,`gnr`) VALUES (?, ?, ?)");
		    insert.setInt(1, bnr);
		    insert.setInt(2, anzahlmit);
		    insert.setInt(3, gebäudeNr);
		    insert.execute();
		    insert.close();
		    
		    final PreparedStatement nummerQuery = conn.prepareStatement("SELECT "
		    		+ "MAX(bnr) AS bnr FROM buero");
		    ResultSet generatedKeys = nummerQuery.executeQuery();
		    generatedKeys.next();
		    int lastUnrnummer = generatedKeys.getInt("bnr");
		    generatedKeys.close();
		    nummerQuery.close();
		    return lastUnrnummer;
		  
			
		} catch(SQLException e) {
			  System.err.println("SQLExceptionBüro: "+e.getMessage()+"_state:_"+e.getSQLState());
		}
		return -1;
	}
	public int neueGarage(int garnr, int anzahlmit, int gebäudeNr) {
		try {
			final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
					+ "`garage`(`garagenr`, `anzahlmit`,`gnr`) VALUES (?, ?, ?)");
		    insert.setInt(1, garnr);
		    insert.setInt(2, anzahlmit);
		    insert.setInt(3, gebäudeNr);
		    insert.execute();
		    insert.close();
		    
		    final PreparedStatement nummerQuery = conn.prepareStatement("SELECT "
		    		+ "MAX(garagenr) AS garagenr FROM garage");
		    ResultSet generatedKeys = nummerQuery.executeQuery();
		    generatedKeys.next();
		    int nummer = generatedKeys.getInt("garagenr");
		    generatedKeys.close();
		    nummerQuery.close();
		    return nummer;
		  
			
		} catch(SQLException e) {
			System.err.println("SQLExceptionGarage: "+e.getMessage()+"_state:_"+e.getSQLState());
		}
		return -1;
	}
	public int neuerMechaniker(int svnr, int gehalt, int telefonnummer, int kundennummer) {
		try {
			final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
					+ "`mechaniker`(`svnr`, `gehalt`, `telefonnummer`, `mnr`) VALUES (?, ?, ?, ?)");
		    insert.setInt(1, svnr);
		    insert.setInt(2, gehalt);
		    insert.setInt(3, telefonnummer);
		    insert.setInt(4, kundennummer);
		    insert.execute();
		    insert.close();
		    final PreparedStatement nummerQuery = conn.prepareStatement("SELECT "
		    		+ "MAX(mnr) AS mnr FROM mechaniker");
		    ResultSet generatedKeys = nummerQuery.executeQuery();
		    generatedKeys.next();
		    int nummer = generatedKeys.getInt("mnr");
		    generatedKeys.close();
		    nummerQuery.close();
		    return nummer;
			
		} catch(SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
		}
		return -1;
	}
	public int neueBueroangestellte(int gehalt, int telefonnummer, int svnr, int kundennummer) {
		try {
			final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
					+ "`bueromitarbeiter`(`gehalt`, `telefonnummer`, `svnr`, `mnr`) VALUES (?, ?, ?, ?)");
			insert.setInt(1, gehalt);
			insert.setInt(2, telefonnummer);
			insert.setInt(3, svnr);
		    insert.setInt(4, kundennummer);
		    insert.execute();
		    insert.close();
		    
		    final PreparedStatement nummerQuery = conn.prepareStatement("SELECT "
		    		+ "MAX(mnr) AS mnr FROM bueromitarbeiter");
		    ResultSet generatedKeys = nummerQuery.executeQuery();
		    generatedKeys.next();
		    int nummer = generatedKeys.getInt("mnr");
		    generatedKeys.close();
		    nummerQuery.close();
		    return nummer;
		  
			
		} catch(SQLException e) {
			System.err.println("SQLException: "+e.getMessage());
		}
		return -1;
	}
	public int neuerChef(int nr) {
		try {
			final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
					+ "`chef`(`mnr`) VALUES (?)");
		    insert.setInt(1, nr);
		    insert.execute();
		    insert.close();
		    final PreparedStatement nummerQuery = conn.prepareStatement("SELECT "
		    		+ "COUNT(*) AS gesamtlist FROM chef");
		    ResultSet generatedKeys = nummerQuery.executeQuery();
		    generatedKeys.next();
		    int nummer = generatedKeys.getInt("gesamtlist");
		    generatedKeys.close();
		    nummerQuery.close();
		    return ++nummer;
		} catch(SQLException e) {
			System.err.println("SQLExceptionChef: "+e.getMessage());
		}
		return -1;
	}
	public int neuesFahrzeug(String marke, int baujahr, int mnr_mechaniker, int mnr_buero) {
		try {
			final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
					+ "`fahrzeug`(`marke`, `baujahr`, `mnr_mechaniker`, `mnr_buero`) VALUES (?, ?, ?, ?)");
		    insert.setString(1, marke);
		    insert.setInt(2, baujahr);
		    insert.setInt(3, mnr_mechaniker);
		    insert.setInt(4, mnr_buero);
		    insert.execute();
		    insert.close();
		    final PreparedStatement nummerQuery = conn.prepareStatement("SELECT "
		    		+ "COUNT(*) AS gesamtlist FROM fahrzeug");
		    ResultSet generatedKeys = nummerQuery.executeQuery();
		    generatedKeys.next();
		    int nummer = generatedKeys.getInt("gesamtlist");
		    generatedKeys.close();
		    nummerQuery.close();
		    return ++nummer;
		} catch(SQLException e) {
			System.err.println("SQLExceptionFahrzeug: "+e.getMessage()+"_state:_"+e.getSQLState());
		}
		return -1;
	}
	public int neuesBueroMit(int bnr, int mnr, int gnr) {
	try {
		final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
				+ "`bueromit`(`bnr`, `mnr`,`gnr`) VALUES (?, ?, ?)");
	    insert.setInt(1, bnr);
	    insert.setInt(2, mnr);
	    insert.setInt(3, gnr);
	    insert.execute();
	    insert.close();
	    
	    final PreparedStatement nummerQuery = conn.prepareStatement("SELECT "
	    		+ "COUNT(*) AS gesamtlist FROM bueromit");
	    ResultSet generatedKeys = nummerQuery.executeQuery();
	    generatedKeys.next();
	    int nummer = generatedKeys.getInt("gesamtlist");
	    generatedKeys.close();
	    nummerQuery.close();
	    return nummer;
	  
		
	} catch(SQLException e) {
		System.err.println( "SQLExceptionBueroMit: "+e.getMessage());
	}
	return -1;
}
public int neueGarageMit(int mnr, int garagenr, int gnr) {
	try {
		final PreparedStatement insert = conn.prepareStatement("INSERT INTO "
				+ "`garagemit`(`mnr`, `garagenr`, `gnr`) VALUES (?, ?, ?)");
	    insert.setInt(1, mnr);
	    insert.setInt(2, garagenr);
	    insert.setInt(3, gnr);
	    insert.execute();
	    insert.close();
	    
	    final PreparedStatement nummerQuery = conn.prepareStatement("SELECT "
	    		+ "COUNT(*) AS gesamtlist FROM garagemit");
	    ResultSet generatedKeys = nummerQuery.executeQuery();
	    generatedKeys.next();
	    int nummer = generatedKeys.getInt("gesamtlist");
	    generatedKeys.close();
	    nummerQuery.close();
	    return nummer;
	} catch(SQLException e) {
		System.err.println("SQLExceptionGarageMit: "+e.getMessage());
	}
	return -1;
}

}