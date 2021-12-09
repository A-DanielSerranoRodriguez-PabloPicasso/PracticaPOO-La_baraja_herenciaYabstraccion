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
			if(this.getPuntos() <= 5) {
				this.mano.insertaCartaFinal(this.mesa.robarCartaDeBaraja());
				this.setPuntos(getPuntos()
						+ this.mano.lista_cartas.get(this.mano.getCantidadCartas() - 1).getValor7yMedia());
				this.setPasar(false);
				this.setRondasJugadas(getRondasJugadas() + 1);
			} else {
				this.setPasar(true);
				this.setRondasJugadas(getRondasJugadas() + 1);
			}
		}
	}

}
