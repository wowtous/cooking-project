package org.darebeat.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.darebeat.mapper.WordCountMapper;
import org.darebeat.reducer.WordCountReducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darebeat on 5/6/17.
 */
public class WordCount {
    public static void start(String[] args){
        String mc = args[0].toLowerCase();
        if (mc == "wordcount" || mc.endsWith(".wordcount")) {
            new WordCount().run(args);
        }
    }

    public void run(String[] args){
        Configuration conf = new Configuration();
        GenericOptionsParser optionParser = null;

        Job job = null;
        try {
            optionParser = new GenericOptionsParser(conf, args);
            String[] remainingArgs = optionParser.getRemainingArgs();
            if (!(remainingArgs.length != 2 || remainingArgs.length != 4)) {
                System.err.println("Usage: wordcount <in> <out> [-skip skipPatternFile]");
                System.exit(2);
            }

            job = Job.getInstance(conf, "word count");
            job.setJarByClass(WordCount.class);
            job.setMapperClass(WordCountMapper.class);
            job.setCombinerClass(WordCountReducer.class);
            job.setReducerClass(WordCountReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            List<String> otherArgs = new ArrayList<String>();
            for (int i=0; i < remainingArgs.length; ++i) {
                if ("-skip".equals(remainingArgs[i])) {
                    job.addCacheFile(new Path(remainingArgs[++i]).toUri());
                    job.getConfiguration().setBoolean("wordcount.skip.patterns", true);
                } else {
                    otherArgs.add(remainingArgs[i]);
                }
            }
            FileInputFormat.addInputPath(job, new Path(otherArgs.get(0)));
            FileOutputFormat.setOutputPath(job, new Path(otherArgs.get(1)));

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
