package controllers;

import com.google.gson.Gson;

import modelMaria.Users;
import modelMongo.UserasJson;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.MariadbConn;
import services.MongodbConnection;

public class SigninPageController extends Controller{
	
	/*
	 * Mongodb connection and Mariadb connection Both the connections are written
	 * in the services package
	 */
	MongodbConnection mongo = new MongodbConnection("127.0.0.1", 27017);
	MariadbConn maria = new MariadbConn();
	/*
	 *  Defined transactional as the user will use the JPA entity manager to persist the user details
	 *  in the Maria db  And the siginvalidate is the method used to validate the 
	 *  user using both maria and mongo db
	 *  
	 *  The signinvalidate method will read the user details from the http request object[implicit] and the 
	 *  request object body is reading the form url encode, which is encoded with a json string in the ajax 
	 *  call - the json string is labeled with formdata
	 */
	
	@Transactional
	public Result signinvalidate(){
		String signinformdata = request().body().asFormUrlEncoded().get("formdata")[0];
		//Gson is the google library for making a json string into a Java class
		Gson gson = new Gson();
		/*
		 *  So the String sigininform data which is recieved from the http request
		 *  is turned into UserasJson Object which is a entity representer of the 
		 *  Mongo database entity 
		 */
		
		UserasJson user = gson.fromJson(signinformdata,UserasJson.class);
		/*
		 *  getting the username and password from the form data after turning it into UserasJson
		 *  Entity Object- The Age and Gender will be null here 
		 */
		
		String username = user.getUsername();
		String password = user.getPassword();
  System.out.println("==========Mongo=============");
		System.out.println(username+" "+password);
		/*
		 * the method user findUserByUserNameAndPassword is in the Mongodb Service, the 
		 * Mongo service in the services package which return a UserasJson Object which 
		 * consists of the user details retrieved from the mongodb 
		 */
		try {
			user = mongo.findUserbyUsernameAndPassword(username, password);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("==========Maria=============");
		
		/*
		 * The Maria getUserInf is passed with username and password details and the username 
		 * and password are matched with the users in the mariadb and if there is user the user will
		 * be returned which is represented by the Entity Object of Maria Users as "Users".
		 * 
		 */
		Users users=null;
		try {
			users = maria.getUserInf(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users==null||user==null){
			return ok("error");
		}
		else
		return ok("validated "+ user.getGender()+ user.getAge());
	}
}
