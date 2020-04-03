package beans;

public class appointment {
	public patient pat;
	public patient getPat() {
		return pat;
	}

	public void setPat(patient pat) {
		this.pat = pat;
	}

	public int getAppoint_id() {
		return appoint_id;
	}

	public void setAppoint_id(int appoint_id) {
		this.appoint_id = appoint_id;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public int getDep_id() {
		return dep_id;
	}

	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}

	public appointment(int appoint_id, String patient_id, int dep_id,String appoint_date,String reserved_time,boolean paid) {
		
		this.appoint_id = appoint_id;
		this.patient_id = patient_id;
		this.dep_id = dep_id;
		this.appoint_date = appoint_date;
		this.paid = paid;
		this.book_time = reserved_time;
		
	}
	

	public appointment(int appoint_id, String patient_id, int dep_id,String appoint_date) {
		
		this.appoint_id = appoint_id;
		this.patient_id = patient_id;
		this.dep_id = dep_id;
		this.appoint_date = appoint_date;

		
	}
	
	

	private int appoint_id;
	
	private String patient_id;
	
	private int dep_id;

	private String appoint_date;
	
	public String getAppoint_date() {
		return appoint_date;
	}

	public void setAppoint_date(String appoint_date) {
		this.appoint_date = appoint_date;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getBook_time() {
		return book_time;
	}

	public void setBook_time(String book_time) {
		this.book_time = book_time;
	}

	private boolean paid;
	
	private String book_time;
	
}
