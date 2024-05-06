package logica;

public class WrongStringToAnguloFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongStringToAnguloFormatException() {
        super("Formato incorrecto para convertir String a objeto Angulo");
    }

    public WrongStringToAnguloFormatException(String message) {
        super(message);
    }
	
}
