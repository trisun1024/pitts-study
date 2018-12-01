package Query4_1;

import com.mongodb.AggregationOutput;
import static com.mongodb.client.model.Filters.expr;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.QueryOperators;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Variable;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.orderBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Query4 {

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
        String dbName = "myDB";
        String collectionName1 = "movies";
        String collectionName2 = "tags";

        // Connecting with Mongodb server
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Mongodb connection
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> movies = database.getCollection(collectionName1);
        MongoCollection<Document> tags = database.getCollection(collectionName2);

        // Print results
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
       
/*         BasicDBObject query = new BasicDBObject();
        		 query.append(QueryOperators.AND, 
        		new BasicDBObject[] { new BasicDBObject("MovieID", 4973),
        				             new BasicDBObject("UserID",15)
        		});
        find(query);*/
        
/*        AggregateIterable<Document> query4 = tags.aggregate(Arrays.asList(
                Aggregates.group("$MovieID", avg("AverageRating", "$Rating")),
                Aggregates.sort(orderBy(ascending("_id")))
        ));

        printResult(query4);*/

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

    }
}

/*db.movies.aggregate([
                     {
                        $lookup:
                           {
                             from: "tags",
                             let: { movieid: "$MovieID" },
                             pipeline: [
                                { $match:
                                   { $expr:
                                      { $and:
                                         [
                                           { $eq: [ "$MovieID",  "$$movieid" ] }
                                         ]
                                      }
                                   }
                                },
                                { $project: { _id:0,Tag:1 } }
                             ],
                             as: "movietags"
                           }
                      }
                  ]).pretty()*/