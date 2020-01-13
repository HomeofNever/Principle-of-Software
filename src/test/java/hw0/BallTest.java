package hw0;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.BeforeClass;
import org.junit.Test;

public class BallTest {

	private static Ball b = null;
	private static final double BALL_VOLUME = 20.0;
	private static final double JUNIT_DOUBLE_DELTA = 0.0001;
	private static final Color COLOR = Color.BLUE;
	
	@BeforeClass
	public static void setupBeforeTests() throws Exception {
		b = new Ball(BALL_VOLUME, COLOR);
	}
	
	@Test
	public void testVolume() {
		assertEquals("b.getVolume()", BALL_VOLUME, b.getVolume(), JUNIT_DOUBLE_DELTA);
	}
	
	@Test
	public void testColor() {
		assertEquals("b.getColor()", COLOR, b.getColor());
	}
	
	@Test
	public void testCreateWithValidStringVolume() {
		String volume = "21.4e2";
		assertEquals("new(" + volume + ").getVolume()", Double.parseDouble(volume),
				new Ball(volume, Color.WHITE).getVolume(), JUNIT_DOUBLE_DELTA);
	}

}
