package beans;

public class person {
	
	public person(String fullname, String birth_date, String phone_numb, String person_id) {
		super();
		this.fullname = fullname;
		this.birth_date = birth_date;
		this.phone_numb = phone_numb;
		this.person_id = person_id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getPhone_numb() {
		return phone_numb;
	}
	public void setPhone_numb(String phone_numb) {
		this.phone_numb = phone_numb;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	
	
	private String fullname;	
	private String birth_date;
	private String phone_numb;
	private String person_id;
	
	
	
}
