import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;



/* TO DO 
 *
 * afficher coup conseillé proba (archi dur a faire ca) 
 * 
 * */

public class Game_window {

	// On créé une instance de chacun des joueurs possibles, donc le dealer, les autres, et moi 
	Dealer dealer = new Dealer(); 
	Me me = new Me(); 
	Other other = new Other() ; 
	// on créé les 2 instances du joueur pour etre prêt si split 
	Me me1 = new Me(); 
	Me me2 = new Me(); 
	
	// pour la gestion du sabot 
	int tailleSabot = 6; // variable qui affiche le nombre de paquets de cartes dans le sabot 
	int nbCartesSabot = tailleSabot * 52 ; // variables qui représente le nombre total de carte dans le sabot
	
	// compteur pour le hi lo 
	int compteurHiLo = 0 ; 
	String CoupDeuxMe = "" ; 
	
	// mot a faire passer pour le coup à jouer 
	String coupAJouer = "" ; 
	String coupMe = "";
	String coupMe1 = "";
	String coupMe2 = ""; 
	
	JTextArea txtProba ; 
	JTextArea txtProba_1 ; 
	JTextArea txtLoHi ;
	JTextArea scoreMe ; 
	JTextArea scoreMe1;
	JTextArea scoreMe2;
	JTextArea scoreDealer ;
	JTextArea txtCoupJouer;
	
	JLabel lblSabot ; 
	JLabel lblDealerScore ; 
	JLabel lblMeScore ; 
	JLabel lblMeScoreS1 ; 
	JLabel lblMeScoreS2 ; 
	JLabel lblCoupJouer ;
	
	JList list ; 
	
	JButton btnMeCard ; 
	JButton btnSplit1;
	JButton btnSplit2;
	
	ArrayList<info_cartes> carteSabot; 
	
	public void maj_affichage() {
		// affichage des probas de chaque carte 
		txtProba.setText("As : "+carteSabot.get(0).getNb_poss()+"\n2 : "+carteSabot.get(1).getNb_poss()+"\n3 : "+carteSabot.get(2).getNb_poss()+"\n4 : "+carteSabot.get(3).getNb_poss()+"\n5 : "+carteSabot.get(4).getNb_poss()+"\n6 : "+carteSabot.get(5).getNb_poss()+"\n7 : "+carteSabot.get(6).getNb_poss()+"\n8 : "+carteSabot.get(7).getNb_poss()+"\n9 : "+carteSabot.get(8).getNb_poss()+"\n10 : "+carteSabot.get(9).getNb_poss()+"\nV : "+carteSabot.get(10).getNb_poss()+"\nD : "+carteSabot.get(11).getNb_poss()+"\nR : "+carteSabot.get(12).getNb_poss());
		txtProba_1.setText(carteSabot.get(0).getProba_tomb() +"\n" + carteSabot.get(1).getProba_tomb() +"\n"+carteSabot.get(2).getProba_tomb() +"\n"+carteSabot.get(3).getProba_tomb() +"\n"+carteSabot.get(4).getProba_tomb() +"\n"+carteSabot.get(5).getProba_tomb() +"\n"+carteSabot.get(6).getProba_tomb() +"\n"+carteSabot.get(7).getProba_tomb() +"\n"+carteSabot.get(8).getProba_tomb() +"\n"+carteSabot.get(9).getProba_tomb() +"\n"+carteSabot.get(10).getProba_tomb() +"\n"+carteSabot.get(11).getProba_tomb() +"\n"+carteSabot.get(12).getProba_tomb());
		// compteur HI LO
		if (compteurHiLo >= 0) {txtLoHi.setText("Lo-Hi\n "+compteurHiLo);} // on affiche compteur lo hi positif 
		else {txtLoHi.setText("Lo-Hi\nM"+compteurHiLo*(-1));} // compteur lo hi négatif 
		// MAJ affichage non splité 
		lblMeScore.setText("Score : " + String.valueOf(me.calcul_score())); // on met a jour le score de moi
		// MAJ affichage splité 
		lblMeScoreS1.setText(String.valueOf(me1.calcul_score())); // on met a jour le score de moi 
		lblMeScoreS2.setText(String.valueOf(me2.calcul_score())); // on met a jour le score de moi 
		// MAJ affichage dealer 
		lblDealerScore.setText("Score : " + String.valueOf(dealer.calcul_score().get(0))); // on met a jour le score
		// MAJ affichage coup a jouer joueur seul 
		lblCoupJouer.setText(coupMe);
		// MAJ affichage coup a jouer splité 
		txtCoupJouer.setText(CoupDeuxMe);
		}
	
	// fonction qu'on va appeler plusieurs fois, sert à mettre à jour le coup a jouer 
	public String coupAJouer(Me joueur) throws FileNotFoundException {
		
		String file = "tableauProba.txt"; // nom du fichier 
		File myObj = new File(file); // on le charge 
		Scanner myReader = new Scanner(myObj); // on le scanne 

		// il faut préparer les scores du joueur et du dealer 
		String fscoreDealer = dealer.getFirstValeur();
		String fscoreMe = joueur.getJeuCalcul(); 
		
		// pour selectionner la colonne 
		int colonne = 0 ; 
		
		// maintenant on a les deux scores bien, il faut juste les retrouver dans le tableau 
		while(myReader.hasNextLine()) { // on go ligne par ligne 
			String data = myReader.nextLine(); // on récupère la ligne 
			String[] ligne = data.split("\t"); 
			
			// on cherche la colonne 
			if (ligne[0].compareTo("X")==0) { // si c'est la première ligne, il faut trouver la bonne colonne (score dealer)
				for (int i = 0 ; i < ligne.length ; i++) { // on va parcourir toute la première ligne 
					if (ligne[i].compareTo(fscoreDealer)==0) { // si on a trouvé, il faut garder la colonne 
						colonne = i ; // on sauve le numero de la colonne 
						//System.out.println("On a trouvé la colonne"); 
					}
				}
			}
			
			// maintenant, on cherche la ligne 
			if (ligne[0].compareTo(fscoreMe) == 0) { // si le premier élément de la ligne vaut le score du joueur, il faut aller voir la colonne trouvée précédemment 
				 //System.out.println("Coup a jouer : "+ ligne[colonne]); 
				 coupAJouer = "A jouer : "+ ligne[colonne] ; 
				 break ; 	 
			} 
		}
		myReader.close(); 
		
		return coupAJouer; 
	} 
	
	public void maj_last_carte() {
		carteSabot.get(list.getSelectedIndex()).carte_recu(); // on dit qu'on a bien recu la carte 
		nbCartesSabot -- ; 
		
		// partie comptage Lo Hi
				if (list.getSelectedIndex()==0 || list.getSelectedIndex() >= 9) { // si c'est un AS, 10, V, D, R. 
					compteurHiLo ++ ;
				}
				else if (list.getSelectedIndex()>0 && list.getSelectedIndex()<6) { // si c'est 2, 3, 4, 5, 6
					compteurHiLo -- ;
				} // si c'est le reste, a savoir 7, 8, 9, on ne fait rien 
	}
	
	public void maj_proba() throws FileNotFoundException {
		
		// partie calcul de proba 
		for (int i=0; i<carteSabot.size(); i++) { // pour remplir les autres infos pour les cartes 
			carteSabot.get(i).poss_maj() ; // on remet le cout a jour 
			carteSabot.get(i).proba(nbCartesSabot) ;// on calcule la proba 
		}

		// partie calcul coup à jouer 
		if (dealer.getNbCartes() == 1 && me.getNbCartes() >= 2) { // on commence a proposer le coup a faire que quand le dealer a une carte et le joueur 2 
			coupMe = coupAJouer(me); 
		}
		
		// partie calcul coup à jouer 
		if (dealer.getNbCartes() == 1 && me1.getNbCartes() >= 2) { // on commence a proposer le coup a faire que quand le dealer a une carte et le joueur 2 
			coupMe1 = coupAJouer(me1); 
		}
		
		// partie calcul coup à jouer 
		if (dealer.getNbCartes() == 1 && me2.getNbCartes() >= 2) { // on commence a proposer le coup a faire que quand le dealer a une carte et le joueur 2 
			coupMe2 = coupAJouer(me2); 
		}

		CoupDeuxMe = coupMe1 +"\n"+coupMe2 ;
		maj_affichage(); 
	}
	
	public void maj_sabot() {
		nbCartesSabot = tailleSabot*52; // on recalcule le nombre de cartes 
		lblSabot.setText("Sabot : "+tailleSabot+" paquets");
		for (int i=0; i<carteSabot.size(); i++) { // pour remplir les autres infos pour les cartes 
			carteSabot.get(i).setNb_max(4*tailleSabot); // on met le nombre max de la même carte à 4* la taille du sabot
			carteSabot.get(i).poss_maj() ; // on remet le cout a jour 
			carteSabot.get(i).proba(nbCartesSabot) ;// on calcule la proba 
		}
		try{maj_proba();} 
		catch(FileNotFoundException e_majSabot){System.out.println("ERROR");}
	}
	
	private JFrame frame ; 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game_window window = new Game_window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Game_window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*
		 * 
		 * Initialisation des variables 
		 * 
		 * */
		
		/* Tableau des cartes */
		ArrayList<Card> listeCartes = new ArrayList<Card>(
	        Arrays.asList(new Card(1),new Card(2),new Card(3),new Card(4),new Card(5),new Card(6),new Card(7),new Card(8),new Card(9),new Card(10),new Card(11),new Card(12),new Card(13)));

		/* Tableau nom des cartes */
		ArrayList<String> nomCartes = new ArrayList<String>(
		        Arrays.asList("As","2", "3", "4", "5" , "6", "7" , "8", "9", "10", "V", "D", "R"));
		
		/* creation du sabot */ 
		carteSabot = new ArrayList<info_cartes>( // variable dans laquelle on va mettre tous les couples 
				 Arrays.asList(new info_cartes(1),new info_cartes(2),new info_cartes(3),new info_cartes(4),new info_cartes(5),new info_cartes(6),new info_cartes(7),new info_cartes(8),new info_cartes(9),new info_cartes(10),new info_cartes(11),new info_cartes(12),new info_cartes(13))); 
		
		
		for (int i=0; i<carteSabot.size(); i++) { // pour remplir les autres infos pour les cartes 
			carteSabot.get(i).setNb_max(4*tailleSabot); // on met le nombre max de la même carte à 4* la taille du sabot 
			carteSabot.get(i).setNb_passe(0); // et on précise que la carte est passé 0 fois 
		}
		// partie application 
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * 
		 * Choix de carte   
		 * 
		 * */
		
		// liste déroulante pour choisir la carte 
		list = new JList();
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		list.setVisibleRowCount(6);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi"};
				public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(258, 38, 107, 195);
		frame.getContentPane().add(list);
		
		/*
		 * 
		 * Affichage probas / compteur / sabot  
		 * 
		 * */
		
		/* Pour le coup à jouer */
		lblCoupJouer = new JLabel("Coup à jouer");
		lblCoupJouer.setBounds(23, 66, 100, 167);
		frame.getContentPane().add(lblCoupJouer);
		
		txtCoupJouer = new JTextArea();
		txtCoupJouer.setBackground(UIManager.getColor("CheckBox.background"));
		txtCoupJouer.setText("");
		txtCoupJouer.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtCoupJouer.setBounds(23, 66, 100, 100);
		frame.getContentPane().add(txtCoupJouer);
		txtCoupJouer.setVisible(false);
		
		/* Pour les probas */ 
		txtProba = new JTextArea();
		txtProba.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtProba.setText("As : 24"+"\n2 : 24"+"\n3 : 24"+"\n4 : 24"+"\n5 : 24"+"\n6 : 24"+"\n7 : 24"+"\n8 : 24"+"\n9 : 24"+"\n10 : 24"+"\nV : 24"+"\nD : 24"+"\nR : 24");
		txtProba.setBounds(377, 38, 40, 169);
		frame.getContentPane().add(txtProba);
		
		txtProba_1 = new JTextArea();
		txtProba_1.setText("7,69\n7,69\n7,69\n7,69\n7,69\n7,69\n7,69\n7,69\n7,69\n7,69\n7,69\n7,69\n7,69");
		txtProba_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtProba_1.setBounds(421, 38, 27, 169);
		frame.getContentPane().add(txtProba_1);
		
		/* Pour le compteur HI LO */
		txtLoHi = new JTextArea();
		txtLoHi.setText("Lo-Hi");
		txtLoHi.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		txtLoHi.setBounds(377, 226, 59, 40);
		frame.getContentPane().add(txtLoHi);
		
		/* Gestion du sabot */
		lblSabot = new JLabel("Sabot : "+tailleSabot+" paquets");
		lblSabot.setBounds(14, 11, 117, 16);
		frame.getContentPane().add(lblSabot);
		
		/*
		 * 
		 * Affichage joueur et dealer  
		 * 
		 * */
		
		/* Pour affichage du score du joueur */ 
		JLabel lblMeTitre = new JLabel("Me");
		lblMeTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeTitre.setBounds(140, 168, 61, 16);
		frame.getContentPane().add(lblMeTitre);
		
		scoreMe = new JTextArea();
		scoreMe.setText("");
		scoreMe.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		scoreMe.setBackground(UIManager.getColor("CheckBox.background"));
		scoreMe.setBounds(143, 188, 110, 37);
		frame.getContentPane().add(scoreMe);
		
		lblMeScore = new JLabel("Score : 0");
		lblMeScore.setBounds(143, 240, 74, 23);
		frame.getContentPane().add(lblMeScore);
		
		lblMeScoreS1 = new JLabel("");
		lblMeScoreS1.setBounds(136, 240, 37, 23);
		lblMeScoreS1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		frame.getContentPane().add(lblMeScoreS1);
		lblMeScoreS1.setVisible(false);
		
		lblMeScoreS2 = new JLabel("");
		lblMeScoreS2.setBounds(197, 240, 37, 23);
		lblMeScoreS2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		frame.getContentPane().add(lblMeScoreS2);
		lblMeScoreS2.setVisible(false);
		
		scoreMe1 = new JTextArea();
		scoreMe1.setText("");
		scoreMe1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		scoreMe1.setBackground(SystemColor.window);
		scoreMe1.setBounds(110, 188, 60, 40);
		scoreMe1.setVisible(false);
		frame.getContentPane().add(scoreMe1);
		
		scoreMe2 = new JTextArea();
		scoreMe2.setText("");
		scoreMe2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		scoreMe2.setBackground(SystemColor.window);
		scoreMe2.setBounds(185, 188, 60, 40);
		scoreMe2.setVisible(false);
		frame.getContentPane().add(scoreMe2);
		
		/* Pour affichage du score du Dealer */ 
		JLabel lblDealerTitre = new JLabel("Dealer");
		lblDealerTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealerTitre.setBounds(140, 86, 61, 16);
		frame.getContentPane().add(lblDealerTitre);
		
		scoreDealer = new JTextArea();
		scoreDealer.setText("");
		scoreDealer.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		scoreDealer.setBackground(UIManager.getColor("CheckBox.background"));
		scoreDealer.setBounds(143, 39, 110, 37);
		frame.getContentPane().add(scoreDealer);
		
		/* AFFICHAGE DES SCORES */ 
		lblDealerScore = new JLabel("Score : 0");
		lblDealerScore.setBounds(143, 8, 103, 23);
		frame.getContentPane().add(lblDealerScore);

		/*
		 * 
		 * BOUTONS 
		 * 
		 * */
		
		// partie split jeu 
		JButton btnSplit = new JButton("Split");
		btnSplit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// le split n'est possible que si le joueur a deux cartes identiques 
				if (me.carte_player.size() == 2 && me.carte_player.get(0).getPoint() == me.carte_player.get(1).getPoint()  ) { // s'il a 2 cartes avec la même valeur, alors paire, donc on autorise le split 
						
						// On met un bel affichage adapté a un split 
						btnSplit.setVisible(false); // on fait disparaitre le bouton split graphiquement, histoire de pouvoir afficher les score des deux joueurs
						btnMeCard.setVisible(false); // on enleve le + cartes 
						scoreMe.setVisible(false); // pour montrer les deux trucs derrière 
						
						// on fait apparaitre les boutons pour ajouter une carte aux jeux 
						btnSplit1.setVisible(true);
						btnSplit2.setVisible(true);
						
						// on enlève l'ancien score et on en ajoute 2 
						lblMeScore.setVisible(false);
						lblMeScoreS1.setVisible(true);
						lblMeScoreS2.setVisible(true);
						
						// pour afficher la nouvelle case avec les probas 
						txtCoupJouer.setVisible(true);
						lblCoupJouer.setVisible(false);
						
						// affichage des cartes 
						scoreMe1.setVisible(true);
						scoreMe2.setVisible(true);
						
						me1.add_carte(me.carte_player.get(0)); // on ajoute la première carte qui y es déjà 
						me2.add_carte(me.carte_player.get(1));
						
						scoreMe1.setText(me1.printCartesSplit());
						scoreMe2.setText(me2.printCartesSplit());
						
						maj_affichage(); 
						// calculer et afficher les cartes et les scores et tout le bordel 
				}
			}
		});
		btnSplit.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnSplit.setBounds(214, 237, 47, 29);
		frame.getContentPane().add(btnSplit);
		
		JButton btnSabotMoins = new JButton("-");
		btnSabotMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tailleSabot -- ; // on décrémente la taille du sabot et donc le nombre de cartes 
				maj_sabot() ; 
			}
		});
		btnSabotMoins.setBounds(6, 31, 55, 30);
		frame.getContentPane().add(btnSabotMoins);
		
		JButton btnSabotPlus = new JButton("+");
		btnSabotPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tailleSabot ++ ; // on décrémente la taille du sabot et donc le nombre de cartes 
				maj_sabot() ; 
			}
		});
		btnSabotPlus.setBounds(68, 31, 55, 30);
		frame.getContentPane().add(btnSabotPlus);

		// Préparer bouton autre 
		JButton btnOtherCard = new JButton("+ autre carte");
		btnOtherCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				other.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte 
				maj_last_carte(); 
				try{maj_proba();} 
				catch(FileNotFoundException e_btnOther){System.out.println("ERROR");} // on met a jour les probas  
				
			}
		});
		btnOtherCard.setBounds(6, 237, 117, 29);
		frame.getContentPane().add(btnOtherCard);
		
		btnMeCard = new JButton("+ carte");
		btnMeCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (me.getNbCartes() <10) {
					me.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte 
					scoreMe.setText(me.printCartes()); // on affiche
					maj_last_carte(); 
					try{maj_proba();} catch(FileNotFoundException e_btnMe){System.out.println("ERROR");}  // on met a jour les probas 
					}
			}
		});
		btnMeCard.setBounds(252, 237, 117, 29);
		frame.getContentPane().add(btnMeCard);
		
		JButton btnDealerCard = new JButton("+ carte");
		btnDealerCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				if (dealer.getNbCartes() <10) {
					dealer.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte 
					scoreDealer.setText(dealer.printCartes());
					maj_last_carte(); 
					try{maj_proba();} catch(FileNotFoundException e_btnDealer){System.out.println("ERROR");}  // on met a jour les probas
				}
			}
		});
		btnDealerCard.setBounds(252, 6, 117, 29);
		frame.getContentPane().add(btnDealerCard);
		
		btnSplit1 = new JButton("+");
		btnSplit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (me1.getNbCartes() <8) {
					me1.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte
					scoreMe1.setText(me1.printCartesSplit());
					try{maj_proba();} catch(FileNotFoundException e_btnMe){System.out.println("ERROR");}  // on met a jour les probas 
					}
			}
		});
		btnSplit1.setBounds(252, 237, 59, 29);
		frame.getContentPane().add(btnSplit1);
		btnSplit1.setVisible(false);
		
		btnSplit2 = new JButton("+");
		btnSplit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (me2.getNbCartes() <8) {
					me2.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte 
					scoreMe2.setText(me2.printCartesSplit());
					maj_last_carte(); 
					try{maj_proba();} catch(FileNotFoundException e_btnMe){System.out.println("ERROR");}  // on met a jour les probas 
					}
			}
		});
		btnSplit2.setBounds(310 , 237, 59, 29);
		frame.getContentPane().add(btnSplit2);
		btnSplit2.setVisible(false);
	
		JButton btnMancheOver = new JButton("Next hand");
		btnMancheOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblDealerScore.setText("Score : 0");  // on remet l'affichage à 0
				lblMeScore.setText("Score : 0");  
				lblMeScoreS1.setText(""); 
				lblMeScoreS1.setText(""); 
				
				dealer.init_cartes() ; // on enlève les anciennes cartes 
				me.init_cartes() ; 
				me1.init_cartes() ; 
				me2.init_cartes() ; 
				
				// nbCartesMe = me1.getNbCartes() = me2.getNbCartes() = 0 ; 
				scoreMe.setText(""); 
				scoreMe1.setText(""); 
				scoreMe2.setText(""); 
				scoreDealer.setText(""); 
				
				// on fait réapparaitre les boutons qui auraient pu disparaitre à cause du split 
				btnSplit.setVisible(true);
				btnMeCard.setVisible(true); 
				scoreMe.setVisible(true); 
				btnSplit1.setVisible(false);
				btnSplit2.setVisible(false);
				lblMeScore.setVisible(true);
				lblMeScoreS1.setVisible(false);
				lblMeScoreS2.setVisible(false);
				scoreMe1.setVisible(false);
				scoreMe2.setVisible(false);
				txtCoupJouer.setVisible(false);
				lblCoupJouer.setVisible(true);
				
			}
		});
		btnMancheOver.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnMancheOver.setBounds(363, 7, 85, 29);
		frame.getContentPane().add(btnMancheOver);
		
	}
}
