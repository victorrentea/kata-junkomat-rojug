package junkomat;

// Lombok!!! e bun. ar fi doar "@Data si gata".
class PurchasedDrinkAndChange {
	private final boolean success;
	private final Coins change;
	public PurchasedDrinkAndChange(boolean success, Coins change) {
		this.success = success;
		this.change = change;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public Coins getChange() {
		return change;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((change == null) ? 0 : change.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchasedDrinkAndChange other = (PurchasedDrinkAndChange) obj;
		if (change == null) {
			if (other.change != null)
				return false;
		} else if (!change.equals(other.change))
			return false;
		if (success != other.success)
			return false;
		return true;
	}

	
	
}