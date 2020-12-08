import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Collections;

public class BrandModels {
    Document doc;
    String carBrand;

    BrandModels(String carBrand) throws IOException {
        this.carBrand = carBrand;
        this.doc = Jsoup.connect("https://www.cars-data.com/en/" + this.carBrand.toLowerCase().replace(' ','-')).get();
    }
    public void getModels(){
        Element HtmlElements = doc.select("section").get(2);
        Elements modelElements = HtmlElements.getElementsByClass("col-4");
        for (Element element : modelElements) {
            System.out.println(element.getAllElements().get(1).attr("title"));
        }
    }

}
