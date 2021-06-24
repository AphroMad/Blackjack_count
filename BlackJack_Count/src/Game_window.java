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



/* TO DO 
 * 
 * afficher proba chaque carte tombe 
 * afficher compteur hi lo
 * 
 * afficher coup conseillé tableau 
 * afficher coup conseillé proba (archi dur a faire ca) 
 * */


public class Game_window {

	// variable pour compter les nombres de cartes du joueur / dealer 
	int nbCartesDealer = 0 ; 
	int nbCartesMe = 0 ; 
	
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
		
		// partie BJ 
		Dealer dealer = new Dealer(); 
		Me me = new Me(); 
		
		/* Tableau des cartes */
		ArrayList<Card> listeCartes = new ArrayList<Card>(
	        Arrays.asList(new Card(1),new Card(2),new Card(3),new Card(4),new Card(5),new Card(6),new Card(7),new Card(8),new Card(9),new Card(10),new Card(11),new Card(12),new Card(13)));

		/* Tableau nom des cartes */
		ArrayList<String> nomCartes = new ArrayList<String>(
		        Arrays.asList("As","2", "3", "4", "5" , "6", "7" , "8", "9", "10", "V", "D", "R"));
		
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
		JList list = new JList();
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
		
		// la ou on mettra les probas que chaque carte tombe 
		JLabel lblNewLabel = new JLabel("Proba");
		lblNewLabel.setBounds(383, 37, 61, 221);
		frame.getContentPane().add(lblNewLabel);
		
		/* AFFICHAGE DES SCORES */ 
		JLabel lblDealerScore = new JLabel("Score : 0");
		lblDealerScore.setBounds(143, 8, 103, 23);
		frame.getContentPane().add(lblDealerScore);
		
		JLabel lblMeScore = new JLabel("Score : 0");
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
		
		/* Tableau des cartes du joueur et du dealer  */
		ArrayList<JLabel> cartesDealer = new ArrayList<JLabel>(
	        Arrays.asList(lblDealerCarte_1,lblDealerCarte_2,lblDealerCarte_3,lblDealerCarte_4,lblDealerCarte_5,lblDealerCarte_6,lblDealerCarte_7,lblDealerCarte_8, lblDealerCarte_9));

		ArrayList<JLabel> cartesMe = new ArrayList<JLabel>(
		        Arrays.asList(lblMeCarte_1,lblMeCarte_2,lblMeCarte_3,lblMeCarte_4,lblMeCarte_5,lblMeCarte_6,lblMeCarte_7,lblMeCarte_8, lblMeCarte_9));

		// Préparer bouton autre 
		JButton btnOtherCard = new JButton("+ autre carte");
		btnOtherCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("value is :"+ list.getSelectedValue());
			}
		});
		btnOtherCard.setBounds(6, 237, 117, 29);
		frame.getContentPane().add(btnOtherCard);
		
		
		JButton btnMeCard = new JButton("+ carte");
		btnMeCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (nbCartesMe <9) {
					System.out.println("value is :"+ list.getSelectedValue() + list.getSelectedIndex());
					
					JLabel case_now = cartesMe.get(nbCartesMe) ; // on trouve le bon label 
					nbCartesMe ++ ; // on incrémente le nombre de cartes que le dealer a pour le tour d'après 
					
					System.out.println(me+"\n"+dealer);
					
					me.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte 
					case_now.setText(nomCartes.get(list.getSelectedIndex())); // on met la carte dans la case 
					
					System.out.println(me+"\n"+dealer);
					
					lblMeScore.setText("Score : " + String.valueOf(me.calcul_score().get(0))); // on met a jour le score 
					}
				
				
			}
		});
		btnMeCard.setBounds(252, 237, 117, 29);
		frame.getContentPane().add(btnMeCard);
		
		JButton btnDealerCard = new JButton("+ carte");
		btnDealerCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				if (nbCartesDealer <9) {
				System.out.println("value is :"+ list.getSelectedValue() + list.getSelectedIndex());
				
				JLabel case_now = cartesDealer.get(nbCartesDealer) ; // on trouve le bon label 
				nbCartesDealer ++ ; // on incrémente le nombre de cartes que le dealer a pour le tour d'après 
				
				dealer.add_carte(listeCartes.get(list.getSelectedIndex())); // on ajoute la carte 
				case_now.setText(nomCartes.get(list.getSelectedIndex())); // on met la carte dans la case 
				
				lblDealerScore.setText("Score : " + String.valueOf(dealer.calcul_score().get(0))); // on met a jour le score 
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
		lblNewLabel_1.setBounds(23, 11, 100, 207);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
