package packGestionDonnees.donnees;

/**
 * @author
 * 
 * POJO contenant la liste de toutes les informations
 * concernant les données présentes dans la partie Champs
 * de l'IHM. <BR /> 
 * <BR />
 * Permet de mettre à jour les champs dans l'IHM pendant les conversions.
 */
public class ListeChamps {
	
	private String DonneesBrute = "";
	private String nomChamp = "";
	private int position_debut = 0;
	private int position_fin = 0;
	private int nbrCaractParChamp = 0;
	private String donnee = "";
	
	/**
	 * Constructeur
	 */
	public ListeChamps() {
		super();
	}

	public String getDonneeBrute() {
		return DonneesBrute;
	}

	public void setDonneeBrute(String donneesBrute) {
		DonneesBrute = donneesBrute;
	}
	
	public String getNomChamp() {
		return nomChamp;
	}

	public void setNomChamp(String nomChamp) {
		this.nomChamp = nomChamp;
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

	public int getNbrCaractParChamp() {
		return nbrCaractParChamp;
	}

	public void setNbrCaractParChamp(int nbrCaractParChamp) {
		this.nbrCaractParChamp = nbrCaractParChamp;
	}

	public String getDonnee() {
		return donnee;
	}

	public void setDonnee(String donnee) {
		this.donnee = donnee;
	}
	
}
