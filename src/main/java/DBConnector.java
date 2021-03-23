import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String URL = "jdbc:mysql://localhost:3306/car_db";
    private static String USER = "root";

    public static Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL,USER,null);
            System.out.println("Connected to database");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
