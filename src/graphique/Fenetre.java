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
	
	/**
	 * Constructeur
	 */
	public Fenetre() {
		super();

		this.setSize(width, height);
		this.setTitle(title);
		
		JPanel jPanel = new JPanel(new MigLayout("debug, fillx, wrap"));
		this.add(jPanel);
	
		JPanel jPanelEntete = new JPanel(new MigLayout("debug, fillx, wrap"));			// Panel contenant la ligne de donnees + les deux boutons de conversion

		JPanel jPanelLigne = new JPanel(new MigLayout("debug, fillx"));			// Panel qui va contenir la ligne de données
		JLabel jlLigne = new JLabel("Ligne : ");	// Création du label : "Ligne : "
		jPanelLigne.add(jlLigne);
		jPanelEntete.add(jPanelLigne);

		JTextField jTextFieldNom = new JTextField();
		Dimension preferredSize = new Dimension(100, 20);
		jTextFieldNom.setPreferredSize(preferredSize);
		jPanelLigne.add(jTextFieldNom, "grow");

		JPanel jPanelBoutons = new JPanel();

		JButton jbGenValeurs = new JButton("Ligne => Valeur");
		jPanelBoutons.add(jbGenValeurs);
		JButton jbGenLigne = new JButton("Valeur => Ligne");
		jPanelBoutons.add(jbGenLigne);
		jPanelEntete.add(jPanelBoutons);
		
		JPanel jPanelChamps = new JPanel();
		//JPanel jPanelChamp = new JPanel();
		
		jPanel.add(jPanelEntete, "growx");

		jPanel.add(jPanelChamps);
	}
}
