package packGestionDonnees.donnees;

/**
 * @author
 * POJO permettant d'accéder aux données de paramétrage <BR /> 
 * de découpage des données. Ces paramètres sont stockés <BR />
 * dans un fichier JSON <BR />
 *		- nom <BR />
 *		- position_debut <BR />
 *		- position_fin
 *
 */
public class Parametres {
	
	private String nom = "";
	private int position_debut = 0;
	private int position_fin = 0;

	/**
	 * Constructeur par défaut
	 */
	public Parametres() {
		super();
	}

	//Méthodes d'accès aux différents champs 

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPosition_debut() {
		return position_debut;
	}
	public void setPosition_debut(int position_debut) {
		this.position_debut = position_debut;
	}
	public int getPosition_fin() {
		return position_fin;
	}
	public void setPosition_fin(int position_fin) {
		this.position_fin = position_fin;
	}
}
