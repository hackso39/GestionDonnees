package packGestionDonnees.donnees;

/**
 * @author
 * POJO permettant d'acc�der aux donn�es de param�trage <BR /> 
 * de d�coupage des donn�es. Ces param�tres sont stock�s <BR />
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
	 * Constructeur par d�faut
	 */
	public Parametres() {
		super();
	}

	//M�thodes d'acc�s aux diff�rents champs 

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
