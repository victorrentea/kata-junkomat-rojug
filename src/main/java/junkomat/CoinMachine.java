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

	/// MUTATOARE - face side-effecturi -->
	public Coins acceptPaymentCoins(int changeCents, Coins depositedCoins) {
		Coins allCoins = new Coins();
		allCoins = allCoins.add(coinStock);
		allCoins = allCoins.add(depositedCoins);
		
		//daca as adauga aici monezile userului, daca crapa cu exceptioe la getLargestCoinLessThan 
		// --> ar trebui sa prind exceptia ca sa-i scot banii omului din stocu meu 
		
		Coins changeCoins = new Coins();
		while (changeCents > 0) {
			Coin coin = getLargestCoinLessThan(allCoins, changeCents); // CAND CRAPA ASTA ???!
			changeCents -= coin.cents;
			changeCoins = changeCoins.add(coin);
		}
		coinStock = coinStock.add(depositedCoins).remove(changeCoins);
		return changeCoins;
	}
 
//	public void addRemoveCoins(Coins toRemove, Coins toAdd) {
//	}

	private Coin getLargestCoinLessThan(Coins allCoins, int cents) {
		return COINS_ORDERED.stream()
				.filter(c -> c.cents <= cents)
				.filter(c -> allCoins.get(c) >= 1)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Too small division or n-am monezi frate"));
	}
}