package junkomat;
import static java.util.Collections.singletonMap;
import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

public class CoinMachineTest {
	
	
	private CoinMachine machine = new CoinMachine();

	@Test
	public void fourEur() {
		machine.setCoinStock(Coins.UNLIMITED_COINS);
		Coins change = machine.acceptPaymentCoins(400, new Coins());
		assertEquals(new Coins(Coin.TWO_EURO, Coin.TWO_EURO), change);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tooLowDivision() {
		machine.setCoinStock(Coins.UNLIMITED_COINS);
		machine.acceptPaymentCoins(1, new Coins());
	}
	
	@Test
	public void eigthy() {
		machine.setCoinStock(Coins.UNLIMITED_COINS);
		Coins change = machine.acceptPaymentCoins(80, new Coins());
		assertEquals(new Coins(Coin.TEN_CENTS, Coin.TWENTY_CENTS, Coin.FIFTY_CENTS), change);
	}
	
	
	@Test
	public void haveToReturn20Cents_butOnlyHas10CentsCoinsInStock() {
		machine.setCoinStock(new Coins(singletonMap(Coin.TEN_CENTS, Integer.MAX_VALUE)));
		Coins change = machine.acceptPaymentCoins(20, new Coins());
		assertEquals(new Coins(Coin.TEN_CENTS, Coin.TEN_CENTS), change);
	}
	
	
	@Test
	public void givenEmptyMachine_whenUserProvides2x10cents_andWants10CentsChange_machineGivesOneOfHisCoinsBack() {
		Coins change = machine.acceptPaymentCoins(10, new Coins(singletonMap(Coin.TEN_CENTS, 2)));
		// Dar daca cumva totusi nu poate da restul omului ?? Ar trebui sa 'anulez' refill-ul
		assertEquals(new Coins(Coin.TEN_CENTS), change);
	}
	
	
	
	
	private static void assertSameLists(List<Coin> expected, List<Coin> actual) {
		expected.sort(comparing(Coin::getCents));
		actual.sort(comparing(Coin::getCents));
		assertEquals(expected, actual);
	}
	
	
	
}
