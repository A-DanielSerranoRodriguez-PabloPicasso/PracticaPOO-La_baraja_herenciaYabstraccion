package models;

import java.util.ArrayList;
import java.util.Scanner;

public class SieteYMedia extends AbstractGame {
	private int fin = 0;

	// Const
	public SieteYMedia() {
		super();
	}

	@Override
	public void bienvenida() {
		int contador = 0;

		System.out.println("### Bienvenid@s al juego de las 7 y media ###");

		menuPrincipal();

		System.out.println("\nGenial, esta es la lista de jugadores");
		System.out.println("--------------------------------------\n");
		for (AbstractPlayer jugador : this.jugadores) {
			System.out.print("  " + jugador.getNombre() + "    \t");
			jugador.setMesa(this.getMesa());
			contador++;
			if (contador == 3) {
				System.out.println();
				contador = 0;
			}
		}

		barajar();
		System.out.println("\n\n\n\n### COMENCEMOS ###\n");
		start();
	}

	@Override
	public void menuPrincipal() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int cantJugadores = 0, tipoBaraja = 0;
		boolean error;

		System.out.println("\n¿Solo o en grupo?");
		do {
			System.out.println("\n1.Solo\n2.En grupo");
			System.out.print("\n->: ");
			switch (Integer.parseInt(sc.next())) {
			case 1:
				// TODO Asignar una IA (hay que crearla todavía xD)
				error = false;
				break;
			case 2:
				error = false;
				System.out.println("\n¿Cuántos vaís a ser?");
				System.out.print("->: ");
				cantJugadores = Integer.parseInt(sc.next());
				for (int i = 0; i < cantJugadores; i++) {
					System.out.print("\nNombre jugador " + (i + 1) + ": ");
					this.jugadores.add(new HumanPlayer(sc.next()));
				}
				break;
			default:
				System.out.println("\nElige una opción correcta\n");
				error = true;
			}
		} while (error);

		System.out.println("\n\n¿Con cuantas cartas se va a jugar?");
		do {
			System.out.println("\n1.40\n2.80");
			System.out.print("\n->: ");
			tipoBaraja = Integer.parseInt(sc.next());
			switch (tipoBaraja) {
			case 1:
			case 40:
				this.mesa = new Mesa(1);
				error = false;
				break;
			case 2:
			case 80:
				error = false;
				this.mesa = new Mesa(2);
				break;
			default:
				System.out.println("\nElige una opción correcta\n");
				error = true;
			}
		} while (error);
	}

	@Override
	public AbstractPlayer nextTurno() {
		for (AbstractPlayer jugador : this.jugadores) {
			if (jugador.getRondasJugadas() < this.getRonda()) {
				return jugador;
			}
		}
		return null;
	}

	@Override
	public void start() {
		AbstractPlayer jugador;
		do {
			int subirRonda = 0;
			for (AbstractPlayer player : this.jugadores) {
				if (player.getRondasJugadas() == this.getRonda()) {
					subirRonda++;
				}
			}
			if (subirRonda == this.jugadores.size()) {
				this.setRonda(this.getRonda() + 1);
			} else {
				subirRonda = 0;
			}
			jugador = nextTurno();
			jugador.jugarTurno7yMedia();
			if (jugador.isPasar()) {
				fin++;
			} else {
				if (fin != this.jugadores.size())
					fin = 0;
			}
		} while (fin < this.jugadores.size());
		finish();
	}

	@Override
	public void barajar() {
		this.mesa.getBaraja().barajar();
	}

	@Override
	public void finish() {
		ArrayList<AbstractPlayer> ganadores = new ArrayList<AbstractPlayer>();
		AbstractPlayer ganador = null;
		double winner = Double.MIN_VALUE;
		int contador = 0;

		for (AbstractPlayer jugador : this.jugadores) {
			System.out.println(jugador.getNombre() + ": " + jugador.getPuntos());
			if (jugador.getPuntos() > winner && jugador.getPuntos() <= 7.5) {
				winner = jugador.getPuntos();
				ganador = jugador;
			} else if (jugador.getPuntos() < winner && jugador.getPuntos() >= 7.5) {
				winner = jugador.getPuntos();
				ganador = jugador;
			}
		}
		for (AbstractPlayer jugador : this.jugadores) {
			if (jugador.getPuntos() == winner) {
				ganadores.add(jugador);
			}
		}
		if (ganadores.size() == 1) {
			System.out.println("El ganador es " + ganador.getNombre() + " con un " + ganador.getPuntos());
		} else {
			System.out.println("Los ganadores son:");
			for (AbstractPlayer jugador : ganadores) {
				System.out.print("  " + jugador.getNombre() + "    \t");
				contador++;
				if (contador == 3) {
					System.out.println();
					contador = 0;
				}
			}

			System.out.println("\nCon un: " + winner);
		}

	}

}
