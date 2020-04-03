package dbmanip;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBConnect {

    private static String hostName =   "localhost";
    private static String dbName   =   "hopefully";
    private static String dbUser   =   "root";
    private static String dbPass  =     "";


    public static PreparedStatement getStatment(String sql)  {

        java.sql.Connection conn ;
        try {


        	Class.forName("com.mysql.jdbc.Driver");  

            conn = DriverManager.getConnection("jdbc:mysql://"+hostName +"/"+dbName,dbUser,dbPass);

            PreparedStatement stm = conn.prepareStatement(sql);
            
            //stm.setFetchSize(25);
            		
            return stm;
            
            
        } catch (SQLException e) {
        	System.out.println("Not Connected  :: "+e.getMessage());
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
        	System.out.println("Not Connected  :: "+e.getMessage());
		} 


        return null;
    }
    

    
}
