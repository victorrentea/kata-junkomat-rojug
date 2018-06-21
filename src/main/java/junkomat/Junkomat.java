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
	
	/**
	 * @throws IllegalArgumentException if some problem occurs
	 * @return Coins if the purchase is successful
	 */
	public Coins purchase(DrinkRequest selection, Coins depositCoins) {
		int depositCents = depositCoins.getTotalCents();
		if (!products.containsKey(selection.getCode()))	{
			throw new IllegalArgumentException("Out of stock");
		}
		ProductInfo productInfo = products.get(selection.getCode());
		int priceCents = productInfo.getPrice();
		if (productInfo.getStock() == 0) {
			throw new IllegalArgumentException("Out of stock");
		}
		
		if (depositCents < priceCents) {
			throw new IllegalArgumentException("Not enough money");
		}
		
		int changeCents = depositCoins.getTotalCents() - priceCents;
		
		Coins changeCoins = coinMachine.acceptPaymentCoins(changeCents, depositCoins);
		
		productInfo.decrementStock(); // poa' sa crape asta ?? NU
		
		return changeCoins;
	}
	
	int getStock(DrinkRequest selection) {
		return products.get(selection.getCode()).getStock();
	}

}