package services;

import java.util.List;

import javax.persistence.Query;

import com.google.gson.Gson;

import modelMaria.Users;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
/*
 * This is the Mariadb Connection service.
 * 
 * 1. Get the UserList of users from the Mariadb 
 * 2. Get the  User info from the mariadb from their username and password.
 * 3. For saving a user in the Mariadb
 *  
 */
public class MariadbConn {

	@SuppressWarnings("unchecked")
	public List<Users> getUserList() {

		Query query = JPA.em().createQuery("select u from Users u", Users.class);
		return query.getResultList();

	}
	/*
	 * The method getUserInf by Username and Password is used to return the user 
	 * with the credentials passed from the signin or signup page
	 * The query is used in Named query which is protected against SQL injection 
	 * The first parameter being the username and the second paramaeter is password
	 * 
	 * we are throwing Exception to handle it in the signin controller, if the mariadb 
	 * returns an exception so if the user is not found and a exception is
	 * 
	 */
	@Transactional
	public Users getUserInf(String username, String password) throws Exception {

		final String usernamequery = "SELECT u FROM Users u WHERE u.username =?1 AND u.password =?2";

		Query userDbObject = JPA.em().createQuery(usernamequery);
		userDbObject.setParameter(1, username);
		userDbObject.setParameter(2, password);

		return (Users) userDbObject.getSingleResult();

	}
	
	/*
	 * The Json String which is recieved in the Signup page is turned into java
	 * object using the GSON and saved to the Database. along with the Object id passed 
	 * from the Mongodb
	 */
	@Transactional 
	public void saveUserinMariaAsJson(String jsonString,String id){
		System.out.println("reaching");
		Gson jsonObject = new Gson();
		Users users = jsonObject.fromJson(jsonString, Users.class);
		users.setId(id);
		JPA.em().persist(users);
	}

}
