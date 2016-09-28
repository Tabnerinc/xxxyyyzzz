package services;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import modelMongo.User;
/*
 * The mongodata store returns a datastore "h2o" in the localhost 27017
 */
public class MongodbConnection {
	String ip;
	int port;
	public MongodbConnection(String ip,int port){
		this.ip = ip;
		this.port = port;
	}
	public Datastore mongodatastore(){
		MongoClient client;
		Datastore   mongodatastore=null;
		try {
			 client= new MongoClient(this.ip,this.port);
			 Morphia morphia = new Morphia();
			 mongodatastore = morphia.createDatastore(client,"H2O");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mongodatastore;
		
	}
	
	public User saveUser(String username, String password, String gender, int age){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setAge(age);
		user.setGender(gender);
		mongodatastore().save(user);
		return user;
	}
	
}
