package com.sample.test.impl.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.sample.test.common.Bird;
import com.sample.test.common.BirdService;

public class BirdServiceMongoImpl implements BirdService{
	
	
	private static volatile BirdService birdServiceImpl;
	
	private BirdServiceMongoImpl(){
         
	}
	
	private DBCollection connectAndGetCollection() {
		
			try{
				 MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		         // Now connect to your databases
		         DB db = mongoClient.getDB( "test" );
		         System.out.println("Connect to database successfully");
		         //boolean auth = db.authenticate(myUserName, myPassword);
		         //System.out.println("Authentication: "+auth);
//		         DBCollection birdCol = db.createCollection("birdCol", null);
//		         System.out.println("Collection created successfully");
		         
		         DBCollection birdCol = db.getCollection("birdCol");
		         System.out.println("Collection birdCol selected successfully");
		         return birdCol;
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
	}
	
	public static synchronized BirdService getInstance(){
		if(birdServiceImpl==null){
			birdServiceImpl=new BirdServiceMongoImpl();
		}
		return birdServiceImpl;
	}

	@Override
	public List<String> getBirdList() {
		List<String> birdList = new ArrayList<>();
		DBCollection birdCol=connectAndGetCollection();
		DBCursor cursor = birdCol.find();
        int i = 1;
        while (cursor.hasNext()) { 
        	DBObject s= cursor.next();
        	System.out.println(s.toString());
        	if(s.containsField("visible")){
        		boolean vis=(boolean)s.get("visible");
        		if(vis){
        			 ObjectId id=(ObjectId)s.get("_id");
        			birdList.add(id.toString()); 
        		}
        	}
           i++;
        }
		
		return birdList;
	}
	@Override
	public String getBirdDetails(String birdID) {
		DBCollection birdCol=connectAndGetCollection();
		DBObject o =findDocumentById(birdID, birdCol);
		return o.toString();
	}
	
	public boolean removeBird(String birdId){
		DBCollection birdCol=connectAndGetCollection();
		DBObject o =findDocumentById(birdId, birdCol);
		if(o!=null){
			birdCol.remove(o);
			return true;
		}else{
			return false;
		}
	}
	
	public String addBird(Bird b){
		try{
			DBCollection birdCol=connectAndGetCollection();
		
			DBObject dbObject = (DBObject)JSON.parse(createJson(b));
			birdCol.insert(dbObject);
			
			ObjectId id=(ObjectId)dbObject.get("_id");
			return findDocumentById(id.toString(),birdCol).toString();
			
		}catch(Exception e){
			return null;
		}
		
	}
	
	
	public DBObject findDocumentById(String id, DBCollection col) {
	    BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id));
	    DBObject dbObj = col.findOne(query);
	    return dbObj;
	}
	
	private String createJson(Bird b){
		Gson gson = new GsonBuilder().create();
		return gson.toJson(b);
	}
	
}
