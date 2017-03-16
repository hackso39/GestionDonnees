package packGestionDonnees;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import core.exceptions.GestionDonnesExceptions;
import packGestionDonnees.donnees.DonneesBrutes;
import packGestionDonnees.donnees.Parametres;

/**
 * Cette classe permet de réaliser des opérations sur les fichiers
 * JSON, le parcours de noeuds, la lecture des données. Le découpage
 * et l'assemblage des données brutes ou pour les champs est également
 * réalisé dans cette classe. 
 */

public abstract class OperationsSurDonnees {

	public static List<Parametres> chargementParametres() {

		List<Parametres> parametres = new ArrayList<Parametres>();

		try {
			parametres.addAll(OperationsSurDonnees
					.lectureDonneesDepuisFichierJSON("C:\\WorkSpace\\GestionDonnees\\JSON_files\\data1.json"));

		} catch (GestionDonnesExceptions e) {
			System.out.println("Problème de lecture du fichier JSON.");
		}

		return parametres;
	}

	/**
	 * Cette méthode permet de créer une liste de données après avoir lu un
	 * fichier au format JSON comportant différents types de jeux.
	 * 
	 * @param cheminFichier
	 *            chemin du fichier au format JSON
	 * @return List<Donnees> retourne une liste de Donnees
	 * @throws GestionDonneesExceptions
	 */
	public static List<Parametres> lectureDonneesDepuisFichierJSON(String cheminFichier)
			throws GestionDonnesExceptions {

		JSONObject jsonObject = OutilsFichiers.lectureLigneJson(cheminFichier);
		JSONArray jsonArray = (JSONArray) jsonObject.get("data");

		return traitementDonnees(jsonObject, jsonArray);
	}

	/**
	 * Cette méthode permet de parcourir un fichier JSON contenant les paramètres afin 
	 * de créer les champs dans l'application. Elle retourne la liste de ces paramètres.
	 * 
	 * @param jsonObject noeuds objets contenus dans le fichier JSON
	 * @param jsonArray noeuds tableaux contenus dans le fichier JSON
	 * @return List<Parametres> contient les paramètres concernant les champs affichés dans l'application.
	 * @throws GestionDonnesExceptions 
	 */
	private static List<Parametres> traitementDonnees(JSONObject jsonObject, JSONArray jsonArray)
			throws GestionDonnesExceptions {

		List<Parametres> parametres = new ArrayList<Parametres>();

		// On récupère les données de "data" afin de les créer
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				// System.out.println(jsonArray.get(i));
				jsonObject = (JSONObject) jsonArray.get(i);

				String nom = (String) jsonObject.get("nom");
				// System.out.println("Nom de la donnée : " + nom);

				int position_debut = (int) (long) jsonObject.get("position_debut");
				// System.out.println("Position début = " + position_debut);

				int position_fin = (int) (long) jsonObject.get("position_fin");
				// System.out.println("position_fin = " + position_fin);

				Parametres donneesParam = new Parametres();
				donneesParam.setNom(nom);
				donneesParam.setPosition_debut(position_debut);
				donneesParam.setPosition_fin(position_fin);

				// Calcul du nombre de caractères par champ
				donneesParam.setNbrCaractParChamp((position_fin - position_debut) + 1);

				parametres.add(donneesParam);
			}
		}

		return parametres;
	}

	/**
	 * Découpe la première ligne (données brutes) en valeurs en suivant le modèle représenté par les
	 * paramètres (champs).
	 * 
	 * @param dB
	 * @param params
	 * @return List<String> contient la liste des valeurs à afficher après le nom du champ concerné.
	 */
	public static List<String> decouper(DonneesBrutes dB, List<Parametres> params) {

		List<String> listeDecoupee = new ArrayList<String>();

		String ligne = dB.getDonneesBrutes();

		for (Parametres parametre : params) {
			
			String resultat;
			
			if (parametre.getPosition_debut() > ligne.length()) {
				resultat = "";
			} else {
				
				int positionFinAUtiliser = parametre.getPosition_fin();
				if (positionFinAUtiliser + 1 > ligne.length()) {
					positionFinAUtiliser = ligne.length() - 1;
				}

				resultat = ligne.substring(parametre.getPosition_debut(), positionFinAUtiliser + 1);
			}
			
			listeDecoupee.add(resultat);
		}
		return listeDecoupee;
	}
	
	/**
	 * Cette méthode permet de réunir tous les champs dans une seule String qui sera
	 * ensuite affichée dans la première ligne de l'application.
	 * 
	 * @param params contient la liste des paramètres contenus dans le fichier JSON
	 * @param champs contient la liste de tous les champs (format String)
	 * @return String contient la chaine de tous les champs concaténés
	 */
	public static String assemblerChamps(List<Parametres> params, List<String> champs) {

		String resultat = "";
		
		int tailleChamp = 0;
		String espace = " ";
		
		// Pour chaque champ : concaténer la valeur dans résultat.
		for(int i = 0 ; i < params.size() ; i++) {
			
			// Récupérer la valeur courante avec espace
			String champAvecEspace = champs.get(i);
			tailleChamp = (params.get(i).getPosition_fin() - params.get(i).getPosition_debut()) + 1;
			while(champAvecEspace.length() <  tailleChamp) {
				champAvecEspace += espace;
			}
			
			// Concaténation
			resultat = resultat + champAvecEspace;
		}
		return resultat;
	}
}