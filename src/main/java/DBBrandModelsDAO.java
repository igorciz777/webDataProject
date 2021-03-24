import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBBrandModelsDAO {
    private Connection conn;
    DBBrandModelsDAO(){conn = DBConnector.connect();}

    public void insertModel(int id_brand, String model_name){
        try{
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO car_models(id_brand,model_name,date_of_record) VALUES (?,?,?);");
            stmt.setInt(1,id_brand);
            stmt.setString(2,model_name);
            stmt.setTimestamp(3,new CurrentTimestamp().getTimestamp());
            stmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void printAllModels(){
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM car_models");
            ResultSet resultSet = stmt.executeQuery();
            System.out.println("ID  /   Model name");
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id_model") + " " + resultSet.getString("model_name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void printByID(int id){
        try{
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM car_models WHERE id_brand = ?");
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            System.out.println("ID  /   Model name");
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id_model") + " " + resultSet.getString("model_name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteAllModels(){
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM car_models");
            PreparedStatement stmt2 = conn.prepareStatement("ALTER TABLE car_models AUTO_INCREMENT = 1;");
            stmt.execute();
            stmt2.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
