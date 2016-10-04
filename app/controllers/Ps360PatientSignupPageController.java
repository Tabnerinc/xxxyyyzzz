package controllers;
import java.util.Map;

import modelMaria.Patient;
import modelMaria.Users;
import modelMongo.PatientInfo;
import modelMongo.User;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import utils.MariadbConn;
import utils.MongodbConnection;
// SignUp Controller - SignUp Controller for serving the request from the SignupPage
public class Ps360PatientSignupPageController extends Controller{
/*
 * Mariadb Connection and Mongodb Connection are defined in the Services Package
 */
	MariadbConn maria=new MariadbConn();
	MongodbConnection mongo = new MongodbConnection("10.10.5.214",27289);
	public static int patientacId=1;
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
	    * The formdata is rcieved for the following data so we have to resolve the formdata map 
	    * for the following fields
	    *firstName:
         lastName:
		 emailId:
		 password:
	    */
	    Map<String,String[]> formfields =  request().body().asFormUrlEncoded();
	    /*
	     * We have to write the validations for fileds if empty we donnot accept the record.
	     */
	    String firstname= formfields.get("firstName")[0];
	    String lastname= formfields.get("lastName")[0];
	    String emailId= formfields.get("emailId")[0];
	    String password= formfields.get("password")[0];
	    /*
	     *  as this is signup method the isactive will be false
	     *  Mongo will save only emaid and password
	     */
	    String patientid = null;
	    /*
	     * We have to stop saving in both databases if saving in one database fails
	     * -write code for that
	     * Mongo
	     */
	    PatientInfo patientinfo = new PatientInfo();
	    patientinfo.setAcId(patientid);
	    patientinfo.setFirstname(firstname);
	    patientinfo.setLastname(lastname);
	    patientinfo.setAcId(Integer.toString(patientacId));
	   {
	   String mongouserid = mongo.saveUserInMongo(patientinfo);
	   if(mongouserid!=null){
	   Patient patient = new Patient(Integer.parseInt(mongouserid),Integer.parseInt(mongouserid),emailId,password,false);
	   maria.saveUserInMaria(patient);
	   patientacId++;
	   }
	  //let us redirect the page to login after signup[this has a issue when signing in]
	  return redirect("/signin");
	    }
   }
}
