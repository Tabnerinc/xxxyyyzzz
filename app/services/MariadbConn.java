package services;

import java.util.List;

import javax.persistence.Query;

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

}
