package modelMongo;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class PatientInfo {
@Id
public String acId;
public String firstname;
public String lastname;
public String getAcId() {
	return acId;
}
public void setAcId(String acId) {
	this.acId = acId;
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

}
