import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBUserDAO {
    private Connection conn;
    DBUserDAO(){conn = DBConnector.connect();}

    public boolean Login(String login, String password){
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM car_db_users WHERE login = ?");
            stmt.setString(1,login);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                if(resultSet.getString("password").equals(password)){
                    System.out.println("Logging in...");
                    return true;
                }else{
                    System.out.println("Incorrect password!");
                    return false;
                }
            }
            else{
                System.out.println("User not found");
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void Register(String login, String password){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO car_db_users(login,password,date_of_registration) VALUES (?,?,?)");
            stmt.setString(1,login);
            stmt.setString(2,password);
            LocalDateTime currentDateTime = LocalDateTime.now();
            currentDateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss"));
            Timestamp timestamp = Timestamp.valueOf(currentDateTime);
            stmt.setTimestamp(3,timestamp);
            stmt.execute();
            System.out.println("User registered!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
