package constants;

public class QueryTerms {
	public static final String queryBuero="SELECT bnr, anzahlmit, gnr FROM buero WHERE gnr = ?";
	public static final String queryBueroMit="SELECT bnr, mnr, gnr FROM bueromit WHERE gnr = ?";
	public static final String queryBueroMitarbeiter="SELECT gehalt, telefonnummer, svnr, mnr FROM bueromitarbeiter WHERE mnr = ?";
	public static final String queryMechaniker="SELECT svnr, gehalt, telefonnummer, mnr FROM mechaniker WHERE mnr = ?";
	public static final String queryChef="SELECT mnr FROM chef";
	public static final String queryAllFahrzeug="SELECT marke, baujahr, kennzeichen, mnr_mechaniker, mnr_buero FROM fahrzeug";
	public static final String queryGarage="SELECT garagenr, anzahlmit, gnr FROM garage WHERE gnr = ?";
	public static final String queryGarageMit="SELECT mnr, garagenr, gnr FROM garagemit WHERE gnr = ?";
	public static final String queryAllGebaeude="SELECT name, gnr, strasse, snr, plz, ort, unr FROM gebaeude";
	public static final String queryMitarbeiter="SELECT name, nachname, mnr, unr FROM mitarbeiter WHERE mnr = ?";
	public static final String queryAllMitarbeiter="SELECT name, nachname, mnr, unr FROM mitarbeiter";
	public static final String queryUnternehmen="SELECT name, unr FROM unternehmen";
	public static final String MECHANIKER="mechaniker";
	public static final String BUEROMITARBEITER="bueromitarbeiter";
}
