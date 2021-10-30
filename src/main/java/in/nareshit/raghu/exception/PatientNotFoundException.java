package in.nareshit.raghu.exception;

import java.lang.RuntimeException;
import java.lang.String;

public class PatientNotFoundException extends RuntimeException {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  public PatientNotFoundException() {
  }

  public PatientNotFoundException(String message) {
    super(message);
  }
}