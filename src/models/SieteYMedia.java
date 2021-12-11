package models;

import java.util.ArrayList;
import java.util.Scanner;

public class SieteYMedia extends AbstractGame {
	private int fin = 0;
	modoJuego modo;

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

		System.out.println("\n\n\n\n### COMENCEMOS LA PARTIDA " + modo +" ###\n");
		start();
	}

	@Override
	public void menuPrincipal() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		this.jugadores = new ArrayList<AbstractPlayer>();
		boolean error = true;

		System.out.println("\n¿Solo o en grupo?");
		do {
			System.out.println("\n1.Solo\n2.En grupo");
			System.out.print("\n->: ");
			try {
				switch (Integer.parseInt(sc.nextLine())) {
				case 1:
					modo = modoJuego.vsCPU;
					error = false;

					System.out.print("\nNombre del jugador: ");
					this.jugadores.add(new HumanPlayer(sc.nextLine()));
					this.jugadores.add(new CPU_Player());
					break;

				case 2:
					modo = modoJuego.PvP;
					error = false;
					int cantJugadores = -1;
					// cantJugadores es -1 ya que this.jugadores.size() es 0.
					// Solo sirve para comparar luego la cantidad de jugadores que hemos
					// introducido.

					do {
						try {
							System.out.println("\n¿Cuántos vaís a ser?");
							System.out.print("->: ");
							cantJugadores = Integer.parseInt(sc.nextLine());
							for (int i = 0; i < cantJugadores; i++) {
								System.out.print("\nNombre del jugador " + (i + 1) + ": ");
								this.jugadores.add(new HumanPlayer(sc.nextLine()));
							}
						} catch (Exception e) {
							System.out.println("\n\nIntroduce un numero entero");
						}
					} while (this.jugadores.size() != cantJugadores);
					break;

				default:
					System.out.println("\n\nElige una opción correcta");
				}
			} catch (Exception e) {
				System.out.println("\n\nIntroduce un numero entero");
				// Error = no se han introducido un entero.
			}
		} while (error);

		System.out.println("\n\n¿Con cuantas cartas se va a jugar?");
		do {
			System.out.println("\n1.40\n2.80");
			System.out.print("\n->: ");
			try {
				switch (Integer.parseInt(sc.nextLine())) {
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
					System.out.println("\n\nElige una opción correcta");
					error = true;
				}
			} catch (Exception e) {
				error = true;
				System.out.println("\n\nIntroduce un numero entero");
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

		barajar();
		for (AbstractPlayer player : jugadores) {
			player.jugarTurno7yMedia();
		}
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
				this.fin++;
			} else {
				if (fin != this.jugadores.size())
					this.fin = 0;
			}
			if (this.fin == this.jugadores.size()) {
				this.setFinished(true);
			}
		} while (this.fin < this.jugadores.size());
		if (this.isFinished())
			finish();
	}

	@Override
	public void barajar() {
		this.mesa.getBaraja().barajar();
	}

	@Override
	public void finish() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		ArrayList<AbstractPlayer> ganadores = new ArrayList<AbstractPlayer>();
		double winner = Double.MIN_VALUE;
		int contador = 0, verPuntosReversa = 0;
		boolean error;

		this.setRonda(0);
		this.fin = 0;

		for (AbstractPlayer jugador : this.jugadores) {
			System.out.println(jugador.getNombre() + ": " + jugador.getPuntos());
			if (jugador.getPuntos() > winner && jugador.getPuntos() <= 7.5) {
				winner = jugador.getPuntos();
			} else if (jugador.getPuntos() >= 7.5) {
				verPuntosReversa++;
			}
		}

		if (verPuntosReversa == this.jugadores.size()) {
			winner = Double.MAX_VALUE;
			for (AbstractPlayer jugador : this.jugadores) {
				if (jugador.getPuntos() < winner) {
					winner = jugador.getPuntos();
				}
			}
		}

		for (AbstractPlayer jugador : this.jugadores) {
			if (jugador.getPuntos() == winner) {
				ganadores.add(jugador);
			}
		}

		if (ganadores.size() == 1) {
			System.out.println(
					"\nEl ganador es " + ganadores.get(0).getNombre() + " con un " + ganadores.get(0).getPuntos());
		} else {
			System.out.println("\nLos ganadores son:");
			for (AbstractPlayer jugador : ganadores) {
				System.out.print("  " + jugador.getNombre() + "    \t");
				contador++;
				if (contador == 3) {
					System.out.println();
					contador = 0;
				}
			}

			System.out.println("\n\nCon un: " + winner);
		}
		ganadores.clear();
		winner = 0;

		System.out.println("\n\n\n¿Y ahora?");
		do {
			System.out.println("\n1.Reiniciar\n2.Nueva partida\n3.Salir");
			System.out.print("\n->: ");
			try {
				switch (Integer.parseInt(sc.nextLine())) {
				case 1:
					error = false;
					ArrayList<AbstractPlayer> copia = new ArrayList<AbstractPlayer>();
					System.out.println("\n\n\n");
					for (AbstractPlayer nuevo : this.jugadores) {
						if (nuevo instanceof HumanPlayer) {
							copia.add(new HumanPlayer(nuevo.getNombre()));
						} else if (nuevo instanceof CPU_Player) {
							copia.add(new CPU_Player());
						}
						for (int i = 0; i < nuevo.mano.getCantidadCartas(); i++) {
							this.mesa.addCartaABaraja(nuevo.mano.robar());
						}
					}
					this.jugadores = new ArrayList<AbstractPlayer>();
					for (AbstractPlayer nuevo : copia) {
						if (nuevo instanceof HumanPlayer) {
							this.jugadores.add(new HumanPlayer(nuevo.getNombre()));
						} else if (nuevo instanceof CPU_Player) {
							this.jugadores.add(new CPU_Player());
						}
					}
					for (AbstractPlayer jugador : this.jugadores) {
						jugador.setMesa(this.getMesa());
					}
					start();
					break;
				case 2:
					error = false;
					System.out.println("\n\n\n");
					bienvenida();
					break;
				case 3:
					error = false;
					System.out.println("\n");
					break;
				default:
					System.out.println("\nEliga una opcion correcta\n");
					error = true;
				}
			} catch (Exception e) {
				error = true;
				System.out.println("\n\nIntroduce un numero entero");
			}
		} while (error);
	}

}
