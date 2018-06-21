package junkomat;

public enum Coin {
	ONE_EURO(100),
	TWO_EURO(200),
	FIFTY_CENTS(50),
	TWENTY_CENTS(20),
	TEN_CENTS(10);
	public final int cents;

	private Coin(int cents) {
		this.cents = cents;
	}
	
	public int getCents() {
		return cents;
	}
	
}