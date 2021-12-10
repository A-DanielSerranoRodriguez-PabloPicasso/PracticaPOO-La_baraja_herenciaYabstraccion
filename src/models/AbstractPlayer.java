package models;

public abstract class AbstractPlayer {
	protected Mano mano;
	protected Mesa mesa;
	private String nombre;
	private int rondasJugadas;
	private double puntos;
	private boolean pasar;

	// Const
	/**
	 * Constructor de un jugador. Para asignarle una mesa, usa el Setter.
	 * 
	 * @param nombre String que representa el nombre del jugador.
	 */
	public AbstractPlayer(String nombre) {
		this.nombre = nombre;
		this.puntos = 0;
		this.mano = new Mano();
		this.pasar = false;
		this.rondasJugadas = 0;
	}

	/**
	 * Constructor de una CPU. Para asignarle una mesa, usa el Setter.
	 */
	public AbstractPlayer() {
		this.nombre = "CPU";
		this.puntos = 0;
		this.mano = new Mano();
		this.pasar = false;
		this.rondasJugadas = 0;
	}

	// Getters - Setters
	public String getNombre() {
		return nombre;
	}

	public double getPuntos() {
		return puntos;
	}

	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}

	public Mano getMano() {
		return mano;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public boolean isPasar() {
		return pasar;
	}

	public void setPasar(boolean pasar) {
		this.pasar = pasar;
	}

	public int getRondasJugadas() {
		return rondasJugadas;
	}

	public void setRondasJugadas(int rondasJugadas) {
		this.rondasJugadas = rondasJugadas;
	}

	// Methods
	/**
	 * Jugamos un turno en el juego de cartas '7 y media'.
	 */
	public abstract void jugarTurno7yMedia();

	/**
	 * Mostramos la mano que el jugador posee ahora mismo.
	 */
	public void mostrarMano() {
		this.mano.listarCartas();
	}

	// toString
	@Override
	public String toString() {
		return "AbstractPlayer [nombre=" + nombre + ", puntos=" + puntos + ", mano=" + mano + ", mesa=" + mesa + "]";
	}

}
