package controllers;

import modelMaria.Users;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import services.MariadbConn;

public class MariaDBController extends Controller{
	
	MariadbConn m=new MariadbConn();
	
	@Transactional
	public Users signInValidate(String username,String password)
	{
		Users users = m.getUserInf(username, password);
		
		return users; 
	}

}
