package junkomat;

import java.util.Collections;

import org.junit.Test;

public class JunkomatTest {
	private Junkomat junkomat = new Junkomat();
	
	@Test
	public void nareMonezi() {
		junkomat.refill(Collections.singletonMap(99, new ProductInfo(10, 1)));
		junkomat.purchase(new DrinkRequest(99), new Coins(Coin.TWENTY_CENTS));
	}
}
