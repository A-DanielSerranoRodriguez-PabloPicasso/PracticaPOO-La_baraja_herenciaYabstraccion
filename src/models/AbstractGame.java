package models;

import java.util.ArrayList;

public abstract class AbstractGame {
	private boolean finished;

	@SuppressWarnings("unused")
	private enum modoJuego {
		solitario, vsCPU, PvP, multiplayer
	};

	protected ArrayList<AbstractPlayer> jugadores;
	protected Mesa mesa;
	private int ronda;

	public AbstractGame() {
		this.jugadores = new ArrayList<AbstractPlayer>();
		this.mesa = new Mesa();
		this.ronda = 0;
		this.finished = false;
	}

	// Getters - Setters
	public boolean isFinished() {
		return finished;
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
	abstract void bienvenida();

	abstract void menuPrincipal();

	abstract AbstractPlayer nextTurno();

	abstract void start();

	abstract void barajar();

	abstract void finish();

}
