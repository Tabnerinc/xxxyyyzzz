package controllers;
import modelMaria.Users;
import modelMongo.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.MariadbConn;
import services.MongodbConnection;
public class SignuPageController extends Controller{
/*
 * This method inserts the userdetails in the mongodb and returns the registered with
 */
	MariadbConn maria=new MariadbConn();
	MongodbConnection mongo = new MongodbConnection("127.0.0.1", 27017);
	@Transactional
	public Result InsertUserDetailsAndGetId(String username,String password, String gender,Integer age){
		User user = mongo.saveUser(username, password, gender, age);
		System.out.println(user.getId());
		/*
		 * Here Goes the Mariadb Insertion code
		 */
		saveUserInMaria(user);
		return ok(user.getId());
		
	}

	@Transactional(readOnly=true)
	public Result getUserList()
	{
    	return ok(Json.toJson(maria.getUserList()));
	}
	
	/*
	 * Here we can define a Maria db insertion method to use it in the controller
	 * The method saveUserInMaria will save the user with the Id of the object saved in mongodb
	 * The Id field in mariadb will represent document id in the mongo db
	 */
	@Transactional
	public void saveUserInMaria(User user)
	{
		JPA.em().persist(new Users(user.getId(),user.getUsername(),user.getPassword(),user.getGender(), user.getAge()));
	}
	
	
	/*
	 * Save the Json Data from the user signup form
	 */
   public Result saveUserasJsonMongo(){
	   /*
	    * The formdata is resolved into string from the request body , request is a implicit object
	    * of play. as the request data is labeled with formdata in the ajax the data is resolved as 
	    * formdata and the data string is only one and the formurlencoded will return map we are getting
	    * the first element with the label "formdata"
	    */
	  String jsonstring =  request().body().asFormUrlEncoded().get("formdata")[0];
	  mongo.saveUserasJson(jsonstring);
	  maria.saveUserinMariaAsJson(jsonstring);
	  System.out.println(jsonstring);
	  //let us redirect the page to login after signup
	  return redirect("/signin");
   }
}
