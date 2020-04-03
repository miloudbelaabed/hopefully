package beans;

public class user extends person{
	

	
	
	public user(String fullname, String birth_date, String phone_numb, String person_id, String email, String pass) {
		super(fullname, birth_date, phone_numb, person_id);
		this.email = email;
		this.pass = pass;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	private String email;
	private String pass;
	
	
	
	
}
