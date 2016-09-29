package controllers;

import com.google.gson.Gson;

import modelMongo.UserasJson;
import play.mvc.Controller;
import play.mvc.Result;
import services.MongodbConnection;

public class SigninPageController extends Controller{
	MongodbConnection mongo = new MongodbConnection("127.0.0.1", 27017);
	public Result signinvalidate(){
		String signinformdata = request().body().asFormUrlEncoded().get("formdata")[0];
		Gson gson = new Gson();
		UserasJson user = gson.fromJson(signinformdata,UserasJson.class);
		String userdetails = user.getUsername();
		System.out.println("=======================================");
		System.out.println(userdetails);
		mongo.findUserbyUsername(userdetails);
		return ok("validated"+ " Mr."+userdetails);
	}
}
