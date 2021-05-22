package utils;

public class Paire<T> {
	private final T x;
	private final T y;
	
	/**
	 * Initialise une nouvelle paire d'éléments de type T
	 * @param x Le premier élément de la paire
	 * @param y Le second élément de la paire
	 */
	public Paire(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	public T getX() {
		return x;
	}

	public T getY() {
		return y;
	}
	
	
}
