import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBCarBrandsDAO {
    private Connection conn;
    DBCarBrandsDAO(){conn = DBConnector.connect();}

    public void insertBrand(String brand_name){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO car_brands(brand_name,date_of_record) VALUES (?,?)");
            stmt.setString(1,brand_name);
            LocalDateTime currentDateTime = LocalDateTime.now();
            currentDateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss"));
            Timestamp timestamp = Timestamp.valueOf(currentDateTime);
            stmt.setTimestamp(2,timestamp);
            stmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
