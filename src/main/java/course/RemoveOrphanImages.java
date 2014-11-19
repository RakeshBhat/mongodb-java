package course;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class RemoveOrphanImages {
	
	static String DATABASE = "photosharing";
	static String IMAGES_COLLECTION = "images";
	static String ALBUM_COLLECTION = "albums";

	public static void main(String[] args) throws UnknownHostException {

		MongoClient client = new MongoClient(new ServerAddress("localhost"));

		DB database = client.getDB(DATABASE);
		DBCollection images = database.getCollection(IMAGES_COLLECTION);
		DBCollection albums = database.getCollection(ALBUM_COLLECTION);
		ArrayList<Object> imagesFoundInAlbum = new ArrayList<Object>();
		ArrayList<Object> imageIds = new ArrayList<Object>();
		
		
		DBCursor imgCur = images.find().sort(new BasicDBObject("_id", "1"));

		if (imgCur.hasNext()) {
			while (imgCur.hasNext()) {
				BasicDBObject obj = (BasicDBObject) imgCur.next();
				Object id = obj.get("_id");
				imageIds.add(id);
			}
		}

		DBCursor albCur = albums.find();

		if (albCur.hasNext()) {
			// System.out.println(" IMG has Next ");
			while (albCur.hasNext()) {
				albCur.next();
				// System.out.println(" IMG OBJ Curr: "+ imgCur.toString());
				DBObject obj = albCur.curr();
				if (obj != null) {
					// System.out.println(" IMG OBJ is not NULL "+
					// obj.toString());
					Object id = obj.get("_id");
					imagesFoundInAlbum.add(id);
				}
			}
		}
		
		
		//DBCursor imgCur2 = images.find(new BasicDBObject("images" , id));
		
		if( ! albCur.hasNext()){
			//images.remove(new BasicDBObject("_id" , id));
			//System.out.println(" Removing ID : "+ id);
		}

		
	}//main

}
