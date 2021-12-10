package mainApp;

import java.util.Scanner;

import models.SieteYMedia;

public class MainApp {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean error = true;

		do {
			System.out.println("Bienvenid@ a la biblioteca de juego de cartas. ¿A qué quieres jugar?");
			System.out.println("\n1.7 y media\n2.WIP\n3.WIP\n\n4.Salir");
			System.out.print("\n->: ");
			switch (Integer.parseInt(sc.next())) {
			case 1:
				System.out.println("\n\n");
				SieteYMedia s7m = new SieteYMedia();
				s7m.bienvenida();
				break;

			case 2:
//				WIP
				break;

			case 3:
//				WIP
				break;

			case 4:
				error = false;
				break;

			default:
				System.out.println("\nElige una opcion correcta\n");
			}
		} while (error);

	}

}
