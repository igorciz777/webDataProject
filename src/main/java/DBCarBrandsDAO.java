import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DBCarBrandsDAO {
    private Connection conn;
    DBCarBrandsDAO(){conn = DBConnector.connect();}

    public ResultSet selectBrand(String brand_name){
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM car_brands WHERE brand_name = ?");
            stmt.setString(1,brand_name);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                return resultSet;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void insertBrand(String brand_name){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO car_brands(brand_name,date_of_record) VALUES (?,?)");
            stmt.setString(1,brand_name);
            stmt.setTimestamp(2,new CurrentTimestamp().getTimestamp());
            stmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteAllBrands(){
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM car_brands;");
            PreparedStatement stmt2 = conn.prepareStatement("ALTER TABLE car_brands AUTO_INCREMENT = 1;");
            stmt.execute();
            stmt2.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void addModelsToBrand(String brand_name) throws IOException, SQLException {
        int id_brand = selectBrand(brand_name).getInt(1);
        BrandModels models = new BrandModels(id_brand,brand_name);
        models.writeToDatabase();
    }
}
