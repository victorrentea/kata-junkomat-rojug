package junkomat;

public class ProductInfo {
	private final int price;
	private int stock;
	
	public ProductInfo(int price, int stock) {
		this.price = price;
		this.stock = stock;
	}

	public void decrementStock() {
		if (stock == 0) {
			throw new IllegalArgumentException("Out of stock");
		}
		stock--;
	}

	public int getPrice() {
		return price;
	}
	
	public int getStock() {
		return stock;
	}
	
	
}