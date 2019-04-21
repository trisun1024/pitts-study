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

public class Q1Q2Combination {

    private static String inputFile;
    private static String outputFile;
    private static String midFile;

    private static String getMatchLog(String s) {
        String str = "";
        String[] strQ = s.split("\"");
        if (strQ.length > 1) {
            String strPath = strQ[1];
            String[] strS = strPath.split(" ");
            if (strS.length > 1) {
                str = strS[1];
            }
        }
        return str;
    }

    private static JavaPairRDD<String, Integer> stepOne() {
        // Configuration
        SparkConf sparkConf = new SparkConf().setAppName("CCmini-2 Part-3 Q1Q2");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);

        // Import data
        JavaRDD<String> input = sc.textFile(inputFile).cache();

        // Split by space
        JavaRDD<String> term = input.flatMap(s -> Arrays.asList(getMatchLog(s)).iterator());
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
        sorts.repartition(1).saveAsTextFile(midFile);
        return sorts;
    }

    public static void main(String[] args) throws IOException {

        inputFile = "access_log"; // Should be some file on your system
        midFile = "hw2/output/part3/q1q2mid";
        outputFile = "hw2/output/part3/q1q2res";

        // File check, make sure both input and output path is okay.
        Configuration fileConf = new Configuration();
        FileSystem fs = FileSystem.get(fileConf);
        if (!fs.exists(new Path(inputFile))) {
            System.out.println("File don't exist, please check your path!");
        }
        if (fs.exists(new Path(outputFile)) || fs.exists(new Path(midFile))) {
            fs.delete(new Path(midFile));
            fs.delete(new Path(outputFile));
        }

        // Count start time
        long startTime = System.currentTimeMillis();
        JavaPairRDD<String, Integer> out = stepOne().cache();
        // Get url matchup
        JavaPairRDD<String, Integer> res1 = out.filter(x -> x._1.contains("/assets/img/loading.gif"));
        JavaPairRDD<String, Integer> res2 = out.filter(x -> x._1.contains("/assets/js/lightbox.js"));

        res1.saveAsTextFile(outputFile + "1");
        res2.saveAsTextFile(outputFile + "2");
        // Count end time
        long endTime = System.currentTimeMillis();
        // Time performance
        long totalTime = endTime - startTime;
        long totalTime2 = totalTime / 1000;
        System.out.println("---------------------------------------");
        System.out.println("Timetaken(in seconds): " + totalTime2);

        List<Tuple2<String, Integer>> list1 = res1.take(10);
        for (int i = 0; i < list1.size(); i++) {

            System.out.println(list1.get(i) + "\n");
        }
        List<Tuple2<String, Integer>> list2 = res2.take(10);
        for (int i = 0; i < list2.size(); i++) {

            System.out.println(list2.get(i) + "\n");
        }

    }
}
