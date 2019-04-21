package DBQueryThree;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static DBMoviesBuilder.DBMoviesBuilder.getDocument;
import static com.mongodb.client.model.Aggregates.sortByCount;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

public class DBQueryThree {

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

        //3) Write a query that finds to number of movies in each genre.
        //
        //db.movies.aggregate([
        //    { $project : { genre : { $split: ["$Genres", "|"] }, MovieID: 1} },
        //    { $unwind : "$genre" },
        //    { $group: { _id: "$genre", numberOfMovies: { $sum: 1 } } }
        //]).pretty()

        // This is how I get question 3 result in java

        MongoCollection<Document> movies = database.getCollection("movies");

        AggregateIterable<Document> queryThreeExtract = movies.aggregate(Arrays.asList(
                Aggregates.project(fields(include("Genres", "MovieID"), excludeId())
                )));

        MongoCollection<Document> tempDrop = database.getCollection("tempGenre");
        tempDrop.drop();
        MongoCollection<Document> tempGenre = database.getCollection("tempGenre");

        BasicDBObject doc = new BasicDBObject();
        MongoCursor<Document> cursor = queryThreeExtract.iterator();

        while (cursor.hasNext()) {
            Document item_doc = cursor.next();

            String genreBeforeSep = item_doc.getString("Genres");
            int movieID = item_doc.getInteger("MovieID");

            String[] arrayToSplit = genreBeforeSep.split("\\|");

            for (int i = 0; i < arrayToSplit.length; i++) {
                System.out.println(arrayToSplit[i]);
                doc.append("MovieID", movieID);
                doc.append("Genres", arrayToSplit[i]);
            }
            Document genreNew = getDocument(doc);
            tempGenre.insertOne(genreNew);
        }


        AggregateIterable<Document> QueryThreeFinal = tempGenre.aggregate(Arrays.asList(
                sortByCount("$Genres")
        ));

        printResult(QueryThreeFinal);

    }
}
