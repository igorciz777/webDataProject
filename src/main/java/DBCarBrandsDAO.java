import java.sql.Connection;
import java.sql.ResultSet;

public class DBCarBrandsDAO {
    private Connection conn;
    DBCarBrandsDAO(){conn = DBConnector.connect();}

    private void createBrand(ResultSet resultSet){

    }
    public void getBrand(int id_brand){

    }
    public void getBrand(String brand_name){

    }
}
