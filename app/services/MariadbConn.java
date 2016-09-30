package services;

import java.util.List;

import javax.persistence.Query;

import modelMaria.Users;
import play.db.jpa.JPA;

public class MariadbConn {
	
	
	public List<Users> getUserList()
	{
    	Query query = JPA.em().createQuery("select u from Users u",Users.class);
		return query.getResultList();
		
	}
	
	

}
