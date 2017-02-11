package packGestionDonnees;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import core.exceptions.GestionDonnesExceptions;
import packGestionDonnees.donnees.Parametres;

public abstract class  OperationsSurDonnees {
	
	/**
	 * Cette méthode permet de créé une liste de données après avoir lu un fichier
	 * au format JSON comportant différents types de jeux.
	 * 
	 * @param cheminFichier
	 *            chemin du fichier au format JSON
	 * @return List<Donnees> retourne une liste de Donnees
	 * @throws GestionDonneesExceptions
	 */
	public static List<Parametres> lectureDonneesDepuisFichierJSON(String cheminFichier) throws GestionDonnesExceptions {

		JSONObject jsonObject = OutilFichiers.lectureLigneJson(cheminFichier);
		JSONArray jsonArray = (JSONArray) jsonObject.get("data");

		return traitementDonnees(jsonObject, jsonArray);
	}

	private static List<Parametres> traitementDonnees(JSONObject jsonObject, JSONArray jsonArray) throws GestionDonnesExceptions {
		
		List<Parametres> parametres = new ArrayList<Parametres>();
		
		// On récupère les données de "data" afin de les créer
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.size(); i++) {
				System.out.println(jsonArray.get(i));
				jsonObject = (JSONObject) jsonArray.get(i);

				String nom = (String) jsonObject.get("nom");
				System.out.println("Nom de la donnée : " + nom);

				//String position_debut = (String) jsonObject.get("position_debut");
				int position_debut = (int) (long) jsonObject.get("position_debut");
				System.out.println("Position début = " + position_debut);
				
				//String position_fin = (String) jsonObject.get("position_fin");
				int position_fin = (int) (long) jsonObject.get("position_fin");
				System.out.println("position_fin = " + position_fin);
				
				
				Parametres donneesParam = new Parametres();
				donneesParam.setNom(nom);
				donneesParam.setPosition_debut(position_debut);
				donneesParam.setPosition_fin(position_fin);
				
				parametres.add(donneesParam);
			}
		}
		
		return parametres;
	}
}