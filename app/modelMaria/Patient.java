package modelMaria;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * This is the patient entity in Mariadb
 */
@Entity
public class Patient {
@Id
public int acId;
public int patientId;
public String emailId;
public String password;
public boolean isActive;
public Patient(){
	
}

public Patient(int acId,int patientId,String emailId,String password,boolean isActive){
	this.acId=acId;
	this.patientId = patientId;
	this.emailId = emailId;
	this.password = password;
	this.isActive = isActive;
}
public int getAcId() {
	return acId;
}
public void setAcId(int acId) {
	this.acId = acId;
}
public int getPatientId() {
	return patientId;
}
public void setPatientId(int patientId) {
	this.patientId = patientId;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public boolean isActive() {
	return isActive;
}
public void setActive(boolean isActive) {
	this.isActive = isActive;
}

}
