package junkomat;

public class DrinkRequest {
	final int code;

	public DrinkRequest(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public String toString() {
		return "DrinkRequest [code=" + code + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrinkRequest other = (DrinkRequest) obj;
		if (code != other.code)
			return false;
		return true;
	}
}