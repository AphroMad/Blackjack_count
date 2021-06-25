import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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



/* TO DO 
 * 
 * Probleme des 3 as, ca plante ca mere, capter pourquoi et comment le régler 
 * 
 * afficher coup conseillé tableau 
 * afficher coup conseillé proba (archi dur a faire ca) 
 * 
 * */


public class Game_window {


	// partie BJ 
	Dealer dealer = new Dealer(); 
	Me me = new Me(); 
	Other other = new Other() ; 
	
	// variable pour compter les nombres de cartes du joueur / dealer 
	int nbCartesDealer = 0 ; 
	int nbCartesMe = 0 ; 
	
	// pour la gestion du sabot 
	int tailleSabot = 6; // variable qui affiche le nombre de paquets de cartes dans le sabot 
	int nbCartesSabot = tailleSabot * 52 ; // variables qui représente le nombre total de carte dans le sabot
	
	// compteur pour le hi lo 
	int compteurHiLo = 0 ; 
	
	/* Tableau des cartes du joueur et du dealer  */
	ArrayList<JLabel> cartesDealer; 
	ArrayList<JLabel> cartesMe ; 
	
	JTextArea txtProba ; 
	JTextArea txtProba_1 ; 
	JTextArea txtLoHi ;
	
	JLabel lblSabot ; 
	JLabel lblDealerScore ; 
	JLabel lblMeScore ; 
	
	JList list ; 
	
	ArrayList<info_cartes> carteSabot; 
	
	public void maj_affichage() {
		System.out.println("On met a jour l'affichage bitch");
		txtProba.setText("As : "+carteSabot.get(0).getNb_poss()+"\n2 : "+carteSabot.get(1).getNb_poss()+"\n3 : "+carteSabot.get(2).getNb_poss()+"\n4 : "+carteSabot.get(3).getNb_poss()+"\n5 : "+carteSabot.get(4).getNb_poss()+"\n6 : "+carteSabot.get(5).getNb_poss()+"\n7 : "+carteSabot.get(6).getNb_poss()+"\n8 : "+carteSabot.get(7).getNb_poss()+"\n9 : "+carteSabot.get(8).getNb_poss()+"\n10 : "+carteSabot.get(9).getNb_poss()+"\nV : "+carteSabot.get(10).getNb_poss()+"\nD : "+carteSabot.get(11).getNb_poss()+"\nR : "+carteSabot.get(12).getNb_poss());
		txtProba_1.setText(carteSabot.get(0).getProba_tomb() +"\n" + carteSabot.get(1).getProba_tomb() +"\n"+carteSabot.get(2).getProba_tomb() +"\n"+carteSabot.get(3).getProba_tomb() +"\n"+carteSabot.get(4).getProba_tomb() +"\n"+carteSabot.get(5).getProba_tomb() +"\n"+carteSabot.get(6).getProba_tomb() +"\n"+carteSabot.get(7).getProba_tomb() +"\n"+carteSabot.get(8).getProba_tomb() +"\n"+carteSabot.get(9).getProba_tomb() +"\n"+carteSabot.get(10).getProba_tomb() +"\n"+carteSabot.get(11).getProba_tomb() +"\n"+carteSabot.get(12).getProba_tomb());
		if (compteurHiLo >= 0) {txtLoHi.setText("Lo-Hi\n "+compteurHiLo);} // on affiche compteur lo hi positif 
		else {txtLoHi.setText("Lo-Hi\nM"+compteurHiLo*(-1));} // compteur lo hi négatif 
		lblMeScore.setText("Score : " + String.valueOf(me.calcul_score().get(0))); // on met a jour le score de moi 
		lblDealerScore.setText("Score : " + String.valueOf(dealer.calcul_score().get(0))); // on met a jour le score 
		
	}
	
	public void maj_proba() {
		
		carteSabot.get(list.getSelectedIndex()).carte_recu(); // on dit qu'on a bien recu la carte 
		nbCartesSabot -- ; 
		for (int i=0; i<carteSabot.size(); i++) { // pour remplir les autres infos pour les cartes 
			carteSabot.get(i).poss_maj() ; // on remet le cout a jour 
			carteSabot.get(i).proba(nbCartesSabot) ;// on calcule la proba 
		}
		if (list.getSelectedIndex()==0 || list.getSelectedIndex() >= 9) { // si c'est un AS, 10, V, D, R. 
			compteurHiLo ++ ;
		}
		else if (list.getSelectedIndex()>0 && list.getSelectedIndex()<6) { // si c'est 2, 3, 4, 5, 6
			compteurHiLo -- ;
		}
		// si c'est le reste, a savoir 7, 8, 9, on ne fait rien 
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
		maj_proba(); 	
		maj_affichage(); 
	}
	
	private JFrame frame;

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
		
		// titre (dealer / me) 
		JLabel lblDealerTitre = new JLabel("Dealer");
		lblDealerTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblDealerTitre.setBounds(140, 86, 61, 16);
		frame.getContentPane().add(lblDealerTitre);
		
		JLabel lblMeTitre = new JLabel("Me");
		lblMeTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeTitre.setBounds(140, 168, 61, 16);
		frame.getContentPane().add(lblMeTitre);
		
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
		
		/* AFFICHAGE DES SCORES */ 
		lblDealerScore = new JLabel("Score : 0");
		lblDealerScore.setBounds(143, 8, 103, 23);
		frame.getContentPane().add(lblDealerScore);
		
		
		lblMeScore = new JLabel("Score : 0");
		lblMeScore.setBounds(143, 240, 103, 23);
		frame.getContentPane().add(lblMeScore);
		
		/* Les cartes */ 
		JLabel lblDealerCarte_1 = new JLabel("");
		lblDealerCarte_1.setBounds(143, 36, 20, 15);
		frame.getContentPane().add(lblDealerCarte_1);
		
		JLabel lblDealerCarte_2 = new JLabel("");
		lblDealerCarte_2.setBounds(163, 36, 20, 15);
		frame.getContentPane().add(lblDealerCarte_2);
		
		JLabel lblDealerCarte_3 = new JLabel("");
		lblDealerCarte_3.setBounds(183, 36, 20, 15);
		frame.getContentPane().add(lblDealerCarte_3);
		
		JLabel lblDealerCarte_4 = new JLabel("");
		lblDealerCarte_4.setBounds(143, 51, 20, 15);
		frame.getContentPane().add(lblDealerCarte_4);
		
		JLabel lblDealerCarte_5 = new JLabel("");
		lblDealerCarte_5.setBounds(163, 51, 20, 15);
		frame.getContentPane().add(lblDealerCarte_5);
		
		JLabel lblDealerCarte_6 = new JLabel("");
		lblDealerCarte_6.setBounds(183, 51, 20, 15);
		frame.getContentPane().add(lblDealerCarte_6);
		
		JLabel lblDealerCarte_7 = new JLabel("");
		lblDealerCarte_7.setBounds(143, 66, 20, 15);
		frame.getContentPane().add(lblDealerCarte_7);
		
		JLabel lblDealerCarte_8 = new JLabel("");
		lblDealerCarte_8.setBounds(163, 66, 20, 15);
		frame.getContentPane().add(lblDealerCarte_8);
		
		JLabel lblDealerCarte_9 = new JLabel("");
		lblDealerCarte_9.setBounds(183, 66, 20, 15);
		frame.getContentPane().add(lblDealerCarte_9);
		
		JLabel lblMeCarte_1 = new JLabel("");
		lblMeCarte_1.setBounds(143, 218, 20, 15);
		frame.getContentPane().add(lblMeCarte_1);
		
		JLabel lblMeCarte_2 = new JLabel("");
		lblMeCarte_2.setBounds(163, 218, 20, 15);
		frame.getContentPane().add(lblMeCarte_2);
		
		JLabel lblMeCarte_3 = new JLabel("");
		lblMeCarte_3.setBounds(183, 218, 20, 15);
		frame.getContentPane().add(lblMeCarte_3);
		
		JLabel lblMeCarte_4 = new JLabel("");
		lblMeCarte_4.setBounds(143, 203, 20, 15);
		frame.getContentPane().add(lblMeCarte_4);
		
		JLabel lblMeCarte_5 = new JLabel("");
		lblMeCarte_5.setBounds(163, 203, 20, 15);
		frame.getContentPane().add(lblMeCarte_5);
		
		JLabel lblMeCarte_6 = new JLabel("");
		lblMeCarte_6.setBounds(183, 203, 20, 15);
		frame.getContentPane().add(lblMeCarte_6);
		
		JLabel lblMeCarte_7 = new JLabel("");
		lblMeCarte_7.setBounds(143, 188, 20, 15);
		frame.getContentPane().add(lblMeCarte_7);
		
		JLabel lblMeCarte_8 = new JLabel("");
		lblMeCarte_8.setBounds(163, 188, 20, 15);
		frame.getContentPane().add(lblMeCarte_8);
		
		JLabel lblMeCarte_9 = new JLabel("");
		lblMeCarte_9.setBounds(183, 188, 20, 15);
		frame.getContentPane().add(lblMeCarte_9);
		
		cartesDealer = new ArrayList<JLabel>(
		        Arrays.asList(lblDealerCarte_1,lblDealerCarte_2,lblDealerCarte_3,lblDealerCarte_4,lblDealerCarte_5,lblDealerCarte_6,lblDealerCarte_7,lblDealerCarte_8, lblDealerCarte_9));

		
		cartesMe = new ArrayList<JLabel>(
		        Arrays.asList(lblMeCarte_1,lblMeCarte_2,lblMeCarte_3,lblMeCarte_4,lblMeCarte_5,lblMeCarte_6,lblMeCarte_7,lblMeCarte_8, lblMeCarte_9));

		// Préparer bouton autre 
		JButton btnOtherCard = new JButton("+ autre carte");
		btnOtherCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				other.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte 
				
				maj_proba() ; // on met a jour les probas  
				
			}
		});
		btnOtherCard.setBounds(6, 237, 117, 29);
		frame.getContentPane().add(btnOtherCard);
		
		
		JButton btnMeCard = new JButton("+ carte");
		btnMeCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (nbCartesMe <9) {
					//System.out.println("value is :"+ list.getSelectedValue() + list.getSelectedIndex());
					
					JLabel case_now = cartesMe.get(nbCartesMe) ; // on trouve le bon label 
					nbCartesMe ++ ; // on incrémente le nombre de cartes que le dealer a pour le tour d'après 
					
					me.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte 
					case_now.setText(nomCartes.get(list.getSelectedIndex())); // on met la carte dans la case 
					
					maj_proba() ; // on met a jour les probas 
					
					}
				
				
			}
		});
		btnMeCard.setBounds(252, 237, 117, 29);
		frame.getContentPane().add(btnMeCard);
		
		JButton btnDealerCard = new JButton("+ carte");
		btnDealerCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				if (nbCartesDealer <9) {
				//System.out.println("value is :"+ list.getSelectedValue() + list.getSelectedIndex());
				
					JLabel case_now = cartesDealer.get(nbCartesDealer) ; // on trouve le bon label 
					nbCartesDealer ++ ; // on incrémente le nombre de cartes que le dealer a pour le tour d'après 
					
					dealer.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte 
					case_now.setText(nomCartes.get(list.getSelectedIndex())); // on met la carte dans la case 
					
					maj_proba() ; // on met a jour les probas
				
				}
			}
		});
		btnDealerCard.setBounds(252, 6, 117, 29);
		frame.getContentPane().add(btnDealerCard);
		
		JButton btnMancheOver = new JButton("Manche over");
		btnMancheOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblDealerScore.setText("Score : 0");  // on remet l'affichage à 0
				lblMeScore.setText("Score : 0");  
				
				dealer.init_cartes() ; // on enlève les anciennes cartes 
				me.init_cartes() ; 
				
				nbCartesDealer = 0 ; // on met le nombre de cartes à 0 
				nbCartesMe = 0 ; 
				
				for (int i=0; i<cartesDealer.size();i++) {// on passe par tous les affichages de cartes 
					cartesDealer.get(i).setText("") ; // on remet le texte a vide 
					cartesMe.get(i).setText(""); 
				}
				
			}
		});
		btnMancheOver.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnMancheOver.setBounds(363, 7, 85, 29);
		frame.getContentPane().add(btnMancheOver);
		
		JLabel lblNewLabel_1 = new JLabel("Coup à jouer");
		lblNewLabel_1.setBounds(23, 66, 100, 152);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
