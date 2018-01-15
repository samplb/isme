package convertDB;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
	private MongoClient mongoConnection;
	
	public void connect() throws Exception {
		mongoConnection = new MongoClient( "localhost" , 27017 );
	}
	public void disconnect() throws Exception {
		    if (null != mongoConnection) {
		    	mongoConnection.close();
		    }	
	}
	public MongoDatabase createDatabase(String databaseName) throws Exception {
		return mongoConnection.getDatabase(databaseName);
	}
	public void dropDatabase(MongoDatabase x) throws Exception {
		x.drop();
	}
	public MongoClient getConnection() {
		return this.mongoConnection;
	}
	
}
