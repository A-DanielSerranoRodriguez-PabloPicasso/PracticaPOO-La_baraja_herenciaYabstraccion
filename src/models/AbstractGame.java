package models;

import java.util.ArrayList;

public abstract class AbstractGame {
	private boolean finished;

	@SuppressWarnings("unused")
	protected enum modoJuego {
		solitario, vsCPU, PvP, multiplayer
	};

	protected ArrayList<AbstractPlayer> jugadores;
	protected Mesa mesa;
	private int ronda;

	public AbstractGame() {
		this.jugadores = null;
		this.mesa = new Mesa();
		this.ronda = 0;
		this.finished = false;
	}

	// Getters - Setters
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public ArrayList<AbstractPlayer> getJugadores() {
		return jugadores;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	// Methods
	/**
	 * Se da la bienvenida al juego. Se llama a menuPrincipal(), barajar() y luego a
	 * start().
	 */
	abstract void bienvenida();

	/**
	 * Se recoge el modo de juego, los jugadores que participan y la cantidad de
	 * cartas a usar.
	 */
	abstract void menuPrincipal();

	/**
	 * Recorre la lista de jugadores, comprobando si han jugado el turno de la ronda
	 * actual o no.
	 * 
	 * @return Objeto 'AbstractPlayer' que representa el jugador que va a jugar.
	 */
	abstract AbstractPlayer nextTurno();

	/**
	 * El juego en sí. Comprobamos cuantas rondas han jugado los jugadores, y si
	 * todos han jugado esta ronda, empezamos la siguiente. Llamamos a nexTurno()
	 * para saber quien juega ahora, y si todos los jugadores pasan ronda,
	 * terminamos la partida.
	 */
	abstract void start();

	/**
	 * Barajamos la baraja de la mesa.
	 */
	abstract void barajar();

	/**
	 * Comprobamos los requerimientos para ver si hay algun ganador, y lo mostramos.
	 * Si se desea jugar otra partida, llamamos a start().
	 */
	abstract void finish();

}
