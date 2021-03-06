package core.exceptions;

/**
 * Classe contenant les exceptions pour l'application.
 * 
 * Elle contient les constructeurs de la classe Exception
 */
public class GestionDonnesExceptions extends Exception {

	private static final long serialVersionUID = 1L;

	public GestionDonnesExceptions() {
		super();
	}

	public GestionDonnesExceptions(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GestionDonnesExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public GestionDonnesExceptions(String message) {
		super(message);
	}

	public GestionDonnesExceptions(Throwable cause) {
		super(cause);
	}
}
