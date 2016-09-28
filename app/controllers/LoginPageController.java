package controllers;
import javax.persistence.Query;

import modelMaria.Users;
import modelMongo.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.MariadbConn;
import services.MongodbConnection;
public class LoginPageController extends Controller{
/*
 * This method inserts the userdetails in the mongodb and returns the registered with
 */
	MariadbConn service=new MariadbConn();
	@Transactional
	public Result InsertUserDetailsAndGetId(String username,String password, String gender,Integer age){
		MongodbConnection mongo = new MongodbConnection("127.0.0.1", 27017);
		User user = mongo.saveUser(username, password, gender, age);
		System.out.println(user.getId());
		/*
		 * Here Goes the Mariadb Insertion code
		 */
		saveUser(user);
		return ok(user.getId());
		
	}
	
	
	/*
	 * Here we can define a Maria db insertion method to use it in the controller
	 */
	@Transactional(readOnly=true)
	public Result getUserList()
	{
	
    	return ok(Json.toJson(service.getUserList()));
		
	}
	
	@Transactional
	public void saveUser(User user)
	{
		
		JPA.em().persist(new Users(user.getId(),user.getUsername(),user.getPassword(),user.getGender(), user.getAge()));
	}
	
   
}
