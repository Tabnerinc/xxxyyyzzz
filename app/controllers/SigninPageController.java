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
	MongodbConnection mongo = new MongodbConnection("127.0.0.1", 27017);
	MariadbConn maria = new MariadbConn();
	@Transactional
	public Result signinvalidate(){
		String signinformdata = request().body().asFormUrlEncoded().get("formdata")[0];
		Gson gson = new Gson();
		UserasJson user = gson.fromJson(signinformdata,UserasJson.class);
		String username = user.getUsername();
		String password = user.getPassword();
		System.out.println("==========Mongo=============");
		
		System.out.println(username+" "+password);
		user = mongo.findUserbyUsernameAndPassword(username, password);
		
		Users users = maria.getUserInf(username, password);
		System.out.println(users.getId());
		return ok("validated "+ user.getGender()+ user.getAge());
	}
}
