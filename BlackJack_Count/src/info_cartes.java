
public class info_cartes {

	// classe qui contiendra 3 infos pour chaque carte : sa valeur (1-13), le nombre max d'occurence en fonction du sabot, et le nombre de carte passée
	
	private int valeur ; 
	private int nb_max ; 
	private int nb_passe ; 
	private double nb_poss ; 
	private double proba_tomb;
	
	public info_cartes(int valeur_in, int nb_max_in, int nb_passe_in) {
		valeur = valeur_in ; 
		nb_max = nb_max_in ; 
		nb_passe = nb_passe_in ; 	
	}
	
	public info_cartes(int valeur_in) {
		valeur = valeur_in ; 	
	}

	public void poss_maj() {// fonction qui met a jour le nb poss 
		nb_poss = nb_max - nb_passe ; 
	}
	
	public void proba(int nbCartes) { // calcul de la proba que la carte tombe en fonction de la taille du sabot 
		proba_tomb = (nb_poss / nbCartes )*100  ; 
	}
	
	public void carte_recu() { // fonction qui dit qu'on a recu une carte et donc il faut augmenter son nombre de fois qu'elle a été recue 
		nb_passe ++ ; 
	}
	
	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public int getNb_max() {
		return nb_max;
	}

	public void setNb_max(int nb_max) {
		this.nb_max = nb_max;
	}

	public int getNb_passe() {
		return nb_passe;
	}

	public void setNb_passe(int nb_passe) {
		this.nb_passe = nb_passe;
	}

	@Override
	public String toString() {
		return "info_cartes [valeur=" + valeur + ", nb_max=" + nb_max + ", nb_passe=" + nb_passe + ", nb_poss="
				+ nb_poss + "]";
	}

	public int getNb_poss() {
		
		int entier = (int) nb_poss ; 
		return entier;
	}

	public void setNb_poss(int nb_poss) {
		this.nb_poss = nb_poss;
	}

	public double getProba_tomb() {
		return proba_tomb;
	}

	public void setProba_tomb(int proba_tomb) {
		this.proba_tomb = proba_tomb;
	}
	
	
}
