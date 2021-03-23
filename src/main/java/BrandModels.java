import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandModels {
    Document doc;
    int brandId;
    String carBrand;
    ArrayList<String> modelList;
    private DBCarBrandsDAO carBrandsDAO;
    private DBBrandModelsDAO brandModelsDAO;

    BrandModels(int brandId, String carBrand) throws IOException {
        this.carBrand = carBrand;
        this.brandId = brandId;
        this.doc = Jsoup.connect("https://www.cars-data.com/en/" + this.carBrand.toLowerCase().replace(' ', '-')).ignoreContentType(true).get();
        this.modelList = setModelList();
        this.carBrandsDAO = new DBCarBrandsDAO();
        this.brandModelsDAO = new DBBrandModelsDAO();
    }

    private ArrayList<String> setModelList() {
        Elements HtmlElements = doc.getElementsByClass("models");
        Elements modelElements = new Elements();
        for (Element element : HtmlElements) {
            modelElements.addAll(element.getElementsByClass("col-4"));
        }
        ArrayList<String> modelList = new ArrayList<>();
        for (Element element : modelElements) {
            modelList.add(element.getAllElements().get(1).attr("title"));
        }
        return modelList;
    }
    public void writeToDatabase(){
        for (String modelName : modelList) {
            brandModelsDAO.insertModel(brandId,modelName);
        }
    }

    public ArrayList<String> getModels() {
        return modelList;
    }

}
