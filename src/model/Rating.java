package model;

public enum Rating {
	one(1),
	two(2),
	three(3),
	four(4),
	five(5);

	private final int value;
	
	Rating(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}