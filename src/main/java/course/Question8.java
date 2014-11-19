package course;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


/*
 * No need to create collections, the animals collection was created on the fly
 * Throws below error and creates only 1 document
 * 
 * Exception in thread "main" com.mongodb.MongoException$DuplicateKey: E11000 duplicate key error index: test.animals.$_id_  dup key: { : ObjectId('53c27e467e5554f9084f7c3b') }
at com.mongodb.CommandResult.getException(CommandResult.java:98)
at com.mongodb.CommandResult.throwOnError(CommandResult.java:134)
at com.mongodb.DBTCPConnector._checkWriteError(DBTCPConnector.java:142)
at com.mongodb.DBTCPConnector.say(DBTCPConnector.java:183)
at com.mongodb.DBTCPConnector.say(DBTCPConnector.java:155)
at com.mongodb.DBApiLayer$MyCollection.insert(DBApiLayer.java:270)
at com.mongodb.DBApiLayer$MyCollection.insert(DBApiLayer.java:226)
at com.mongodb.DBCollection.insert(DBCollection.java:75)
at com.mongodb.DBCollection.insert(DBCollection.java:59)
at com.mongodb.DBCollection.insert(DBCollection.java:104)
at course.Question8.main(Question8.java:23)
*/

public class Question8 {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient c =  new MongoClient(new MongoClientURI("mongodb://localhost"));
        DB db = c.getDB("test");
        DBCollection animals = db.getCollection("animals");

        BasicDBObject animal = new BasicDBObject("animal", "monkey");

        //animals.insert(animal);
        animal.removeField("animal"); // removes object ,
        animal.append("animal", "cat");
        animals.insert(animal);
//        animal.removeField("animal");
//        animal.append("animal", "lion");
//        animals.insert(animal);
        
       int TotalAnimals =  animals.find().count();
       System.out.println(" Total Animals: " + TotalAnimals);
	}

}
