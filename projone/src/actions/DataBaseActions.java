package actions;
import java.sql.*;

public class DataBaseActions {

	// https://www.javatpoint.com/example-to-connect-to-the-oracle-database
	
	public static Connection con;
	public static void Connect() throws Exception
	{

		//try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");

			Statement stmt=con.createStatement();

			ResultSet rs=stmt.executeQuery("select * from emp");
			while(rs.next())
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

			con.close();
		//}
		//catch(Exception e){
		//	con.close();
		//}		
	}
}
