import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class BrandModels {
    Document doc;
    String carBrand;
    ArrayList<String> modelList;

    BrandModels(String carBrand) throws IOException {
        this.carBrand = carBrand;
        this.doc = Jsoup.connect("https://www.cars-data.com/en/" + this.carBrand.toLowerCase().replace(' ', '-')).get();
        this.modelList = setModelList();
    }

    private ArrayList<String> setModelList() {
        Element HtmlElements = doc.select("section").get(2);
        Elements modelElements = HtmlElements.getElementsByClass("col-4");
        ArrayList<String> modelList = new ArrayList<>();
        for (Element element : modelElements) {
            modelList.add(element.getAllElements().get(1).attr("title"));
        }
        return modelList;
    }

    public ArrayList<String> getModels() {
        return modelList;
    }

}
