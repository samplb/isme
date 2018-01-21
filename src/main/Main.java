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
				System.out.println("Do you wish to upload Demo-Content in mySql Database? Please enter 'insert'.\n Or convert mySql Data to MongoDB? Please enter 'convert'.");
				System.out.println("Please enter 'exit' to close Program.");
				answer=in.next().toLowerCase();
				if(answer.matches("insert")) {
					DemoDBInsertion.main(args);
				} else if(answer.matches("convert")) {
					Verwaltung.main(args);
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
