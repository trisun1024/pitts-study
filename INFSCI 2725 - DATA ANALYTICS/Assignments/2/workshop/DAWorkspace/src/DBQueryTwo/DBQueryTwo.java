package DBQueryTwo;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static DBMoviesBuilder.DBMoviesBuilder.getDocument;
import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.push;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class DBQueryTwo {

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
        MongoCollection<Document> ratings = database.getCollection("test");

        //2) Write a query that finds users who are similar to a given user (target user),
        // the id of the target user is an input parameter.
        // Users are similar to the target user if they rate the same movies.

        // Please input a target UserID for searching suitable one
        //Scanner in = new Scanner(System.in);
        //int targetUser = in.nextInt();
        Double userID = 1.0;

        // Step 1. Find total number of user
        //db.ratings.aggregate([
        //     {
        //       $group:
        //         {
        //			_id: "$MovieID",
        //           max: { $max: "$UserID" }
        //         }
        //     }
        //])

        AggregateIterable<Document> tableOne = ratings.aggregate(Arrays.asList(
                Aggregates.project(fields(include("Genres", "MovieID"), excludeId())
                )));
        printResult(tableOne);

        // Step 2. Create a temporary table containing relation table between userid and rating movies
        //db.ratings.aggregate([
        //    { $group: {
        //        _id: "$UserID",
        //        count: { $sum: 1 },
        //        Movies: {$push: "$MovieID"}
        //    }}
        //])

        AggregateIterable<Document> tableTwo = ratings.aggregate(Arrays.asList(
                Aggregates.group("$UserID",
                        Accumulators.sum("NumberOfMovies", 1),
                        Accumulators.push("Movies", "$MovieID"))
                ));

        MongoCursor<Document> tableTwoIter = tableTwo.iterator();
        MongoCollection<Document> tempUser = database.getCollection("tempuser");

        while (tableTwoIter.hasNext()) {
            Document doc = tableTwoIter.next();
            tempUser.insertOne(doc);
        }

        // Step 3. Use lookup function find our match
        //db.tempuser.aggregate([
        //   {
        //      $lookup:
        //         {
        //           from: "tempuser",
        //           let: { numberOfMovies: "$numberOfMovies", movies: "$Movies" },
        //           pipeline: [
        //              { $match:
        //                 { $expr:
        //                    { $and:
        //                       [
        //                         { $eq: [ "$Movies",  "$$movies" ] },
        //                         { $eq: [ "$numberOfMovies",  "$$numberOfMovies" ] }
        //                       ]
        //                    }
        //                 }
        //              }
        //           ],
        //           as: "moviematch"
        //         }
        //    },
        //    { $match: { "moviematch._id": { $eq: 1} }},
        //    { $match: { "_id": { $ne: 1} }},
        //    { $project: { moviematch: 0}}
        //]).pretty()


        AggregateIterable<Document> out = tempUser.aggregate(Arrays.asList(
                lookup("tempUser", "Movies", "Movies", "moviematch"),
                Aggregates.match(Filters.ne("_id", userID))
        ));

        // Java is not prefectly supported to MongoDB, This impact our result with a null. But MongoDB shell works fine

        printResult(out);


        //in.close();

    }
}
