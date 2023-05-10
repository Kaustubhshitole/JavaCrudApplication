
import java.sql.*;

public class DBConnection {

    public static Connection con = null;

    public static Object Connect;

    public static void ConnectDB () {

        System.out.println("--- MySQL JDBC Connection Testing -----");

        try {

            Class.forName("com.mysql.jdbc.Driver"); //loading the driver

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("MySQL JDBC Driver Registered!");

        try {

            //Connect to the database
            con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/test", "root", "password");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (con != null) {
            System.out.println("You made it, take control your database now!");

        } else {
            System.out.println("Failed to make connection!");
        }
    }
} //end class
