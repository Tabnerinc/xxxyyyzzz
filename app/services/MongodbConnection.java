package services;

import java.net.UnknownHostException;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.MongoClient;

import modelMongo.User;
import modelMongo.UserasJson;
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
			 mongodatastore = morphia.createDatastore(client,"H2o");
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
	
	
	public void saveUserasJson(String jsonstring){
		/*
		   * Now we have to convert the Json string into the entity which represnets the entity in 
		   * Mongo, That is serializing the string into the UserasJson class
		   */
		Gson gson = new Gson();
		UserasJson userjson = gson.fromJson(jsonstring,UserasJson.class);
		mongodatastore().save(userjson);
	}
	
	
	public void findUserbyUsername(String userid){
	System.out.println(userid);
	List<UserasJson> username = mongodatastore().find(UserasJson.class).filter("username",userid).asList();
	String user = username.get(0).getGender()+""+username.get(0).getAge();
	System.out.println(user);
	}
}
