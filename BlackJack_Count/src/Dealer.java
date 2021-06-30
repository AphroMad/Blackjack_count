import java.util.*;


public class Dealer {
	
	public static ArrayList<Card> carte_player = new ArrayList<Card>(); // tableau qui contient les cartes du joueur
	public boolean gotAnAs = false ; // variable qui indique si le joueur a un as 
	
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

	
	/*
	public void play() { // fonction pour que le dealer tire une carte tant que son score est en dessous de 17 
		ArrayList<Integer> score = new ArrayList<>(); // on créé le tableau de résultat 
		boolean score_done = false ; // on set une variable qui détermine si le croupier a fini de piocher ou non
		int highest_score = 0 ; // variable qui représente le score le plus haut 
		boolean bust = false ; // variable qui sert a déterminer si le croupier bust ou non 
		
		System.out.println("IN play"); 
		while (score_done == false) { // tant que le croupier n'a pas atteint la valeur min
			score = this.calcul_score() ; // on recup le score
			for (int i=0; i<score.size();i++) // on passe par toutes les cartes 
			{	
				if (score.get(i) <= 21) { // si on dépasse pas le max de point 
					if (highest_score < score.get(i)) { // si on est plus haut que l'ancien meilleur score 
						highest_score = score.get(i) ; // alors on change le nouveau haut score
					}
				}
			}
			if (highest_score >= 17) { // si le plus haut score est plus haut ou égal a 17 alors go 
				score_done = true ; 
			}
			else { // s'il n'atteint pas 17 ou plus, il faut tirer une autre carte 
				this.add_carte(new Card(3)); // on pioche une autre carte 
			}
			
		}

	}
*/
	
	
	
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
 