package services;

import java.util.List;

import javax.persistence.Query;

import com.google.gson.Gson;

import modelMaria.Users;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class MariadbConn {

	public List<Users> getUserList() {

		Query query = JPA.em().createQuery("select u from Users u", Users.class);
		return query.getResultList();

	}

	@Transactional
	public Users getUserInf(String username, String password) {

		final String usernamequery = "SELECT u FROM Users u WHERE u.username =?1 AND u.password =?2";

		Query userDbObject = JPA.em().createQuery(usernamequery);
		userDbObject.setParameter(1, username);
		userDbObject.setParameter(2, password);

		return (Users) userDbObject.getSingleResult();

	}
	
	@Transactional 
	public void saveUserinMariaAsJson(String jsonString){
		System.out.println("reaching");
		Gson jsonObject = new Gson();
		Users users = jsonObject.fromJson(jsonString, Users.class);
		JPA.em().persist(users);
		System.out.println("====Maria persisted====="+users.getUsername());
	}

}
