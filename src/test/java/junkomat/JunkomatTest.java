package junkomat;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JunkomatTest {
	
	private CoinMachine coinMachine = new CoinMachine();
	
	private Junkomat junkomat = new Junkomat(coinMachine);
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test(expected = IllegalArgumentException.class)
	public void unableToProdiveChange() {
		junkomat.refill(Collections.singletonMap(99, new ProductInfo(10, 1)));
		junkomat.purchase(new DrinkRequest(99), new Coins(Coin.TWENTY_CENTS));
	}
	
	
	@Test
	public void notEnoughMoney() {
		expectedException.expectMessage("Not enough money");
		junkomat.refill(Collections.singletonMap(99, new ProductInfo(20, 1)));
		coinMachine.setCoinStock(Coins.UNLIMITED_COINS);
		junkomat.purchase(new DrinkRequest(99), new Coins(Coin.TEN_CENTS));
	}
	
	@Test
	public void productNotInStock() {
		expectedException.expectMessage("Out of stock");
		junkomat.refill(Collections.singletonMap(99, new ProductInfo(20, 0)));
		coinMachine.setCoinStock(Coins.UNLIMITED_COINS);
		junkomat.purchase(new DrinkRequest(99), new Coins(Coin.TWO_EURO));
	}
	
	@Test
	public void productCodeNotDefined() {
		expectedException.expectMessage("Out of stock");
		coinMachine.setCoinStock(Coins.UNLIMITED_COINS);
		junkomat.purchase(new DrinkRequest(99), new Coins(Coin.TWO_EURO));
	}
	
	@Test
	public void stockIsDecrementedAfterSuccessfulPurchase() {
		junkomat.refill(Collections.singletonMap(99, new ProductInfo(20, 1)));
		coinMachine.setCoinStock(Coins.UNLIMITED_COINS);
		junkomat.purchase(new DrinkRequest(99), new Coins(Coin.TWO_EURO));
		assertEquals(0, junkomat.getStock(new DrinkRequest(99)));
	}
	
	@Test
	public void stockIsNotDecrementedIfNotEnoughChange() {
		junkomat.refill(Collections.singletonMap(99, new ProductInfo(10, 1)));
		coinMachine.setCoinStock(Coins.EMPTY);
		try {
			junkomat.purchase(new DrinkRequest(99), new Coins(Coin.TWENTY_CENTS));
		} catch (Exception e) {
			//shaorma -- ignore exception
		}
		assertEquals(1, junkomat.getStock(new DrinkRequest(99)));
	}
}
