package controllers;

import java.net.UnknownHostException;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import modelMongo.User;
import play.mvc.Controller;
import play.mvc.Result;
import utils.MongodbConnectionWithAuthentication;

public class SignUpTestWithMongodbWithouMorphia extends Controller{

	MongodbConnectionWithAuthentication mongodb = new MongodbConnectionWithAuthentication();
	public Result saveUserInMongo(){
		String email = request().body().asFormUrlEncoded().get("emailId")[0];
		String password = request().body().asFormUrlEncoded().get("password")[0];
		String firstName = request().body().asFormUrlEncoded().get("firstName")[0];
		String secondName = request().body().asFormUrlEncoded().get("lastName")[0];
		User user = new User(firstName,secondName,email,password);
		Gson userjson = new Gson();
		String userjsonstring = userjson.toJson(user);
		/************The Above Part is converting the form content into JSON String which we will
		 *get from the front end****/
		/*
		 * Save the json string in mongo db 
		 * 
		 */
		saveuserInfoinMongo(userjsonstring);
		return ok(userjsonstring);
	}
	
	
	public void saveuserInfoinMongo(String jsonstring){
		DB db = null;
		DBObject patientInfo = (DBObject)JSON.parse(jsonstring);
		try {
			db = mongodb.mongodbconnectionwith();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		db.getCollection("PatientInfo").save(patientInfo);
	}
}
