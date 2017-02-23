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

	private JPanel jPanel = new JPanel(new MigLayout("debug, fillx, wrap"));
	private JPanel jPanelChamps = new JPanel();

	private JPanel jPanelEntete = new JPanel(new MigLayout("debug, fillx, wrap"));			// Panel contenant la ligne de donnees + les deux boutons de conversion
	private JPanel jPanelLigne = new JPanel(new MigLayout("debug, fillx"));			// Panel qui va contenir le label + la ligne de données
	
	/**
	 * Constructeur
	 */
	public Fenetre() {
		super();

		this.setSize(width, height);
		this.setTitle(title);
		
		this.add(jPanel);
	
		JLabel jlLigne = new JLabel("Ligne : ");	// Création du label : "Ligne : "
		jPanelLigne.add(jlLigne);
		jPanelEntete.add(jPanelLigne);

		JTextField jTextFieldNom = new JTextField();
		Dimension preferredSize = new Dimension(1000, 20);
		jTextFieldNom.setPreferredSize(preferredSize);
		//jTextFieldNom.isEditable();					// Peut servir !
		jPanelLigne.add(jTextFieldNom, "growx");		// deja teste avec grow mais pas genial

		JPanel jPanelBoutons = new JPanel();

		JButton jbGenValeurs = new JButton("Ligne => Valeur");
		jPanelBoutons.add(jbGenValeurs);
		JButton jbGenLigne = new JButton("Valeur => Ligne");
		jPanelBoutons.add(jbGenLigne);
		jPanelEntete.add(jPanelBoutons);
		
		//JPanel jPanelChamp = new JPanel();
		
		jPanel.add(jPanelEntete, "growx");

		jPanel.add(jPanelChamps);
		
 	}
	
	/**
	 * Methode qui permet d'ajouter un champ par 
	 * information (ligne) lue dans le fichier JSON
	 */
	public void ajouterUnChamp(String nom){
		
		JLabel jlLigne = new JLabel("Ligne : ");	// Création du label : "Ligne : "
		jPanelLigne.add(jlLigne);
		jPanelEntete.add(jPanelLigne);

		JTextField jTextFieldNom = new JTextField();
		Dimension preferredSize = new Dimension(1000, 20);
		jTextFieldNom.setPreferredSize(preferredSize);
		//jTextFieldNom.isEditable();					// Peut servir !
		jPanelLigne.add(jTextFieldNom, "growx");		// deja teste avec grow mais pas genial
		
		//this.jPanel.
	}
}
