package logQ4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class logQ4 {


    public static void main(String[] args) throws Exception {

        if(args.length!=3){
            System.out.println("usage: [input] [temp] [output]");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        // job 1
        Job job1 = Job.getInstance(conf, "count");
        job1.setJarByClass(Count.class);
        job1.setMapperClass(Count.logCountMapper.class);
        job1.setCombinerClass(Count.logCountReducer.class);
        job1.setReducerClass(Count.logCountReducer.class);
        job1.setMapOutputKeyClass(Text.class);// map output key setting
        job1.setMapOutputValueClass(IntWritable.class);// map output value setting
        job1.setOutputKeyClass(Text.class);// reduce output key setting
        job1.setOutputValueClass(IntWritable.class);// reduce output value setting
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));
        job1.setInputFormatClass(TextInputFormat.class);
        job1.setOutputFormatClass(TextOutputFormat.class);
        // job 2
        Job job2 = Job.getInstance(conf, "topK");
        job2.setJarByClass(TopK.class);
        job2.setMapperClass(TopK.logTopKMapper.class);
        //job2.setCombinerClass(TopK.logTopKReducer.class);
        job2.setReducerClass(TopK.logTopKReducer.class);
        job2.setMapOutputKeyClass(IntWritable.class);// map output key setting
        job2.setMapOutputValueClass(Text.class);// map output value setting
        job2.setOutputKeyClass(Text.class);// reduce output key setting
        job2.setOutputValueClass(IntWritable.class);// reduce output value setting
        FileInputFormat.addInputPath(job2, new Path(args[1]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));
        job2.setInputFormatClass(TextInputFormat.class);
        job2.setOutputFormatClass(TextOutputFormat.class);

        // Control panel
        ControlledJob controlledJob1 = new ControlledJob(conf);
        ControlledJob controlledJob2 = new ControlledJob(conf);
        controlledJob1.setJob(job1);
        controlledJob2.setJob(job2);
        controlledJob2.addDependingJob(controlledJob1);

        JobControl jc = new JobControl("cf");
        jc.addJob(controlledJob1);
        jc.addJob(controlledJob2);

        Thread thread = new Thread(jc);
        thread.start();

        // If job finished, print information
        while (true) {
            if (jc.allFinished()) {
                System.out.println(jc.getSuccessfulJobList());
                jc.stop();
                break;
            }
        }

    }
}

