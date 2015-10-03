package nl.ns.rtsanalyzer.analyze;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import nl.ns.rtsanalyzer.domain.CiCoReisTransactie;
import nl.ns.rtsanalyzer.persistence.TransactionsDao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * Writes data for analysis.
 * 
 * @author haiko
 *
 */
public class DataWriter {
	
	@Autowired
	private TransactionsDao transactionsDao;
		
	public static void main(String[] args) {
		String inputPath = args[0];
		try {
			DataWriter dw  = new DataWriter();
			dw.init();
			dw.createFile(inputPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init(){
		// setup Spring context
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("rtsanalyzer-context.xml");
	}
	
	public void createFile(String filename) throws IOException{
		// create sequencefile(s)
		Configuration conf = new Configuration();
				
		FileSystem fs = FileSystem.get(new File(filename).toURI(), conf);
		fs.createNewFile(new Path(filename));
		Path rtsData = new Path(filename);

		SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,
				rtsData, IntWritable.class, CiCoReisTransactie.class);
		List<CiCoReisTransactie> transacties = transactionsDao.fetchAllCicoTransactions();
		
		int key = 0;
		for (Iterator iterator = transacties.iterator(); iterator.hasNext();) {
			CiCoReisTransactie ciCoReisTransactie = (CiCoReisTransactie) iterator
					.next();
			key+=1;
			writer.append(new IntWritable(key),ciCoReisTransactie);
		}
		
		IOUtils.closeStream(writer);
	}


	/**
	 * @param transactionsDao the transactionsDao to set
	 */
	public void setTransactionsDao(TransactionsDao transactionsDao) {
		this.transactionsDao = transactionsDao;
	}


}
