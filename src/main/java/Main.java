import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Document doc = null;
        try{
            doc = Jsoup.connect("https://www.autoevolution.com/cars/").get(); //encyklopedia aut
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element element1 = doc.getAllElements().first();
        System.out.println(element1.toString());
    }
}
