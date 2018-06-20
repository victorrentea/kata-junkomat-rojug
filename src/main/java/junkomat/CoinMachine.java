package junkomat;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CoinMachine {
	
	private final static List<Coin> COINS_ORDERED = Stream.of(Coin.values())
			.sorted(comparing(Coin::getCents).reversed())
			.collect(toList());
	
	private Coins coinStock = new Coins();
	
	public CoinMachine() {
	}
	
	public void setCoinStock(Coins newStock) {
		this.coinStock = newStock;
	}

	
	public Coins provideChange(int cents, Coins userCoins) {
		Coins allCoins = new Coins();
		allCoins = allCoins.add(coinStock);
		allCoins = allCoins.add(userCoins);
		
		Coins coins = new Coins();
		while (cents > 0) {
			Coin coin = getLargestCoinLessThan(allCoins, cents); // CAND CRAPA ASTA ???!
			cents -= coin.cents;
			coins = coins.add(coin);
		}
		return coins;
	}

	private Coin getLargestCoinLessThan(Coins allCoins, int cents) {
		return COINS_ORDERED.stream()
				.filter(c -> c.cents <= cents)
				.filter(c -> allCoins.get(c) >= 1)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Too small division or n-am monezi frate"));
	}
}