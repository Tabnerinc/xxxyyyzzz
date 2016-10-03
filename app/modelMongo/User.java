package modelMongo;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
//This is a Mongo db User enity
@Entity
public class User {
	
@Id
public String id;
public String firstname;
public String lastname;
public String username;
public String password;
public String gender;
public int age;
public User(){
	
}
public User(String firstname,String lastname,String username,String password){
	this.firstname= firstname;
	this.lastname=lastname;
	this.username=username;
	this.password=password;
}
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
