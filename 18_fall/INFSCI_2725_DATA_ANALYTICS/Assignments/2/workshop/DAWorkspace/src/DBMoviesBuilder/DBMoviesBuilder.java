package DBMoviesBuilder;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBMoviesBuilder {


    public static Document getDocument(BasicDBObject doc)
    {
        if(doc == null) return null;
        Map<String, Object> originalMap = doc.toMap();
        Map<String, Object> resultMap = new HashMap<>(doc.size());
        for(Map.Entry<String, Object> entry : originalMap.entrySet())
        {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value == null)
            {
                continue;
            }
            if(value instanceof BasicDBObject)
            {
                value = getDocument((BasicDBObject)value);
            }
            if(value instanceof List<?>)
            {
                List<?> list = (List<?>) value;
                if(list.size() > 0)
                {
                    // check instance of first element
                    Object firstElement = list.get(0);
                    if(firstElement instanceof BasicDBObject)
                    {
                        List<Document> resultList = new ArrayList<>(list.size());
                        for(Object listElement : list)
                        {
                            resultList.add(getDocument((BasicDBObject)listElement));
                        }
                        value = resultList;
                    }
                    else
                    {
                        value = list;
                    }
                }
            }
            resultMap.put(key, value);
        }
        Document result = new Document(resultMap);
        return result;
    }

    public static void main(String[] args) {

        // Prepare documents
        String dbName = "mydb";
        String collectionName = "movies";
        String path = "/Users/pangjing/Desktop/movies.dat";
        List<String> head = new ArrayList<>();
        head.add("MovieID");
        head.add("Title");
        head.add("Genres");

        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        // Mongodb connection
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCollection<Document> coll = database.getCollection(collectionName);

        // Data import
        Scanner sc;
        try {
            File inFile = new File(path);
            FileInputStream inputStream = new FileInputStream(inFile);
            sc = new Scanner(inputStream);
            BasicDBObject doc = new BasicDBObject();
            while (sc.hasNextLine()) {
                String tempString = sc.nextLine();
                String[] split = tempString.split("::");
                for (int i = 0; i < head.size(); i++) {
                    if (i == 0) {
                        doc.append(head.get(i), Integer.parseInt(split[i]));
                    } else {
                        doc.append(head.get(i), split[i]);
                    }
                }
                System.out.println(doc);
                Document docs = getDocument(doc);
                coll.insertOne(docs);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mongoClient.close();

    }
}


