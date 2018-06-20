package junkomat;

import java.util.HashMap;
import java.util.Map;

// stateful
public class Junkomat {
	
	private final CoinMachine coinMachine;
	
	private Map<Integer, ProductInfo> products = new HashMap<>();
	
	// 3 care ia 'subcomponenta' ca param de constructor
	public Junkomat(CoinMachine coinMachine) {
		this.coinMachine = coinMachine;
	}

	public void refill(Map<Integer, ProductInfo> newProducts) {
		this.products = newProducts;
	}
	
	
	
	//1 care expune prea multe  vs Tell Don't Ask
//	public CoinMachine getCoinMachine() {
//		return coinMachine;
//	}
	// 2 care face delegare
//	public void setCoinStock(Coins newStock) {
//		coinMachine.setCoinStock(newStock);
//	}
	
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