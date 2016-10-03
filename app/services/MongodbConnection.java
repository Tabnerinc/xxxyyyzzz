package services;
import java.net.UnknownHostException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.google.gson.Gson;
import com.mongodb.MongoClient;


import modelMongo.User;
import modelMongo.UserasJson;
/*
 * The MongodbConnection is to provide connection service for the mongodb,
 * The mongodb is defined with a constructor to be able to change the 
 * port and ip .
 * And the the methods have the following functionalities
 * 1. mongodatastore is to return a datastore with the a database in the connection
 * "H2o" defined in the datastore createdatastore method.
 * 2. 
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
			 mongodatastore = morphia.createDatastore(client,"h2otest");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mongodatastore;
	}
		
	
	/*
	 * The Method saveUserasJson will save the user details in the Mongodb 
	 * which accepts the json string of user details and the json string is
	 * converted into Java Object[Here we are representing the Obeject as UserasJson]
	 * 
	 *  We are returning a User so that the id of the user object can be used to save 
	 *  the user details with same id in mariadb
	 */
	public UserasJson saveUserasJson(String jsonstring){
		/*
		   * Now we have to convert the Json string into the entity which represnets the entity in 
		   * Mongo, That is serializing the string into the UserasJson class
		   */
		Gson gson = new Gson();
		UserasJson userjson = gson.fromJson(jsonstring,UserasJson.class);
		mongodatastore().save(userjson);
		return userjson;
	}
	
	
	/* 
	 * The findUserbyUsernameAndpassword is used to find the user details in mongodb 
	 * using username and password. 
	 */
	public UserasJson findUserbyUsernameAndPassword(String username,String password) throws Exception{
	System.out.println(username);
	UserasJson users = mongodatastore().find(UserasJson.class).filter("username",username).filter("password", password).get();
	return users;	
	}
	
	
	/*
	 * Save User model in mongo when the parameters we receive is not a json 
	 * instead if we get paramters also return the object id of the saved object so we can link the 
	 * monog document with the  maria record
	 */
	
	public String saveUserInMongo(User user){
		mongodatastore().save(user);
		
		return user.getId();
	}
	
	public boolean userAlreadyPresentInMongo(String username){
		User useralreadypresent = mongodatastore().find(User.class).filter("username",username).get();
		if(useralreadypresent!=null){
			return true;
		}
		else
			return false;
	}
	/*
	 * find user from mongo from the table other than json format
	 */
	public User findUserbyUsernameAndPasswordinUserCollection(String username,String password) throws Exception{
		System.out.println(username);
		User users = mongodatastore().find(User.class).filter("username",username).filter("password", password).get();
		return users;	
		}
}
