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

public class Launcher {

	/* pour test : 
	 * aaaabbbbccccdddd
	 * 9611publA49F
	 * 9611pub A49F
	 */
	public static void main(String[] args) {
//		lanceur1();
//		lanceur2();
		lanceur3();
//		System.exit(0);
	}

	private static void lanceur3() {
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static void lanceur2() {
		
		DonneesBrutes donneesBrutes = new DonneesBrutes();
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		List<Parametres> parametres = new ArrayList<Parametres>();
		try {
			parametres.addAll(OperationsSurDonnees.lectureDonneesDepuisFichierJSON("C:\\WorkSpace\\GestionDonnees\\JSON_files\\data1.json"));
					
		} catch (GestionDonnesExceptions e) {
			System.out.println("Problème de lecture du fichier JSON.");
		}
		
		System.out.println("Veuillez renseigner les différents champs :");
		String chaineDonneesBrutes = "";
		
		for(int i = 0 ; i < parametres.size() ; i++) {
			int nbCar = (parametres.get(i).getPosition_fin() - parametres.get(i).getPosition_debut()) + 1;
			System.out.print(parametres.get(i).getNom() + " (" + nbCar + " caractère(s) maximum) : " );
			
			String temp = sc.nextLine();
			chaineDonneesBrutes = chaineDonneesBrutes + temp;		
		}
		donneesBrutes.setDonneesBrutes(chaineDonneesBrutes);
		
		System.out.println(donneesBrutes.getDonneesBrutes());
	}

	private static void lanceur1() {
		
		DonneesBrutes donneesBrutes = new DonneesBrutes();
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir les données à traiter : ");
		donneesBrutes.setDonneesBrutes(sc.nextLine());
		System.out.println("Les données brutes contiennent la chaine suivante : " + donneesBrutes.getDonneesBrutes());
		
		List<Parametres> parametres = new ArrayList<Parametres>();
	
		try {
			parametres.addAll(OperationsSurDonnees.lectureDonneesDepuisFichierJSON("C:\\WorkSpace\\GestionDonnees\\JSON_files\\data1.json"));
					
		} catch (GestionDonnesExceptions e) {
			System.out.println("Problème de lecture du fichier JSON.");
		}
		
		Affichage.afficheDonnees(donneesBrutes, parametres);
	}
}
