package DBQueries;

import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import sun.jvm.hotspot.runtime.BasicObjectLock;

import javax.print.Doc;

import static DBMoviesBuilder.DBMoviesBuilder.getDocument;
import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.expr;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.orderBy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBQueries {

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

    public static void main(String[] args) throws FileNotFoundException {

        // Prepare documents
        String dbName = "mydb";

        // Connecting with Mongodb server
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Mongodb connection
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> ratings = database.getCollection("ratings");
        MongoCollection<Document> tags = database.getCollection("tags");
        MongoCollection<Document> movies = database.getCollection("movies");

        // 4) Write 3 different queries of your choice to demonstrate that your data storage is working.

        // 4-1) Show each movie’s information and the tags.
        List<Variable<?>> variables = Arrays.asList(
                new Variable<>("movieid", "$MovieID"));

        List<Bson> pipeline = Arrays.asList(
                Aggregates.match(expr(new Document("$and",
                        Arrays.asList((new Document("$eq", Arrays.asList("$MovieID", "$$movieid"))))))),
                project(fields(include("Tag"), excludeId())));

        AggregateIterable<Document> a = movies.aggregate(Arrays.asList(
                Aggregates.lookup("tags", variables, pipeline, "movietags")
        ));

        printResult(a);


        // 4-2) Show all movie information evaluated by person with Userid of 1024

        AggregateIterable<Document> queryOne = tags.aggregate(Arrays.asList(
                //Use lookup to combine "tags" collection and "movies" collection
                Aggregates.lookup("movies", "MovieID", "MovieID", "movieInfo"),
                //Use match to select person with Userid of 1024
                Aggregates.match(Filters.eq("UserID", 1024)),
                //Use project to filter useful information and output.
                //In this selection, only reserve the attributes "UserID", "MovieID" in "tags", and attributes "Title", "Genres" in "movies"
                Aggregates.project(Projections.fields(Projections.include("UserID", "MovieID", "movieInfo.Title", "movieInfo.Genres"), Projections.excludeId()))
        ));

        printResult(queryOne);


        // 4.3) Show all movies with four-or-above average rating.
        AggregateIterable<Document> queryFourPointThree = ratings.aggregate(Arrays.asList(
                Aggregates.group("$MovieID", avg("AverageRating", "$Rating")),
                //Find average rating of each movie.

                Aggregates.match(Filters.gte("AverageRating" , 4)),
                //Find movies with four-or-above average rating from AverageRating created above.

                Aggregates.sort(orderBy(ascending("AverageRating")))
                //The result will be sorted by ascending scores.
        ));
        printResult(queryFourPointThree);

    }
}






