package models;

public class CPU_Player extends AbstractPlayer {

	public CPU_Player() {
		super();
	}

	@Override
	public void jugarTurno7yMedia() {
		if (this.getMano().isVacia()) {
			this.mano.lista_cartas.add(this.mesa.robarCartaDeBaraja());
			this.setPuntos(this.getPuntos() + this.mano.lista_cartas.get(0).getValor7yMedia());
		} else {
			if (this.mano.getCantidadCartas() == 1)
				System.out.println("Turno de la CPU. Puntuación: " + this.mano.lista_cartas.get(0).getValor7yMedia() + "\n\n");
			else
				System.out.println("Turno de la CPU. Puntuación: " + this.mano.lista_cartas.get(0).getValor7yMedia() + " + ?\n\n");

			if (this.getPuntos() <= 4.5) {
				this.mano.insertaCartaFinal(this.mesa.robarCartaDeBaraja());
				this.setPuntos(
						getPuntos() + this.mano.lista_cartas.get(this.mano.getCantidadCartas() - 1).getValor7yMedia());
				this.setPasar(false);
				this.setRondasJugadas(getRondasJugadas() + 1);
			} else {
				this.setPasar(true);
				this.setRondasJugadas(getRondasJugadas() + 1);
			}
		}
	}

}
