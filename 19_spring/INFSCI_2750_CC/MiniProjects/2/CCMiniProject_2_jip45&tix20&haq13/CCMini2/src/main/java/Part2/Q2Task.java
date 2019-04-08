package Part2;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Q2Task {

    public static void main(String[] args) throws IOException {

        String inputFile = "user_artists.dat"; // Should be some file on your system
        String outputFile = "hw2/output/part2/q2res";

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
        SparkConf sparkConf = new SparkConf().setAppName("CCMini-2 Part-2");
        SparkSession spark = SparkSession.builder().config(sparkConf).getOrCreate();

        // Import dataset
        Dataset<Row> artist = spark.read().format("com.databricks.spark.csv").option("delimiter", "\t").option("inferSchema", "true").option("header", "true").load(inputFile);

        // Calculate dataset
        Dataset<Row> lines = artist.groupBy(artist.col("artistID")).sum("weight");
        Dataset<Row> rank = lines.sort(lines.col("sum(weight)").desc());

        // Save data into output file
        rank.rdd().saveAsTextFile(outputFile);
    }
}
