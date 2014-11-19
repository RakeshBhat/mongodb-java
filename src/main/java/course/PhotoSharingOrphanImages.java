package course;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class PhotoSharingOrphanImages {
	
	static String DATABASE = "photosharing";
	static String IMAGES_COLLECTION = "images";
	static String ALBUM_COLLECTION = "albums";

	public static void main(String[] args) throws UnknownHostException {

		MongoClient client = new MongoClient(new ServerAddress("localhost"));

		DB database = client.getDB(DATABASE);
		DBCollection images = database.getCollection(IMAGES_COLLECTION);
		DBCollection albums = database.getCollection(ALBUM_COLLECTION);
		
		DBCursor imgCur = images.find().sort(new BasicDBObject("_id", "1"));

		
		if(imgCur.hasNext())
		{
			//System.out.println(" IMG has Next ");
			while(imgCur.hasNext())
			{
				//System.out.println(" IMG OBJ Curr: "+ imgCur.toString());
				DBObject obj = imgCur.next();
				 //imgCur.curr();
				if ( obj != null )
				{
					//System.out.println(" IMG OBJ is not NULL "+ obj.toString());
					Object id = obj.get("_id");
					DBCursor albCur = albums.find(new BasicDBObject("images" , id));
					
					if( ! albCur.hasNext()){
						images.remove(new BasicDBObject("_id" , id));
						//System.out.println(" Removing ID : "+ id);
					}
				}
			}
		}
	}

}
