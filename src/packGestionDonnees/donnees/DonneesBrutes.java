package packGestionDonnees.donnees;

/**
 * POJO DonneesBrutes permet de stocker ou d'acceder
 * à la valeur contenue dans le premier champ : "Ligne"
 */
public class DonneesBrutes {
	
	private String DonneesBrutes = "";
	
	public DonneesBrutes() {
		super();
	}

	public String getDonneesBrutes() {
		return DonneesBrutes;
	}

	public void setDonneesBrutes(String donneesBrutes) {
		DonneesBrutes = donneesBrutes;
	}
}
