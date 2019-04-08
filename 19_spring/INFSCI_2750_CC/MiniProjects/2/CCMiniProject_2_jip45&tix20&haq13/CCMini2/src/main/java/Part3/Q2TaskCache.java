package Part3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Q2TaskCache {

    private static String getUrl(String s) {
        if (s.contains("/assets/js/lightbox.js")) return "/assets/js/lightbox.js";
        return "";
    }

    public static void main(String[] args) throws IOException {

        String inputFile = "access_log"; // Should be some file on your system
        String outputFile = "hw2/output/part3/q2res-cache";

        // File check, make sure both input and output path is okay.
        Configuration fileConf = new Configuration();
        FileSystem fs = FileSystem.get(fileConf);
        if (!fs.exists(new Path(inputFile))) {
            System.out.println("File don't exist, please check your path!");
        }
        if (fs.exists(new Path(outputFile))) {
            fs.delete(new Path(outputFile));
        }

        // Count start time
        long startTime = System.currentTimeMillis();

        // Configuration
        SparkConf sparkConf = new SparkConf().setAppName("CCmini-2 Part-3 Q2");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        // Import data
        JavaRDD<String> input = sc.textFile(inputFile).cache();

        // Split by space
        JavaRDD<String> term = input.flatMap(s -> Arrays.asList(getUrl(s)).iterator());
        // Match pairs
        JavaPairRDD<String, Integer> pairs = term.mapToPair(s -> new Tuple2(s, 1)); //
        // Reduce similar ones
        JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b);
        // Sort data
        JavaPairRDD<String, Integer> sorts = counts
                .repartition(1)
                .mapToPair((row) -> new Tuple2<>(row._2, row._1))
                .sortByKey(false)
                .mapToPair((row) -> new Tuple2<>(row._2, row._1));

        // Save data into output file
        sorts.saveAsTextFile(outputFile);

        // Count end time
        long endTime = System.currentTimeMillis();
        // Time performance
        long totalTime = endTime - startTime;
        long totalTime2 = totalTime / 1000;
        System.out.println("---------------------------------------");
        List<Tuple2<String, Integer>> list = sorts.take(10);
        for(int i=0;i<list.size();i++){

            System.out.println(list.get(i)+"\n");
        }
        System.out.println("Timetaken(in seconds): " + totalTime2);
    }
}
