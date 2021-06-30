import java.util.*;


public class Dealer extends Player {
	
	//public static ArrayList<Card> carte_player = new ArrayList<Card>(); // tableau qui contient les cartes du joueur
	// public boolean gotAnAs = false ; // variable qui indique si le joueur a un as 
	
	public Dealer() { // constructeur 
		super(); 
	}
	
	
	public ArrayList<Card> getCarte_player() {
		return carte_player;
	}
	
	public String getFirstValeur() {
		int point = carte_player.get(0).getPoint() ; 
		String result ; 
		if (point>10) { // si c'est un as 
			result = "A" ; 
		}
		else { // si c'est un nombre entre 2 et 10, on passe de int a string 
			result = String.valueOf(point); 
		}
		
		return result; 
	}
	
	public void add_carte(Card carte) // fonction pour ajouter une carte 
	{
		carte_player.add(carte); 
		if (carte.getValeur()==1) { // si on a un as 
			this.gotAnAs = true ;  // alors on le note qqpart 
		}
	}
	
	public void init_cartes() { // fonction qui vide le tableau 
		carte_player.clear();
	}
	



	
	
	@Override
	public String toString() {
		return "Dealer [getCarte_player()=" + getCarte_player() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public boolean isGotAnAs() {
		return gotAnAs;
	}

	public void setGotAnAs(boolean gotAnAs) {
		this.gotAnAs = gotAnAs;
	}

}
 