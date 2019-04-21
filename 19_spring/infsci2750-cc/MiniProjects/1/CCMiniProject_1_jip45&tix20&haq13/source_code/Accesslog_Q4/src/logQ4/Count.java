package logQ4;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Count {

    public static class logCountMapper extends Mapper<Object, Text, Text, IntWritable> {
        private IntWritable result = new IntWritable();
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            // Find correct path and numbers of existence.
            String line = value.toString();
            String[] strQ = line.split(" ");
            if (strQ.length > 1) {
                String str = strQ[0];
                word.set(str);
                result.set(1);
                context.write(word, result);
            }
        }
    }


    public static class logCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable numCount : values) {
                count += numCount.get();
            }
            result.set(count);
            context.write(key, result);
        }
    }

}
