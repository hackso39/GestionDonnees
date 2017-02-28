package packGestionDonnees;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * 		Cette classe permet de réaliser des opérations sur les 
 * 		fichiers comme par exemple la lecture.
 */
public class OutilsFichiers {
	/**
	 * Constructeur par défaut
	 */
	public OutilsFichiers() {
		super();
	}

	/**
	 * Fonctionnement JSON : when syntax is {}then this is JsonObject when
	 * syntax is [] then this is JsonArray
	 * 
	 * @param cheminFichier
	 * @return JSONObject contient toutes les informations présentes dans le
	 *         fichier JSON
	 */
	public static JSONObject lectureLigneJson(String cheminFichier) {

		JSONParser parser = new JSONParser();

		try {

			return (JSONObject)  parser.parse(new FileReader(cheminFichier));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new JSONObject();
	}
}
