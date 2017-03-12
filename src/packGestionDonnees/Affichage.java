package packGestionDonnees;

import java.util.List;

import packGestionDonnees.donnees.DonneesBrutes;
import packGestionDonnees.donnees.Parametres;

public class Affichage {

	public Affichage() {
		super();
	}

	/**
	 * Méthode qui prend en paramètres les données brutes à découper ainsi que
	 * le paramètrage du découpage de ces données.
	 * 
	 * @param donneesBrutes
	 * @param parametres
	 */
	public static void afficheDonnees(DonneesBrutes donneesBrutes, List<Parametres> parametres) {

		System.out.println("\n=========== Résultats ===========");

		/*
		 * On parcourt toute la liste des paramètres (boucle) - on prend le
		 * segment de données indiqué par position debut et fin, - on affiche le
		 * nom : donnes ATTENTION : le fichier de données doit être juste, sinon
		 * décalages !!!
		 */
		// Parametres param;

		List<String> listeDecoupee = OperationsSurDonnees.decouper(donneesBrutes, parametres);
		for (int i = 0; i < listeDecoupee.size(); i++) {
			System.out.println(parametres.get(i).getNom() + " : " + listeDecoupee.get(i));
		}

		// for(int i = 0 ; i < parametres.size() ; i++) {
		// param = parametres.get(i);
		//
		// String donnees =
		// donneesBrutes.getDonneesBrutes().substring(param.getPosition_debut(),
		// param.getPosition_fin() + 1);
		//
		// System.out.println(param.getNom() + " : " + donnees);
		// }
	}
}