package model;

public class User {

	private int recruitment_id;
	private String fullname;
	private String phone_number;
	private String nrc_number;
	private String address;
	private String marital_status;
	private String gender;
	private String position;
	private String upload_resume;
	private String education_background;
	private String status;
	private int user_id;
	private String office_branch;
	private String admin_comment;
	private String email;

	public User(int rcID, String fullname, String phone_number, String nrc_number, String address,
			String marital_status, String gender, String position, String upload_resume, String education_background,
			String status, int user_id, String office_branch, String admin_comment, String email) {
		this.recruitment_id = rcID;
		this.fullname = fullname;
		this.phone_number = phone_number;
		this.nrc_number = nrc_number;
		this.address = address;
		this.marital_status = marital_status;
		this.gender = gender;
		this.position = position;
		this.upload_resume = upload_resume;
		this.education_background = education_background;
		this.status = status;
		this.user_id = user_id;
		this.office_branch = office_branch;
		this.admin_comment = admin_comment;
		this.email = email;
	}

	public int getRecruitment_id() {
		return recruitment_id;
	}

	public void setRecruitment_id(int recruitment_id) {
		this.recruitment_id = recruitment_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getNrc_number() {
		return nrc_number;
	}

	public void setNrc_number(String nrc_number) {
		this.nrc_number = nrc_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUpload_resume() {
		return upload_resume;
	}

	public void setUpload_resume(String upload_resume) {
		this.upload_resume = upload_resume;
	}

	public String getEducation_background() {
		return education_background;
	}

	public void setEducation_background(String education_background) {
		this.education_background = education_background;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOffice_branch() {
		return office_branch;
	}

	public void setOffice_branch(String office_branch) {
		this.office_branch = office_branch;
	}

	public String getAdmin_comment() {
		return admin_comment;
	}

	public void setAdmin_comment(String admin_comment) {
		this.admin_comment = admin_comment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
