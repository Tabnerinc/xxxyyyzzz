package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import modelMaria.Users;
import modelMongo.UserasJson;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.MariadbConn;
import services.MongodbConnection;
public class MongoController extends Controller {

	MongodbConnection mongo = new MongodbConnection("127.0.0.1", 27017);
  public Result signinvalidation(String username,String password)
  {  
		//String username = request().body().asFormUrlEncoded().get("formdata")[0];

   UserasJson user=mongo.findUserbyUsernameAndPassword(username,password);
   if(user!=null)
    {
    	return ok(Json.toJson(user));
    }
    
    
	//return ok(username);
	  return null;
  }

}
