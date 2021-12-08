package models;

public abstract class AbstractPlayer {
	private String nombre;
	private double puntos;
	protected Mano mano;
	protected Mesa mesa;
	private boolean pasar;
	private int rondasJugadas;

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
	public abstract void jugarTurno7yMedia();

	public void mostrarMano() {
		System.out.println(this.mano.toString());
	}

	// toString
	@Override
	public String toString() {
		return "AbstractPlayer [nombre=" + nombre + ", puntos=" + puntos + ", mano=" + mano + ", mesa=" + mesa + "]";
	}

}
