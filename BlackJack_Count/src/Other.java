import java.util.ArrayList;

public class Other extends Player {

public static ArrayList<Card> carte_player = new ArrayList<Card>(); // tableau qui contient les cartes du joueur
	
	public ArrayList<Card> getCarte_player() {
		return carte_player;
	}
	
	public void add_carte(Card carte) // fonction pour ajouter une carte 
	{
		carte_player.add(carte); 
	}
	
	public void init_cartes() { // fonction qui vide le tableau 
		carte_player.clear();
	}
	

}
 