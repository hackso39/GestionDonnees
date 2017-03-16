package packGestionDonnees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;

import core.exceptions.GestionDonnesExceptions;
import graphique.Fenetre;
import packGestionDonnees.donnees.Parametres;
import packGestionDonnees.donnees.DonneesBrutes;

/**
 * Classe permettant de lancer l'application GestionDonnees. 
 */
public class Launcher {

	/**
	 * M�thode : main
	 * @param args
	 */
	public static void main(String[] args) {
//		lanceur1();	// Chargement du fic JSON avec affichage en console des �l�ments contenus dedans
//		lanceur2();	// Chargement du fic JSON, affichage en console am�lior� des �l�ments contenus dedans  
//		lanceur3(); // Affichage d'une fen�tre en vue de mettre en place l'IHM de l'application
//		lanceur4();	// Chargement du fic JSON + Affichage dans l'IHM des noms des rubriques (Ex de champs : UF, March�, Produit)
		lanceur5();	// Chargement fic JSON + IHM avec conversion Ligne r�partie dans les champs lorsque clic sur bouton
	}

	/**
	 * Lanceur 5
 	 * 
 	 * Principe :
	 * - lire les donnees contenues dans le fichier JSON, 	
	 * - creer la fen�tre IHM,
	 * - saisir les donn�es du champ : Ligne,
	 * - cliquer sur le bouton de conversion,
	 * - les champs sont aliment�s et affich�s dans l'IHM.
	 */
	private static void lanceur5() {

		List<Parametres> parametres = new ArrayList<Parametres>();
		parametres.addAll(OperationsSurDonnees.chargementParametres());
		
		Fenetre fen = new Fenetre(parametres);
		fen.setVisible(true);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Lanceur 4
	 * 
	 * Principe :
	 * - lire les donnees du fichier JSON, 	
	 * - creer la fen�tre
	 * - creer autant de ligne dans la fenetre qu'il y a de champs dans le fichier
	 */
	private static void lanceur4() {
		
		DonneesBrutes donneesBrutes = new DonneesBrutes();
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);	// r�f�rence permettant la saisie des donn�es dans les champs
		
		List<Parametres> parametres = new ArrayList<Parametres>();
		try {
			parametres.addAll(OperationsSurDonnees.lectureDonneesDepuisFichierJSON("C:\\WorkSpace\\GestionDonnees\\JSON_files\\data1.json"));
					
		} catch (GestionDonnesExceptions e) {
			System.out.println("Probl�me de lecture du fichier JSON.");
		}
		
		System.out.println("Veuillez renseigner les diff�rents champs :");
		String chaineDonneesBrutes = "";
		
		Fenetre fen = new Fenetre();

		// Parcours de tous les param�tres contenus dans le fichier JSON afin de calculer le nombre de caract�res par champ
		for(int i = 0 ; i < parametres.size() ; i++) {
			int nbCar = (parametres.get(i).getPosition_fin() - parametres.get(i).getPosition_debut()) + 1;
			System.out.print(parametres.get(i).getNom() + " (" + nbCar + " caract�re(s) maximum) : " );
			
			String temp = sc.nextLine();
			chaineDonneesBrutes = chaineDonneesBrutes + temp;
			
			// Creer un champ avec getNom present dans parametre
			//fen.ajouterUnChamp(parametres.get(i).getNom(), parametres.get(i).getPosition_debut(), parametres.get(i).getPosition_fin());
			
		}
		donneesBrutes.setDonneesBrutes(chaineDonneesBrutes);
		
		System.out.println(donneesBrutes.getDonneesBrutes());
		
		fen.setVisible(true);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Lanceur3 pour test de la fenetre mise en place
	 */
	private static void lanceur3() {
		
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Lanceur 2
	 */
	private static void lanceur2() {
		
		DonneesBrutes donneesBrutes = new DonneesBrutes();
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		List<Parametres> parametres = new ArrayList<Parametres>();
		try {
			parametres.addAll(OperationsSurDonnees.lectureDonneesDepuisFichierJSON("C:\\WorkSpace\\GestionDonnees\\JSON_files\\data1.json"));
					
		} catch (GestionDonnesExceptions e) {
			System.out.println("Probl�me de lecture du fichier JSON.");
		}
		
		System.out.println("Veuillez renseigner les diff�rents champs :");
		String chaineDonneesBrutes = "";
		
		for(int i = 0 ; i < parametres.size() ; i++) {
			int nbCar = (parametres.get(i).getPosition_fin() - parametres.get(i).getPosition_debut()) + 1;
			System.out.print(parametres.get(i).getNom() + " (" + nbCar + " caract�re(s) maximum) : " );
			
			String temp = sc.nextLine();
			chaineDonneesBrutes = chaineDonneesBrutes + temp;		
		}
		donneesBrutes.setDonneesBrutes(chaineDonneesBrutes);
		
		System.out.println(donneesBrutes.getDonneesBrutes());
	}

	/**
	 * Lanceur 1
	 */
	private static void lanceur1() {
		
		DonneesBrutes donneesBrutes = new DonneesBrutes();
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir les donn�es � traiter : ");
		donneesBrutes.setDonneesBrutes(sc.nextLine());
		System.out.println("Les donn�es brutes contiennent la chaine suivante : " + donneesBrutes.getDonneesBrutes());
		
		List<Parametres> parametres = new ArrayList<Parametres>();
	
		try {
			parametres.addAll(OperationsSurDonnees.lectureDonneesDepuisFichierJSON("C:\\WorkSpace\\GestionDonnees\\JSON_files\\data1.json"));
					
		} catch (GestionDonnesExceptions e) {
			System.out.println("Probl�me de lecture du fichier JSON.");
		}
		
		Affichage.afficheDonnees(donneesBrutes, parametres);
	}
}
