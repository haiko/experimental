package nl.ns.rtsanalyzer.analyze;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reducer voor de vertrektijden analyse.
 * 
 * @author haiko
 * 
 */
public class RtsReducer extends
		Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

	private static Logger logger = LoggerFactory.getLogger(RtsReducer.class);

	private IntWritable result = new IntWritable();

	/**
	 * Reduce functie. Hier worden alle waardes met dezelfde key aangeboden.
	 * input[vertrekuur, list[1]]
	 * 
	 */
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {

		logger.info("reducing");

		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}


		result.set(sum);
		context.write(key, result);
	}
}
