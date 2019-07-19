package dev.emax.sortbench.profiler;

public class SortbenchProfiler {

	/**
	 * Il tempo totale trascorso
	 */
	private long profilerElapsed;

	/**
	 * L'inizio e la fine di questo intervallo
	 */
	private Long profilerInterval;

	/**
	 * Avvia/Riprende il timer del profiler
	 */
	public void profilerStart() {
		if (profilerInterval != null)
			throw new IllegalStateException("Il profiler è già avviato");

		profilerInterval = System.nanoTime();
	}

	/**
	 * Mette in pausa il timer del profiler
	 */
	public void profilerPause() {
		if (profilerInterval == null)
			throw new IllegalStateException("Il profiler non è avviato");

		profilerElapsed += System.nanoTime() - profilerInterval;
		profilerInterval = null;
	}

	/**
	 * Resetta il timer
	 */
	public void profilerReset() {
		this.profilerInterval = null;
		this.profilerElapsed = 0;
	}

	/**
	 * Ritorna il tempo trascorso
	 *
	 * @return il tempo trascorso
	 */
	public long profilerElapsed() {
		return profilerElapsed;
	}

	/**
	 * Ritorna il tempo trascorso in millisecondi
	 *
	 * @return il tempo trascorso in millisecondi
	 */
	public double profilerElapsedMs() {
		return profilerElapsed / 1_000_000D;
	}


}
