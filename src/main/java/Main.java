import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.autoevolution.com/cars/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc == null) throw new AssertionError();

        CarBrands brands = new CarBrands(doc);

        brands.getAllBrands();
        System.out.println("######");
        brands.groupBrandsByLetter('T');
    }
}
