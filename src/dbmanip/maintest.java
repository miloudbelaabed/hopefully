package dbmanip;


import beans.nurse;

public class maintest {

	public static void main(String[] args) {
		
		nurse per = new nurse("","","","","","", 0);
		
		per.setBirth_date("1998-03-23");
		per.setFullname("belkhir reda");
		
		per.setPhone_numb("0123456789");
		
		per.setPerson_id("999555333");
		per.setEmail("belmolred@gmail.com");
		per.setPass("lvloui");
		
		
		if( nurseDB.insertNurse(per,"4") ) {
			
			System.out.println("inserted.");
			
		}else {
			System.out.println("Not Inserted.");
		}
	}

}
