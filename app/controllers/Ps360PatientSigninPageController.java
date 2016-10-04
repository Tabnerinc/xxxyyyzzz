package controllers;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.inject.Inject;

import modelMaria.Patient;
import modelMaria.Users;
import modelMongo.User;
import play.Configuration;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import utils.MariadbConn;
import utils.MongodbConnection;

public class Ps360PatientSigninPageController extends Controller{
	
	/*
	 * Mongodb connection and Mariadb connection Both the connections are written
	 * in the services package
	 */
	MongodbConnection mongo = new MongodbConnection("10.10.5.214",27289);
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
	@Inject
	private Configuration configuration;
	@Transactional
	public Result signinvalidate(){
		//console out
		
		System.out.println(configuration.getString("db.default.password"));
		Map<String, String[]> formdata = request().body().asFormUrlEncoded();
		Set<String> keys= formdata.keySet();
		Iterator<String> it = keys.iterator();
		String username = formdata.get(it.next())[0];
		String password = formdata.get(it.next())[0];
		Result result = finduser(username,password);
		return result;
	}
	
	@Transactional
	public Result finduser(String username,String password){
		/*
		 *  So the String sigininform data which is recieved from the http request
		 *  is turned into UserasJson Object which is a entity representer of the 
		 *  Mongo database entity 
		 */
		
		/*
		 *  getting the username and password from the form data after turning it into UserasJson
		 *  Entity Object- The Age and Gender will be null here 
		 */
		
		System.out.println("==========Maria=============");
		
		/*
		 * The Maria getUserInf is passed with username and password details and the username 
		 * and password are matched with the users in the mariadb and if there is user the user will
		 * be returned which is represented by the Entity Object of Maria Users as "Users".
		 * 
		 */
		Patient users=null;
		try {
			users = maria.getUserInf(username, password);
			System.out.println(username+password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(users==null){
			return redirect("/signin");
		}
		else
		return redirect("/home");
	}
}
