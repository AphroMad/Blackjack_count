import java.util.ArrayList;
import java.util.Hashtable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner ; 

public class Execute {

	public static void main(String[] args) throws FileNotFoundException
	{
		
		System.out.println("Hello"); 
		
		// Création et affichage d'une carte 
		Card carte_1 = new Card(1);
		System.out.println(carte_1);
		Card carte_2 = new Card(12);
		System.out.println(carte_2);
		Card carte_3 = new Card(7);
		System.out.println(carte_3);
		
		
		Me player = new Me(); 
		
		Dealer dealer = new Dealer(); 
 
		player.add_carte(carte_1);
		dealer.add_carte(carte_2);
		player.add_carte(carte_3);
		
		System.out.println("Me : "+player+"\nDealer : "+ dealer);
		
		
		// creating a My HashTable Dictionary
		Hashtable<String, Hashtable<String, String>> tableauProba = new Hashtable<String,  Hashtable<String, String>>();
		
		
		
		String file = "tableauProba.txt";
		
		File myObj = new File(file); 
		Scanner myReader = new Scanner(myObj);
		
		while(myReader.hasNextLine()) {
			String data = myReader.nextLine(); 
			String[] test = data.split("\t"); 
			System.out.println(test);
			/*
			for (String a : test) {
				System.out.println(a);
			}
			*/
			
		}
		
		myReader.close(); 
		
		
		/*
		
		// Création et affichage d'un other 
		Other player_1 = new Other(); 
		System.out.println(player_1); 
		player_1.add_carte(carte_1);
		player_1.add_carte(carte_2);
		player_1.add_carte(carte_3);
		player_1.add_carte(carte_1);
		System.out.println(player_1); 
		
		// Création et affichage 
		ArrayList<Integer> result = Other.calcul_score();
		System.out.println(result);
		*/
		
		
		
	}
	
}
