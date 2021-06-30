
final public class Card { // classe qui représentera une carte 

	private String couleur = "" ; // coeur, carreau, pique, trèfle 
	private int valeur = 0 ; // valeur allant de 1 a 13 (Valet = 11, Dame = 12, Roi = 13)
	private int point = 0 ; // nombre de point que vaut la carte, i.e. roi = 10, as = 1 ou 11, etc ... 
	
	
	// Constructeur 
	public Card (int valeur)
	{
		// on donne la valeur, throw une erreur si negatif ou si >13
		this.valeur = valeur ; // RAJOUTE L'EXCEPTION GROSSE FOLLE 
		// on calcule le nombre de point en fonction de la valeur 
		if(valeur > 10) {this.point = 10;}
		else if (valeur == 1) {this.point = 1000;}
		else {this.point = valeur;}
	}


	public String getCouleur() {
		return couleur;
	}


	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


	public int getValeur() {
		return valeur;
	}


	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}
	
	@Override
	public String toString() {
		return "Card [couleur=" + couleur + ", valeur=" + valeur + ", point=" + point + "]";
	}
}
