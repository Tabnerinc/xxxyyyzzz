package controllers;
import model.User;
import play.mvc.Controller;
import play.mvc.Result;
import services.MongodbConnection;
public class LoginPageController extends Controller{
/*
 * This method inserts the userdetails in the mongodb and returns the registered with
 */
	public Result InsertUserDetailsAndGetId(String username,String password, String gender,Integer age){
		MongodbConnection mongo = new MongodbConnection("127.0.0.1", 27017);
		User user = mongo.saveUser(username, password, gender, age);
		System.out.println(user.getId());
		/*
		 * Here Goes the Mariadb Insertion code
		 */
		return ok("Registered"+user.getId());
	}
	
	
	/*
	 * Here we can define a Maria db insertion method to use it in the controller
	 */
}
