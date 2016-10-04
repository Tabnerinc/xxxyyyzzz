package utils;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongodbConnectionWithAuthentication {

	public DB mongodbconnectionwith() throws UnknownHostException{
		String ip = "localhost";
		int port = 27017;
		MongoClient client = new MongoClient(ip,port);
		DB db = client.getDB("H2O");
		return db;
	}
}
