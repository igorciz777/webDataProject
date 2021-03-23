import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarBrands {
    Document doc;
    ArrayList<String> brandList;
    private DBCarBrandsDAO carBrandsDAO;

    CarBrands(Document doc){
        this.doc = doc;
        this.brandList = setBrands();
        carBrandsDAO = new DBCarBrandsDAO();
    }

    private ArrayList<String> setBrands(){
        Element HtmlElement = doc.getAllElements().first();
        Elements carBrandsElement = HtmlElement.getElementsByClass("col-2 center");
        ArrayList<String> brandList = new ArrayList<>();
        for (Element element : carBrandsElement) {
            brandList.add(element.getAllElements().get(1).attr("title"));
        }
        return brandList;
    }
    public void writeToDatabase() {
        for (String brandName : brandList) {
            carBrandsDAO.insertBrand(brandName);
        }
    }
}
