package graphique;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import packGestionDonnees.OperationsSurDonnees;
import packGestionDonnees.donnees.DonneesBrutes;
import packGestionDonnees.donnees.Parametres;

/**
 * l'IHM est compos�e de deux parties : <BR />
 * - ent�te, <BR />
 * - champs. <BR />
 * La partie Ent�te contient le champ : Ligne et les deux boutons de conversion.
 * <BR />
 * La partie Champs contient tous les champs pr�sents dans le fichier JSON.
 */
public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;

	private int width = 640;
	private int height = 480;

	private String title = "Gestion de donn�es";

	private JPanel jPanel;
	private JPanel jPanelEntete;
	private JPanel jPanelLigne;
	private JTextField jTextFieldLigne;
	private JPanel jPanelChamps;

	private DonneesBrutes dB = new DonneesBrutes();

	private List<Parametres> param;

	private List<JTextField> listeChampsJTF = new ArrayList<JTextField>();

	/**
	 * Constructeur par d�faut
	 */
	public Fenetre() {

		super();
	}

	/**
	 * Construction de la fen�tre principale (IHM)
	 * 
	 * @param parametres
	 *            : contient la liste de tous les champs pr�sents dans le
	 *            fichier JSON
	 */
	public Fenetre(List<Parametres> parametres) {

		this.param = parametres;

		this.setSize(this.width, this.height);
		this.setTitle(this.title);

		// Cr�ation du panel ligne
		this.jPanelLigne = new JPanel(new MigLayout("fillx")); // Panel qui va
																// contenir le
																// label + la
																// ligne de
																// donn�es
		JLabel jlLigne = new JLabel("Ligne : "); // Cr�ation du label : "Ligne :
													// "
		this.jPanelLigne.add(jlLigne);
		Dimension preferredSize = new Dimension(1000, 20);
		this.jTextFieldLigne = new JTextField();
		this.jTextFieldLigne.setPreferredSize(preferredSize);
		this.jPanelLigne.add(this.jTextFieldLigne, "growx");

		// Cr�ation du panel boutons
		JPanel jPanelBoutons = new JPanel();
		JButton jbGenValeurs = new JButton("Ligne => Valeur");
		jbGenValeurs.addActionListener(new boutonLigneValeur());
		jPanelBoutons.add(jbGenValeurs);

		JButton jbGenLigne = new JButton("Valeur => Ligne");
		jbGenLigne.addActionListener(new boutonValeurLigne());
		jPanelBoutons.add(jbGenLigne);

		// Cr�ation du panel ent�te qui contient les panels : ligne et boutons
		this.jPanelEntete = new JPanel(new MigLayout("fillx, wrap")); // Panel
																		// contenant
																		// la
																		// ligne
																		// de
																		// donnees
																		// + les
																		// deux
																		// boutons
																		// de
																		// conversion
		this.jPanelEntete.add(this.jPanelLigne);
		this.jPanelEntete.add(jPanelBoutons);

		// Cr�ation du panel champs
		this.jPanelChamps = new JPanel(new MigLayout("fillx, wrap"));

		// Cr�ation du panel principal qui contient les panels : ent�te et
		// champs
		this.jPanel = new JPanel(new MigLayout("fillx, wrap"));
		this.add(this.jPanel);
		this.jPanel.add(this.jPanelEntete, "growx");
		this.jPanel.add(this.jPanelChamps);

		affichageChampsVides(parametres);
		// alimentationDesChamps(parametres);
	}

	/**
	 * On affichage les champs vides au lancement de l'application Cette m�thode
	 * est utilis�e une seule fois.
	 * 
	 * @param parametres
	 */
	private void affichageChampsVides(List<Parametres> parametres) {

		for (int i = 0; i < parametres.size(); i++) {

			String nomChamp = parametres.get(i).getNom() + " (max : " + parametres.get(i).getNbrCaractParChamp()
					+ " caract.) : ";
			JPanel jPanelChamp = new JPanel(new MigLayout("fillx"));
			JLabel jlChamp = new JLabel(nomChamp); // Cr�ation du label qui
													// contiendra le nom du
													// champ
			jPanelChamp.add(jlChamp);

			JTextField jTextFieldChamp = new JTextField();
			this.listeChampsJTF.add(jTextFieldChamp);
			Dimension preferredSize = new Dimension(1000, 20);
			jTextFieldChamp.setPreferredSize(preferredSize);
			jPanelChamp.add(jTextFieldChamp, "growx"); // On ajoute les
														// jTextField contenant
														// les valeurs des
														// champs
			this.jPanelChamps.add(jPanelChamp);
		}
	}

	/**
	 * En cliquant sur le bouton Ligne => valeur, on remplit les champs en
	 * partant des donn�es brutes pr�sentes dans le premier champ : "Ligne : "
	 */
	private class boutonLigneValeur implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Fenetre.this.dB.setDonneesBrutes(Fenetre.this.jTextFieldLigne.getText());
			// FIXME : alimentationDesChampsV1(Fenetre.this.param);
			alimentationDesChampsV2(Fenetre.this.param);
			Fenetre.this.jPanelChamps.validate();
		}

		
	}
	
	private void alimentationDesChampsV2(List<Parametres> param) {
		// TODO Auto-generated method stub
		
		List<String> listeDecoupee = OperationsSurDonnees.decouper(this.dB, param);
		
		for (int i = 0; i < listeDecoupee.size(); i++) {
			this.listeChampsJTF.get(i).setText(listeDecoupee.get(i));
		}
		
	}

	/**
	 * 
	 * @param parametres
	 */
	private void alimentationDesChampsV1(List<Parametres> parametres) {
		for (int i = 0; i < parametres.size(); i++) {
			alimenterChampV1(parametres.get(i).getNom(), parametres.get(i).getPosition_debut(),
					parametres.get(i).getPosition_fin(), parametres.get(i).getNbrCaractParChamp(), i);
		}
	}

	/**
	 * Methode qui permet d'ajouter un champ par information (ligne) lue dans le
	 * fichier JSON
	 * 
	 * @param nom
	 * @param position_fin
	 * @param position_debut
	 */
	public void alimenterChampV1(String nom, int position_debut, int position_fin, int nbrCaractParChamp, int i) { // !!!
																													// UTILISER
																													// nbrCaractParChamp

		String valeurChamp = "";
		String vide = " ";

		if (!this.dB.getDonneesBrutes().isEmpty()) { // Exemple : aaa de 0 � 3 =
														// 4, donc il manque un
														// caract�re (espace)

			// v�rifier qu'� partir de la position_debut il y a un caract�re
			// jusqu'� la position_fin, sinon, ajouter un espace dans la chaine
			while (this.dB.getDonneesBrutes().length() < position_fin + 1) {
				String temp = this.dB.getDonneesBrutes();
				this.dB.setDonneesBrutes(temp + vide);
			}
			valeurChamp = this.dB.getDonneesBrutes().substring(position_debut, position_fin + 1);
			// dB.setDonneesBrutes(valeurChamp);
		}

		// Exemple : UF (max : 4 caract.)

		// listeChampsJTF.add(jTextFieldChamp); // A chaque clic sur L => V, la
		// taille de la liste augmente, donc trouver une autre solution !!!
		// this.listeChampsJTF.set(i, jTextFieldValeurChamp); // Fonctionne !!!
		this.listeChampsJTF.get(i).setText(valeurChamp); // Ne fonctionne pas,
															// (m'expliquer
															// pourquoi !!!)
	}

	/**
	 * - effacer les donn�es brutes dans le champ de la premi�re ligne - lire
	 * les donn�es des champs (boucle sur tous les champs) - apr�s saisie dans
	 * chaque champ, ajouter des espaces si besoin - alimenter la premi�re ligne
	 * avec les donn�es issus des champs
	 */
	private class boutonValeurLigne implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Fenetre.this.jTextFieldLigne.setText(null); // On efface le contenu
														// de la ligne A TESTER
			creerLigneDonneesBrutes();
			// jTextFieldLigne.validate(); // <== ne doit pas �tre n�cessaire,
			// et ne doit pas fonctionner !!!
		}

	}

	/**
	 * Lors de l'appel � cette m�thode par un clic sur le bouton : "Valeur =>
	 * Ligne", l'application peut �tre dans diff�rents �tats : - la ligne de
	 * donn�es contient des donn�es, les champs sont vides => la ligne de
	 * donn�es brutes est mise � jour (les champs sont vides ou avec des
	 * espaces) - la ligne de donn�es est vide, les champs contiennent des
	 * donn�es => la ligne de donn�es brutes est mise � jour et contient les
	 * valeurs des champs.
	 *
	 * @param nom
	 * @param position_debut
	 * @param position_fin
	 * @param nbrCaractParChamp
	 */
	// private void creerLigneDonneesBrutes(String nom, int position_debut, int
	// position_fin, int nbrCaractParChamp) {
	private void creerLigneDonneesBrutes() {
		/*
		 * - Pour tous les champs, s'ils ne sont pas vides, on reprend les
		 * valeurs des champs pour cr�er la ligne de donn�es brutes
		 */
		String unEspace = " ";
		String LigneDB = "";
		String valeurChamp = "";
		// for(int i = 0 ; i < param.size() ; i++) {
		for (int i = 0; i < this.listeChampsJTF.size(); i++) { // Attention,
																// apr�s appui
																// sur le bt, sa
																// valeur passe
																// de 3 � 6 !!!
			// On r�cup�re la valeur du champ dans la liste des champs
			valeurChamp = this.listeChampsJTF.get(i).getText(); // Si le
																// valeurChamp
																// est vide,
																// valeurChamp
																// contient : ""

			System.out.println("Valeur de i : " + i + ", Valeur du champ : " + valeurChamp + ", Taille ListeJTF : "
					+ this.listeChampsJTF.size());
			// v�rifier qu'� partir de la position_debut il y a un caract�re
			// jusqu'� la position_fin, sinon, ajouter un espace dans la chaine
			if (valeurChamp.length() < this.param.get(i).getNbrCaractParChamp()) { // L'arrayList
																					// param
																					// va
																					// jusqu'�
																					// 2
																					// alors
																					// que
																					// i
																					// =
																					// 3
																					// donc
																					// IndexOutOfBoundsException
				while (valeurChamp.isEmpty() || valeurChamp.length() < this.param.get(i).getNbrCaractParChamp()) {
					valeurChamp = valeurChamp + unEspace;
				}
			}
			LigneDB = LigneDB + valeurChamp;
		}
		this.dB.setDonneesBrutes(LigneDB);
		// on affiche le r�sultat dans la ligne de donn�es brutes
		this.jTextFieldLigne.setText(this.dB.getDonneesBrutes());
	}
}
