import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CarBrands {
    Document doc;

    CarBrands(Document doc){
        this.doc = doc;
    }

    public void getAllBrands(){
        Element HtmlElement = doc.getAllElements().first();
        Elements carBrandsElement = HtmlElement.getElementsByClass("col2width fl bcol-white carman");
        for (Element element : carBrandsElement) {
            System.out.println(element.getAllElements().get(1).attr("title"));
        }
    }
    public void groupBrandsByLetter(char x){
        Element HtmlElement = doc.getAllElements().first();
        Elements carBrandsElement = HtmlElement.getElementsByClass("col2width fl bcol-white carman");
        for (Element element : carBrandsElement) {
            String s = element.getAllElements().get(1).attr("title");
            if (s.charAt(0) == x) {
                System.out.println(s);
            }
        }
    }
}
