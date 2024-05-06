package logica;

import javax.swing.JOptionPane;

public class MainTest {

	/*
	 * Esta clase MainTest solo es para hacer pruebas logicas, no es el programa final.
	 */
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("--- ejemplo del javadoc ---");
			Angulo ejemplo = new Angulo(360, 60, 60);
			System.out.println(ejemplo);
			
			System.out.println("\n--- sumar() ---");
			
			Angulo angulo1 = new Angulo(300, 240, -1);
			System.out.println(angulo1);
			
			Angulo angulo2 = new Angulo(30, 1, 1);
			System.out.println(angulo2);
			
			System.out.println("resultado:");
			Angulo anguloSumado = Angulo.sumar(angulo1, angulo2);
			System.out.println(anguloSumado);
			
			System.out.println("\n--- restar() ---");
			
			Angulo angulo3 = new Angulo(250, 30, 30);
			System.out.println(angulo3);
			
			Angulo angulo4 = new Angulo(18, 10, 23);
			System.out.println(angulo4);
			
			System.out.println("resultado:");
			Angulo anguloRestado = Angulo.restar(angulo3, angulo4);
			System.out.println(anguloRestado);
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("El programa ha muerto");
			e.printStackTrace();
		}

	}

}
