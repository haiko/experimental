package nl.cybercompany.treinadvies.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ParseUtilTest {

	@Test
	public void testFormatToTwoCharacterNumber() {
		assertEquals("04", ParseUtil.formatToTwoCharacterNumber(4));
		assertEquals("12",  ParseUtil.formatToTwoCharacterNumber(12));
	}

}
