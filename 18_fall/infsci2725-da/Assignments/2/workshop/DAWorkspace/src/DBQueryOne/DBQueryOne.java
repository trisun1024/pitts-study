package DBQueryOne;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.orderBy;

public class DBQueryOne {

    // Create a printer to print out aggreagate iterable documents
    private static void printResult(AggregateIterable<Document> iterable) {
        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
        System.out.println("------------------------------------------------------");
        System.out.println();
    }

    public static void main(String[] args) {

        // Prepare documents
        String dbName = "mydb";

        // Connecting with Mongodb server
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Mongodb connection
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase(dbName);

        // 1) Write a query that finds average rating of each movie.
        //db.ratings.aggregate( [
        //    { $group: { _id: "$MovieID", avgRating: { $avg: "$Rating" } } }, { $sort : { _id : 1 } }
        //]).pretty()

        // This is how I get question 1 result in java

        MongoCollection<Document> ratings = database.getCollection("ratings");

        AggregateIterable<Document> queryOne = ratings.aggregate(Arrays.asList(
                Aggregates.group("$MovieID", avg("AverageRating", "$Rating")),
                Aggregates.sort(orderBy(ascending("_id")))
        ));

        printResult(queryOne);
    }
}
