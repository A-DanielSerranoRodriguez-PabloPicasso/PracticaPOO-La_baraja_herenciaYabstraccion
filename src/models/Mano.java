package models;

import java.util.ListIterator;
import java.util.Scanner;

public class Mano extends Baraja {
	public Mano() {
		super();
	}

	/**
	 * Lista las cartas de la mano actual.
	 */
	public void listarCartas() {
		int contador = 0;
		for (Carta cartas : this.lista_cartas) {
			System.out.print(cartas.getNombreCarta() + " (Numero: " + cartas.getNumero() + " | Palo: "
					+ cartas.getPalo() + ")\t");
			contador++;
			if (contador == 3)
				System.out.println();
		}
		System.out.println();
	}

	/**
	 * Lista las cartas de la mano actual y te permite elegir la carta que quieres
	 * eliminar de esta. Útil para intercambio de cartas o para devolverla a la
	 * baraja.
	 * 
	 * @return Objeto 'Carta' que eliminamos de la mano.
	 */
	public Carta elegirCarta() {
		Carta carta = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		listarCartas();

		System.out.println("¿Que carta quieres? ");
		System.out.print("Numero: ");
		int numero = Integer.parseInt(sc.next());
		System.out.print("Palo: ");
		int palo = Integer.parseInt(sc.next());

		try {
			carta = new Carta(numero, palo);
			ListIterator<Carta> cartas = this.lista_cartas.listIterator();
			while (cartas.hasNext()) {
				if (cartas.next().getId() == carta.getId()) {
					cartas.remove();
				}
			}
			return carta;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return carta;
	}

}
