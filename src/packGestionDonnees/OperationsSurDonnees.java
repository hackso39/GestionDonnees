package packGestionDonnees;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import core.exceptions.GestionDonnesExceptions;
import packGestionDonnees.donnees.DonneesBrutes;
import packGestionDonnees.donnees.Parametres;

/**
 * Cette classe permet de r�aliser des op�rations sur les fichiers
 * JSON, le parcours de noeuds, la lecture des donn�es. Le d�coupage
 * et l'assemblage des donn�es brutes ou pour les champs est �galement
 * r�alis� dans cette classe. 
 */

public abstract class OperationsSurDonnees {

	public static List<Parametres> chargementParametres() {

		List<Parametres> parametres = new ArrayList<Parametres>();

		try {
			parametres.addAll(OperationsSurDonnees
					.lectureDonneesDepuisFichierJSON("C:\\WorkSpace\\GestionDonnees\\JSON_files\\data1.json"));

		} catch (GestionDonnesExceptions e) {
			System.out.println("Probl�me de lecture du fichier JSON.");
		}

		return parametres;
	}

	/**
	 * Cette m�thode permet de cr�er une liste de donn�es apr�s avoir lu un
	 * fichier au format JSON comportant diff�rents types de jeux.
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
	 * Cette m�thode permet de parcourir un fichier JSON contenant les param�tres afin 
	 * de cr�er les champs dans l'application. Elle retourne la liste de ces param�tres.
	 * 
	 * @param jsonObject noeuds objets contenus dans le fichier JSON
	 * @param jsonArray noeuds tableaux contenus dans le fichier JSON
	 * @return List<Parametres> contient les param�tres concernant les champs affich�s dans l'application.
	 * @throws GestionDonnesExceptions 
	 */
	private static List<Parametres> traitementDonnees(JSONObject jsonObject, JSONArray jsonArray)
			throws GestionDonnesExceptions {

		List<Parametres> parametres = new ArrayList<Parametres>();

		// On r�cup�re les donn�es de "data" afin de les cr�er
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				// System.out.println(jsonArray.get(i));
				jsonObject = (JSONObject) jsonArray.get(i);

				String nom = (String) jsonObject.get("nom");
				// System.out.println("Nom de la donn�e : " + nom);

				int position_debut = (int) (long) jsonObject.get("position_debut");
				// System.out.println("Position d�but = " + position_debut);

				int position_fin = (int) (long) jsonObject.get("position_fin");
				// System.out.println("position_fin = " + position_fin);

				Parametres donneesParam = new Parametres();
				donneesParam.setNom(nom);
				donneesParam.setPosition_debut(position_debut);
				donneesParam.setPosition_fin(position_fin);

				// Calcul du nombre de caract�res par champ
				donneesParam.setNbrCaractParChamp((position_fin - position_debut) + 1);

				parametres.add(donneesParam);
			}
		}

		return parametres;
	}

	/**
	 * D�coupe la premi�re ligne (donn�es brutes) en valeurs en suivant le mod�le repr�sent� par les
	 * param�tres (champs).
	 * 
	 * @param dB
	 * @param params
	 * @return List<String> contient la liste des valeurs � afficher apr�s le nom du champ concern�.
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
	 * Cette m�thode permet de r�unir tous les champs dans une seule String qui sera
	 * ensuite affich�e dans la premi�re ligne de l'application.
	 * 
	 * @param params contient la liste des param�tres contenus dans le fichier JSON
	 * @param champs contient la liste de tous les champs (format String)
	 * @return String contient la chaine de tous les champs concat�n�s
	 */
	public static String assemblerChamps(List<Parametres> params, List<String> champs) {

		String resultat = "";
		
		int tailleChamp = 0;
		String espace = " ";
		
		// Pour chaque champ : concat�ner la valeur dans r�sultat.
		for(int i = 0 ; i < params.size() ; i++) {
			
			// R�cup�rer la valeur courante avec espace
			String champAvecEspace = champs.get(i);
			tailleChamp = (params.get(i).getPosition_fin() - params.get(i).getPosition_debut()) + 1;
			while(champAvecEspace.length() <  tailleChamp) {
				champAvecEspace += espace;
			}
			
			// Concat�nation
			resultat = resultat + champAvecEspace;
		}
		return resultat;
	}
}