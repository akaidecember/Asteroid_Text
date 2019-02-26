package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;

import Objects.PlayerShip;


import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import com.codename1.ui.geom.Point2D;

class LocationTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	 
	@Test
	public void test() {
		PlayerShip ps = new PlayerShip();
	    exceptionRule.expect(Exception.class);
	    exceptionRule.expectMessage("The coordinates are out of range.");
	    ps.setLocation(new Point2D(2000,2000));
	    
	}
}
