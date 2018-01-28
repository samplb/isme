/**
 * 
 */
package main;

import java.util.Scanner;
import database.DemoDBInsertion;
import convertDB.Verwaltung;

/**
 * @author bs
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Scanner in=new Scanner(System.in);
			boolean check=true;
			String answer="";
			while(check) {
				System.out.println("\tMenü:"
						+ "\n \t  'reset'"
						+ "\n \t  'insert'"
						+ "\n \t  'basicMongo'"
						+ "\n \t  'prettyMongo'"
						+ "\n \t  'help'"
						+ "\n \t  'exit'"
						+ "\n \n Enter your choice please:");
				answer=in.next().toLowerCase();
				if(answer.matches("help")) {
					System.out.println("Please enter reset if it isn't your first time to use it. 'reset' will drop all databases!"
							+ "\n Do you wish to upload Demo-Content in mySql Database? Please enter 'insert'."
							+ "\n Or convert mySql Data to MongoDB? Please enter 'prettyMongo' for the perfect version."
							+ "\n If you want to work with the website choose 'basicMongo'!");
					System.out.println("Please enter 'exit' to close Program.");
				} else if(answer.matches("reset")) {
					try {
						DemoDBInsertion.reset();
						Verwaltung.reset();
						System.out.println("Sucessfully dropped!");
					} catch(Exception e) {
						System.err.println("Failed to drop, Errormessage:"+e.getMessage());
					}
				} else if(answer.matches("insert")) {
					DemoDBInsertion.main(args);
				} else if(answer.matches("basicmongo")) {
					Verwaltung.main("basicmongo");
				} else if(answer.matches("prettymongo")) {
					Verwaltung.main("prettymongo");
				}else if(answer.matches("exit")) {
					check=false;
					in.close();
				} else {
					System.out.println("Only 'yes' or 'no' accepted.");
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

}
