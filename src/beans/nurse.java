package beans;

public class nurse extends user{
	
	public nurse(String fullname, String birth_date, String phone_numb, String person_id, String email, String pass,
			int dep_id) {
		super(fullname, birth_date, phone_numb, person_id, email, pass);
		
		this.dep_id = dep_id;
	}

	public int getDep_id() {
		return dep_id;
	}

	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}

	private int dep_id;
	
	
}
