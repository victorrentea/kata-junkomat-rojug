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
	
	private static final Map<Coin, Integer> UNLIMITED_COIN_STOCK = 
			Stream.of(Coin.values())
			.collect(toMap(identity(), c -> Integer.MAX_VALUE));
	private CoinMachine machine = new CoinMachine();

	@Test
	public void fourEur() {
		machine.setCoinStock(new Coins(UNLIMITED_COIN_STOCK));
		Coins change = machine.provideChange(400, new Coins());
		assertEquals(new Coins(Coin.TWO_EURO, Coin.TWO_EURO), change);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tooLowDivision() {
		machine.setCoinStock(new Coins(UNLIMITED_COIN_STOCK));
		machine.provideChange(1, new Coins());
	}
	
	@Test
	public void eigthy() {
		machine.setCoinStock(new Coins(UNLIMITED_COIN_STOCK));
		Coins change = machine.provideChange(80, new Coins());
		assertEquals(new Coins(Coin.TEN_CENTS, Coin.TWENTY_CENTS, Coin.FIFTY_CENTS), change);
	}
	
	
	@Test
	public void haveToReturn20Cents_butOnlyHas10CentsCoinsInStock() {
		machine.setCoinStock(new Coins(singletonMap(Coin.TEN_CENTS, Integer.MAX_VALUE)));
		Coins change = machine.provideChange(20, new Coins());
		assertEquals(new Coins(Coin.TEN_CENTS, Coin.TEN_CENTS), change);
	}
	
	
	@Test
	public void givenEmptyMachine_whenUserProvides2x10cents_andWants10CentsChange_machineGivesOneOfHisCoinsBack() {
		Coins change = machine.provideChange(10, new Coins(singletonMap(Coin.TEN_CENTS, 2)));
		// Dar daca cumva totusi nu poate da restul omului ?? Ar trebui sa 'anulez' refill-ul
		assertEquals(new Coins(Coin.TEN_CENTS), change);
	}
	
	
	
	
	private static void assertSameLists(List<Coin> expected, List<Coin> actual) {
		expected.sort(comparing(Coin::getCents));
		actual.sort(comparing(Coin::getCents));
		assertEquals(expected, actual);
	}
	
	
	
}
