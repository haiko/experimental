/**
 * 
 */
package nl.ns.rtsanalyzer.analyze;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import nl.ns.rtsanalyzer.domain.CiCoReisTransactie;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapping {@link CiCoReisTransactie}s naar het uur van de vertrektijd.
 * 
 * @author haiko
 * 
 */
public class RtsMapper extends
		Mapper<IntWritable, CiCoReisTransactie, IntWritable, IntWritable> {

	private static Logger logger = LoggerFactory.getLogger(RtsMapper.class);

	private final static IntWritable one = new IntWritable(1);

	/**
	 *  Map functie voor vertrektijd.
	 *  
	 *  input[regelnummer, reistransactie] -->
	 *  output[vertrekuur,1]
	 *  
	 *  
	 *  @param key willekeurig nummer.
	 *  @param transactie een {@link CiCoReisTransactie}.
	 *  @param context MapReduce Context
	 */
	@Override
	protected void map(IntWritable key, CiCoReisTransactie transactie,
			Context context) throws IOException, InterruptedException {
		int hour = transactie.getVertrek().getTijdstip().getHourOfDay();

		logger.info("mapping transactie:" + transactie.toString());
		context.write(new IntWritable(hour), one);

	}
}
