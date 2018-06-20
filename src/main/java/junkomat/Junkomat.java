package junkomat;

import java.util.HashMap;
import java.util.Map;

// stateful
public class Junkomat {
	
	private final CoinMachine coinMachine;
	
	private Map<Integer, ProductInfo> products = new HashMap<>();
	
	public Junkomat(CoinMachine coinMachine) {
		this.coinMachine = coinMachine;
	}

	public void refill(Map<Integer, ProductInfo> newProducts) {
		this.products = newProducts;
	}
	
	
	public Coins purchase(DrinkRequest selection, Coins depositCoins) {
		int depositCents = depositCoins.getTotalCents();
		ProductInfo productInfo = products.get(selection.getCode());
		int priceCents = productInfo.getPrice();
		if (productInfo.getStock() == 0) {
			throw new IllegalArgumentException("Out of stock");
		}
		
		if (depositCents < priceCents) {
			throw new IllegalArgumentException("Not enough money");
		}
		
		int changeCents = depositCoins.getTotalCents() - priceCents;
		
		// NU AICI decrementez stocu'
		
		Coins changeCoins = coinMachine.provideChange(changeCents, depositCoins);
		
		// ci aici , dupa verificarea ca am sa-i dau rest
		productInfo.decrementStock();
		
		return changeCoins;
	}
	
	int getStock(DrinkRequest selection) {
		return products.get(selection.getCode()).getStock();
	}

}

class ProductInfo {
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