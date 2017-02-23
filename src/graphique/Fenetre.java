package graphique;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class Fenetre extends JFrame{

	private static final long serialVersionUID = 1L;

	private int width = 640;
	private int height = 480;

	private String title = "Gestion de données";

	private JPanel jPanel;
	private JPanel jPanelEntete;
	private JPanel jPanelLigne;
	private JPanel jPanelChamps;
	
	/**
	 * Constructeur
	 */
	public Fenetre() {
		super();

		this.setSize(this.width, this.height);
		this.setTitle(this.title);
		
		// Création du panel ligne
		this.jPanelLigne = new JPanel(new MigLayout("debug, fillx"));			// Panel qui va contenir le label + la ligne de données
		JLabel jlLigne = new JLabel("Ligne : ");	// Création du label : "Ligne : "
		this.jPanelLigne.add(jlLigne);
		Dimension preferredSize = new Dimension(1000, 20);
		JTextField jTextFieldNom = new JTextField();
		jTextFieldNom.setPreferredSize(preferredSize);
		this.jPanelLigne.add(jTextFieldNom, "growx");		// deja teste avec grow mais pas genial
		

		//jTextFieldNom.isEditable();					// Peut servir !

		// Création du panel boutons
		JPanel jPanelBoutons = new JPanel();
		JButton jbGenValeurs = new JButton("Ligne => Valeur");
		jPanelBoutons.add(jbGenValeurs);
		JButton jbGenLigne = new JButton("Valeur => Ligne");
		jPanelBoutons.add(jbGenLigne);

		// Création du panel entête
		this.jPanelEntete = new JPanel(new MigLayout("debug, fillx, wrap"));			// Panel contenant la ligne de donnees + les deux boutons de conversion
		this.jPanelEntete.add(this.jPanelLigne);
		this.jPanelEntete.add(jPanelBoutons);
		
		//JPanel jPanelChamp = new JPanel();
		
		// Création du panel champs
		this.jPanelChamps = new JPanel(new MigLayout("debug, fillx, wrap"));
		
		// Création du panel principal
		this.jPanel = new JPanel(new MigLayout("debug, fillx, wrap"));
		this.add(this.jPanel);
		this.jPanel.add(this.jPanelEntete, "growx");
		this.jPanel.add(this.jPanelChamps); 
		
 	}
	
	/**
	 * Methode qui permet d'ajouter un champ par 
	 * information (ligne) lue dans le fichier JSON
	 */
	public void ajouterUnChamp(String nom){
		
		JPanel jPanelChamp = new JPanel(new MigLayout("debug, fillx"));
		JLabel jlLigne = new JLabel(nom);	// Création du label qui contiendra le nom du champ
//		this.jPanelLigne.add(jlLigne);
//		this.jPanelEntete.add(this.jPanelLigne);
		jPanelChamp.add(jlLigne);
		JTextField jTextFieldNom = new JTextField();
		Dimension preferredSize = new Dimension(1000, 20);
		jTextFieldNom.setPreferredSize(preferredSize);
		//jTextFieldNom.isEditable();					// Peut servir !
		jPanelChamp.add(jTextFieldNom, "growx");		// deja teste avec grow mais pas genial
		this.jPanelChamps.add(jPanelChamp);
		
		//this.jPanel.
	}
}
