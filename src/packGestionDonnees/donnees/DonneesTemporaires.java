package packGestionDonnees.donnees;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author
 * 
 * POJO stockant les donnees temporaires pendant
 * l'utilisation de l'application.
 */
public class DonneesTemporaires {

	private List<String> dt = new ArrayList<String>();
	
	public DonneesTemporaires() {
		super();
	}

	public List<String> getDt() {
		return dt;
	}

	public void setDt(List<String> dt) {
		this.dt = dt;
	}

	
}
