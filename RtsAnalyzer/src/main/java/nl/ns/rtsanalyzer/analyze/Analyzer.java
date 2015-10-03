/**
 * 
 */
package nl.ns.rtsanalyzer.analyze;

import java.io.File;
import java.io.IOException;

import nl.ns.rtsanalyzer.domain.OVChipkaartTransactie;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Analyzes {@link OVChipkaartTransactie}s.
 * 
 * @author haiko
 * 
 */
public class Analyzer extends Configured implements Tool {

	private static final String USER_HOME = "user.home";

	public static String outputPathName = "output";

	private static Logger logger = LoggerFactory.getLogger(Analyzer.class);

	private final static IntWritable one = new IntWritable(1);

	public static void main(String[] args) throws IOException {
		new Analyzer().analyzeTransactions(args);
	}

	public void analyzeTransactions(String[] args) throws IOException {

		try {
			int res = ToolRunner.run(new Configuration(), this,
					args);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int run(String[] args) throws Exception {

		String inputFileName = args[0];
		String outputFileName = args[1];
		String title = args[2];

		Configuration conf = new Configuration();
		conf.set("mapred.job.tracker", "local");
		FileSystem fs = FileSystem.get(conf);
		Path dfsInput = new Path("input");
		fs.copyFromLocalFile(new Path(inputFileName), dfsInput);

		SequenceFile.Reader reader = new SequenceFile.Reader(fs, dfsInput, conf);

		Job job = new Job(conf, "reistransacties vertrektijd analyse");
		job.setJarByClass(Analyzer.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setMapperClass(RtsMapper.class);
		job.setCombinerClass(RtsReducer.class);
		job.setReducerClass(RtsReducer.class);

		job.setInputFormatClass(SequenceFileInputFormat.class);

		// set output format
		job.setOutputFormatClass(TextOutputFormat.class);

		Path outputPath = new Path(outputPathName);
		if (fs.exists(outputPath)) {
			fs.delete(outputPath, true);
		}
		

		FileInputFormat.addInputPath(job, dfsInput);
		TextOutputFormat.setOutputPath(job, outputPath);

		boolean success = job.waitForCompletion(true);
		
		// copy result to local user dir
		copyToLocalFileSystem(conf, fs, outputPath, outputFileName, title);

		return success == true ? 0 : 1;
	}

	private void copyToLocalFileSystem(Configuration conf, FileSystem fs,
			Path outputPath, String outputFileName, String title) throws IOException {
		String userHome = System.getProperty(USER_HOME); 
		
		File outputLocalFile = new File(userHome, outputFileName);
		FileUtil.copyMerge(fs, outputPath, fs, new Path("outputForLocal"), true, conf, "title " + title);
		FileUtil.copy(fs, new Path("outputForLocal"), outputLocalFile, true, conf);
	}
}
