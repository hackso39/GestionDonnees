package graphique;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame{

	private static final long serialVersionUID = 1L;
	private int width = 400;
	private int height = 400;
	private String title = "Gestions de données";
	
	public Fenetre() {
		super();
		this.setSize(width, height);
		this.setTitle(title);
		JPanel jPanel = new JPanel();
		this.add(jPanel);
		JLabel jlLigne = new JLabel("Ligne : ");
		jPanel.add(jlLigne);
		JTextField jTextFieldNom = new JTextField();
		Dimension preferredSize = new Dimension(100, 20);
		jTextFieldNom.setPreferredSize(preferredSize);
		jPanel.add(jTextFieldNom);
		JButton jbGenLigne = new JButton("Valeur => Ligne");
		JButton jbGenValeurs = new JButton("Ligne => Valeur");
		jPanel.add(jbGenLigne);
		jPanel.add(jbGenValeurs);
		
	}
}
