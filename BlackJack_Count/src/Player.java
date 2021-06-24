import java.util.ArrayList;
import java.util.*;

public abstract class Player {
	
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
	
	public static ArrayList<Integer> calcul_score() // function qui va retourner le (ou les s'il y a un as) valeurs possible
	{
		ArrayList<Integer> result = new ArrayList<>(); // on créé le tableau de résultat 
		
		// On connait maintenant le nombre d'as / de scores possibles
		// Et donc on les calcule 
		
		result.add(0); // on ajoute un premier élément, de valeur 0 
		System.out.println("IN calcul_score");
		for (int i=0; i<carte_player.size();i++) // on passe par toutes les cartes 
		{	
			if (carte_player.get(i).getValeur() != 1) // si l'élément n'est pas un as
			{
				for (int j=0; j<result.size();j++) // on passe par tout les scores
				{
						result.set(j, result.get(j)+carte_player.get(i).getPoint()); // on met a jour le score 

						
						if (result.size()>1) { // on supprime seulement s'il y a plus d'une possibilité (i.e. si le joueur a un as) 
							if (result.get(j)+carte_player.get(i).getPoint()<=21) { // si ca dépasse 21 
								System.out.println("On supprime : "+result.get(j));
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
					if (j < result_temp.size()){if (result.get(j)+1<=21) {result.set(j, result.get(j)+1);} else {result.remove(j);}}
					// si seconde moitié tableau 
					else {if (result.get(j)+11<=21) {result.set(j, result.get(j)+11);} else {result.remove(j);}}
					
				}
				//System.out.println("On calcule les 2 nouvelles valeurs possible"+result);
			}	
			System.out.println("Dans boucle : "+result);
		}
		System.out.println("OUT calcul_score");
		return (result);
	}

	@Override
	public String toString() {
		return "Player [carte_player=" + carte_player + "]";
	}
	

	
}
