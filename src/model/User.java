package model;

public class User {
    private int user_id;
    private String email;
    private String fullname;
    private String status;
	public int getUserID() {
		return user_id;
	}
	public void setUserID(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public User(String fullname,String email, String status) {
		super();
		this.email = email;
		this.fullname = fullname;
		this.status = status;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
   
}
