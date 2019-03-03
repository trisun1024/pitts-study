package logQ3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeMap;

public class TopK {

    private static final int TOP_K = 1;

    public static class logTopKMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
        TreeMap<Integer, String> rank = new TreeMap<>();

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            if (line.trim().length() > 0 && line.indexOf("\t") != -1) {
                String[] arr = line.split("\t", 2);
                String name = arr[0];
                Integer num = Integer.parseInt(arr[1]);
                rank.put(num, name);
                if (rank.size() > TOP_K) {
                    rank.remove(rank.firstKey());
                }
            }
        }
        @Override
        protected void cleanup(Mapper<LongWritable, Text, IntWritable, Text>.Context context) throws IOException, InterruptedException {
            for (Integer num : rank.keySet()) {
                context.write(new IntWritable(num), new Text(rank.get(num)));
            }
        }
    }

    public static class logTopKReducer extends Reducer<IntWritable, Text, Text, IntWritable> {

        TreeMap<Integer, String> rank = new TreeMap<>();
        @Override
        public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            rank.put(key.get(), values.iterator().next().toString());
            if (rank.size() > TOP_K) {
                rank.remove(rank.firstKey());
            }
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            for (Integer num : rank.keySet()) {
                context.write(new Text(rank.get(num)), new IntWritable(num));
            }
        }
    }

}
