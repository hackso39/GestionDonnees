package packGestionDonnees;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import core.exceptions.GestionDonnesExceptions;
import packGestionDonnees.donnees.DonneesBrutes;
import packGestionDonnees.donnees.Parametres;

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
	 * Cette m�thode permet de cr�� une liste de donn�es apr�s avoir lu un
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
	 * D�coupe une ligne en valeurs en suivant le mod�le repr�sent� par les
	 * param�tres.
	 * 
	 * @param dB
	 * @param params
	 * @return
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
	
	public static String assemblerChamps(List<Parametres> params, List<String> champs) {

		String resultat = "";
		
		int tailleChamp = 0;
		String espace = " ";
		
		// Pour chaque champ : concat�ner la valeur dans r�sultat.
		for(int i = 0 ; i < params.size() ; i++) {
			
			// R�cup�rer de la valeur courante avec espace
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