package utils;

public class Paire<T> {
	private final T x;
	private final T y;
	
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
