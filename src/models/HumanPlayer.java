package models;

import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {

	public HumanPlayer(String nombre) {
		super(nombre);
	}

	@Override
	public void jugarTurno7yMedia() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean error;

		if (this.getMano().isVacia()) {
			this.mano.lista_cartas.add(this.mesa.robarCartaDeBaraja());
			this.setPuntos(this.getPuntos() + this.mano.lista_cartas.get(0).getValor7yMedia());
		} else {

			System.out
					.println("Turno de " + this.getNombre() + ". Puntos: " + this.getPuntos() + " ¿Qué vas a hacer?\n");
			do {
				System.out.println("1.Coger\n2.Pasar\n");
				System.out.print("->: ");
				try {
					switch (Integer.parseInt(sc.nextLine())) {
					case 1:
						error = false;
						this.mano.lista_cartas.add(this.mesa.robarCartaDeBaraja());
						this.setPuntos(getPuntos()
								+ this.mano.lista_cartas.get(this.mano.getCantidadCartas() - 1).getValor7yMedia());
						this.setPasar(false);
						this.setRondasJugadas(getRondasJugadas() + 1);
						break;

					case 2:
						error = false;
						this.setPasar(true);
						this.setRondasJugadas(getRondasJugadas() + 1);
						break;

					default:
						System.out.println("\n\n### Elige una opción correcta ###\n");
						error = true;
					}
				} catch (Exception e) {
					error = true;
					System.out.println("\n\n### Introduce un numero entero ###\n");
					System.out.println("Puntos: " + this.getPuntos() + "\n");
				}
			} while (error);
			System.out.println("\n");
		}
	}
}
