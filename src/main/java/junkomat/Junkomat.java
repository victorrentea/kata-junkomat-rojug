package junkomat;

import java.util.HashMap;
import java.util.Map;

public class Junkomat {
	
	private CoinMachine coinMachine;
	
	private Map<Integer, ProductInfo> products = new HashMap<>();
	
	public void refill(Map<Integer, ProductInfo> newProducts) {
		this.products = newProducts;
	}
	
	public PurchasedDrinkAndChange purchase(DrinkRequest selection, Coins deposit) {
		int price = products.get(selection.getCode()).getPrice();
		int changeCents = deposit.getTotalCents() - price;
		
		Coins changeCoins = coinMachine.provideChange(changeCents, deposit);
		return null;
	}

}

class ProductInfo {
	private final int price;
	private int stock;
	
	public ProductInfo(int price, int stock) {
		this.price = price;
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}
	
	
}