package logica;

public class Angulo {

	private int grados;
	private int minutos;
	private int segundos;

	/**
	 * Crear un nuevo Angulo.
	 * Ejemplo: Si haces Angulo(360, 60, 60), pasara a ser Angulo(1, 1, 0).
	 * - Si pones 360 grados, este pasara a ser 0 (ha dado una vuelta).
	 * - Si pones 60 minutos, este pasara a ser 0 y se sumara +1 grados.
	 * - Si pones 60 segundos, este pasara a ser 0 y se sumara +1 minutos.
	 * - Lo mismo pasara con valores negativos, pero a la inversa.
	 * @param grados Grados del Angulo. Valores de 0 hasta 359.
	 * @param minutos Minutos del Angulo. Valores de 0 hasta 59.
	 * @param segundos Segundos del Angulo. Valores de 0 hasta 59.
	 */
	public Angulo(int grados, int minutos, int segundos) {
		// pongo los setters en el constructor para hacer la conversion solo en un sitio
		setGrados(grados);
        setMinutos(minutos);
        setSegundos(segundos);
	}
	
	/**
     * Metodo estatico para pasar un String con formato de Angulo, a un objeto Angulo
     * @param txtValue String con formato de Angulo. Ejemplo: (grados):(minutos):(segundos), (minutos):(segundos) o (segundos).
     * @return Angulo Objeto de Angulo.
	 * @throws WrongStringToAnguloFormatException Si el formato de String es incorrecto.
     */
	public static Angulo parseStringToAngulo(String txtValue) throws WrongStringToAnguloFormatException {
		// separar los value1 y value2
		String arrValue[] = txtValue.split(":");
		
		// pasar los string numericos a int
		int arrValueInt[] = new int[arrValue.length];
		for (int i = 0; i < arrValue.length; i++) {
			arrValueInt[i] = Integer.parseInt(arrValue[i]);
		}
		
		// hacer los objetos Angulo dependiendo si hay G:M:S, M:S o S
		Angulo angulo = null;
		
		switch (arrValueInt.length) {
		case 1:
			angulo = new Angulo(0, 0, arrValueInt[0]);
			break;
		case 2:
			angulo = new Angulo(0, arrValueInt[0], arrValueInt[1]);
			break;
		case 3:
			angulo = new Angulo(arrValueInt[0], arrValueInt[1], arrValueInt[2]);
			break;
		default:
			throw new WrongStringToAnguloFormatException();
		}
		//System.out.println(angulo1);
		
		// devolver el Angulo
		return angulo;
	}
	
	/**
	 * Metodo estatico para sumar 2 objetos de Angulo.
	 * @param angulo1 Primer Angulo a sumar.
	 * @param angulo2 Segundo Angulo a sumar.
	 * @return Angulo El Angulo sumado.
	 */
	public static Angulo sumar(Angulo angulo1, Angulo angulo2) {
		// hacer las sumas por separado
		int grados = angulo1.getGrados() + angulo2.getGrados();
        int minutos = angulo1.getMinutos() + angulo2.getMinutos();
        int segundos = angulo1.getSegundos() + angulo2.getSegundos();

        // controlar los segundos de mas
        if (segundos >= 60) {
            segundos -= 60;
            minutos++;
        }

        // controlar los minutos de mas
        if (minutos >= 60) {
            minutos -= 60;
            grados++;
        }

        // controlar los grados de mas
        grados = grados % 360;
        if (grados < 0) {
            grados += 360;
        }
        
        // devuelve un nuevo angulo sumado
        return new Angulo(grados, minutos, segundos);
	}
	
	/**
	 * Metodo estatico para restar 2 objetos de Angulo.
	 * @param angulo1 Primer Angulo a restar.
	 * @param angulo2 Segundo Angulo a restar.
	 * @return Angulo El Angulo restado.
	 */
	public static Angulo restar(Angulo angulo1, Angulo angulo2) {
		// realizar las restas por separado
	    int grados = angulo1.getGrados() - angulo2.getGrados();
	    int minutos = angulo1.getMinutos() - angulo2.getMinutos();
	    int segundos = angulo1.getSegundos() - angulo2.getSegundos();

	    // controlar los segundos negativos
	    if (segundos < 0) {
	        segundos += 60;
	        minutos--;
	    }

	    // controlar los minutos negativos
	    if (minutos < 0) {
	        minutos += 60;
	        grados--;
	    }

	    // controlar los grados negativos
	    grados = (grados % 360 + 360) % 360;

	    // devuelve un nuevo angulo restado
	    return new Angulo(grados, minutos, segundos);
	}

	public int getGrados() {
		return grados;
	}

	public void setGrados(int grados) {
		grados = grados % 360;
        if (grados < 0) {
            grados += 360;
        }
        this.grados = grados;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		int vueltas = minutos / 60;
        minutos = minutos % 60;
        if (minutos < 0) {
            minutos += 60;
            vueltas--;
        }
        setGrados(grados + vueltas);
        this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		int vueltas = segundos / 60;
        segundos = segundos % 60;
        if (segundos < 0) {
            segundos += 60;
            vueltas--;
        }
        setMinutos(minutos + vueltas);
        this.segundos = segundos;
	}
	
	@Override
	public String toString() {
		return String.format("%03d:%02d:%02d", grados, minutos, segundos);
	}
	
}
