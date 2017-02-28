package packGestionDonnees;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import core.exceptions.GestionDonnesExceptions;
import packGestionDonnees.donnees.Parametres;

public abstract class  OperationsSurDonnees {
	

	public static List<Parametres> chargementParametres() {
		
		List<Parametres> parametres = new ArrayList<Parametres>();
		
		try {
			parametres.addAll(OperationsSurDonnees.lectureDonneesDepuisFichierJSON("C:\\WorkSpace\\GestionDonnees\\JSON_files\\data1.json"));
					
		} catch (GestionDonnesExceptions e) {
			System.out.println("Probl�me de lecture du fichier JSON.");
		}
		
		return parametres;
	}
	
	/**
	 * Cette m�thode permet de cr�� une liste de donn�es apr�s avoir lu un fichier
	 * au format JSON comportant diff�rents types de jeux.
	 * 
	 * @param cheminFichier
	 *            chemin du fichier au format JSON
	 * @return List<Donnees> retourne une liste de Donnees
	 * @throws GestionDonneesExceptions
	 */
	public static List<Parametres> lectureDonneesDepuisFichierJSON(String cheminFichier) throws GestionDonnesExceptions {

		JSONObject jsonObject = OutilsFichiers.lectureLigneJson(cheminFichier);
		JSONArray jsonArray = (JSONArray) jsonObject.get("data");

		return traitementDonnees(jsonObject, jsonArray);
	}

	private static List<Parametres> traitementDonnees(JSONObject jsonObject, JSONArray jsonArray) throws GestionDonnesExceptions {
		
		List<Parametres> parametres = new ArrayList<Parametres>();
		
		// On r�cup�re les donn�es de "data" afin de les cr�er
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				//System.out.println(jsonArray.get(i));
				jsonObject = (JSONObject) jsonArray.get(i);

				String nom = (String) jsonObject.get("nom");
				//System.out.println("Nom de la donn�e : " + nom);

				int position_debut = (int) (long) jsonObject.get("position_debut");
				//System.out.println("Position d�but = " + position_debut);

				int position_fin = (int) (long) jsonObject.get("position_fin");
				//System.out.println("position_fin = " + position_fin);
				
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
	 * cette m�thode permet de convertir les donnees brutes provenant de la premiere ligne et
	 *   d'alimenter les champs
	 */
	public static void convertLigneValeurs() {
		
	}

	/**
	 * cette m�thode permet de convertir les donnees presentes dans les champs afin de g�n�rer la
	 * premi�re ligne de donnees brutes
	 */
	public static void convertValeursLigne() {
		
	}
}