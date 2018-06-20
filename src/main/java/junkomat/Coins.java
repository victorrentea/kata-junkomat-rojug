package junkomat;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

// gramada de monezi -- imutabila
class Coins {
	
	public static final Coins UNLIMITED_COINS = new Coins(
			Stream.of(Coin.values())
			.collect(toMap(identity(), c -> Integer.MAX_VALUE)));
	
	
	private static final Map<Coin, Integer> NO_COINS_STOCK = 
			Stream.of(Coin.values())
			.collect(toMap(identity(), c -> 0));
	
	private final Map<Coin, Integer> coinNumbers;

	public Coins() {
		coinNumbers = new HashMap<>(NO_COINS_STOCK);
	}
	
	

	public int get(Coin coin) {
		return coinNumbers.get(coin);
	}



	public Coins(Map<Coin, Integer> coinNumbers) {
		this.coinNumbers = coinNumbers;
	}

	
	
	
	// TWO, TWO, ---> (TWO -> 2)
	public Coins(Coin... coinArr) {
		Map<Coin, Long> numiVine = Stream.of(coinArr)
				.collect(groupingBy(c->c, counting()));
		
		coinNumbers = new HashMap<>(NO_COINS_STOCK);
		for (Coin c : numiVine.keySet()) {
			coinNumbers.put(c,(int)(long)numiVine.get(c));
		}
	}
	
	public Coins add(Coins toAdd) {
		Map<Coin, Integer> newMap = new HashMap<>(this.coinNumbers);
		for (Coin coin : toAdd.coinNumbers.keySet()) {
			newMap.put(coin, toAdd.coinNumbers.get(coin)+ newMap.get(coin));
		}
		return new Coins(newMap); 
	}

	public Coins add(Coin coin) {
		Map<Coin, Integer> newMap = new HashMap<>(coinNumbers);
		newMap.put(coin, 1 + newMap.get(coin));
		Coins newCoins = new Coins(newMap);
		return newCoins;
	}
	
	public int getTotalCents() {
		return coinNumbers.entrySet().stream()
				.mapToInt(entry -> entry.getKey().cents * entry.getValue())
				.sum(); 
	}
	
	
	
	
	

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coinNumbers == null) ? 0 : coinNumbers.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coins other = (Coins) obj;
		if (coinNumbers == null) {
			if (other.coinNumbers != null)
				return false;
		} else if (!coinNumbers.equals(other.coinNumbers))
			return false;
		return true;
	}



	
	
	
	
}