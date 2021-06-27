import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner ; 

public class Execute {

	public static void main(String[] args) throws FileNotFoundException
	{
		
		System.out.println("Hello"); 
		
		// Création et affichage d'une carte 
		Card carte_1 = new Card(1);
		System.out.println(carte_1);
		Card carte_2 = new Card(7);
		System.out.println(carte_2);
		Card carte_3 = new Card(7);
		System.out.println(carte_3);
		
		
		Me player = new Me(); 
		
		Dealer dealer = new Dealer(); 
 
		player.add_carte(carte_1);
		dealer.add_carte(carte_2);
		player.add_carte(carte_3);
		
		System.out.println("Me : "+player+"\nDealer : "+ dealer);
		
	
		
		String file = "tableauProba.txt";
		
		File myObj = new File(file); 
		Scanner myReader = new Scanner(myObj);

		// il faut préparer les scores du joueur et du dealer 
		String scoreDealer = dealer.getFirstValeur();
		System.out.println(scoreDealer);  
		
		String scoreMe = player.getJeuCalcul(); 
		System.out.println("score ME : " + scoreMe); 
		
		int colonne = 0 ; 

		
		// maintenant on a les deux scores bien, il faut juste les retrouver dans le tableau 
		while(myReader.hasNextLine()) { // on go ligne par ligne 
			String data = myReader.nextLine(); // on récupère la ligne 
			String[] ligne = data.split("\t"); 
			
			// on cherche la colonne 
			if (ligne[0].compareTo("X")==0) { // si c'est la première ligne, il faut trouver la bonne colonne (score dealer)
				for (int i = 0 ; i < ligne.length ; i++) { // on va parcourir toute la première ligne 
					if (ligne[i].compareTo(scoreDealer)==0) { // si on a trouvé, il faut garder la colonne 
						colonne = i ; // on sauve le numero de la colonne 
					}
				}
			}
			
			// maintenant, on cherche la ligne 
			if (ligne[0].compareTo(scoreMe) == 0) { // si le premier élément de la ligne vaut le score du joueur, il faut aller voir la colonne trouvée précédemment 
				 System.out.println("Coup a jouer : "+ ligne[colonne]); 
			} 

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
