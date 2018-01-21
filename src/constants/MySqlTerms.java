/**
 * 
 */
package constants;

/**
 * @author bs
 * Liste aller möglichen Daten zum einfügen.
 */
public class MySqlTerms {
	public static final String MECHANIKER="mechaniker";
	public static final String UNTERNEHMEN="amstec";
	public static final String BUEROMITARBEITER="bueromitarbeiter";
	//query abfragen im converter
	
	
	public static final String[] dropStatementsMySQL={
			"DROP TABLE garagemit",
			"DROP TABLE bueromit",
			"DROP TABLE fahrzeug",
			"DROP TABLE buero",
			"DROP TABLE garage",
			"DROP TABLE gebaeude",
			"DROP TABLE chef",
			"DROP TABLE mechaniker",
			"DROP TABLE bueromitarbeiter",
			"DROP TABLE mitarbeiter",
			"DROP TABLE unternehmen"
			};
	public static final String[] createStatementsMySQL= {
			"CREATE TABLE `unternehmen`(`name` varchar(30) DEFAULT NULL,`unr` int(7) NOT NULL)",
			"CREATE TABLE `gebaeude`(`name` varchar(20) DEFAULT NULL,  `gnr` int(5) NOT NULL,  `strasse` varchar(30) DEFAULT NULL,  `snr` int(3) NOT NULL,  `plz` int(5) DEFAULT NULL,  `ort` varchar(40) DEFAULT NULL,  `unr` int(7) NOT NULL)",
			"CREATE TABLE `mitarbeiter`(  `name` varchar(15) DEFAULT NULL,  `nachname` varchar(20) DEFAULT NULL,  `mnr` int(5) NOT NULL,  `unr` int(7) NOT NULL)",
			"CREATE TABLE `buero` (  `bnr` int(5) NOT NULL,  `anzahlmit` int(4) DEFAULT NULL,  `gnr` int(5) NOT NULL)",
			"CREATE TABLE `bueromitarbeiter`(  `gehalt` int(14) DEFAULT NULL,  `telefonnummer` int(10) DEFAULT NULL,  `svnr` int(10) NOT NULL,  `mnr` int(5) NOT NULL)",
			"CREATE TABLE `mechaniker`(  `svnr` int(10) NOT NULL,  `gehalt` int(14) DEFAULT NULL,  `telefonnummer` int(10) DEFAULT NULL,  `mnr` int(5) NOT NULL)",
			"CREATE TABLE `chef`(  `mnr` int(5) NOT NULL)",
			"CREATE TABLE `fahrzeug`(  `marke` varchar(40) DEFAULT NULL,  `baujahr` int(4) DEFAULT NULL,  `kennzeichen` int(10) NOT NULL,  `mnr_mechaniker` int(5) NOT NULL,  `mnr_buero` int(5) NOT NULL)",
			"CREATE TABLE `garage` (  `garagenr` int(3) NOT NULL,  `anzahlmit` int(4) DEFAULT NULL,  `gnr` int(5) NOT NULL)",
			"CREATE TABLE `garagemit`(  `mnr` int(5) NOT NULL,  `garagenr` int(3) NOT NULL,  `gnr` int(5) NOT NULL)",
			"CREATE TABLE `bueromit`(  `bnr` int(5) NOT NULL,  `mnr` int(5) NOT NULL,  `gnr` int(5) NOT NULL)",
			"ALTER TABLE `buero`  ADD PRIMARY KEY (`bnr`,`gnr`),  ADD KEY `buero_ibfk_1` (`gnr`)",
			"ALTER TABLE `bueromit`  ADD PRIMARY KEY (`bnr`,`mnr`,`gnr`),  ADD KEY `bnr` (`bnr`),  ADD KEY `mnr` (`mnr`),  ADD KEY `gnr` (`gnr`)",
			"ALTER TABLE `bueromitarbeiter` ADD PRIMARY KEY (`mnr`),  ADD KEY `mnr` (`mnr`)",
			"ALTER TABLE `chef`  ADD PRIMARY KEY (`mnr`),  ADD KEY `mnr` (`mnr`)",
			"ALTER TABLE `fahrzeug`  ADD PRIMARY KEY (`kennzeichen`),  ADD KEY `mnr_mechaniker` (`mnr_mechaniker`),  ADD KEY `mnr_buero` (`mnr_buero`), MODIFY `kennzeichen` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12349",
			"ALTER TABLE `garage`  ADD PRIMARY KEY (`garagenr`,`gnr`),  ADD KEY `gnr` (`gnr`)",
			"ALTER TABLE `garagemit`  ADD PRIMARY KEY (`mnr`,`garagenr`,`gnr`),  ADD KEY `mnr` (`mnr`),  ADD KEY `garagenr` (`garagenr`),  ADD KEY `gnr` (`gnr`)",
			"ALTER TABLE `gebaeude`  ADD PRIMARY KEY (`gnr`),  ADD KEY `unr` (`unr`)",
			"ALTER TABLE `mechaniker`  ADD PRIMARY KEY (`mnr`),  ADD KEY `mnr` (`mnr`)",
			"ALTER TABLE `mitarbeiter`  ADD PRIMARY KEY (`mnr`),  ADD KEY `unr` (`unr`)",
			"ALTER TABLE `unternehmen`  ADD PRIMARY KEY (`unr`)",
			"ALTER TABLE `garage`  MODIFY `garagenr` int(3) NOT NULL AUTO_INCREMENT",
			"ALTER TABLE `gebaeude`  MODIFY `gnr` int(5) NOT NULL AUTO_INCREMENT",
			"ALTER TABLE `mitarbeiter`  MODIFY `mnr` int(5) NOT NULL AUTO_INCREMENT",
			"ALTER TABLE `buero`  ADD CONSTRAINT `buero_ibfk_1` FOREIGN KEY (`gnr`) REFERENCES `gebaeude` (`gnr`) ON UPDATE CASCADE",
			"ALTER TABLE `bueromit`  ADD CONSTRAINT `bueromit_ibfk_1` FOREIGN KEY (`bnr`) REFERENCES `buero` (`bnr`) ON UPDATE CASCADE,  ADD CONSTRAINT `bueromit_ibfk_2` FOREIGN KEY (`mnr`) REFERENCES `mitarbeiter` (`mnr`) ON UPDATE CASCADE,  ADD CONSTRAINT `bueromit_ibfk_3` FOREIGN KEY (`gnr`) REFERENCES `buero` (`gnr`) ON UPDATE CASCADE",
			"ALTER TABLE `bueromitarbeiter`  ADD CONSTRAINT `bueromitarbeiter_ibfk_1` FOREIGN KEY (`mnr`) REFERENCES `mitarbeiter` (`mnr`) ON UPDATE CASCADE",
			"ALTER TABLE `chef`  ADD CONSTRAINT `chef_ibfk_1` FOREIGN KEY (`mnr`) REFERENCES `mitarbeiter` (`mnr`)",
			"ALTER TABLE `fahrzeug`  ADD CONSTRAINT `fahrzeug_ibfk_1` FOREIGN KEY (`mnr_mechaniker`) REFERENCES `mechaniker` (`mnr`) ON UPDATE CASCADE,  ADD CONSTRAINT `fahrzeug_ibfk_2` FOREIGN KEY (`mnr_buero`) REFERENCES `bueromitarbeiter` (`mnr`) ON UPDATE CASCADE",
			"ALTER TABLE `garage`  ADD CONSTRAINT `garage_ibfk_1` FOREIGN KEY (`gnr`) REFERENCES `gebaeude` (`gnr`) ON UPDATE CASCADE",
			"ALTER TABLE `garagemit` ADD CONSTRAINT `garagemit_ibfk_1` FOREIGN KEY (`mnr`) REFERENCES `mitarbeiter` (`mnr`) ON UPDATE CASCADE,  ADD CONSTRAINT `garagemit_ibfk_2` FOREIGN KEY (`garagenr`) REFERENCES `garage` (`garagenr`) ON UPDATE CASCADE, ADD CONSTRAINT `garagemit_ibfk_3` FOREIGN KEY (`gnr`) REFERENCES `garage` (`gnr`) ON UPDATE CASCADE",
			"ALTER TABLE `gebaeude`  ADD CONSTRAINT `gebaeude_ibfk_1` FOREIGN KEY (`unr`) REFERENCES `unternehmen` (`unr`) ON UPDATE CASCADE",
			"ALTER TABLE `mechaniker`  ADD CONSTRAINT `mechaniker_ibfk_1` FOREIGN KEY (`mnr`) REFERENCES `mitarbeiter` (`mnr`) ON UPDATE CASCADE",
			"ALTER TABLE `mitarbeiter`  ADD CONSTRAINT `mitarbeiter_ibfk_1` FOREIGN KEY (`unr`) REFERENCES `unternehmen` (`unr`) ON UPDATE CASCADE",
	};
		public static final String[] vornamen =
		{
			"Josef",
			"Bernhard",
			"Anna",
			"Lisa",
			"Agathe",
			"Max",
			"Theodor",
			"Dennis",
			"Katrin",
			"Leonie",
			"Sofia",
			"Lena",
			"Thomas",
			"Julia",
			"Regina",
			"Renate",
			"Christian",
			"Maria",
			"Hermine",
			"Greta",
			"Tom",
			"Johannes",
			"Justus",
			"Anne",
			"Marcel",
			"Karlo",
			"Markus",
			"Franz",
			"Klara",
			"Tina",
			"Timon",
			"Jake",
			"Patrick",
			"Alexander",
			"Lukas",
			"Diana",
			"Kevin",
			"Dominik",
			"Theresa",
			"Justus",
			"Natalie"
		};
		/** @see https://de.wikipedia.org/wiki/Familiennamen_in_%C3%96sterreich */
		public static final String[] nachnamen =
		{
			"Gruber",
			"Huber",
			"Wagner",
			"Wagner",
			"Pichler",
			"Moser",
			"Steiner",
			"Mayer",
			"Berger",
			"Hofer",
			"Eder",
			"Bauer",
			"Winkler",
			"Schmid",
			"Weber",
			"Fuchs",
			"Maier",
			"Schwarz",
			"Schneider",
			"Reiter",
			"Leitner",
			"Mayr",
			"Fischer",
			"Schmidt",
			"Wimmer",
			"Egger",
			"Baumgartner",
			"Brunner",
			"Wallner",
			"Auer",
			"Aigner",
			"Wolf",
			"Binder",
			"Ebner",
			"Schuster",
			"Lang",
			"Lechner",
			"Haas",
			"Wieser",
			"Strasser",
			"Stadler",
			"Haider",
			"Weiss",
			"Holzer",
			"Koller",
			"Mair",
			"Riegler",
			"Maurer",
			"Lehner",
			"Winter"
		};
		public static final String[] emailprovider =
		{
			"gmail.com",
			"gmx.at",
			"gmx.net",
			"yahoo.de",
			"univie.ac.at",
			"hotmail.com",
			"live.at",
			"outlook.com",
			"hotmail.de",
			"hotmail.at",
		};
		public static final String[] unternehmensnamen =
			{
				"ABC",
				"TESTING",
				"Dumm",
				"Lustig",
				"Random",
				"Fad",
				"U1",
				"U2",
				"U3",
				"Wetterbericht",
			};
		public static final String[] gebäudenamen =
			{
				"A",
				"B",
				"C",
				"D",
				"E",
				"F",
				"G",
				"H",
				"I",
				"J",
				"K",
				"L",
				"M",
				"N",
				"O",
				"P",
				"Q",
				"R",
				"S",
				"T",
				"U",
				"V",
				"W",
			};
		public static final String[] strassennamen =
			{
					"Albertgasse",
					"Bennogasse",
					"Blindengasse",
					"Buchfeldgasse",
					"Feldgasse",
					"Hamerlingplatz",
					"Josefsgasse",
					"Krotenthallergasse",
					"Kupkagasse",
					"AmEndeDerWelt",
			};
		public static final String[] ortnamen =
				{
						"Duraj",
						"Baltrum",
						"Zuhause",
						"Salzburg",
						"Zederhaus",
						"Prag",
						"Wien",
						"Graz",
						"Neustadt",
						"Innsbruck",
				};
		public static final String[] modelle =
			  {
			    "Beetle Cabriolet",
			    "Audi",
			    "Albrecht",
			    "Angelmar",
			    "Tiguan",
			    "Passat",
			    "Jetta",
			    "Polo",
			    "Golf",
			    "Scirocco",
			    "Sharan",
			    "Audi R8",
			    "Audi A4",
			    "BMW 7er Limousine",
			    "BMW 4er Cabrio",
			    "BMW i8",
			    "Allanto",
			    "Fleetwood",
			    "Coupe DeVille/Sedan DeVille",
			    "Twingo",
			    "Clio",
			    "Captur",
			    "Kangoo",
			    "Kadjar",
			    "Talismann",
			    "Twizy",
			    "Fiat 500",
			    "Eigenbaumodell"
			  };
		
		public static final int maxUnternehmen =30;
		public static final int maxGebäude = 50;
		public static final int maxMitarbeiter = 50;
		public static final int maxFahrzeug=100;
		
		

}
