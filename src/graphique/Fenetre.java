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
import packGestionDonnees.donnees.DonneesBrutes;
import packGestionDonnees.donnees.ListeChamps;
import packGestionDonnees.donnees.Parametres;

/**
 * @author
 *
 * l'IHM est compos�e de deux parties : <BR />
 *		- ent�te, <BR />
 *		- champs. <BR />
 * La partie Ent�te contient le champ : Ligne et les deux boutons de conversion. <BR />
 * La partie Champs contient tous les champs pr�sents dans le fichier JSON.
 */
public class Fenetre extends JFrame{

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
	
	//private List<ListeChamps> listeChamp = new ArrayList<ListeChamps>();

	/**
	 * Constructeur par d�faut
	 */
	public Fenetre() {
		
		super();
	}
	
	/**
	 * Construction de la fen�tre principale (IHM)
	 * 
	 * @param parametres : contient la liste de tous les champs 
	 * 					   pr�sents dans le fichier JSON
	 */
	public Fenetre(List<Parametres> parametres) {
		
		super();

		param = parametres;
		
		this.setSize(this.width, this.height);
		this.setTitle(this.title);
		
		// Cr�ation du panel ligne
		this.jPanelLigne = new JPanel(new MigLayout("debug, fillx"));			// Panel qui va contenir le label + la ligne de donn�es
		JLabel jlLigne = new JLabel("Ligne : ");	// Cr�ation du label : "Ligne : "
		this.jPanelLigne.add(jlLigne);
		Dimension preferredSize = new Dimension(1000, 20);
		jTextFieldLigne = new JTextField();
		jTextFieldLigne.setPreferredSize(preferredSize);
		this.jPanelLigne.add(jTextFieldLigne, "growx");		// deja teste avec grow mais pas genial

		// Cr�ation du panel boutons
		JPanel jPanelBoutons = new JPanel();
		JButton jbGenValeurs = new JButton("Ligne => Valeur");
		jbGenValeurs.addActionListener(new boutonLigneValeur());
		jPanelBoutons.add(jbGenValeurs);
		
		JButton jbGenLigne = new JButton("Valeur => Ligne");
		jbGenValeurs.addActionListener(new boutonValeurLigne());
		jPanelBoutons.add(jbGenLigne);

		// Cr�ation du panel ent�te
		this.jPanelEntete = new JPanel(new MigLayout("debug, fillx, wrap"));			// Panel contenant la ligne de donnees + les deux boutons de conversion
		this.jPanelEntete.add(this.jPanelLigne);
		this.jPanelEntete.add(jPanelBoutons);
		
		// Cr�ation du panel champs
		this.jPanelChamps = new JPanel(new MigLayout("debug, fillx, wrap"));
		
		// Cr�ation du panel principal
		this.jPanel = new JPanel(new MigLayout("debug, fillx, wrap"));
		this.add(this.jPanel);
		this.jPanel.add(this.jPanelEntete, "growx");
		this.jPanel.add(this.jPanelChamps);
		
		alimentationDesChamps(parametres);
 	}
	
	/**
	 * 
	 *
	 */
	private class boutonLigneValeur implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			jPanelChamps.removeAll();
			dB.setDonneesBrutes(jTextFieldLigne.getText());
			alimentationDesChamps(param);
			jPanelChamps.validate();

		}
	}
	
	/**
	 * 
	 *
	 */
	private class boutonValeurLigne implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
//			jPanelChamps.removeAll();
//			dB.setDonneesBrutes(jTextFieldLigne.getText());
//			alimentationDesChamps(param);
//			jPanelChamps.validate();

		}
	}

	/**
	 * 
	 * @param parametres
	 */
	private void alimentationDesChamps(List<Parametres> parametres) {
		for(int i = 0 ; i < parametres.size() ; i++) {
			ajouterNomChamp(parametres.get(i).getNom(), 
					parametres.get(i).getPosition_debut(),
					parametres.get(i).getPosition_fin(), 
					parametres.get(i).getNbrCaractParChamp());
		}
	}
	
	/**
	 * Methode qui permet d'ajouter un champ par 
	 * information (ligne) lue dans le fichier JSON
	 * @param nom
	 * @param position_fin 
	 * @param position_debut 
	 */
	public void ajouterNomChamp(String nom, int position_debut, int position_fin, int nbrCaractParChamp){  // !!! UTILISER nbrCaractParChamp
		
		String valeurChamp = "";
		if(!this.dB.getDonneesBrutes().isEmpty()) {
			valeurChamp = this.dB.getDonneesBrutes().substring(position_debut, position_fin + 1);
		}
		
		// Exemple : UF (max : 4 caract.)
		String nomChamp = nom + " (max : " + nbrCaractParChamp + " caract.) : "; 
		JPanel jPanelChamp = new JPanel(new MigLayout("debug, fillx"));
		JLabel jlChamp = new JLabel(nomChamp);	// Cr�ation du label qui contiendra le nom du champ
		jPanelChamp.add(jlChamp);
		
		JTextField jTextFieldChamp = new JTextField(valeurChamp);
		//JTextField jTextFieldChamp = new JTextField();
		Dimension preferredSize = new Dimension(1000, 20);
		jTextFieldChamp.setPreferredSize(preferredSize);
		jPanelChamp.add(jTextFieldChamp, "growx");
		this.jPanelChamps.add(jPanelChamp);
	}

}
