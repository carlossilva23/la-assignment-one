package model;

public enum Rating {
	noRating(0),
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
	
	public static Rating fromInt(int i) {
		switch (i) {
		case 1: return one;
		case 2: return two;
		case 3: return three;
		case 4: return four;
		case 5: return five;
		default: throw new IllegalArgumentException("Invalid Rating Value");
		}
	}
}