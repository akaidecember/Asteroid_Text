package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Objects.PlayerShip;

public class PlayerShipTest {

	@Test
	public void test() {
		
		PlayerShip ps = new PlayerShip();
		
		ps.setMaxMissiles();
		
		ps.setMissileCount(11);
		
		assertEquals(10, ps.getMissileCount());
		
	}

}
