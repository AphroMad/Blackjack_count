import java.util.ArrayList;
import java.util.*;

public abstract class Player {
	
	public ArrayList<Card> carte_player ; // tableau qui contient les cartes du joueur
	private boolean gotAnAs = false ; // variable qui indique si le joueur a un as 
	
	public Player() { // constructeur 
		this.carte_player = new ArrayList<Card>();
	}
	
	
	public ArrayList<Card> getCarte_player() {
		return carte_player;
	}
	
	public void add_carte(Card carte) // fonction pour ajouter une carte 
	{
		carte_player.add(carte); 
		if (carte.getValeur()==1) { // si on a un as 
			this.gotAnAs = true ;  // alors on le note qqpart 
		}
	}
	
	public String getJeuCalcul() { // fonction pour préparer les calculs du coup à jouer 
		String result = ""; 
		
		if (carte_player.size() == 2 && carte_player.get(0).getPoint() == carte_player.get(1).getPoint()  ) { // s'il a 2 cartes avec la même valeur, alors paire
			if (gotAnAs) {result = "A,A"; }
			else {result = String.valueOf(carte_player.get(0).getPoint()) + "," + String.valueOf(carte_player.get(1).getPoint());} // on met la paire sous forme "X,X" 
		}
		else {  // ici, c'est pas pair, on va d'abord faire la somme de tous les éléments hormis AS et ensuite on adaptera 
			int scoreTempo = 0 ; // pour compter le nombre de point hors as 
			
			for(int i = 0 ; i < carte_player.size(); i++) { // on parcours le jeu du joueur 
				if (carte_player.get(i).getPoint() <= 10) { // toutes les cartes sauf l'as quoi 
					scoreTempo = scoreTempo + carte_player.get(i).getPoint() ; // on augmente le score du joueur 
					System.out.println("Score joueur : "+scoreTempo) ;
				}
			}
			
			if (gotAnAs) { // s'il a un as
				result = "A"+ String.valueOf(scoreTempo) ; // forme AX avec X un int 
			} 
			else { // s'il n'a pas d'as 
				result = String.valueOf(scoreTempo) ; // juste le score normal 
			}
		}
		return result; 
	}
	
	public void init_cartes() { // fonction qui vide le tableau 
		carte_player.clear();
	}
	
	public ArrayList<Integer> calcul_score() // function qui va retourner le (ou les s'il y a un as) valeurs possible
	{
		ArrayList<Integer> result = new ArrayList<>(); // on créé le tableau de résultat 
		
		// On connait maintenant le nombre d'as / de scores possibles
		// Et donc on les calcule 
		
		result.add(0); // on ajoute un premier élément, de valeur 0 
		//System.out.println("IN calcul_score");
		for (int i=0; i<carte_player.size();i++) // on passe par toutes les cartes 
		{	
			if (carte_player.get(i).getValeur() != 1) // si l'élément n'est pas un as
			{
				for (int j=0; j<result.size();j++) // on passe par tout les scores
				{	
						result.set(j, result.get(j)+carte_player.get(i).getPoint()); // on met a jour le score 

						if (result.size()>1) { // on supprime seulement s'il y a plus d'une possibilité (i.e. si le joueur a un as) 
							if (result.get(j)>21) { // si ca dépasse 21 
								//System.out.println("On supprime : "+result.get(j));
								result.remove(j);
							}
						}
				}
			}
			
			else // si l'élément est un as, il va falloir rajouter des résultats dans le tableau des scores
			{
				ArrayList<Integer> result_temp = new ArrayList<>(); // on créé le tableau de résultat 
				
				// on créé un doublon du tableau result 
				for (int j=0; j<result.size();j++) 
				{
					result_temp.add(result.get(j)) ; 
				}
				//System.out.println("tableau de score : "+result+"\nEt sa copie : "+result_temp);
				
				// On double le nombre de valeur du tableau (car 2 possibilités avec AS) 
				for (int j=0; j<result_temp.size();j++) 
				{
					result.add(result_temp.get(j)); 
				}
				//System.out.println("On double le nombre de valeur car AS : "+result);

				// Pour la moitié tu tableau, on fait +1, et pour l'autre moitié, +11 
				for (int j=0; j<result_temp.size()*2;j++) 
				{
					// si première moitié tableau 
					if (j < result_temp.size()){result.set(j, result.get(j)+1);}
					// si seconde moitié tableau 
					else {result.set(j, result.get(j)+11);}
				}
				//System.out.println("On calcule les 2 nouvelles valeurs possible"+result);
				
				
		        ArrayList<Integer> sans_doublons = new ArrayList<>(); // on créé une nouvelle liste
		  
		        for (int score : result) { // on va parcourir la liste 
		  
		            if (!sans_doublons.contains(score)) { // si l'élément n'est pas encore présent dans la liste, 
		            	
		  
		            	sans_doublons.add(score); // alors on l'ajoute 
		            }
		        }
		        //System.out.println("Liste sans doublons : "+sans_doublons + "" + result ) ;
		        
		        result.clear(); // on vide result 
		        result.addAll(sans_doublons); // on lui rajoute tous les trucs de sans doublons 
		        
		        //la on va enlever les données au dessus de 21 sauf s'il n'y en a qu'un 
		        ArrayList<Integer> sous21 = new ArrayList<>(); // on créé une nouvelle liste
		        sous21.add(result.get(0)); // on ajoute le premier element des résultats
		        
		        for (int k=1; k<result.size();k++) { //si le score est sous 21 
		        	if (result.get(k) <= 21 ) {sous21.add(result.get(k));} // alors on l'ajoute au tableau 
		        } // s'il est au dessus bah balek 
		       
		        result.clear(); // on vide result 
		        result.addAll(sous21); // on lui rajoute tous les trucs de sans doublons 
		        
				
			}	
			//System.out.println("Dans boucle : "+result);
		}
		//System.out.println("OUT calcul_score");
		return (result);
	}

	@Override
	public String toString() {
		return "Player [carte_player=" + carte_player + "]";
	}

	public boolean isGotAnAs() {
		return gotAnAs;
	}

	public void setGotAnAs(boolean gotAnAs) {
		this.gotAnAs = gotAnAs;
	}
	

	
}
