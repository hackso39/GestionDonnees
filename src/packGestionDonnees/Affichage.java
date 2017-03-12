package packGestionDonnees;

import java.util.List;

import packGestionDonnees.donnees.DonneesBrutes;
import packGestionDonnees.donnees.Parametres;

public class Affichage {

	public Affichage() {
		super();
	}

	/**
	 * M�thode qui prend en param�tres les donn�es brutes � d�couper ainsi que
	 * le param�trage du d�coupage de ces donn�es.
	 * 
	 * @param donneesBrutes
	 * @param parametres
	 */
	public static void afficheDonnees(DonneesBrutes donneesBrutes, List<Parametres> parametres) {

		System.out.println("\n=========== R�sultats ===========");

		/*
		 * On parcourt toute la liste des param�tres (boucle) - on prend le
		 * segment de donn�es indiqu� par position debut et fin, - on affiche le
		 * nom : donnes ATTENTION : le fichier de donn�es doit �tre juste, sinon
		 * d�calages !!!
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