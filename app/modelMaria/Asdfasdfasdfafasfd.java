package modelMaria;


import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * This is a Mariadb Entity where the entity id is setup by the same id as
 * the Object id of the record in the Mongodb, so by the id of the record we 
 * can pull the record from the Mongodb or viceversa 
 */
@Entity
public class Asdfasdfasdfafasfd {

	@Id
	public String id;
	public String username;
	public String firstname;
	public String lastname;
	public String password;
	public String gender;
	public int age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Asdfasdfasdfafasfd(String id, String username, String password, String gender, int age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.age = age;
	}

	public Asdfasdfasdfafasfd(String firstname, String lastname, String username, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}

	public Asdfasdfasdfafasfd() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
