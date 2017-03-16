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
 * Cette classe contient l'IHM de l'application.
 * 
 * l'IHM est composée de deux parties : <BR />
 * - entête, <BR />
 * - champs. <BR />
 * La partie Entête contient le champ : Ligne et les deux boutons de conversion.
 * <BR />
 * La partie Champs contient tous les champs présents dans le fichier JSON.
 */
public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;

	private int width = 640;
	private int height = 480;

	private String title = "Gestion de données";

	private JPanel jPanel;
	private JPanel jPanelEntete;
	private JPanel jPanelLigne;
	private JTextField jTextFieldLigne;
	private JPanel jPanelChamps;

	private DonneesBrutes dB = new DonneesBrutes();

	private List<Parametres> param;

	private List<JTextField> listeChampsJTF = new ArrayList<JTextField>();

	/**
	 * Constructeur par défaut
	 */
	public Fenetre() {

		super();
	}

	/**
	 * Construction de la fenêtre principale (IHM)
	 * 
	 * @param parametres : contient la liste de tous les champs présents dans le fichier JSON
	 */
	public Fenetre(List<Parametres> parametres) {

		this.param = parametres;

		this.setSize(this.width, this.height);
		this.setTitle(this.title);

		// Création du panel ligne
		// Panel qui va contenir le label + la ligne de données
		this.jPanelLigne = new JPanel(new MigLayout("fillx"));
		// Création du label : "Ligne :
		JLabel jlLigne = new JLabel("Ligne : ");

		this.jPanelLigne.add(jlLigne);
		Dimension preferredSize = new Dimension(1000, 20);
		this.jTextFieldLigne = new JTextField();
		this.jTextFieldLigne.setPreferredSize(preferredSize);
		this.jPanelLigne.add(this.jTextFieldLigne, "growx");

		// Création du panel boutons
		JPanel jPanelBoutons = new JPanel();
		JButton jbGenValeurs = new JButton("Ligne => Valeur");
		jbGenValeurs.addActionListener(new boutonLigneValeur());
		jPanelBoutons.add(jbGenValeurs);

		JButton jbGenLigne = new JButton("Valeur => Ligne");
		jbGenLigne.addActionListener(new boutonValeurLigne());
		jPanelBoutons.add(jbGenLigne);

		// Création du panel entête qui contient les panels : ligne données brutes + 2 boutons
		this.jPanelEntete = new JPanel(new MigLayout("fillx, wrap"));
		this.jPanelEntete.add(this.jPanelLigne);
		this.jPanelEntete.add(jPanelBoutons);

		// Création du panel champs
		this.jPanelChamps = new JPanel(new MigLayout("fillx, wrap"));

		// Création du panel principal qui contient les panels : entête et champs
		this.jPanel = new JPanel(new MigLayout("fillx, wrap"));
		this.add(this.jPanel);
		this.jPanel.add(this.jPanelEntete, "growx");
		this.jPanel.add(this.jPanelChamps);

		affichageChampsVides(parametres);
	}

	/**
	 * On affichage les champs vides au lancement de l'application 
	 * Cette méthode est utilisée une seule fois.
	 * 
	 * @param parametres contient la liste des paramètres afin de créer 
	 * 					 les champs affichés dans l'IHM
	 */
	private void affichageChampsVides(List<Parametres> parametres) {

		for (int i = 0; i < parametres.size(); i++) {

			String nomChamp = parametres.get(i).getNom() + " (max : " + parametres.get(i).getNbrCaractParChamp()
					+ " caract.) : ";
			JPanel jPanelChamp = new JPanel(new MigLayout("fillx"));
			// Création du label qui contiendra le nom du champ
			JLabel jlChamp = new JLabel(nomChamp);
			jPanelChamp.add(jlChamp);

			JTextField jTextFieldChamp = new JTextField();
			this.listeChampsJTF.add(jTextFieldChamp);
			Dimension preferredSize = new Dimension(1000, 20);
			jTextFieldChamp.setPreferredSize(preferredSize);
			// On ajoute les jTextField contenant les valeurs des champs
			jPanelChamp.add(jTextFieldChamp, "growx");
			this.jPanelChamps.add(jPanelChamp);
		}
	}

	/**
	 * En cliquant sur le bouton Ligne => valeur, on remplit les champs en
	 * partant des données brutes présentes dans la première ligne 
	 * de l'IHM : "Ligne : "
	 */
	private class boutonLigneValeur implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Fenetre.this.dB.setDonneesBrutes(Fenetre.this.jTextFieldLigne.getText());
			alimentationDesChamps(Fenetre.this.param);
			Fenetre.this.jPanelChamps.validate();
		}
	}
	
	/**
	 * Cette méthode permet de découper la ligne de données brutes suivant les informations
	 * données par le fichier JSON
	 * 
	 * @param param liste des paramètres concernant les champs définis dans le fichier JSON
	 */
	private void alimentationDesChamps(List<Parametres> param) {
		
		List<String> listeDecoupee = OperationsSurDonnees.decouper(this.dB, param);
		
		for (int i = 0; i < listeDecoupee.size(); i++) {
			this.listeChampsJTF.get(i).setText(listeDecoupee.get(i));
		}
	}

	/**
	 * Méthode qui permet d'ajouter un champ par information lue dans le fichier JSON
	 * 
	 * @param nom
	 * @param position_fin
	 * @param position_debut
	 */
	public void alimenterChamp(String nom, int position_debut, int position_fin, int nbrCaractParChamp, int i) {
		
		String valeurChamp = "";
		String espace = " ";

		// Exemple : aaa de 0 à 3 = 4, donc il manque un caractère (espace)
		if (!this.dB.getDonneesBrutes().isEmpty()) { 

			// vérifier qu'à partir de la position_debut il y a un caractère
			// jusqu'à la position_fin, sinon, ajouter un espace dans la chaine
			while (this.dB.getDonneesBrutes().length() < position_fin + 1) {
				String nomChamp = this.dB.getDonneesBrutes();
				this.dB.setDonneesBrutes(nomChamp + espace);
			}
			valeurChamp = this.dB.getDonneesBrutes().substring(position_debut, position_fin + 1);
		}
		// Exemple : UF (max : 4 caract.)
		this.listeChampsJTF.get(i).setText(valeurChamp);
	}

	/**
	 * bouton permettant de découper la ligne de données brutes afin d'alimenter
	 * les champs suivant la définition donnée par le fichier JSON
	 */
	private class boutonValeurLigne implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// On efface le contenu de la ligne
			Fenetre.this.jTextFieldLigne.setText(null);
			creerLigneDonneesBrutes();
		}
	}

	/**
	 * Méthode permettant de créer la ligne de données brutes
	 * Affichage de cette ligne dans l'IHM (première ligne)
	 */
	private void creerLigneDonneesBrutes() {
		// Création d'une liste de champs à partir de la liste de valeurs des champs contenus dans la liste de jTextField  
		List<String> champs = new ArrayList<String>();
		for(int i = 0 ; i < this.listeChampsJTF.size() ; i++) {
			champs.add(this.listeChampsJTF.get(i).getText());
		}
		// On assemble tous les champs afin de créer la ligne de données brutes
		this.dB.setDonneesBrutes(OperationsSurDonnees.assemblerChamps(param, champs));
		// on affiche le résultat dans la ligne de données brutes
		this.jTextFieldLigne.setText(this.dB.getDonneesBrutes());
	}
}
