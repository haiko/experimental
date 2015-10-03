package nl.ns.rtsanalyzer.analyze;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import nl.ns.rtsanalyzer.domain.CiCoReisTransactie;
import nl.ns.rtsanalyzer.domain.Klasse;
import nl.ns.rtsanalyzer.domain.OVChipEvent;
import nl.ns.rtsanalyzer.persistence.TransactionsDao;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DataWriterTest {
	
	private DataWriter dataWriter;
	
	@Mock
	private TransactionsDao transactionsDao;
	
	private List<CiCoReisTransactie> transacties = new ArrayList<CiCoReisTransactie>();

	@Before
	public void setUp() throws Exception {
		dataWriter = new DataWriter();
		dataWriter.setTransactionsDao(transactionsDao);
		
		OVChipEvent vertrek1 = new OVChipEvent(new DateTime().minusHours(9), "Amsterdam Centraal");
		OVChipEvent aankomst1  = new OVChipEvent(new DateTime().minusHours(8).minusMinutes(30), "Utrecht Centraal");
		CiCoReisTransactie ciCoReisTransactie1 = new CiCoReisTransactie(BigInteger.valueOf(345678L), Integer.valueOf(123456), new DateTime(), vertrek1, aankomst1, BigDecimal.valueOf(8.25d));
		ciCoReisTransactie1.setKlasse(Klasse.TWEEDE);
		
		OVChipEvent vertrek2 = new OVChipEvent(new DateTime().minusHours(4), "Utrecht Centraal");
		OVChipEvent aankomst2  = new OVChipEvent(new DateTime().minusHours(3).minusMinutes(30), "Amsterdam Centraal");
		CiCoReisTransactie ciCoReisTransactie2 = new CiCoReisTransactie(BigInteger.valueOf(345678L), Integer.valueOf(126456), new DateTime(), vertrek2, aankomst2, BigDecimal.valueOf(8.25d));
		ciCoReisTransactie2.setKlasse(Klasse.TWEEDE);
		
		OVChipEvent vertrek3 = new OVChipEvent(new DateTime().minusHours(9), "Den Haag Centraal");
		OVChipEvent aankomst3  = new OVChipEvent(new DateTime().minusHours(8).minusMinutes(15), "Utrecht Centraal");
		CiCoReisTransactie ciCoReisTransactie3 = new CiCoReisTransactie(BigInteger.valueOf(345678L), Integer.valueOf(123456), new DateTime(), vertrek3, aankomst3, BigDecimal.valueOf(11.25d));
		ciCoReisTransactie3.setKlasse(Klasse.TWEEDE);
		
		OVChipEvent vertrek4 = new OVChipEvent(new DateTime().minusHours(4), "Utrecht Centraal");
		OVChipEvent aankomst4  = new OVChipEvent(new DateTime().minusHours(3).minusMinutes(15), "Den Haag Centraal");
		CiCoReisTransactie ciCoReisTransactie4 = new CiCoReisTransactie(BigInteger.valueOf(345678L), Integer.valueOf(123456), new DateTime(), vertrek4, aankomst4, BigDecimal.valueOf(11.25d));
		ciCoReisTransactie4.setKlasse(Klasse.TWEEDE);
		
		transacties.add(ciCoReisTransactie1);
		transacties.add(ciCoReisTransactie2);
		transacties.add(ciCoReisTransactie3);
		transacties.add(ciCoReisTransactie4);
		
	}

	/**
	 * Test om werking datawriter te testen 
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCreateFile() throws IOException {
		if(new File("target/test/rtsdata").exists()){
			new File("target/test/rtsdata").delete();
		}
		
		//stub
		when(transactionsDao.fetchAllCicoTransactions()).thenReturn(transacties);
		
		dataWriter.createFile("target/test/rtsdata");
		
		assertTrue(new File("target/test/rtsdata").exists());
		
	}

}
