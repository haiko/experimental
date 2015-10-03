package nl.ns.rtsanalyzer.persistence;

import java.util.List;

import nl.ns.rtsanalyzer.domain.CiCoReisTransactie;
import nl.ns.rtsanalyzer.domain.OVChipkaartTransactie;

public interface TransactionsDao {
	
	/**
	 * Fetch all {@link CiCoReisTransactie}s.
	 * 
	 * @return a {@link List} with all {@link CiCoReisTransactie}s.
	 */
	public List<CiCoReisTransactie> fetchAllCicoTransactions();
	
	/**
	 * Saves an {@link OVChipkaartTransactie}.
	 * 
	 * @param ovChipkaartTransactie
	 */
	public void save(OVChipkaartTransactie ovChipkaartTransactie);

}
