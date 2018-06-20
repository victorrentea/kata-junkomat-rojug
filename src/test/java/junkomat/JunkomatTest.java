package junkomat;

import java.util.Collections;

import org.junit.Test;

public class JunkomatTest {
	
	private CoinMachine coinMachine = new CoinMachine();
	
	private Junkomat junkomat = new Junkomat(coinMachine);
	
	@Test
	public void nareMonezi() {
		junkomat.refill(Collections.singletonMap(99, new ProductInfo(10, 1)));
		coinMachine.setCoinStock(Coins.UNLIMITED_COINS);
		junkomat.purchase(new DrinkRequest(99), new Coins(Coin.TWENTY_CENTS));
	}
}
