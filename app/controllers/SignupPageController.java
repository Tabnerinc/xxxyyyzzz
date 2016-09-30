package controllers;
import modelMongo.UserasJson;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.MariadbConn;
import services.MongodbConnection;
// SignUp Controller - SignUp Controller for serving the request from the SignupPage
public class SignupPageController extends Controller{
/*
 * Mariadb Connection and Mongodb Connection are defined in the Services Package
 */
	MariadbConn maria=new MariadbConn();
	MongodbConnection mongo = new MongodbConnection("127.0.0.1", 27017);

	/*
	 * Save the Json Data from the user signup form
	 * First the saveUserasJsoninMongoAndMaria ajax called by the signup request 
	 * Then the request object is sent with a json string from the fromtend, 
	 * The json string is resolved into the entity classes both in maria and 
	 * mongo db, in our cases the enity classes are Users for mariadba nd UserasJson
	 * for Mongodb
	 * 
	 * Both the Objects are saved by calling the methods in the service classes
	 */
	@Transactional
   public Result saveUserasJsoninMongoandMaria(){
	   /*
	    * The formdata is resolved into string from the request body , request is a implicit object
	    * of play. as the request data is labeled with formdata in the ajax the data is resolved as 
	    * formdata and the data string is only one and the formurlencoded will return map we are getting
	    * the first element with the label "formdata"
	    */
	  String jsonstring =  request().body().asFormUrlEncoded().get("formdata")[0];
	 UserasJson user = mongo.saveUserasJson(jsonstring);
	  maria.saveUserinMariaAsJson(jsonstring,user.getId());
	  System.out.println(jsonstring);
	  //let us redirect the page to login after signup[this has a issue when signing in]
	  return redirect("/signin");
   }
}
