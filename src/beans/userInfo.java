package beans;

public class userInfo{
	
	 public String id;
	 public String fullName;
	 public String email;
	 public String password;
	 public String departement;
	 
	public userInfo(String id, String fullName, String email, String password, String departement) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.departement = departement;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}

	
	
	
}
