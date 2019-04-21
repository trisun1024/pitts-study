package Q2;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Q2Task {

    public static void main(String[] args) throws IOException {

        String inputFile = "user_artists.dat"; // Should be some file on your system
        String outputFile = "hw2/output/q2res";

        // File check, make sure both input and output path is okay.
        Configuration fileConf = new Configuration();
        FileSystem fs = FileSystem.get(fileConf);
        if (!fs.exists(new Path(inputFile))) {
            System.out.println("File don't exist, please check your path!");
        }
        if (fs.exists(new Path(outputFile))) {
            fs.delete(new Path(outputFile));
        }


        // Configuration
        SparkConf sparkConf = new SparkConf().setAppName("CCMini2Q2");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        // Import data
        JavaRDD<String> input = sc.textFile(inputFile);

        // Split by space
        JavaRDD<String> term = input.flatMap(s -> Arrays.asList(s.split("\t")[1]).iterator());
        // Match pairs
        JavaPairRDD<String, Integer> pairs = term.mapToPair(s -> new Tuple2(s, 1)); //
        // Reduce similar ones
        JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b);
        // Sort data
        JavaPairRDD<String, Integer> sorts = counts
                .repartition(1)
                .mapToPair((row)-> new Tuple2<>(row._2,row._1))
                .sortByKey(false)
                .mapToPair((row)-> new Tuple2<>(row._2,row._1));

        // Save data into output file
        sorts.saveAsTextFile(outputFile);
    }
}
