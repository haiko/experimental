/**
 * 
 */
package nl.ns.rtsanalyzer.persistence;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import nl.ns.rtsanalyzer.domain.CiCoReisTransactie;
import nl.ns.rtsanalyzer.domain.Klasse;
import nl.ns.rtsanalyzer.domain.OVChipEvent;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author haiko
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rtsanalyzer-test-context.xml"})
public class TransactionDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private TransactionsDaoImpl transactionsDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
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
		
		transactionsDao.save(ciCoReisTransactie1);
		transactionsDao.save(ciCoReisTransactie2);
		transactionsDao.save(ciCoReisTransactie3);
		transactionsDao.save(ciCoReisTransactie4);	
	}

	/**
	 * Test method for {@link nl.ns.rtsanalyzer.persistence.TransactionsDaoImpl#fetchAllCicoTransactions()}.
	 */
	@Test
	public void shouldFetchAllCicoTransactions() {
		List<CiCoReisTransactie> cicoTransacties = transactionsDao.fetchAllCicoTransactions();
		assertNotNull(cicoTransacties);
		assertEquals(4, cicoTransacties.size());
	}

	/**
	 * @param transactionsDao the transactionsDao to set
	 */
	public void setTransactionsDao(TransactionsDaoImpl transactionsDao) {
		this.transactionsDao = transactionsDao;
	}
}
