/**
 * 
 */
package database;

import java.sql.SQLException;
import java.util.ArrayList;

import constants.MySqlTerms;

/**
 * @author bs
 *
 */
public class DemoDBInsertion {

	/**
	 * @param args
	 * fügt Random werte ein.
	 */
	public static void main(String[] args)  {
		ArrayList<Integer> mechNrList=new ArrayList<>(),
							bNrList=new ArrayList<>(),
							kundenNrList=new ArrayList<>(),
							unternehmenNrList=new ArrayList<>(),
							gebäudeNrList=new ArrayList<>(),
							bueroNrList=new ArrayList<>(),
							garageNrList=new ArrayList<>();
	
		final DBConnection connection = new DBConnection();
		try {
			connection.connect();
			connection.dropTables();
			connection.createTables();
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ConnectionError: "+ e.getMessage());
		}
		
		System.out.println("Verbindung mit Datenbank hergestellt...");
		System.out.println("Bef\u00fcllung mit Zufallsdaten beginnt jetzt!");

		int mechanikernr=1;
		int bueromitarbeiter=1;
	    int kundennummer=1;
	    int unternehmenNr=1;
	    int gebäudeNr=1;
		int bueronr=1;
		int garagennr=1;
	
// füge neue unternehmen ein.
				System.out.println("insertUnternehmen");
			    for(int j=0; j<MySqlTerms.maxUnternehmen;j++) {
			    	boolean untNr=false;
			    	while(!untNr) {
			    		String name=randomString(MySqlTerms.unternehmensnamen);
			    		unternehmenNr=connection.neuesUnternehmen(name,unternehmenNr);
			    		unternehmenNrList.add(unternehmenNr);
			    		if(++unternehmenNr>0)  {
			   	          untNr = true;
			   	        } else {
			   	        	System.err.println("ENDEUnternehmen wegen ERROR");
			   	        	return;
			   	        }
			    	}
			    }		
//  Mitarbeiter erstellen;
		System.out.println("insertMitarbeiter");
	    for(int i=0; i<MySqlTerms.maxMitarbeiter; i++) {
	      boolean knr = false;
	      while (!knr) {
	        String vorname = randomString(MySqlTerms.vornamen);
	        String nachname = randomString(MySqlTerms.nachnamen);
	        int unr=randomIntExclNull(MySqlTerms.maxUnternehmen);
	        kundennummer = connection.neuerMitarbeiter(vorname, nachname, unr);
	        kundenNrList.add(kundennummer);
	        int kundennummerTemp=++kundennummer;
	        
	        if(kundennummerTemp>0)  {
	          knr = true;
	        } else {
	        	System.err.println("ENDEMitarbeiter wegen ERROR");
	        	return;
	        }
	      }
	    	//50%pro Chance
	    	if(randomBoolean()) {
//neues Mechaniker
	    		boolean mechnr=false;
	    		while(!mechnr) {
	    			int svnr=randomIntExclNull(MySqlTerms.maxMitarbeiter);
	    			int gehalt=randomIntExclNull(MySqlTerms.maxMitarbeiter);
	    			int telefonnummer=randomIntExclNull(1000000000);
	    			int mnr=kundenNrList.get(kundenNrList.size()-1);
	    			mechNrList.add(mnr);
	    			if(telefonnummer<100000000) {telefonnummer=(telefonnummer+100000000);}
	    			kundennummer=connection.neuerMechaniker(svnr, gehalt, telefonnummer, mnr);
	    			mechanikernr=++kundennummer;
	    			if(mechanikernr>0)  {
	    				mechnr = true;
	    				mechanikernr++;
	  	   	        } else {
	  	   	        	System.err.println("ENDEMechaniker wegen ERROR");
	  	   	        	return;
	  	   	        }
	    		}
	    	} else {
//neue Büromitarbeiter
	    		boolean buernrB=false;
	    		while(!buernrB) {
	    			int svnr=randomIntExclNull(MySqlTerms.maxMitarbeiter);
	    			int gehalt=randomIntExclNull(MySqlTerms.maxMitarbeiter);
	    			int telefonnummer=randomIntExclNull(1000000000);
	    			int mnr=kundenNrList.get(kundenNrList.size()-1);
	    			bueroNrList.add(mnr);
	    			if(telefonnummer<100000000) {telefonnummer=(telefonnummer+100000000);}
	    			kundennummer=connection.neueBueroangestellte(gehalt, telefonnummer, svnr, mnr);
	    			bueromitarbeiter=++kundennummer;
	    			if(bueromitarbeiter>0)  {
	    				buernrB = true;
	    				bueromitarbeiter++;
	  	   	        } else {
	  	   	        	System.err.println("ENDEBüroMitarbeiter wegen ERROR");
	  	   	        	return;
	  	   	        }
	    		}
	    	}
	    }
	    //schutz
	    //    connection.disconnect();
	     //   connection.connect();

// füge gebäude ein
		System.out.println("insertGebäude");
		
	   for(int j=0; j<MySqlTerms.maxGebäude;j++) {
	    	boolean gebNr=false;
	    	while(!gebNr) {
	    		String name=randomString(MySqlTerms.gebäudenamen);
	    		String strasse=randomString(MySqlTerms.strassennamen);
	    		String ort=randomString(MySqlTerms.ortnamen);
	    		int plz=randomInt(9999);
	    		if(plz<1000) plz=plz+1000; //da plz 4stellig
	    		int snr=randomIntExclNull(100);
	    		int unr=unternehmenNrList.get(randomIntExclNull(unternehmenNrList.size()));
	    		gebäudeNr=connection.neuesGebaeude(name,strasse, ort, plz, snr, unr);
	    		gebäudeNrList.add(gebäudeNr);
	    		int gebäudeNrTemp=++gebäudeNr;
	    		if(gebäudeNrTemp>0)  {
	   	          gebNr = true;
	   	        } else {
	   	        	System.err.println("ENDEGebäude wegen ERROR");
	   	        	return;
	   	        }
	    	}
	    	//50%pro gebäude.
	    	if(randomBoolean()) {
//neues Büro
	    		boolean buenr=false;
	    		while(!buenr) {
	    			int anzahlmit=randomIntExclNull(MySqlTerms.maxMitarbeiter);
	    			int bList=bNrList.size()+1;
	    			int bnr=gebäudeNrList.size()-1;
	    			bueronr=connection.neuesBuero(bList, anzahlmit,gebäudeNrList.get(bnr) );
	    			bNrList.add(bueronr);
	    			bnr=++bueronr;
	    			if(bnr>0)  {
	  	   	          buenr = true;
	  	   	        } else {
	  	   	        	System.err.println("ENDEBüro wegen ERROR");
	  	   	        	return;
	  	   	        }
	    		}
	    	} else {
//neue Garage
	    		boolean ganr=false;
	    		while(!ganr) {
	    			int anzahlmit=randomInt(MySqlTerms.maxMitarbeiter);
	    			garageNrList.add(gebäudeNrList.get(gebäudeNrList.size()-1));
	    			int garnr=garageNrList.size()+1;
	    			garagennr=connection.neueGarage(garnr, anzahlmit,gebäudeNrList.get(gebäudeNrList.size()-1 ) );
	    			garageNrList.add(garagennr);
	    			garnr=++garagennr;
	    			if(garnr>0)  {
	  	   	          ganr = true;
	  	   	        } else {
	  	   	        	System.err.println("ENDEBüro wegen ERROR");
	  	   	        	return;
	  	   	        }
	    		}
	    	}
	    }
	    
	  //schutz
       // connection.disconnect();
       // connection.connect();
	    
//Fahrzeug
        System.out.println("insertFahrzeug");
	    for(int j=0; j<MySqlTerms.maxFahrzeug;j++) {
	    	boolean fahrzNr=false;
	    	int fahrzeugNr=1;
	    	while(!fahrzNr) {
	    		String marke=randomString(MySqlTerms.modelle);
	    		int baujahr=randomInt(10000);
	    		int mnr_mechaniker=mechNrList.get(randomInt(mechNrList.size()));
	    		int mnr_buero=bueroNrList.get(randomInt(bueroNrList.size()));
	    		if(baujahr<1000) baujahr+=1000; //da baujahr 4stellig
	    		fahrzeugNr=connection.neuesFahrzeug(marke,baujahr, mnr_mechaniker, mnr_buero);
	    		
	    		if(fahrzeugNr>0)  {
	    			fahrzNr = true;
	   	        } else {
	   	        	System.err.println("ENDEFahrzeug wegen ERROR");
	   	        	return;
	   	        }
	    	}
	    }
//Chef
	    System.out.println("insertChef");
		boolean chnr=false;
		int chefnr=1;
		while(!chnr) {
			int nr=kundenNrList.get(randomIntExclNull(kundenNrList.size()));
			chefnr=connection.neuerChef(nr);
			
			if(chefnr>0)  {
				chnr = true;
	   	    } else {
	   	        	System.err.println("ENDEChef wegen ERROR");
	   	        	break;
	   	    }
		}
		System.out.println("Finished Loading Datasets");
	}
	
	/**
	 * @return a random String from given Array
	 * 
	 */
	private static String randomString(String[] array) {
	    return array[new java.util.Random().nextInt(array.length)];
	  }
	  
	/**
	 * @return a random integer from one to exclusiveMax
	 * 
	 */
	private static int randomIntExclNull(int exclusiveMax) {
		int x=new java.util.Random().nextInt(exclusiveMax);
		if(x==0) {
			return 1;
		}
	    return x;
	  }
	/**
	 * @return a random integer from zero to exclusiveMax
	 * 
	 */
	private static int randomInt(int exclusiveMax) {
		return new java.util.Random().nextInt(exclusiveMax);
	  }
	/**
	 * @return randomly either <code>true</code> or <code>false</code>
	 * 
	 */
	private static boolean randomBoolean() {
	    return new java.util.Random().nextBoolean();
	  }
	

}
