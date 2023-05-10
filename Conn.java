
import java.sql.*;

public class Conn {
    static Connection  con ;
    Connection c;
    Statement s;
    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/product-db","root","Shit@222");
            s =c.createStatement();

        }catch(Exception e){
            System.out.println("database error" + e);
        }
    }
}