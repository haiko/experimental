/**
 * 
 */
package nl.ns.rtsanalyzer.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nl.ns.rtsanalyzer.domain.CiCoReisTransactie;
import nl.ns.rtsanalyzer.domain.OVChipkaartTransactie;

/**
 *  Persistence operaties op {@link OVChipkaartTransactie}
 * 
 * @author haiko
 * 
 */
@Transactional
public class TransactionsDaoImpl implements TransactionsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CiCoReisTransactie> fetchAllCicoTransactions() {
		Criteria crit = getSession().createCriteria(CiCoReisTransactie.class);
		return crit.list();
	}
	
	@Override
	public void save(OVChipkaartTransactie ovChipkaartTransactie) {
		getSession().save(ovChipkaartTransactie);		
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	
}
