package nl.cybercompany.treinadvies.util;

import static nl.cybercompany.treinadvies.util.StringUtil.trimAll;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StringUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTrimAll() {
		String s = " TRAIN                 ";
		String s2 = "TRAIN";
		
		assertEquals(s2, trimAll(s));
	}

}
